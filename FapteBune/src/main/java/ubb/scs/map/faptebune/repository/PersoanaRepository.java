package ubb.scs.map.faptebune.repository;

import ubb.scs.map.faptebune.domain.Persoana;
import ubb.scs.map.faptebune.domain.validators.Validator;

import java.util.Optional;

public class PersoanaRepository extends AbstractFileRepository<Long, Persoana> {

    public PersoanaRepository(Validator<Persoana> validator, String file) {
        super(validator, file);
    }

    @Override
    public Optional<Persoana> findOne(Long aLong) {
        return super.findOne(aLong);
    }

    @Override
    public Iterable<Persoana> findAll() {
        return super.findAll();
    }

    @Override
    public Persoana lineToEntity(String line) {
        String[] fields = line.split(",");
        return new Persoana(Long.parseLong(fields[0]), fields[1], fields[2], fields[3],
                fields[4], fields[5], fields[6], fields[7], fields[8]);
    }

    @Override
    public String entityToLine(Persoana entity) {
        return entity.toString();
    }

    @Override
    public Optional<Persoana> save(Persoana entity) {
        return super.save(entity);
    }

    @Override
    public Optional<Persoana> delete(Long aLong) {
        return super.delete(aLong);
    }

    @Override
    public Optional<Persoana> update(Persoana entity) {
        return super.update(entity);
    }
}

