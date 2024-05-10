package org.acme.rest.controller;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.mapper.MapperCliente;
import org.acme.rest.model.ClienteRequest;
import org.acme.rest.model.ClienteResponse;
import org.acme.rest.model.GenericResponse;
import org.acme.rest.service.ClienteService;
import org.acme.rest.service.model.ClienteRequestDto;
import org.acme.rest.service.model.ClienteResponseDto;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.hibernate.engine.spi.Status.DELETED;

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
