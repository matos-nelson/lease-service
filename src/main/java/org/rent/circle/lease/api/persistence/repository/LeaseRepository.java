package org.rent.circle.lease.api.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.rent.circle.lease.api.persistence.model.Lease;

@ApplicationScoped
public class LeaseRepository implements PanacheRepository<Lease> {

    public Lease findByIdAndManagerId(Long id, String managerId) {
        Parameters queryParams = Parameters.with("id", id).and("managerId", managerId);
        return find("id = :id and managerId = :managerId", queryParams).firstResult();
    }
}
