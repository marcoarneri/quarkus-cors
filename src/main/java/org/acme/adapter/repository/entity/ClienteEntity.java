package org.acme.adapter.repository.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CLIENT_ENTITY")
public class ClienteEntity extends PanacheEntity {

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "COGNOME", nullable = false)
    private String cognome;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "ETA", nullable = false)
    private Long eta;

}
