package ubb.scs.map.zboruri.service;


import ubb.scs.map.zboruri.domain.Client;
import ubb.scs.map.zboruri.domain.Flight;
import ubb.scs.map.zboruri.domain.Ticket;
import ubb.scs.map.zboruri.repo.ClientRepo;
import ubb.scs.map.zboruri.repo.FlightRepo;
import ubb.scs.map.zboruri.repo.TicketRepo;
import ubb.scs.map.zboruri.utils.observer.ObservableImplementat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Service extends ObservableImplementat {
    private ClientRepo clientRepo;
    private FlightRepo flightRepo;
    private TicketRepo ticketRepo;
    //private List<Observer<EntityChangeEvent>> observers= new ArrayList<>();

    public Service(ClientRepo clientRepo,FlightRepo flightRepo,TicketRepo ticketRepo) {
        this.clientRepo = clientRepo;
        this.flightRepo=flightRepo;
        this.ticketRepo=ticketRepo;
    }

    public List<Client> getLocations() {
        return clientRepo.getAll();
    }
    public List<Flight> getFlights() {
        return flightRepo.getAll();
    }
    public List<Ticket> getTickets() {return ticketRepo.getAll();}

    public Client getClientByUsername(String username){
        for(Client c: clientRepo.getAll())
            if(c.getUsername().equals(username))
                return c;
        return null;
    }

    public void adaugaTicket(String username, Long idFlight){
        Ticket ticket=new Ticket(username,idFlight, LocalDateTime.now());
        ticketRepo.adauga(ticket);
    }

    public Flight[] findAllOnPageFilter(int currentPag, String from, String to, LocalDate departure) {
        return flightRepo.findAllOnPageFilter(currentPag, from, to, departure).toArray(Flight[]::new);
    }

    public int getNoOfPages(String from, String to, LocalDate start) {
        return flightRepo.countEntries(from, to, start);
    }
}
