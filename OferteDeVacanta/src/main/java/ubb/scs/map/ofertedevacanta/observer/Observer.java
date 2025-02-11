package ubb.scs.map.ofertedevacanta.observer;


import ubb.scs.map.ofertedevacanta.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}