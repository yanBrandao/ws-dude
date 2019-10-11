package service.impl;

import domain.BaseEntity;
import exception.InvalidEntityException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import service.GenericService;
import utils.ValidateUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.List;

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


    protected String getNotFoundMessage() {
        return APP_NOT_FOUND;
    }

}
