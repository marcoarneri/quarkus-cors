package org.acme.domain.ports;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.service.model.ClienteRequestDto;
import org.acme.domain.service.model.ClienteResponseDto;

import java.util.List;

@ApplicationScoped
public interface ClienteService {

    Uni<ClienteResponseDto> getCliente(Long id);

    Uni<List<ClienteResponseDto>> getClienti();

    Uni<Void> save(ClienteRequestDto requestDto);

    Uni<Void> delete(Long id);

    Uni<Void> update(ClienteRequestDto requestDto);
}
