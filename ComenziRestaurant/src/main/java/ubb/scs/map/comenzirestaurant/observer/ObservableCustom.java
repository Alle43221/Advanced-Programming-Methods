package ubb.scs.map.comenzirestaurant.observer;


import ubb.scs.map.comenzirestaurant.events.Event;

public interface ObservableCustom<E extends Event> {
    void addObserver(Observer<E> e);
    void removeObserver(Observer<E> e);
    void notifyObservers(E t);
}
