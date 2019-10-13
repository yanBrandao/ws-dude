package com.dudes.wsdude.service.impl;

import com.dudes.wsdude.domain.Address;
import com.dudes.wsdude.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends GenericServiceImpl<Address, Long> implements AddressService {

    @Override
    public void trim(Address entity) {
        entity.setStreet(entity.getStreet().trim());
        entity.setNeighborhood(entity.getNeighborhood().trim());
        entity.setNumber(entity.getNumber().trim());
    }
}
