package ubb.scs.map.comenzirestaurant.observer;


import ubb.scs.map.comenzirestaurant.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}