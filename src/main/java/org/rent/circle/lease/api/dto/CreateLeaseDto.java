package org.rent.circle.lease.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CreateLeaseDto {

    @NotNull
    private Long ownerId;

    @NotNull
    private Long propertyId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private BigDecimal monthlyRent;

    @NotNull
    private BigDecimal securityDepositHeld;

    @NotNull
    private BigDecimal petDepositHeld;

    @NotEmpty
    private List<CreateTenantDto> tenants;
}
