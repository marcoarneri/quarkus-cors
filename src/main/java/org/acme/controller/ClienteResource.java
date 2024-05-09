package org.acme.controller;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.acme.repository.entity.ClienteEntity;

@Path("/cliente")
@ApplicationScoped
public class ClienteResource {

    @GET
    @Path("/{id}")
    public Uni<ClienteEntity> getSingle(Long id) {
        return ClienteEntity.findById(id);
    }

}
