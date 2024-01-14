package org.rent.circle.lease.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaseDto {

    private Long id;
    private Long ownerId;
    private Long propertyId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal monthlyRent;
    private BigDecimal securityDepositHeld;
    private BigDecimal petDepositHeld;
    private List<TenantDto> tenants;
}
