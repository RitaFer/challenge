package com.athornatus.api.requests;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddressRequest {
    private UUID id;
    private String cep;
    private String city;
    private String number;
    private String street;
    private Boolean principal;
}
