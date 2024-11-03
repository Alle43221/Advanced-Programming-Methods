package ubb.scs.map.domain.validators;

import ubb.scs.map.domain.Friendship;

import java.util.Objects;

/**
 * The FriendshipValidator class is a singleton that validates instances of the Friendship class.
 * It checks for conditions that ensure a friendship is valid before it can be saved in the repository.
 */
public class FriendshipValidator implements Validator<Friendship> {
    private static FriendshipValidator instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private FriendshipValidator() {
    }

    /**
     * Provides a global point of access to the singleton instance of FriendshipValidator.
     *
     * @return The single instance of FriendshipValidator.
     */
    public static FriendshipValidator getInstance() {
        if(instance == null) {
            instance = new FriendshipValidator();
        }
        return instance;
    }

    /**
     * Validates a Friendship object to ensure it meets the necessary criteria.
     *
     * @param obj The Friendship object to validate.
     * @throws ValidationException If the friendship is invalid, for example:
     *                             - If both user IDs are the same (indicating a duplicate friendship).
     *                             - If either user ID is less than 0.
     */
    @Override
    public void validate(Friendship obj) throws ValidationException {
        if(Objects.equals(obj.getUserId2(), obj.getUserId1())) {
            throw new ValidationException("Duplicate ID");
        }
        if(obj.getUserId2()<0){
            throw new ValidationException("User ID must be greater than 0");
        }
        if(obj.getUserId1()<0){
            throw new ValidationException("User ID must be greater than 0");
        }
    }

}
