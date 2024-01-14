package org.rent.circle.lease.api.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rent.circle.lease.api.persistence.BaseModel;

@Entity
@Table(name = "tenant")
@Setter
@Getter
@ToString
public class Tenant extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lease_id", insertable = false, updatable = false, nullable = false)
    private Long leaseId;

    @Column(name = "resident_id")
    private Long residentId;

    @Column(name = "is_primary")
    private boolean primary;
}
