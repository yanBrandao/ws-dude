package com.dudes.wsdude.controller;

import com.dudes.wsdude.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericController<T extends BaseEntity<I>, I, D> {

    Page<D> findAllPaginated(Pageable page);

    D getById(I id);

    D create(D dto);

    D update(I id, D dto);

    void remove(I id);
}
