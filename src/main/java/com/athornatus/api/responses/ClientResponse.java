package com.athornatus.api.responses;

import com.athornatus.domain.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private UUID id;
    private String name;
    private LocalDate birthday;
    private List<Address> addressList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
