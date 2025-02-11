package ubb.scs.map.ofertedevacanta.service;


import ubb.scs.map.ofertedevacanta.domain.*;
import ubb.scs.map.ofertedevacanta.events.CustomEvent;
import ubb.scs.map.ofertedevacanta.observer.Observable;
import ubb.scs.map.ofertedevacanta.observer.Observer;
import ubb.scs.map.ofertedevacanta.repo.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<CustomEvent> {
    private LocationRepo locationRepo;
    private HotelRepo hotelRepo;
    private OffRepo offRepo;
    private ClientRepo clientRepo;
    private RezervareRepo rezervareRepo;
    private List<Observer<CustomEvent>> observers=new ArrayList<>();
    

    public Service(LocationRepo locationRepo, HotelRepo hotelRepo, OffRepo offRepo, ClientRepo clientRepo,RezervareRepo rezervareRepo) {
        this.locationRepo = locationRepo;
        this.hotelRepo = hotelRepo;
        this.offRepo = offRepo;
        this.clientRepo=clientRepo;
        this.rezervareRepo=rezervareRepo;
    }

    public List<Location> getLocations() {
        return locationRepo.getAll();
    }

    public List<Hotel> getHotels() {
        return hotelRepo.getAll();
    }
    public List<Offers> getOffers() {
        return offRepo.getAll();
    }
    public List<Client> getClients() {
        return clientRepo.getAll();
    }

    public Double getLocationId(String location) {
        for(Location loc: locationRepo.getAll()){
            if(loc.getName().equals(location))
                return loc.getId();
        }
        return 0.0;
    }

    public Client getClientById(Long idClient){
        for(Client c: clientRepo.getAll())
            if(c.getId().equals(idClient))
                return c;
        return null;
    }

    public List<Offers> getOffersAvailable(Long idClient){
        List<Offers> all=new ArrayList<>();
        Client client=getClientById(idClient);
        for(Offers off: offRepo.getAll())
        {
            if(off.getEnd().compareTo(LocalDate.now())>0 && client.getFidelityGrade()>=off.getPercent())
                all.add(off);
        }
        return all;
    }

    public void adaugaRezervare(Long idClient,Double idHotel,LocalDate dataStart,Integer noNights){
        Double id;
        List<Rezervare> rezv=new ArrayList<>();
        rezv=rezervareRepo.getAll();
        if(rezv.size()==0) id=1.0;
        else{
            id=rezv.get(rezv.size() - 1).getId()+1;
        }

        Rezervare r=new Rezervare(id,idClient,idHotel,dataStart,noNights);
        rezervareRepo.adauga(r);
    }

    @Override
    public void addObserver(Observer<CustomEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<CustomEvent> e) {
        //observers.remove(e);
    }

    @Override
    public void notifyObservers(CustomEvent t) {

        observers.stream().forEach(x->x.update(t));
    }

    public void makeEvent(CustomEvent t){
        notifyObservers(t);
    }
}
