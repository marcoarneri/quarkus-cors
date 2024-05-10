package org.acme.adapter.rest.model;

import lombok.Data;

@Data
public class ClienteResponse {

    private Long id;

    private String nome;

    private String cognome;

    private String username;

    private Long eta;

}
