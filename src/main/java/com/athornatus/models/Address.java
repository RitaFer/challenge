package com.athornatus.models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {
    private String cep;
    private String city;
    private String number;
    private String street;
    private Boolean principal;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    public boolean isPrincipal() {
        return this.principal;
    }
}
