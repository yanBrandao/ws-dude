package com.dudes.wsdude.service.impl;

import com.dudes.wsdude.domain.Dude;
import com.dudes.wsdude.repository.DudeRepository;
import com.dudes.wsdude.service.DudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DudeServiceImpl extends GenericServiceImpl<Dude, Long> implements DudeService {

    @Autowired
    private DudeRepository repository;

    @Override
    public Dude findByCPF(String cpf){
        return repository.findFirstByCPF(cpf);
    }

    @Override
    public void trim(Dude entity) {
        entity.setName(entity.getName().trim());
        entity.setCPF(entity.getCPF().trim());
        entity.setLastName(entity.getLastName().trim());
    }
}
