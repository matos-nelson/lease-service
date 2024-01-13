package org.rent.circle.lease.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
import org.rent.circle.lease.api.persistence.model.Lease;
import org.rent.circle.lease.api.persistence.repository.LeaseRepository;
import org.rent.circle.lease.api.service.mapper.LeaseMapper;

@AllArgsConstructor
@ApplicationScoped
@Slf4j
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final LeaseMapper leaseMapper;

    @Transactional
    public Long saveLease(String managerId, CreateLeaseDto createLeaseInfo) {
        Lease lease = leaseMapper.toModel(createLeaseInfo);
        lease.setManagerId(managerId);

        leaseRepository.persist(lease);
        return lease.getId();
    }
}
