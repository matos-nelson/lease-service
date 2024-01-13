package org.rent.circle.lease.api.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateTenantDtoTest {

    @Test
    public void CreateTenantDto_SettersAndGetters_ShouldWork() {

        // Arrange
        BeanTester beanTester = new BeanTester();

        // Act
        beanTester.testBean(CreateTenantDto.class);

        // Assert
    }
}
