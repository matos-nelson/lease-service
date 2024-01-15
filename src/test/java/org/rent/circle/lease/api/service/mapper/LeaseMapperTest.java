package org.rent.circle.lease.api.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
import org.rent.circle.lease.api.dto.CreateTenantDto;
import org.rent.circle.lease.api.dto.LeaseDto;
import org.rent.circle.lease.api.persistence.model.Lease;
import org.rent.circle.lease.api.persistence.model.Tenant;

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
            .ownerId(2L)
            .propertyId(3L)
            .startDate(LocalDate.now())
            .endDate(LocalDate.now())
            .monthlyRent(BigDecimal.ONE)
            .securityDepositHeld(BigDecimal.ZERO)
            .petDepositHeld(BigDecimal.TEN)
            .build();

        // Act
        Lease result = leaseMapper.toModel(createLeaseDto);

        // Assert
        assertEquals(createLeaseDto.getOwnerId(), result.getOwnerId());
        assertEquals(createLeaseDto.getPropertyId(), result.getPropertyId());
        assertEquals(createLeaseDto.getStartDate(), result.getStartDate());
        assertEquals(createLeaseDto.getEndDate(), result.getEndDate());
        assertEquals(createLeaseDto.getMonthlyRent(), result.getMonthlyRent());
        assertEquals(createLeaseDto.getSecurityDepositHeld(), result.getSecurityDepositHeld());
        assertEquals(createLeaseDto.getPetDepositHeld(), result.getPetDepositHeld());
    }

    @Test
    public void toModel_WhenGivenACreateLeaseDtoWithTenants_ShouldMap() {
        // Arrange
        CreateTenantDto createTenantDto = CreateTenantDto.builder()
            .residentId(1L)
            .primary(true)
            .build();

        CreateLeaseDto createLeaseDto = CreateLeaseDto.builder()
            .ownerId(2L)
            .propertyId(3L)
            .startDate(LocalDate.now())
            .endDate(LocalDate.now())
            .monthlyRent(BigDecimal.ONE)
            .securityDepositHeld(BigDecimal.ZERO)
            .petDepositHeld(BigDecimal.TEN)
            .tenants(Collections.singletonList(createTenantDto))
            .build();

        // Act
        Lease result = leaseMapper.toModel(createLeaseDto);

        // Assert
        assertEquals(createLeaseDto.getOwnerId(), result.getOwnerId());
        assertEquals(createLeaseDto.getPropertyId(), result.getPropertyId());
        assertEquals(createLeaseDto.getStartDate(), result.getStartDate());
        assertEquals(createLeaseDto.getEndDate(), result.getEndDate());
        assertEquals(createLeaseDto.getMonthlyRent(), result.getMonthlyRent());
        assertEquals(createLeaseDto.getSecurityDepositHeld(), result.getSecurityDepositHeld());
        assertEquals(createLeaseDto.getPetDepositHeld(), result.getPetDepositHeld());
        assertEquals(createTenantDto.getResidentId(), result.getTenants().get(0).getResidentId());
        assertEquals(createTenantDto.isPrimary(), result.getTenants().get(0).isPrimary());
    }

    @Test
    public void toDto_WhenGivenNull_ShouldReturnNull() {
        // Arrange

        // Act
        LeaseDto result = leaseMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    public void toDto_WhenGivenAValidLease_ShouldMap() {
        // Arrange
        Lease lease = new Lease();
        lease.setId(1L);
        lease.setManagerId("abc123");
        lease.setOwnerId(2L);
        lease.setPropertyId(3L);
        lease.setStartDate(LocalDate.now());
        lease.setEndDate(LocalDate.now());
        lease.setMonthlyRent(BigDecimal.ONE);
        lease.setSecurityDepositHeld(BigDecimal.ZERO);
        lease.setPetDepositHeld(BigDecimal.TEN);

        // Act
        LeaseDto result = leaseMapper.toDto(lease);

        // Assert
        assertEquals(lease.getId(), result.getId());
        assertEquals(lease.getOwnerId(), result.getOwnerId());
        assertEquals(lease.getPropertyId(), result.getPropertyId());
        assertEquals(lease.getStartDate(), result.getStartDate());
        assertEquals(lease.getEndDate(), result.getEndDate());
        assertEquals(lease.getMonthlyRent(), result.getMonthlyRent());
        assertEquals(lease.getSecurityDepositHeld(), result.getSecurityDepositHeld());
        assertEquals(lease.getPetDepositHeld(), result.getPetDepositHeld());
        assertNull(result.getTenants());
    }

    @Test
    public void toDto_WhenGivenAValidLeaseWithTenants_ShouldMap() {
        // Arrange
        Lease lease = new Lease();
        lease.setId(1L);

        Tenant tenant = new Tenant();
        tenant.setId(5L);
        tenant.setLeaseId(lease.getId());
        tenant.setPrimary(true);

        lease.setTenants(Collections.singletonList(tenant));

        // Act
        LeaseDto result = leaseMapper.toDto(lease);

        // Assert
        assertEquals(tenant.getResidentId(), result.getTenants().get(0).getResidentId());
        assertEquals(tenant.isPrimary(), result.getTenants().get(0).isPrimary());
    }

    @Test
    public void toDtoList_WhenGivenValidLeases_ShouldMap() {
        // Arrange
        Lease lease = new Lease();
        lease.setId(1L);
        lease.setManagerId("abc123");
        lease.setOwnerId(2L);
        lease.setPropertyId(3L);
        lease.setStartDate(LocalDate.now());
        lease.setEndDate(LocalDate.now());
        lease.setMonthlyRent(BigDecimal.ONE);
        lease.setSecurityDepositHeld(BigDecimal.ZERO);
        lease.setPetDepositHeld(BigDecimal.TEN);

        // Act
        List<LeaseDto> result = leaseMapper.toDtoList(Collections.singletonList(lease));

        // Assert
        assertEquals(lease.getId(), result.get(0).getId());
        assertEquals(lease.getOwnerId(), result.get(0).getOwnerId());
        assertEquals(lease.getPropertyId(), result.get(0).getPropertyId());
        assertEquals(lease.getStartDate(), result.get(0).getStartDate());
        assertEquals(lease.getEndDate(), result.get(0).getEndDate());
        assertEquals(lease.getMonthlyRent(), result.get(0).getMonthlyRent());
        assertEquals(lease.getSecurityDepositHeld(), result.get(0).getSecurityDepositHeld());
        assertEquals(lease.getPetDepositHeld(), result.get(0).getPetDepositHeld());
        assertNull(result.get(0).getTenants());
    }

    @Test
    public void toDtoList_WhenGivenValidLeasesWithTenants_ShouldMap() {
        // Arrange
        Lease lease = new Lease();
        lease.setId(1L);

        Tenant tenant = new Tenant();
        tenant.setId(5L);
        tenant.setLeaseId(lease.getId());
        tenant.setPrimary(true);

        lease.setTenants(Collections.singletonList(tenant));

        // Act
        List<LeaseDto> result = leaseMapper.toDtoList(Collections.singletonList(lease));

        // Assert
        assertEquals(tenant.getResidentId(), result.get(0).getTenants().get(0).getResidentId());
        assertEquals(tenant.isPrimary(), result.get(0).getTenants().get(0).isPrimary());
    }
}