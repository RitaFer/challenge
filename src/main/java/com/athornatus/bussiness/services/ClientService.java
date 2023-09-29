package com.athornatus.bussiness.services;

import com.athornatus.bussiness.util.ConverterEntities;
import com.athornatus.bussiness.util.exceptions.BadRequestException;
import com.athornatus.bussiness.util.exceptions.NotFoundException;
import com.athornatus.domain.model.Address;
import com.athornatus.domain.model.Client;
import com.athornatus.domain.repositories.ClientRepository;
import com.athornatus.api.requests.AddressRequest;
import com.athornatus.api.requests.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ConverterEntities converter;
    private final AddressService addressService;

    @Transactional
    public Client create(final ClientRequest clientForm){
        if(clientForm.getId() != null){
            throw new BadRequestException("You can't create a client with an id configureted.");
        }

        if(clientForm.getName() == null || clientForm.getBirthday() == null){
            throw new BadRequestException("You can't send null attributes.");
        }

        Client client = converter.clientRequestToClient(clientForm);
        client.setAddressList(null);
        final Client clientSaved = clientRepository.saveAndFlush(client);

        if(clientForm.getAddressList() != null && !clientForm.getAddressList().isEmpty()){
            clientForm.getAddressList().forEach(addressRequest -> addressService.create(clientSaved, addressRequest));
        }

        return findById(clientSaved.getId());
    }

    @Transactional(readOnly = true)
    public Page<Client> findAll(final Pageable pageable){
        return clientRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Client findById(final UUID id){
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found client with id:"+id));
    }

    @Transactional
    public Client update(final ClientRequest clientForm){
        if(clientForm.getId() == null){
            throw new BadRequestException("You must send an id when update a client.");
        }

        if(clientForm.getName() == null || clientForm.getBirthday() == null){
            throw new BadRequestException("You can't send null attributes.");
        }

        Client oldClient = findById(clientForm.getId());
        Client newClient = clientRepository.saveAndFlush(converter.clientToClient(oldClient, converter.clientRequestToClient(clientForm)));

        List<Address> actualAddress = oldClient.getAddressList();
        List<Address> newAddress = new ArrayList<>();

        if(clientForm.getAddressList() != null && !clientForm.getAddressList().isEmpty()) {
            for (AddressRequest addressRequest : clientForm.getAddressList()) {
                if (addressRequest.getId() == null) {
                    newAddress.add(addressService.create(newClient, addressRequest));
                } else {
                    newAddress.add(addressService.update(addressRequest));
                }
            }
        }

        if (actualAddress != null && !actualAddress.isEmpty()) {
            addressService.updateDeleteList(actualAddress, newAddress);
        }

        return findById(newClient.getId());
    }

    @Transactional
    public void delete(final UUID id){
        Client client = findById(id);
        clientRepository.delete(client);
    }
}
