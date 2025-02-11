package ubb.scs.map.comenzirestaurant.service;

import ubb.scs.map.comenzirestaurant.domeniu.Angajat;
import ubb.scs.map.comenzirestaurant.domeniu.MyMenuItem;
import ubb.scs.map.comenzirestaurant.domeniu.Order;
import ubb.scs.map.comenzirestaurant.domeniu.Table;
import ubb.scs.map.comenzirestaurant.events.ChangeEventType;
import ubb.scs.map.comenzirestaurant.events.UtilizatorEntityChangeEvent;
import ubb.scs.map.comenzirestaurant.observer.ObservableCustom;
import ubb.scs.map.comenzirestaurant.observer.Observer;
import ubb.scs.map.comenzirestaurant.repository.Repository;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service implements ObservableCustom<UtilizatorEntityChangeEvent> {
    private Repository<Long, Angajat> repoAngajat;
    private List<Observer<UtilizatorEntityChangeEvent>> observers=new ArrayList<>();
    private Repository<Long, Order> repoOrder;
    private Repository<Long, MyMenuItem> repoMenuItem;
    private Repository<Long, Table>repoTable;
    public Service(Repository<Long,Angajat>repoAngajat, Repository<Long, MyMenuItem>repoMenuItem, Repository<Long,Table>repoTable, Repository<Long,Order>repoOrder) {
        this.repoAngajat = repoAngajat;
        this.repoOrder = repoOrder;
        this.repoMenuItem = repoMenuItem;
        this.repoTable=repoTable;
    }
    @Override
    public void addObserver(Observer<UtilizatorEntityChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<UtilizatorEntityChangeEvent> e) {

    }

    @Override
    public void notifyObservers(UtilizatorEntityChangeEvent t) {
        observers.stream().forEach(x -> x.update(t));
    }
    public Iterable<Angajat>getAngajati(){
        return repoAngajat.findAll();
    }
    public Iterable<MyMenuItem>getMenuItems(){
        return repoMenuItem.findAll();
    }
    public Iterable<Table>getTables(){
        return repoTable.findAll();
    }
    public Map<String, List<MyMenuItem>> getMenuGroupedByCategory() {
        Map<String, List<MyMenuItem>> result = new HashMap<>();
        for (MyMenuItem menuItem : repoMenuItem.findAll()) {
            result.computeIfAbsent(menuItem.getCategorie(), k -> new ArrayList<>()).add(menuItem);
        }
        return result;
    }

    public void saveOrder(Order order) {
        repoOrder.save(order);
        notifyObservers(new UtilizatorEntityChangeEvent(ChangeEventType.ADD, order));
    }
    public List<Order> getOrders() {
        return StreamSupport.stream(repoOrder.findAll().spliterator(), false)
                .sorted(Comparator.comparing(Order::getDate))
                .collect(Collectors.toList());
    }

    public MyMenuItem MenufindOne(Long id) {
        return repoMenuItem.findOne(id).orElse(null);
    }
}


