package org.rent.circle.lease.api.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateLeaseDtoTest {

    @Test
    public void CreateLeaseDto_SettersAndGetters_ShouldWork() {
        // Arrange
        BeanTester beanTester = new BeanTester();
        beanTester.addExcludedField("startDate");
        beanTester.addExcludedField("endDate");
        beanTester.addExcludedField("monthlyRent");
        beanTester.addExcludedField("securityDepositHeld");
        beanTester.addExcludedField("petDeposit");

        // Act
        beanTester.testBean(CreateLeaseDto.class);

        // Assert

        // Test Excluded fields

        // Arrange
        CreateLeaseDto lease = new CreateLeaseDto();

        // Act
        lease.setStartDate(LocalDate.now().minusDays(1L));
        lease.setEndDate(LocalDate.now());
        lease.setMonthlyRent(BigDecimal.ONE);
        lease.setSecurityDepositHeld(BigDecimal.TEN);
        lease.setPetDeposit(BigDecimal.ZERO);

        // Assert
        assertNotNull(lease.getStartDate());
        assertNotNull(lease.getEndDate());
        assertEquals(BigDecimal.ONE, lease.getMonthlyRent());
        assertEquals(BigDecimal.TEN, lease.getSecurityDepositHeld());
        assertEquals(BigDecimal.ZERO, lease.getPetDeposit());
    }
}
