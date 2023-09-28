package com.athornatus.api.controllers;

import com.athornatus.domain.model.Client;
import com.athornatus.api.requests.ClientRequest;
import com.athornatus.api.responses.ClientResponse;
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
@RequestMapping(path = ApiMapping.clients.MAPPING)
public class ClientRestController {
    private final ClientService clientService;
    private final ConverterEntities converter;

    @PostMapping()
    public ResponseEntity<ClientResponse> createClient(@RequestBody final ClientRequest clientForm){
        Client client = clientService.create(clientForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.clientToClientResponse(client));
    }

    @GetMapping()
    public ResponseEntity<Page<ClientResponse>> findAllClients(@PageableDefault Pageable pageable){
        Page<Client> response = clientService.findAll(pageable);

        if(response.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response.map(converter::clientToClientResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findClientById(@PathVariable final UUID id){
        Client client = clientService.findById(id);
        return ResponseEntity.ok(converter.clientToClientResponse(client));
    }

    @PutMapping()
    public ResponseEntity<ClientResponse> updateClient(@RequestBody final ClientRequest clientForm){
        Client client = clientService.update(clientForm);
        return ResponseEntity.ok(converter.clientToClientResponse(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable final UUID id){
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
