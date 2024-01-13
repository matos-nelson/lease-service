package service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
import org.rent.circle.lease.api.dto.CreateTenantDto;
import org.rent.circle.lease.api.persistence.model.Lease;
import org.rent.circle.lease.api.service.mapper.LeaseMapper;

@QuarkusTest
public class LeaseMapperTest {

    @Inject
    LeaseMapper leaseMapper;


    @Test
    public void toModel_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        Lease result = leaseMapper.toModel(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toModel_WhenGivenACreateLeaseDto_ShouldMap() {
        // Arrange
        CreateLeaseDto createLeaseDto = CreateLeaseDto.builder()
            .managerId(1L)
            .ownerId(2L)
            .propertyId(3L)
            .startDate(LocalDate.now())
            .endDate(LocalDate.now())
            .monthlyRent(BigDecimal.ONE)
            .securityDepositHeld(BigDecimal.ZERO)
            .petDeposit(BigDecimal.TEN)
            .build();

        // Act
        Lease result = leaseMapper.toModel(createLeaseDto);

        // Assert
        assertEquals(createLeaseDto.getManagerId(), result.getManagerId());
        assertEquals(createLeaseDto.getOwnerId(), result.getOwnerId());
        assertEquals(createLeaseDto.getPropertyId(), result.getPropertyId());
        assertEquals(createLeaseDto.getStartDate(), result.getStartDate());
        assertEquals(createLeaseDto.getEndDate(), result.getEndDate());
        assertEquals(createLeaseDto.getMonthlyRent(), result.getMonthlyRent());
        assertEquals(createLeaseDto.getSecurityDepositHeld(), result.getSecurityDepositHeld());
        assertEquals(createLeaseDto.getPetDeposit(), result.getPetDeposit());
    }

    @Test
    public void toModel_WhenGivenACreateLeaseDtoWithTenants_ShouldMap() {
        // Arrange
        CreateTenantDto createTenantDto = CreateTenantDto.builder()
            .residentId(1L)
            .primary(true)
            .build();

        CreateLeaseDto createLeaseDto = CreateLeaseDto.builder()
            .managerId(1L)
            .ownerId(2L)
            .propertyId(3L)
            .startDate(LocalDate.now())
            .endDate(LocalDate.now())
            .monthlyRent(BigDecimal.ONE)
            .securityDepositHeld(BigDecimal.ZERO)
            .petDeposit(BigDecimal.TEN)
            .tenants(Collections.singletonList(createTenantDto))
            .build();

        // Act
        Lease result = leaseMapper.toModel(createLeaseDto);

        // Assert
        assertEquals(createLeaseDto.getManagerId(), result.getManagerId());
        assertEquals(createLeaseDto.getOwnerId(), result.getOwnerId());
        assertEquals(createLeaseDto.getPropertyId(), result.getPropertyId());
        assertEquals(createLeaseDto.getStartDate(), result.getStartDate());
        assertEquals(createLeaseDto.getEndDate(), result.getEndDate());
        assertEquals(createLeaseDto.getMonthlyRent(), result.getMonthlyRent());
        assertEquals(createLeaseDto.getSecurityDepositHeld(), result.getSecurityDepositHeld());
        assertEquals(createLeaseDto.getPetDeposit(), result.getPetDeposit());
        assertEquals(createTenantDto.getResidentId(), result.getTenants().get(0).getResidentId());
        assertEquals(createTenantDto.isPrimary(), result.getTenants().get(0).isPrimary());
    }
}
