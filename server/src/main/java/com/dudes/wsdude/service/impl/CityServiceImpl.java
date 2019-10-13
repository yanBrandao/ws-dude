package com.dudes.wsdude.service.impl;

import com.dudes.wsdude.domain.City;
import com.dudes.wsdude.service.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends GenericServiceImpl<City, Long> implements CityService {

    @Override
    public void trim(City entity) {
        entity.setName(entity.getName().trim());
    }
}