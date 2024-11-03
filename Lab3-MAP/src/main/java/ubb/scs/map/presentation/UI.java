package ubb.scs.map.presentation;

import ubb.scs.map.service.ServiceCommunities;
import ubb.scs.map.service.ServiceNetwork;

import java.util.Scanner;

/**
 * The UI (User Interface) class provides a text-based interface for interacting with
 * the network and community services. It allows users to add or delete users and friendships,
 * and also provides functionality for displaying community-related statistics.
 */
public class UI {

    /**
     * Constructs a UI instance with the specified service network and service communities.
     *
     * @param srv The service network used for managing user and friendship operations.
     * @param sr The service communities used for managing community-related operations.
     */
    public UI(ServiceNetwork srv, ServiceCommunities sr ) {
        serviceNetwork = srv;
        serviceCommunities=sr;
    }
    private static final Scanner sc = new Scanner(System.in);
    private static ServiceNetwork serviceNetwork;
    private static ServiceCommunities serviceCommunities;

    /**
     * A utility method that checks if a given string is numeric (i.e., can be parsed as a number).
     *
     * @param strNum The string to be checked.
     * @return True if the string is numeric, otherwise false.
     */
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static String meniu=
            "MENU:\n"+
            "-------------------------------\n"+
            "1. Add user (format: id;LastName;FirstName)\n"+
            "2. Delete user (format: id)\n"+
            "3. Add friendship (format: id1;id2)\n"+
            "4. Delete friendship (format: id1;id2)\n"+
            "5. Number of communities\n"+
            "6. Most sociable community\n"+
            "0. Exit";


    /**
     * The main loop of the UI. Continuously presents the menu, processes user input,
     * and calls the appropriate service methods based on user commands.
     */
    public void run(){
        System.out.println(meniu);
        String comanda, content;
        while(true){
            System.out.print(">>>");
            comanda=sc.nextLine();

            switch (comanda){
                case "1":{
                    System.out.print("Add user: ");
                    content=sc.nextLine();
                    String[] splited = content.split(";");
                    if(splited.length!=3){
                        System.out.println("Invalid format!");
                    }
                    else if(!isNumeric(splited[0])){
                        System.out.println("Invalid argument!");
                    }
                    else{
                        try{
                            serviceNetwork.saveUser(splited[1], splited[2], Long.parseLong(splited[0]));
                            System.out.println("User added!");
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
                case "2":{
                    System.out.print("Delete user: ");
                    content=sc.nextLine();
                    String[] splited = content.split(";");
                    if(splited.length!=1){
                        System.out.println("Invalid format!");
                    }
                    else if(!isNumeric(splited[0])){
                        System.out.println("Invalid argument!");
                    }
                    else{
                        try{
                            serviceNetwork.deleteUser(Long.parseLong(splited[0]));
                            System.out.println("User deleted!");
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
                case "3":{
                    System.out.print("Add friendship: ");
                    content=sc.nextLine();
                    String[] splited = content.split(";");
                    if(splited.length!=2){
                        System.out.println("Invalid format!");
                    }
                    else if(!isNumeric(splited[0]) || !isNumeric(splited[1])){
                        System.out.println("Invalid argument!");
                    }
                    else{
                        try{
                            serviceNetwork.saveFriendship(Long.parseLong(splited[0]), Long.parseLong(splited[1]));
                            System.out.println("Friendship added!");
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
                case "4":{
                    System.out.print("Delete friendship: ");
                    content=sc.nextLine();
                    String[] splited = content.split(";");
                    if(splited.length!=2){
                        System.out.println("Invalid format!");
                    }
                    else if(!isNumeric(splited[0]) || !isNumeric(splited[1])){
                        System.out.println("Invalid argument!");
                    }
                    else{
                        try{
                            serviceNetwork.deleteFriendship(Long.parseLong(splited[0]), Long.parseLong(splited[1]));
                            System.out.println("Friendship deleted!");
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }
                case "5":{
                    Long rez= serviceCommunities.connectedCommunities();
                    System.out.println("Number of communities: " + rez);
                    break;
                }
                case "6":{
                    System.out.println("Most sociable community:");
                    System.out.println(serviceCommunities.mostSocialCommunity());
                    break;
                }
                case "0":{
                    System.out.println("Exiting...");
                    return;
                }
                default:{
                    System.out.println("Invalid command!");
                }
            }
        }
    }
}
