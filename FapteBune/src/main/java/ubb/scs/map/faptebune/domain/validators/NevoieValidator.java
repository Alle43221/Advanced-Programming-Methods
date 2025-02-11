package ubb.scs.map.faptebune.domain.validators;

import ubb.scs.map.faptebune.domain.Nevoie;

public class NevoieValidator implements Validator<Nevoie> {
    @Override
    public void validate(Nevoie entity) throws ValidationException {
        //TODO: implement method validate
        if(entity.getTitlu() == null)
            throw new ValidationException("title cannot be null");
        if(entity.getDescriere() == null)
            throw new ValidationException("descriere cannot be null");
        if(entity.getDeadline() == null)
            throw new ValidationException("deadline cannot be null");
        if(entity.getStatus() == null)
            throw new ValidationException("status cannot be null");
    }
}
