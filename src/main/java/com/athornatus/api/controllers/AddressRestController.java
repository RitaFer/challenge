package com.athornatus.api.controllers;

import com.athornatus.domain.model.Address;
import com.athornatus.domain.model.Client;
import com.athornatus.api.requests.AddressRequest;
import com.athornatus.api.responses.AddressResponse;
import com.athornatus.bussiness.services.AddressService;
import com.athornatus.bussiness.services.ClientService;
import com.athornatus.bussiness.util.ApiMapping;
import com.athornatus.bussiness.util.ConverterEntities;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiMapping.address.MAPPING)
public class AddressRestController {
    private final AddressService addressService;
    private final ClientService clientService;
    private final ConverterEntities converter;

    @PostMapping()
    public ResponseEntity<AddressResponse> createAddress(@PathVariable UUID idClient, @RequestBody final AddressRequest addressForm){
        Client client = clientService.findById(idClient);
        Address address = addressService.create(client, addressForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.addressToAddressResponse(address));
    }

    @GetMapping()
    public ResponseEntity<Page<AddressResponse>> findAllAddressByClient(@PathVariable UUID idClient, @PageableDefault Pageable pageable){
        Page<Address> response = addressService.findAll(idClient, pageable);

        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response.map(converter::addressToAddressResponse));
    }

    @GetMapping("/principal")
    public ResponseEntity<AddressResponse> findPrincipalAddressByClient(@PathVariable UUID idClient){
        Address address = addressService.findPrincipalAddress(idClient);
        return ResponseEntity.ok(converter.addressToAddressResponse(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> findAddressById(@PathVariable UUID idClient, @PathVariable final UUID id){
        Address address = addressService.findById(id);
        return ResponseEntity.ok(converter.addressToAddressResponse(address));
    }

    @PutMapping()
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable UUID idClient, @RequestBody final AddressRequest addressForm){
        Address address = addressService.update(addressForm);
        return ResponseEntity.ok(converter.addressToAddressResponse(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable UUID idClient, @PathVariable final UUID id){
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }
}
