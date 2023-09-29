package com.athornatus.bussiness.services;

import com.athornatus.bussiness.util.ConverterEntities;
import com.athornatus.bussiness.util.exceptions.BadRequestException;
import com.athornatus.bussiness.util.exceptions.NotFoundException;
import com.athornatus.domain.model.Address;
import com.athornatus.domain.model.Client;
import com.athornatus.domain.repositories.AddressRepository;
import com.athornatus.api.requests.AddressRequest;
import com.athornatus.bussiness.util.UpdateDeleteList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final ConverterEntities converter;

    @Transactional
    public Address create(final Client client, final AddressRequest addressForm){
        if(addressForm.getId() != null){
            throw new BadRequestException("You can't create a address with an id configureted.");
        }

        Address address = converter.addressRequestToAddress(addressForm);
        address.setClient(client);
        final Address addressSaved = addressRepository.saveAndFlush(address);

        return findById(addressSaved.getId());
    }

    @Transactional(readOnly = true)
    public Page<Address> findAll(final UUID idClient, final Pageable pageable){
        return addressRepository.findAllByClient_Id(idClient, pageable);
    }

    @Transactional(readOnly = true)
    public Address findById(final UUID id){
        return addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found address with id:"+id));
    }

    @Transactional(readOnly = true)
    public Address findPrincipalAddress(final UUID id){
        return addressRepository.findByClient_IdAndPrincipalTrue(id);
    }

    @Transactional
    public Address update(final AddressRequest addressForm){
        if(addressForm.getId() == null){
            throw new BadRequestException("You must send an id when update an address.");
        }

        Address oldAddress = findById(addressForm.getId());
        Address newAddress = addressRepository.saveAndFlush(converter.addressToAddress(oldAddress, converter.addressRequestToAddress(addressForm)));

        return findById(newAddress.getId());
    }

    @Transactional
    public void delete(final UUID id){
        Address client = findById(id);
        addressRepository.delete(client);
    }

    @Transactional
    public void updateDeleteList(List<Address> actualList, List<Address> toKeepList){
        UpdateDeleteList<Address> updateList = new UpdateDeleteList<>(addressRepository);
        updateList.updateList(actualList, toKeepList);
    }
}
