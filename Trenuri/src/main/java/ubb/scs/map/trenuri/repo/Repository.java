package ubb.scs.map.trenuri.repo;


import java.util.List;

public interface Repository<E> {
    //E findOne(ID id);
    //E save(E entity);
    //E delete(ID id);
    // update(E entity);
    List<E> getAll();
}
