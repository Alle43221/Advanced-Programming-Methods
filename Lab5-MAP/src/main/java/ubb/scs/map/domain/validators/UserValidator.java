package ubb.scs.map.domain.validators;


import ubb.scs.map.domain.User;

/**
 * The UserValidator class is a singleton that validates instances of the User class.
 * It ensures that the user data meets specific criteria before it can be saved in the repository.
 */
public class UserValidator implements Validator<User> {
    private static UserValidator instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private UserValidator() {
    }

    /**
     * Provides a global point of access to the singleton instance of UserValidator.
     *
     * @return The single instance of UserValidator.
     */
    public static UserValidator getInstance() {
        if(instance == null) {
            instance = new UserValidator();
        }
        return instance;
    }

    /**
     * Validates a User object to ensure it meets the necessary criteria.
     *
     * @param entity The User object to validate.
     * @throws ValidationException If the user is invalid, for example:
     *                             - If the first name is empty.
     *                             - If the last name is empty.
     *                             - If the first character of the last name is not an uppercase letter.
     *                             - If the first character of the first name is not an uppercase letter.
     */
    @Override
    public void validate(User entity) throws ValidationException {
        if(entity.getFirstName().isEmpty())
            throw new ValidationException("Empty first name");
        if(entity.getLastName().isEmpty())
            throw new ValidationException("Empty last name");
        if(entity.getLastName().charAt(0)<'A' || entity.getLastName().charAt(0)>'Z')
            throw new ValidationException("Invalid last name");
        if(entity.getFirstName().charAt(0)<'A' || entity.getFirstName().charAt(0)>'Z')
            throw new ValidationException("Invalid first name");
    }
}
