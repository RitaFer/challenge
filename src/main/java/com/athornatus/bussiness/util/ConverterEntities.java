package com.athornatus.bussiness.util;

import com.athornatus.domain.model.Address;
import com.athornatus.domain.model.Client;
import com.athornatus.api.requests.AddressRequest;
import com.athornatus.api.requests.ClientRequest;
import com.athornatus.api.responses.AddressResponse;
import com.athornatus.api.responses.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterEntities {
    private final ModelMapper modelMapper;

    public ClientResponse clientToClientResponse(final Client client){
        return modelMapper.map(client, ClientResponse.class);
    }

    public Client clientRequestToClient(final ClientRequest clientRequest){
        return modelMapper.map(clientRequest, Client.class);
    }

    public Client clientToClient(final Client oldClient, final Client newClient){
        return Client.builder().id(oldClient.getId()).name(newClient.getName()).birthday(newClient.getBirthday()).build();
    }

    public AddressResponse addressToAddressResponse(final Address address){
        return modelMapper.map(address, AddressResponse.class);
    }

    public Address addressRequestToAddress(final AddressRequest addressRequest){
        return modelMapper.map(addressRequest, Address.class);
    }

    public Address addressToAddress(final Address oldAddress, final Address newAddress){
        return Address.builder().id(oldAddress.getId()).cep(newAddress.getCep()).city(newAddress.getCity()).number(newAddress.getNumber()).street(newAddress.getStreet()).principal(newAddress.getPrincipal()).build();
    }
}
