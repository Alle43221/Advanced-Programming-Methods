package ubb.scs.map.faptebune.service;

import ubb.scs.map.faptebune.domain.Entity;
import ubb.scs.map.faptebune.domain.Nevoie;
import ubb.scs.map.faptebune.domain.Persoana;
import ubb.scs.map.faptebune.repository.NevoieRepository;
import ubb.scs.map.faptebune.repository.PersoanaRepository;
import ubb.scs.map.faptebune.utils.events.ChangeEventType;
import ubb.scs.map.faptebune.utils.events.PersoanaEntityChangeEvent;
import ubb.scs.map.faptebune.utils.observer.Observable;
import ubb.scs.map.faptebune.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service implements Observable<PersoanaEntityChangeEvent> {

    private final PersoanaRepository persoaneRepository;
    private final NevoieRepository nevoieRepository;
    private List<Observer<PersoanaEntityChangeEvent>> observers=new ArrayList<>();
    

    public Service(PersoanaRepository persoaneRepository, NevoieRepository nevoieRepository) {
        this.persoaneRepository = persoaneRepository;
        this.nevoieRepository = nevoieRepository;
    }

    public Optional<Persoana> findByCredentials(String username) {
        return StreamSupport.stream(persoaneRepository.findAll().spliterator(), false)
                .filter((x)-> Objects.equals(x.getUsername(), username)).findFirst();
    }

    public Long getNextPersoanaId() {
        Optional<Long> rez= StreamSupport.stream(persoaneRepository.findAll().spliterator(), false)
                .map(Entity::getId).reduce((x, y)->x>y?x:y);
        return rez.<Long>map(aLong -> aLong + 1).orElse(1L);
    }
    public Long getNextNevoieId() {
        Optional<Long> rez= StreamSupport.stream(nevoieRepository.findAll().spliterator(), false)
                .map(Entity::getId).reduce((x, y)->x>y?x:y);
        return rez.<Long>map(aLong -> aLong + 1).orElse(1L);
    }

    public Optional<Persoana> addPersoana(Persoana m) {
        Optional<Persoana> p = persoaneRepository.save(m);
        if(p.isPresent()) {
            return p;
        }
        notifyObservers(new PersoanaEntityChangeEvent(ChangeEventType.UPDATE, m));
        return Optional.empty();
    }

    @Override
    public void addObserver(Observer<PersoanaEntityChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<PersoanaEntityChangeEvent> e) {
        //observers.remove(e);
    }

    @Override
    public void notifyObservers(PersoanaEntityChangeEvent t) {

        observers.stream().forEach(x->x.update(t));
    }

    public Iterable<Persoana> getAllPersoane() {
        return persoaneRepository.findAll();
    }

    public Iterable<Nevoie> getAllNevoie() {
        return nevoieRepository.findAll();
    }

    public Iterable<Nevoie> getAllNevoieFromCity(String city) {
        List<Nevoie> res=new ArrayList<>();
        for (Nevoie n : nevoieRepository.findAll()) {
            Optional<Persoana> p=persoaneRepository.findOne(n.getOmInNevoie());
            if(p.isPresent())
            {
                if(p.get().getOras().equals(city)){
                    res.add(n);
                }
            }
        }
        return res;
    }

    public Optional<Persoana> findOnePersoana(Long sessionID) {
        return persoaneRepository.findOne(sessionID);
    }

    public Iterable<Nevoie> getAllNevoieSolvedByPerson(Long id) {
        return StreamSupport.stream(nevoieRepository.findAll().spliterator(), false)
                .filter(x->x.getOmSalvator()==id).collect(Collectors.toList());
    }

    public Optional<Nevoie> selectedNevoie(Nevoie n, Long sessionID) {
        n.setOmSalvator(sessionID);
        n.setStatus("Erou gasit!");
        Optional<Nevoie> p = nevoieRepository.update(n);
        if(p.isPresent()) {
            return p;
        }
        notifyObservers(new PersoanaEntityChangeEvent(ChangeEventType.UPDATE, new Persoana()));
        return nevoieRepository.update(n);

    }

    public Optional<Nevoie> addNevoie(Nevoie n) {
        Optional<Nevoie> p = nevoieRepository.save(n);
        if(p.isPresent()) {
            return p;
        }
        notifyObservers(new PersoanaEntityChangeEvent(ChangeEventType.UPDATE, new Persoana()));
        return Optional.empty();
    }
}
