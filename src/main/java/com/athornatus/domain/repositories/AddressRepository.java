package com.athornatus.domain.repositories;

import com.athornatus.domain.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Page<Address> findAllByClient_Id(final UUID id, Pageable pageable);
    Address findByClient_IdAndPrincipalTrue(final UUID id);
}
