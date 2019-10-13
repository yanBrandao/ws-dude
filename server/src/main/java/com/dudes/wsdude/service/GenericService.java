package com.dudes.wsdude.service;

import com.dudes.wsdude.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<T extends BaseEntity<I>, I> {
    T saveOrUpdate(T entity);

    List<T> getAll();

    Page<T> getAllPaginated(Pageable pageable);

    T get(I id);

    T add(T entity);

    T update(I id, T entity);

    void validateId(T entity, boolean isUpdate);

    boolean exists(I id);

    void trim(T entity);

    void remove(T entity);

    void removeById(I id);

}
