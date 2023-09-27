package com.athornatus.responses;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AddressResponse {
    private UUID id;
    private String cep;
    private String city;
    private String number;
    private String street;
    private Boolean principal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
