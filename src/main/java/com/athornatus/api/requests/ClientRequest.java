package com.athornatus.api.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ClientRequest {
    private UUID id;
    @NotNull @NotBlank(message = "Name is mandatory.")
    private String name;
    @NotNull @NotBlank(message = "Birthday is mandatory.")
    private LocalDate birthday;
    private List<AddressRequest> addressList;
}
