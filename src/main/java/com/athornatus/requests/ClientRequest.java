package com.athornatus.requests;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ClientRequest {
    private String name;
    private LocalDate birthday;
    private List<AddressRequest> addressList;
}
