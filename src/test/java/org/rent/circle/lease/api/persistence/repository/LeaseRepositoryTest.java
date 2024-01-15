package org.rent.circle.lease.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.rent.circle.lease.api.persistence.model.Lease;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class LeaseRepositoryTest {

    @Inject
    LeaseRepository leaseRepository;

    @Test
    @TestTransaction
    public void findByIdAndManagerId_WhenLeaseDoesNotExist_ShouldReturnNull() {
        // Arrange

        // Act
        Lease result = leaseRepository.findByIdAndManagerId(123L, "456");

        // Assert
        assertNull(result);
    }

    @Test
    @TestTransaction
    public void findByIdAndManagerId_WhenCalled_ShouldReturnLease() {
        // Arrange

        // Act
        Lease result = leaseRepository.findByIdAndManagerId(200L, "test_user");

        // Assert
        assertNotNull(result);
    }

    @Test
    @TestTransaction
    public void findLeases_WhenLeasesDoNotExist_ShouldReturnEmptyList() {
        // Arrange

        // Act
        List<Lease> results = leaseRepository.findLeases("456", 0, 10);

        // Assert
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    @TestTransaction
    public void findLeases_WhenLeasesExist_ShouldReturnLeases() {
        // Arrange

        // Act
        List<Lease> results = leaseRepository.findLeases("test_user", 0, 10);

        // Assert
        assertNotNull(results);
        assertFalse(results.isEmpty());
    }
}
