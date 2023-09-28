package com.athornatus.api.requests;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ClientRequest {
    private UUID id;
    private String name;
    private LocalDate birthday;
    private List<AddressRequest> addressList;
}
