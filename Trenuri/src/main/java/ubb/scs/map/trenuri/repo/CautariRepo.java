package ubb.scs.map.trenuri.repo;

import ubb.scs.map.trenuri.domain.Cautare;

import java.util.ArrayList;
import java.util.List;

public class CautariRepo implements Repository<Cautare> {
    private List<Cautare> cautari=new ArrayList<>();

    @Override
    public List<Cautare> getAll() {
        return cautari;
    }


    public void remove(String idOm) {
        cautari.removeIf(c -> c.getIdOm().equals(idOm));
    }

    public void add(Cautare c) {
        cautari.add(c);
    }
}
