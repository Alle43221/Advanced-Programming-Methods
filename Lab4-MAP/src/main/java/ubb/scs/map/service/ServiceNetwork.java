package ubb.scs.map.service;

import ubb.scs.map.domain.*;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * ServiceNetwork is a service class responsible for managing users and friendships in the network.
 * It provides methods to add, delete, and manage relationships between users, interacting with
 * the underlying repositories and handling business logic related to users and friendships.
 */
public class ServiceNetwork {
    private static Network network;


    /**
     * Constructor for ServiceNetwork.
     *
     * @param r The Network object containing the repositories for users and friendships.
     */
    public ServiceNetwork(Network r) {
        network = r;
    }

    /**
     * Deletes a user from the network and also removes all friendships involving that user.
     *
     * @param id The ID of the user to be deleted.
     * @throws ServiceException if the user does not exist in the repository.
     */
    public void deleteUser(Long id) {
        Optional<User> rez= network.getUserRepo().delete(id);
        if(rez.isEmpty()) {
            throw new ServiceException("Non-existent user!");
        }
        StreamSupport.stream(network.getFriendshipRepo().findAll().spliterator(), false).
                filter(t->Objects.equals(t.getUserId2(), id) || Objects.equals(t.getUserId1(), id)).
                        forEach(t->network.getFriendshipRepo().delete(new Tuple<>(t.getUserId1(), t.getUserId2())));
    }

    /**
     * Saves a new user to the network repository.
     *
     * @param lastname The last name of the user.
     * @param firstname The first name of the user.
     * @param id The ID to assign to the new user.
     * @throws ServiceException if the user already exists in the repository.
     */
    public void saveUser(String lastname, String firstname, Long id) {
        User user = new User(lastname, firstname);
        user.setId(id);
        Optional<User> rez= network.getUserRepo().save(user);
        if(rez.isPresent()) {
            throw new ServiceException("Existent user!");
        }
    }

    /**
     * Saves a friendship between two users in the network.
     *
     * @param id1 The ID of the first user.
     * @param id2 The ID of the second user.
     * @throws ServiceException if either user does not exist or the friendship already exists.
     */
    public void saveFriendship(Long id1, Long id2){
        Optional<User> rez= network.getUserRepo().findOne(id1);
        Optional<User> rez1= network.getUserRepo().findOne(id2);
        if(rez.isEmpty() || rez1.isEmpty()) {
            throw new ServiceException("Non-existent user!");
        }
        Optional<Friendship> rez2;
        if(id2<id1){
            rez2= network.getFriendshipRepo().findOne(new Tuple<>(id2, id1));
        }
        else{
            rez2= network.getFriendshipRepo().findOne(new Tuple<>(id1, id2));
        }
        if(rez2.isPresent()){
            throw new ServiceException("Existing friendship!");
        }
        network.getFriendshipRepo().save(new Friendship(id1, id2));
    }

    /**
     * Deletes an existing friendship between two users.
     *
     * @param id The ID of the first user.
     * @param id1 The ID of the second user.
     * @throws ServiceException if the friendship does not exist in the repository.
     */
    public void deleteFriendship(Long id, Long id1){
        Optional<Friendship> rez2= network.getFriendshipRepo().findOne(new Tuple<>(id, id1));
        if(rez2.isEmpty()){
            throw new ServiceException("Non-existent friendship!");
        }
        if(id<id1){
            network.getFriendshipRepo().delete(new Tuple<>(id, id1));
        }
       else{
           network.getFriendshipRepo().delete(new Tuple<>(id1, id));
        }
    }
}
