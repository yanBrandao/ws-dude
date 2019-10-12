package com.dudes.wsdude.service;

import com.dudes.wsdude.domain.Dude;
import com.dudes.wsdude.service.GenericService;
import org.springframework.stereotype.Service;

public interface DudeService extends GenericService<Dude, Long> {
    Dude findByCPF(String cpf);
}
