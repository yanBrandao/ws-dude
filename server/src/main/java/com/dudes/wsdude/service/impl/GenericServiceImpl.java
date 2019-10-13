package com.dudes.wsdude.service.impl;

import com.dudes.wsdude.domain.BaseEntity;
import com.dudes.wsdude.exception.InvalidEntityException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dudes.wsdude.service.GenericService;
import com.dudes.wsdude.utils.ValidateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.List;

@Service
public abstract class GenericServiceImpl<T extends BaseEntity<I>, I extends Serializable> implements GenericService<T, I> {
    private static final String APP_NOT_FOUND = "dude.notFound";
    private static final String ID_MUST_BE_FILLED_MESSAGE = "dude.id.mustBeFilled";
    private static final String ID_CANNOT_BE_FILLED_MESSAGE = "dude.id.cannotBeFilled";
    private static final String ID_CONSTRAINT_VIOLATION_ON_DELETE_MESSAGE = "dude.constraintViolationOnDelete";

    protected final Log logger = LogFactory.getLog(getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    protected JpaRepository<T, I> repository;

    @Transactional
    @Override
    public T saveOrUpdate(T entity) {
        try {
            return repository.save(entity);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityException(e.getMessage());
        }
    }

    @Override
    public List<T> getAll() {
        List<T> entities = repository.findAll();
        ValidateUtils.checkFound(entities, getNotFoundMessage());
        return entities;
    }

    @Override
    public Page<T> getAllPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T get(I id) {
        T entity = repository.findById(id).orElse(null);
        ValidateUtils.checkFound(entity, getNotFoundMessage());
        return entity;
    }

    @Transactional
    @Override
    public T add(T entity) {
        trim(entity);
        validateId(entity, false);
        entity.setId(null);
        return this.saveOrUpdate(entity);
    }

    @Transactional
    @Override
    public T update(I id, T entity) {
        trim(entity);
        validateId(id, entity);
        validateId(entity, true);
        return this.saveOrUpdate(entity);
    }

    @Override
    public boolean exists(I id) {
        return repository.existsById(id);
    }

    @Override
    public void validateId(T entity, boolean isUpdate) {
        if (isUpdate) {
            ValidateUtils.checkBiggerThanZero((Long) entity.getId(), getIdMustBeFilledMessage());
        } else {
            ValidateUtils.checkMustBeNullOrZero((Long) entity.getId(), getIdCannotBeFilledMessage());
        }
    }

    @Override
    public void remove(T entity) {
        try {
            repository.delete(entity);
        } catch (ConstraintViolationException | DataIntegrityViolationException | UnexpectedRollbackException e) {
            throw new InvalidEntityException(getConstraintViolationExceptionOnDeleteMessage());
        }
    }

    @Override
    public void removeById(I id) {
        try {
            repository.deleteById(id);
        } catch (ConstraintViolationException | DataIntegrityViolationException | UnexpectedRollbackException e) {
            throw new InvalidEntityException(getConstraintViolationExceptionOnDeleteMessage());
        }
    }

    void validateId(I id, T entity){
        if(entity.getId() != id)
            throw new InvalidEntityException(getIdCannotBeFilledMessage());
    }

    protected String getIdMustBeFilledMessage() {
        return ID_MUST_BE_FILLED_MESSAGE;
    }

    protected String getIdCannotBeFilledMessage() {
        return ID_CANNOT_BE_FILLED_MESSAGE;
    }

    protected String getNotFoundMessage() {
        return APP_NOT_FOUND;
    }

    protected String getConstraintViolationExceptionOnDeleteMessage() {
        return ID_CONSTRAINT_VIOLATION_ON_DELETE_MESSAGE;
    }

}
