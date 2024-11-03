package ubb.scs.map.repository.memory;


import ubb.scs.map.domain.Entity;
import ubb.scs.map.domain.validators.ValidationException;
import ubb.scs.map.domain.validators.Validator;
import ubb.scs.map.repository.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * The InMemoryRepository class provides an in-memory implementation of the
 * Repository interface for managing entities of type E identified by type ID.
 *
 * @param <ID> The type of the identifier for the entity.
 * @param <E> The type of the entity
 */
public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID,E> {

    private Validator<E> validator;

    /// Map which contains the entities stored in memory.
    protected Map<ID,E> entities;

    /**
     * Constructs an InMemoryRepository with the specified validator.
     *
     * @param validator The validator used to validate entities before saving or updating them.
     */
    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities= new HashMap<>();
    }

    /**
     * Finds and returns the entity with the specified ID.
     *
     * @param id The ID of the entity to find.
     * @return The entity with the specified ID, or null if not found.
     * @throws IllegalArgumentException if the provided ID is null.
     */
    @Override
    public E findOne(ID id) {
        if(id==null)
            throw new IllegalArgumentException("id is null");
        return entities.get(id);
    }

    /**
     * Returns all entities stored in the repository.
     *
     * @return An iterable collection of all entities.
     */
    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }


    /**
     * Saves the specified entity to the repository.
     *
     * @param entity The entity to save.
     * @return null if the entity is successfully saved; otherwise, returns the existing entity if the ID already exists.
     * @throws ValidationException if the entity is not valid.
     * @throws IllegalArgumentException if the given entity is null.
     */
    @Override
    public E save(E entity) throws ValidationException {

        if(entity==null)
            throw new IllegalArgumentException("ENTITY CANNOT BE NULL");
        validator.validate(entity);
        E oldEntity = entities.get(entity.getId());
        if (oldEntity != null) {
            return oldEntity;
        } else {
            entities.put(entity.getId(), entity);
            return null;
        }
    }


    /**
     * Deletes the entity with the specified ID from the repository.
     *
     * @param id The ID of the entity to delete.
     * @return The deleted entity, or null if it was not found.
     * @throws IllegalArgumentException if the provided ID is null.
     */
    @Override
    public E delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id must be not null");
        return entities.remove(id);
    }

    /**
     * Updates the specified entity in the repository.
     *
     * @param entity The entity to update.
     * @return null if the entity is successfully updated; otherwise, returns the entity if it does not exist.
     * @throws IllegalArgumentException if the given entity is null.
     */
    @Override
    public E update(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        validator.validate(entity);
        if (entities.get(entity.getId()) != null) {
            entities.put(entity.getId(), entity);
            return null;
        }
        return entity;
    }
}
