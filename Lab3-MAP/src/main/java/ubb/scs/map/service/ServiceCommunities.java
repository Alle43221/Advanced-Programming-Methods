package ubb.scs.map.service;

import ubb.scs.map.domain.Friendship;
import ubb.scs.map.domain.Network;
import ubb.scs.map.domain.User;

import java.util.*;

/**
 * The ServiceCommunities class provides functionality for analyzing user communities
 * within a social network. It calculates the number of connected communities and
 * identifies the most social community based on friendships.
 */
public class ServiceCommunities {
    private static Network network;
    private HashMap<Long, List<Long>> adjList;

    /**
     * Constructs a ServiceCommunities object with the specified network.
     *
     * @param r The network containing users and friendships.
     */
    public ServiceCommunities(Network r) {
        network = r;
    }

    /**
     * Constructs the adjacency list from the friendships in the network.
     * Each user is mapped to a list of their friends.
     */
    private void setAdjList(){
        adjList = new HashMap<Long, List<Long>>();
        for (User user : network.getUserRepo().findAll()) {
            List<Long> friends = new ArrayList<>();
            for (Friendship friendship : network.getFriendshipRepo().findAll()) {
                if (friendship.getUserId1().equals(user.getId()))
                    friends.add(friendship.getUserId2());
                if (friendship.getUserId2().equals(user.getId()))
                    friends.add(friendship.getUserId1());
            }
            this.adjList.put(user.getId(), friends);
        }
    }

    /**
     * Performs a Depth-First Search (DFS) on the user graph to mark connected users.
     *
     * @param v      The current user ID being visited.
     * @param visited A map to track visited users.
     */
    void DFS(Long v, HashMap<Long, Boolean> visited) {
        visited.put(v, true);
        if (adjList.containsKey(v)) {
            for (Long x : adjList.get(v)) {
                if (!visited.containsKey(x))
                    DFS(x, visited);
            }
        }
    }

    /**
     * Performs a Depth-First Search (DFS) on the user graph to mark connected users.
     *
     * @param v      The current user ID being visited.
     * @param dist A map to track visited users.
     * @param distance Distance from the starting node of the DFS.
     */
    void DFS(Long v, HashMap<Long, Long> dist, Long distance) {
        dist.put(v, distance);
        if (adjList.containsKey(v)) {
            for (Long x : adjList.get(v)) {
                if (!dist.containsKey(x))
                    DFS(x, dist, distance+1);
            }
        }
    }

    /**
     * Calculates the number of connected communities in the network.
     *
     * @return The number of connected communities.
     */
    public Long connectedCommunities() {
        setAdjList();

        // list of ids of users
        List<Long> ids = new ArrayList<>();
        for (User user : network.getUserRepo().findAll()) {
            ids.add(user.getId());
        }

        long nrOfCommunities = 0L;
        HashMap<Long, Boolean> visited = new HashMap<>();
        for (Long v : ids) {
            if (!visited.containsKey(v)) {
                DFS(v, visited);
                nrOfCommunities++;
            }
        }
        return nrOfCommunities;
    }


    /**
     * Calculates the diameter of the graph starting from a given arbitrary vertex.
     * The diameter is defined as the longest shortest path between any two vertices in the graph.
     * This method uses two Depth-First Search (DFS) traversals to determine the diameter.
     *
     * @param arbitraryStart the starting vertex from which the first DFS will be initiated.
     * @param visited a HashMap that tracks which vertices have been visited during the diameter calculation.
     *                This is typically used to prevent re-visiting vertices in the graph.
     * @return the diameter of the graph, which is the maximum distance found during the second DFS traversal.
     */
    public Long calculateDiameter(Long arbitraryStart,  HashMap<Long, Boolean> visited) {

        HashMap<Long, Long> distance = new HashMap<>();
        DFS(arbitraryStart, distance, 0L);

        Long farthestVertex = Collections.max(distance.entrySet(), Map.Entry.comparingByValue()).getKey();

        distance.clear();
        DFS(farthestVertex, distance, 0L);
        for(Long i:distance.keySet()){
            if(distance.containsKey(i)){
                visited.put(i, true);
            }
        }

        return Collections.max(distance.values());
    }


    /**
     * Identifies the most social community in the network.
     *
     * @return A list containing the ID(s) of the most social community.
     */
    public List<Long> mostSocialCommunity() {
        setAdjList();

        // list of ids of users
        List<Long> ids = new ArrayList<>();
        for (User user : network.getUserRepo().findAll()) {
            ids.add(user.getId());
        }

        HashMap<Long, Boolean> visited = new HashMap<>();
        long candidate = -1, maximumDistance = 0;

        for (Long v : ids) {
            if (!visited.containsKey(v)) {
               Long rez=calculateDiameter(v, visited);
               if(rez>maximumDistance){
                   maximumDistance = rez;
                   candidate = v;
               }
            }
        }

        visited.clear();
        DFS(candidate, visited);
        List<Long> nodes = new ArrayList<>(List.of());
        nodes.addAll(visited.keySet());
        return nodes;
    }
}
