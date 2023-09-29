package com.athornatus.api.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private UUID id;
    private String cep;
    private String city;
    private String number;
    private String street;
    private Boolean principal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ClientResponse client;
}
