package ubb.scs.map.comenzirestaurant.validator;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}