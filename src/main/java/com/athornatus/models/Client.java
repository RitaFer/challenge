package com.athornatus.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends BaseEntity {
    private String name;
    private LocalDate birthday;

    @OneToMany (mappedBy = "client")
    private List<Address> addressList;
}
