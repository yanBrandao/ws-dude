package com.dudes.wsdude.service.impl;

import com.dudes.wsdude.domain.Gender;
import com.dudes.wsdude.repository.DudeRepository;
import com.dudes.wsdude.repository.GenderRepository;
import com.dudes.wsdude.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServiceImpl extends GenericServiceImpl<Gender, Long> implements GenderService {
    @Autowired
    private GenderRepository repository;

    @Override
    public void trim(Gender entity) {
        entity.setName(entity.getName().trim());
    }
}
