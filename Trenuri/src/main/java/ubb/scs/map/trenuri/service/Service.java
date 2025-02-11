package ubb.scs.map.trenuri.service;



import ubb.scs.map.trenuri.domain.Cautare;
import ubb.scs.map.trenuri.domain.City;
import ubb.scs.map.trenuri.domain.TrainStation;
import ubb.scs.map.trenuri.repo.CautariRepo;
import ubb.scs.map.trenuri.repo.CityRepo;
import ubb.scs.map.trenuri.repo.TrainStationRepo;
import ubb.scs.map.trenuri.utils.observer.ObservableImplementat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Service extends ObservableImplementat {
    private CityRepo cityRepo;
    private TrainStationRepo trainStationRepo;
    private CautariRepo cautariRepo;

    public Service(CityRepo cityRepo, TrainStationRepo trainStationRepo, CautariRepo cautariRepo) {
        this.cityRepo=cityRepo;
        this.trainStationRepo=trainStationRepo;
        this.cautariRepo=cautariRepo;
    }

    public List<City> getCities() {
        return cityRepo.getAll();
    }
    public List<TrainStation> getTrainStations() {
        return trainStationRepo.getAll();
    }

    public String getNameById(String idOras){
        for(City c: cityRepo.getAll()){
            if(c.getId().equals("C"+idOras))
                return c.getNume();
        }
        return null;
    }

    public void addCautare(String idOm, String idDeparture, String idDestination){
        cautariRepo.remove(idOm);
        Cautare c=new Cautare(idOm,idDeparture,idDestination);
        cautariRepo.add(c);
        this.notifyObservers();
    }

    public Integer nrPersoane(String from,String to){
        Integer cnt=0;
        for(Cautare c: cautariRepo.getAll())
        {
            if(c.getIdDeparture().equals(from) && c.getIdDestination().equals(to)) {
                cnt++;
            }
        }
        return cnt-1;
    }

    public void removeCautare(String idOm) {
        cautariRepo.remove(idOm);
        this.notifyObservers();
    }

    public List<List<TrainStation>> bk(String from, String to, List<TrainStation> solution, Set<String> visited) {
        List<List<TrainStation>> allSolutions = new ArrayList<>();

        // Base case: If the last station in the solution reaches the destination
        if (!solution.isEmpty() && getNameById(solution.get(solution.size() - 1).getDestination()).equals(to)) {
            allSolutions.add(new ArrayList<>(solution));  // Add a complete route to solutions
            return allSolutions;
        }

        // Iterate over all the train stations to continue the path
        List<TrainStation> trainStations = trainStationRepo.getAll();

        for (TrainStation t : trainStations) {
            // Avoid revisiting stations and handle empty solution
            if (!visited.contains(t.getDeparture())) {
                // If the solution is empty, start from the 'from' station
                if (solution.isEmpty() && getNameById(t.getDeparture()).equals(from)) {
                    visited.add(t.getDeparture());  // Mark the station as visited
                    solution.add(t);  // Add this station to the current route
                    allSolutions.addAll(bk(from, to, solution, visited));  // Recursively continue the search
                    visited.remove(t.getDeparture());  // Backtrack: unmark the station as visited
                    solution.remove(solution.size() - 1);  // Backtrack: remove the station from the route
                }
                // Handle non-empty solution and proceed with a valid train station
                else if (!solution.isEmpty() && solution.get(solution.size() - 1).getDestination().equals(t.getDeparture()) && !getNameById(t.getDestination()).equals(from)) {
                    visited.add(t.getDeparture());  // Mark the station as visited
                    solution.add(t);  // Add this station to the current route
                    allSolutions.addAll(bk(from, to, solution, visited));  // Continue the search
                    visited.remove(t.getDeparture());  // Backtrack: unmark the station as visited
                    solution.remove(solution.size() - 1);  // Backtrack: remove the station from the route
                }
            }
        }

        return allSolutions;  // Return all valid solutions
    }


}
