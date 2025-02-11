package ubb.scs.map.template.service;

import ubb.scs.map.template.controller.TableController;
import ubb.scs.map.template.domain.AdoptionCenter;
import ubb.scs.map.template.domain.Animal;
import ubb.scs.map.template.domain.Request;
import ubb.scs.map.template.repository.RepoAnimal;
import ubb.scs.map.template.repository.RepoCenter;
import ubb.scs.map.template.repository.RepoRequest;
import ubb.scs.map.template.utils.events.RequestEntityChangeEvent;
import ubb.scs.map.template.utils.observer.Observable;
import ubb.scs.map.template.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service implements Observable<RequestEntityChangeEvent> {
    private List<Observer<RequestEntityChangeEvent>> observers=new ArrayList<>();
    private RepoAnimal repoA;
    private RepoCenter repoC;
    private RepoRequest repoR;

    public Service( RepoAnimal repoA, RepoCenter repoC, RepoRequest repoR ) {
        this.repoA = repoA;
        this.repoC = repoC;
        this.repoR = repoR;
    }


    @Override
    public void addObserver(Observer<RequestEntityChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<RequestEntityChangeEvent> e) {
        observers.remove(e);

    }
    public void notifyObservers(RequestEntityChangeEvent t) {
        observers.stream().forEach(x -> x.update(t));
    }

    public List<Animal> getAllAnimalsFromCenter(int centerId) {
        return StreamSupport.stream(repoA.findAll().spliterator(), false).filter(
                x->x.getCenterId()==centerId
        ).collect(Collectors.toList());
    }

    public List<AdoptionCenter> getAllCenters() {
        return StreamSupport.stream(repoC.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Optional<AdoptionCenter> getAdoptionCenter(int centerId) {
        return repoC.findOne(centerId);
    }

    public List<Animal> getAllOfType(Animal.Type type, int idCenter) {
        return StreamSupport.stream(repoA.findAllOfType(type).spliterator(), false)
                .filter(x->idCenter==x.getCenterId()).collect(Collectors.toList());
    }

    public void Location(RequestEntityChangeEvent t) {
        Iterable<AdoptionCenter> res = repoC.findAllFromLocation(t.getLocation());

        List<Integer> list = StreamSupport.stream(res.spliterator(), false)
                .map(AdoptionCenter::getId)
                .toList();
        observers.stream()
                .filter(observer -> observer instanceof TableController)
                .map(observer -> (TableController) observer)
                .filter(controller -> list.contains(controller.getCenterId()))
                .forEach(observer -> observer.update(t));
    }


    public void addRequest(int centerId, Integer id) {
        Request res = repoR.save(new Request(1, centerId, id));
        Location(new RequestEntityChangeEvent(null, res, repoC.findOne(centerId).get().getLocation()));
    }

    public void editRequest(Request request, int newCenterId) {
        repoR.delete(request.getId());
        Animal  t=getAnimal(Integer.valueOf(request.getIdAnimal()));
        t.setCenterId(newCenterId);
        repoA.update(t);
        notifyObservers(new RequestEntityChangeEvent(null, new Request(-1, -1, -1), ""));
    }

    public Animal getAnimal(Integer idAnimal) {
        return repoA.findOne(idAnimal).get();
    }
}
