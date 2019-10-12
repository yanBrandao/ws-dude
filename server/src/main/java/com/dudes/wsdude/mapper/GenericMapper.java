package com.dudes.wsdude.mapper;

import com.dudes.wsdude.domain.BaseEntity;
import org.springframework.data.domain.Page;

public interface GenericMapper<E extends BaseEntity, D> {
    D convertToDTO(E entity);

    E convertToEntity(D dto);

    default Page<D> convertToPageDTO(Page<E> page){
        return page.map(this::convertToDTO);
    }

}
