package org.acme.rest.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.mapper.MapperCliente;
import org.acme.repository.ClienteRepository;
import org.acme.repository.entity.ClienteEntity;
import org.acme.rest.service.model.ClienteRequestDto;
import org.acme.rest.service.model.ClienteResponseDto;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private MapperCliente mapperCliente;

    @WithSession
    public Uni<ClienteResponseDto> getCliente(Long id){
        return clienteRepository.findById(id)
                .onItem().transform(clienteEntity -> {
                    if (clienteEntity != null){
                        return mapperCliente.toResponseDto(clienteEntity);
                    } else {
                        //TODO: Modificare con exception custom
                        return null;
                    }
                });
    }

    @WithSession
    public Uni<List<ClienteResponseDto>> getClienti(){
        return clienteRepository.findAll()
                .list().map(mapperCliente::toResponseDtoList);
    }

    @WithSession
    @WithTransaction
    public Uni<Void> save(ClienteRequestDto requestDto){
        ClienteEntity entity = mapperCliente.toEntity(requestDto);
        return clienteRepository.persist(entity).replaceWithVoid();
    }

    @WithSession
    @WithTransaction
    public Uni<Void> delete(Long id){
        return clienteRepository.deleteById(id).replaceWithVoid();
    }

    @WithSession
    @WithTransaction
    public Uni<Void> update(ClienteRequestDto requestDto){
        Parameters parameters = Parameters.with("id", requestDto.getId())
                .and("nome", requestDto.getNome())
                .and("cognome", requestDto.getCognome())
                .and("username", requestDto.getUsername())
                .and("eta", requestDto.getEta());
        return clienteRepository.findById(requestDto.getId())
                .onItem().transformToUni(clienteEntity -> {
                    if (clienteEntity != null){
                        return clienteRepository.update("UPDATE ClienteEntity SET nome = :nome, cognome = :cognome, username = :username, eta = :eta WHERE id = :id",
                                parameters).replaceWithVoid();
                    } else {
                        //TODO: Modificare con exception custom
                        return Uni.createFrom().nullItem();
                    }
                });
    }
}
