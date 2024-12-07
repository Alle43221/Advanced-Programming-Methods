using Sem10_MAP.Domain;

namespace Sem10_MAP.Repository;

public interface IRepository<E, ID> where E :Entity<ID>
{
    E? FindOne(ID id);
    IEnumerable<E> FindAll();
    E? Save(E entity);
    E? Delete(ID id);
    E? Update(E entity);
}