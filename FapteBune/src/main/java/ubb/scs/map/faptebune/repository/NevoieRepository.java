package ubb.scs.map.faptebune.repository;

import ubb.scs.map.faptebune.domain.Nevoie;
import ubb.scs.map.faptebune.domain.validators.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class NevoieRepository extends AbstractFileRepository<Long, Nevoie> {
    public NevoieRepository(Validator<Nevoie> validator, String file) {
        super(validator, file);
    }

    @Override
    public Optional<Nevoie> findOne(Long aLong) {
        return super.findOne(aLong);
    }

    @Override
    public Iterable<Nevoie> findAll() {
        return super.findAll();
    }

    @Override
    public Nevoie lineToEntity(String line) {
        String[] fields = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dateTime = LocalDate.parse(fields[3], formatter);
        LocalDateTime t= dateTime.atTime(0, 0);

        return new Nevoie(Long.parseLong(fields[0]), fields[1], fields[2], t,
                Long.parseLong(fields[4]), Long.parseLong(fields[5]), fields[6]);
    }

    @Override
    public String entityToLine(Nevoie entity) {
        return entity.toString();
    }

    @Override
    public Optional<Nevoie> save(Nevoie entity) {
        return super.save(entity);
    }

    @Override
    public Optional<Nevoie> delete(Long aLong) {
        return super.delete(aLong);
    }

    @Override
    public Optional<Nevoie> update(Nevoie entity) {
        return super.update(entity);
    }
}
