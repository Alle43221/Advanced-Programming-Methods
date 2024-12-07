using Sem10_MAP.Domain;

namespace Sem10_MAP.Repository;

public class InMemoryRepository<E, ID>: IRepository<E, ID> where E: Entity<ID>
{
    Dictionary<ID, E> Dictionary = new();
    
    public E? FindOne(ID id)
    {
        Dictionary.TryGetValue(id, out E? value);
        return value;
    }
    
    public IEnumerable<E> FindAll()
    {
        return Dictionary.Values;
    }

    public virtual E? Save(E entity)
    {
        return Dictionary.TryAdd(entity.Id, entity) ? entity : null;
    }
    
    public virtual E? Delete(ID id)
    {
        Dictionary.Remove(id, out E? entity);
        return entity;
    }

    public virtual E? Update(E entity)
    {
        if (!Dictionary.ContainsKey(entity.Id))
        {
            return null;
        }
        Dictionary[entity.Id] = entity;
        return entity;
    }
}