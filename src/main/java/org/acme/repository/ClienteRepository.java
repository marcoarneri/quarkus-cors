package org.acme.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.repository.entity.ClienteEntity;

@ApplicationScoped
public class ClienteRepository implements PanacheRepositoryBase<ClienteEntity, Long> {


}
