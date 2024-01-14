package org.rent.circle.lease.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class TenantDtoTest {

    @Test
    public void TenantDto_SettersAndGetters_ShouldWork() {

        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(TenantDto.class);

        // Assert
    }
}
