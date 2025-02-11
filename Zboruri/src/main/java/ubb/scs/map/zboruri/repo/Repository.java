package ubb.scs.map.zboruri.repo;


import java.util.List;

public interface Repository<E> {
    List<E> getAll();
}
