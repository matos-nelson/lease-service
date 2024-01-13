package org.rent.circle.lease.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
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
        Lease lease = new Lease();
        lease.setId(100L);

        CreateLeaseDto createLease = CreateLeaseDto.builder()
            .build();

        when(leaseMapper.toModel(createLease)).thenReturn(lease);

        // Act
        Long result = leaseService.saveLease(createLease);

        // Assert
        assertEquals(lease.getId(), result);
        verify(leaseRepository, times(1)).persist(lease);
    }
}
