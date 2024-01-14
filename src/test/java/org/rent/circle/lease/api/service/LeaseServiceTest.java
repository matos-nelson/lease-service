package org.rent.circle.lease.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
import org.rent.circle.lease.api.dto.LeaseDto;
import org.rent.circle.lease.api.persistence.model.Lease;
import org.rent.circle.lease.api.persistence.repository.LeaseRepository;
import org.rent.circle.lease.api.service.mapper.LeaseMapper;

@QuarkusTest
public class LeaseServiceTest {

    @InjectMock
    LeaseRepository leaseRepository;

    @InjectMock
    LeaseMapper leaseMapper;

    @Inject
    LeaseService leaseService;

    @Test
    public void saveLease_WhenCalled_ShouldSaveLease() {
        // Arrange
        String managerId = "abc123";
        Lease lease = new Lease();
        lease.setId(100L);

        CreateLeaseDto createLease = CreateLeaseDto.builder()
            .build();

        when(leaseMapper.toModel(createLease)).thenReturn(lease);

        // Act
        Long result = leaseService.saveLease(managerId, createLease);

        // Assert
        assertEquals(lease.getId(), result);
        verify(leaseRepository, times(1)).persist(lease);
    }

    @Test
    public void getLease_WhenLeaseCantBeFound_ShouldReturnNull() {
        // Arrange
        Long leaseId = 100L;
        String managerId = "abc123";

        when(leaseRepository.findByIdAndManagerId(leaseId, managerId)).thenReturn(null);

        // Act
        LeaseDto result = leaseService.getLease(leaseId, managerId);

        // Assert
        assertNull(result);
    }

    @Test
    public void getLease_WhenCalled_ShouldReturnLease() {
        // Arrange
        Long leaseId = 100L;
        String managerId = "abc123";

        Lease lease = new Lease();
        lease.setId(100L);
        lease.setManagerId(managerId);

        LeaseDto leaseDto = LeaseDto.builder()
            .id(lease.getId())
            .build();

        when(leaseRepository.findByIdAndManagerId(leaseId, managerId)).thenReturn(lease);
        when(leaseMapper.toDto(lease)).thenReturn(leaseDto);

        // Act
        LeaseDto result = leaseService.getLease(leaseId, managerId);

        // Assert
        assertEquals(leaseDto, result);
    }
}
