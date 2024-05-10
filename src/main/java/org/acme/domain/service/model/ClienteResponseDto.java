package org.acme.domain.service.model;

import lombok.Data;

@Data
public class ClienteResponseDto {

    private Long id;

    private String nome;

    private String cognome;

    private String username;

    private Long eta;

}
