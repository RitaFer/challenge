package com.athornatus.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequest {
    private String cep;
    private String city;
    private String number;
    private String street;
    private Boolean principal;
}
