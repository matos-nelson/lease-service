package org.rent.circle.lease.api.resource;

import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.rent.circle.lease.api.dto.CreateLeaseDto;
import org.rent.circle.lease.api.dto.LeaseDto;
import org.rent.circle.lease.api.service.LeaseService;

@AllArgsConstructor
@Authenticated
@Path("/lease")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class LeaseResource {

    private final LeaseService leaseService;
    private final JsonWebToken jwt;

    @POST
    public Long saveLease(@Valid CreateLeaseDto createLease) {
        return leaseService.saveLease(jwt.getName(), createLease);
    }

    @GET
    @Path("/{id}")
    public LeaseDto getLease(@PathParam("id") Long leaseId) {
        return leaseService.getLease(leaseId, jwt.getName());
    }

    @GET
    public List<LeaseDto> getLeases(
        @QueryParam("page") @NotNull @Min(0) Integer page,
        @QueryParam("pageSize") @NotNull @Min(1) Integer pageSize) {
        return leaseService.getLeases(jwt.getName(), page, pageSize);
    }
}
