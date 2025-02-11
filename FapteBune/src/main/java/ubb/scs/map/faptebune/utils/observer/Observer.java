package ubb.scs.map.faptebune.utils.observer;


import ubb.scs.map.faptebune.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}