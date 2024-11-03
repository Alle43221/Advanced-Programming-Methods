package ubb.scs.map;

import ubb.scs.map.domain.Network;
import ubb.scs.map.domain.validators.FriendshipValidator;
import ubb.scs.map.domain.validators.UserValidator;
import ubb.scs.map.presentation.UI;
import ubb.scs.map.repository.file.FriendshipRepository;
import ubb.scs.map.repository.file.UserRepository;
import ubb.scs.map.service.ServiceCommunities;
import ubb.scs.map.service.ServiceNetwork;

/**
 * The Main class is the entry point of the application.
 * It sets up the necessary components (repositories, services, and UI) and starts the user interface (UI).
 */
public class Main {

    /// default constructor
    public Main() {
    }

    /**
     * The main method, where the program execution begins. It initializes the network, services,
     * and user interface, and then runs the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {

    Network network =new Network(new UserRepository(UserValidator.getInstance(), "./data/utilizatori.txt"),
            new FriendshipRepository(FriendshipValidator.getInstance(), "./data/prietenii.txt"));

    ServiceNetwork serviceNetwork =new ServiceNetwork(network);
    ServiceCommunities serviceCommunities =new ServiceCommunities(network);
    UI userInterface=new UI(serviceNetwork, serviceCommunities);
    userInterface.run();
}}