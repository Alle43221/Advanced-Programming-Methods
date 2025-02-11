package ubb.scs.map.template.repository;


import ubb.scs.map.template.domain.Entity;

/**
 * CRUD operations ubb.scs.map.faptebune.repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in ubb.scs.map.faptebune.repository
 */

public interface Repository<ID, E extends Entity<ID>> {


    /**
     *
     * @return all entities
     */
    Iterable<E> findAll();

}

