package ubb.scs.map.repository.file;

import ubb.scs.map.domain.Friendship;
import ubb.scs.map.domain.Tuple;
import ubb.scs.map.domain.validators.Validator;

/**
 * Repository class for performing CRUD (Create, Read, Update, Delete) operations on Friendships.
 * It extends AbstractFileRepository to handle persistence of Friendship entities.
 *
 * Friendships are represented by a tuple of two Long values (representing user IDs),
 * and this repository reads from and writes to a file to store friendship data.
 */
public class FriendshipRepository extends AbstractFileRepository<Tuple<Long, Long>, Friendship> {

    /**
     * Constructor for FriendshipRepository.
     *
     * @param validator The validator used to validate Friendship entities.
     * @param fileName The name of the file where friendship data is stored.
     */
    public FriendshipRepository(Validator<Friendship> validator, String fileName) {
        super(validator, fileName);
    }

    /**
     * Converts a line of text from the file into a Friendship entity.
     *
     * The expected format of the line is "userId1;userId2", where both values are Longs representing
     * the IDs of the two users involved in the friendship.
     *
     * @param line The line from the file that contains friendship data.
     * @return A Friendship object created from the line.
     */
    @Override
    public Friendship lineToEntity(String line) {
        String[] splited = line.split(";");
        return new Friendship(Long.parseLong(splited[0]), Long.parseLong(splited[1]));
    }

    /**
     * Converts a Friendship entity into a line of text for saving to the file.
     *
     * The format of the line is "userId1;userId2".
     *
     * @param entity The Friendship entity to be converted to a string line.
     * @return A string representing the Friendship, formatted for file storage.
     */
    @Override
    public String entityToLine(Friendship entity) {
        return entity.getUserId1() + ";" + entity.getUserId2();
    }

}
