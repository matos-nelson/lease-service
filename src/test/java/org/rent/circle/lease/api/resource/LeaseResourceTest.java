package org.rent.circle.lease.api.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.rent.circle.lease.api.annotation.AuthUser;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
import org.rent.circle.lease.api.dto.CreateTenantDto;

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
    @NoLeaseUser
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

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @TestSecurity(user = "new_user")
    @JwtSecurity(claims = {
        @Claim(key = "user_id", value = "def456")
    })
    public @interface NoLeaseUser {

    }
}
