package ubb.scs.map.faptebune.domain.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}