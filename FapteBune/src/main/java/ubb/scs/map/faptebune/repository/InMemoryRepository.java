package ubb.scs.map.faptebune.repository;


import ubb.scs.map.faptebune.domain.Entity;
import ubb.scs.map.faptebune.domain.validators.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID,E> {

    protected Validator<E> validator;
    protected Map<ID,E> entities;

    public InMemoryRepository() {
        entities = new HashMap<>();
    }

    protected InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public Optional<E> findOne(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id must be not null");
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public Optional<E> save(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null");
        E oldEntity = entities.get(entity.getId());
        if (oldEntity != null) {
            return Optional.of(oldEntity);
        } else {
            entities.put(entity.getId(), entity);
            return Optional.empty();
        }
    }

    @Override
    public Optional<E> delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id must be not null");
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<E> update(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        if (entities.get(entity.getId()) != null) {
            entities.put(entity.getId(), entity);
            return Optional.empty();
        }
        return Optional.of(entity);
    }
}
