package com.dudes.wsdude.service.impl;

import com.dudes.wsdude.domain.State;
import com.dudes.wsdude.service.StateService;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl extends GenericServiceImpl<State, Long> implements StateService {

    @Override
    public void trim(State entity) {
        entity.setName(entity.getName().trim());
        entity.setInitials(entity.getInitials().trim());
    }
}
