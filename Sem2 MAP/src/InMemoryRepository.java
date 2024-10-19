import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID,E> {

    protected Map<ID,E> entities;

    public InMemoryRepository() {
        entities=new HashMap<ID,E>();
    }

    @Override
    public E findOne(ID id) {
        if(id==null)
            throw new IllegalArgumentException("id is null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) {
        /**
         *
         * @param entity
         *         entity must be not null
         * @return null- if the given entity is saved
         *         otherwise returns the entity (id already exists)
         * @throws ValidationException
         *            if the entity is not valid
         * @throws IllegalArgumentException
         *             if the given entity is null.     *
         */
        if(entity==null)
            throw new IllegalArgumentException("ENTITY CANNOT BE NULL");
        if(entities.containsKey(entity.getId()))
            return entity;
        else{
            entities.put(entity.getId(),entity);
            return null;
        }
    }

    @Override
    public E delete(ID id) {
        if(id==null)
            throw new IllegalArgumentException("id is null");
        if(!entities.containsKey(id)){
            return null;
        }
        else {
            return entities.remove(id);
        }
    }

    @Override
    public E update(E entity) {
        if(entity==null)
            throw new IllegalArgumentException("ENTITY CANNOT BE NULL");
        if(!entities.containsKey(entity.getId())){
            entities.put(entity.getId(),entity);
            return entity;
        }
        else{
            entities.put(entity.getId(),entity);
            return null;
        }
    }
}
