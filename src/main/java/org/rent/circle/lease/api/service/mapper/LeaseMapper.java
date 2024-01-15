package org.rent.circle.lease.api.service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
import org.rent.circle.lease.api.dto.LeaseDto;
import org.rent.circle.lease.api.persistence.model.Lease;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface LeaseMapper {

    Lease toModel(CreateLeaseDto createLease);

    LeaseDto toDto(Lease lease);

    List<LeaseDto> toDtoList(List<Lease> leases);
}
