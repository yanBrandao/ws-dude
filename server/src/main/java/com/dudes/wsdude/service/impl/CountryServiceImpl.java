package com.dudes.wsdude.service.impl;

import com.dudes.wsdude.domain.Country;
import com.dudes.wsdude.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl extends GenericServiceImpl<Country, Long> implements CountryService {

    @Override
    public void trim(Country entity) {
        entity.setName(entity.getName().trim());
        entity.setInitials(entity.getInitials().trim());
    }
}