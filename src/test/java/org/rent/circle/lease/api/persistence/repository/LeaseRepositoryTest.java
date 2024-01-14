package org.rent.circle.lease.api.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
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
}
