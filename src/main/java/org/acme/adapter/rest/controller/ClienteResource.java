package org.acme.adapter.rest.controller;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.adapter.rest.mapper.MapperCliente;
import org.acme.adapter.rest.model.ClienteRequest;
import org.acme.adapter.rest.model.ClienteResponse;
import org.acme.adapter.rest.model.GenericResponse;
import org.acme.domain.ports.ClienteService;
import org.acme.domain.service.ClienteServiceImpl;
import org.acme.domain.service.model.ClienteRequestDto;
import org.acme.domain.service.model.ClienteResponseDto;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.OK;

@Path("/clients")
@ApplicationScoped
public class ClienteResource {

    @Inject
    private ClienteService clienteService;

    @Inject
    private MapperCliente mapperCliente;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ClienteResponse> getSingleCliente(@PathParam("id") Long id) {
        Uni<ClienteResponseDto> responseDto = clienteService.getCliente(id);
        return responseDto.onItem().transform(dto -> mapperCliente.toResponse(dto));
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<ClienteResponse>> getClienti() {
        Uni<List<ClienteResponseDto>> responseDto = clienteService.getClienti();
        return responseDto.map(mapperCliente::toResponseList);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<RestResponse<GenericResponse>> addCliente(ClienteRequest request){
        ClienteRequestDto requestDto = mapperCliente.toRequestDto(request);
        return clienteService.save(requestDto)
                .onItem().transform(ignore -> RestResponse.status(
                        CREATED,
                        GenericResponse.builder().message("Cliente salvato con successo").build()
                ));
    }

    @DELETE
    @Path("/delete/{id}")
    public Uni<RestResponse<GenericResponse>> deleteCliente(@PathParam("id") Long id) {
        return clienteService.delete(id)
                .onItem().transform(ignore -> RestResponse.status(
                        OK,
                        GenericResponse.builder().message("Cliente eliminato con successo").build()
                ));
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<RestResponse<GenericResponse>> updateCliente(ClienteRequest request){
        ClienteRequestDto requestDto = mapperCliente.toRequestDto(request);
        return clienteService.update(requestDto)
                .onItem().transform(ignore -> RestResponse.status(
                        OK,
                        GenericResponse.builder().message("Cliente aggiornato con successo").build()
                ));
    }

}
