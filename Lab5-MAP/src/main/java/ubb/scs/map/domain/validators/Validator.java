package ubb.scs.map.domain.validators;

/**
 * The Validator interface provides a contract for validating objects of type T.
 * Implementing classes must provide the logic to validate the specific type of entity they are designed for.
 *
 * @param <T> the type of entity to be validated.
 */
public interface Validator<T> {

    /**
     * Validates the given entity.
     *
     * @param entity The entity to validate.
     * @throws ValidationException If the entity is found to be invalid.
     */
    void validate(T entity) throws ValidationException;
}