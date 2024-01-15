package org.rent.circle.lease.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import io.restassured.common.mapper.TypeRef;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.lease.api.annotation.AuthUser;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
import org.rent.circle.lease.api.dto.CreateTenantDto;
import org.rent.circle.lease.api.dto.LeaseDto;

@QuarkusTest
@TestHTTPEndpoint(LeaseResource.class)
@QuarkusTestResource(H2DatabaseTestResource.class)
@AuthUser
public class LeaseResourceTest {

    @Test
    public void Post_WhenGivenAnInValidRequestToSave_ShouldReturnBadRequest() {
        // Arrange
        CreateLeaseDto createLeaseDto = CreateLeaseDto.builder()
            .propertyId(3L)
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(createLeaseDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void Post_WhenGivenAValidRequestToSave_ShouldReturnSavedLeaseId() {
        // Arrange
        CreateTenantDto createTenantDto = CreateTenantDto.builder()
            .residentId(5L)
            .primary(true)
            .build();
        CreateLeaseDto createLeaseDto = CreateLeaseDto.builder()
            .ownerId(1L)
            .propertyId(2L)
            .startDate(LocalDate.now())
            .endDate(LocalDate.now())
            .monthlyRent(BigDecimal.ONE)
            .securityDepositHeld(BigDecimal.ZERO)
            .petDepositHeld(BigDecimal.TEN)
            .tenants(Collections.singletonList(createTenantDto))
            .build();

        // Act
        // Assert
        given()
            .contentType("application/json")
            .body(createLeaseDto)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("1"));
    }

    @Test
    public void GET_WhenLeaseCantBeFound_ShouldReturnNoContent() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/123")
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void GET_WhenLeaseIsFound_ShouldReturnLease() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("/100")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("id", is(100),
                "ownerId", is(200),
                "propertyId", is(300),
                "startDate", is("2020-10-01"),
                "endDate", is("2021-10-01"),
                "monthlyRent", is(1000.0F),
                "securityDepositHeld", is(1500.0F),
                "petDepositHeld", is(250.0F),
                "tenants[0].residentId", is(500),
                "tenants[0].primary", is(true));
    }

    @Test
    @NoLeaseUser
    public void GET_getMaintenanceRequests_WhenRequestsCantBeFound_ShouldReturnNoRequests() {
        // Arrange

        // Act
        // Assert
        given()
            .when()
            .get("?page=0&pageSize=10")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body(is("[]"));
    }

    @Test
    @SingleLeaseUser
    public void GET_getMaintenanceRequests_WhenRequestsAreFound_ShouldReturnRequests() {
        // Arrange

        // Act
        List<LeaseDto> result = given()
            .when()
            .get("?page=0&pageSize=10")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(new TypeRef<>() {
            });

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(200L, result.get(0).getId());
        assertEquals(600L, result.get(0).getOwnerId());
        assertEquals(700L, result.get(0).getPropertyId());
        assertEquals(LocalDate.of(2020, 10, 1), result.get(0).getStartDate());
        assertEquals(LocalDate.of(2021, 10, 1), result.get(0).getEndDate());
        assertEquals(BigDecimal.valueOf(1000).doubleValue(), result.get(0).getMonthlyRent().doubleValue());
        assertEquals(BigDecimal.valueOf(1500).doubleValue(), result.get(0).getSecurityDepositHeld().doubleValue());
        assertEquals(BigDecimal.valueOf(250).doubleValue(), result.get(0).getPetDepositHeld().doubleValue());
        assertEquals(1, result.get(0).getTenants().size());
        assertEquals(900L, result.get(0).getTenants().get(0).getResidentId());
        assertTrue(result.get(0).getTenants().get(0).isPrimary());
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @TestSecurity(user = "new_user")
    @JwtSecurity(claims = {
        @Claim(key = "user_id", value = "def456")
    })
    public @interface NoLeaseUser {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @TestSecurity(user = "test_user")
    @JwtSecurity(claims = {
        @Claim(key = "user_id", value = "123456")
    })
    public @interface SingleLeaseUser {

    }
}
