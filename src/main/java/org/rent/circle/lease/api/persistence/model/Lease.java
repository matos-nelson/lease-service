package org.rent.circle.lease.api.persistence.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.lease.api.persistence.BaseModel;

@Entity
@Table(name = "lease")
@Setter
@Getter
@ToString
public class Lease extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manager_id")
    private String managerId;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "monthly_rent")
    private BigDecimal monthlyRent;

    @Column(name = "security_deposit_held")
    private BigDecimal securityDepositHeld;

    @Column(name = "pet_deposit")
    private BigDecimal petDeposit;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lease_id", referencedColumnName = "id", nullable = false)
    private List<Tenant> tenants;
}