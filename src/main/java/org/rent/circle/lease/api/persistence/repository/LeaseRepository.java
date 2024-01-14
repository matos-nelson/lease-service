package org.rent.circle.lease.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.lease.api.persistence.model.Lease;

@ApplicationScoped
public class LeaseRepository implements PanacheRepository<Lease> {

}
