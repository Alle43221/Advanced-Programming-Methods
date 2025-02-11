package ubb.scs.map.faptebune.domain.validators;

import ubb.scs.map.faptebune.domain.Persoana;

public class PersoanaValidator implements Validator<Persoana> {
    @Override
    public void validate(Persoana entity) throws ValidationException {
        //TODO: implement method validate
        if(entity.getNume() == null || entity.getPrenume() == null)
            throw new ValidationException("Names cannot be null");
        if(entity.getOras() == null || entity.getStrada() == null || entity.getNumarStrada() == null)
            throw new ValidationException("Incomplete address!");
        if(entity.getPassword() == null)
            throw new ValidationException("Invalid password!");
        if(entity.getUsername() == null)
            throw new ValidationException("Invalid username!");
    }
}
