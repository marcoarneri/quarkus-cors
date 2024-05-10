package org.acme.domain.ports;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.adapter.repository.entity.ClienteEntity;

@ApplicationScoped
public interface ClienteRepository extends PanacheRepositoryBase<ClienteEntity, Long> {
}
