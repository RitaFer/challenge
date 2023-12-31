package com.athornatus.domain.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now().atZone(ZoneId.of("UTC")).toLocalDateTime();
    }

    public boolean isEqualsId(UUID id) {
        return id.equals(this.id);
    }
}
