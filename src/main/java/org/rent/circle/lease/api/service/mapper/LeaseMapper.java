package org.rent.circle.lease.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
import org.rent.circle.lease.api.persistence.model.Lease;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface LeaseMapper {

    Lease toModel(CreateLeaseDto createLease);
}
