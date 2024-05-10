package org.acme.adapter.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.adapter.repository.entity.ClienteEntity;
import org.acme.domain.ports.ClienteRepository;

@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteRepository {


}
