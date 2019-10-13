package com.dudes.wsdude.service.impl;

import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.service.GenderService;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl extends GenericServiceImpl<Gender, Long> implements GenderService {
    @Override
    public void trim(Gender entity) {
        entity.setName(entity.getName().trim());
    }
}
