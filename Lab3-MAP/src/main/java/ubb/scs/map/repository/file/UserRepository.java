package ubb.scs.map.repository.file;

import ubb.scs.map.domain.User;
import ubb.scs.map.domain.validators.Validator;

/**
 * UserRepository is a concrete class that extends AbstractFileRepository to manage
 * User entities. It reads from and writes to a file, storing user information and
 * transforming between file lines and User objects.
 */
public class UserRepository extends AbstractFileRepository<Long, User>{

    /**
     * Constructor for UserRepository.
     *
     * @param validator The validator used to validate User entities.
     * @param fileName The name of the file from which User data is read and to which it is saved.
     */
    public UserRepository(Validator<User> validator, String fileName) {
        super(validator, fileName);
    }

    /**
     * Converts a line of text from the file into a User entity.
     *
     * The line is expected to be formatted as: "id;firstName;lastName".
     *
     * @param line The string line from the file to be converted into a User object.
     * @return The User object created from the line of text.
     */
    @Override
    public User lineToEntity(String line) {
        String[] splited = line.split(";");
        User u = new User(splited[1], splited[2]);
        u.setId(Long.parseLong(splited[0]));
        return u;
    }

    /**
     * Converts a User entity into a line of text for writing to the file.
     *
     * The format of the line is: "id;firstName;lastName".
     *
     * @param entity The User entity to be converted to a string line.
     * @return A string representation of the User entity, formatted for file storage.
     */
    @Override
    public String entityToLine(User entity) {
        return entity.getId() + ";" + entity.getFirstName() + ";" + entity.getLastName();
    }
}
