package ubb.scs.map.template.utils.observer;


import ubb.scs.map.template.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}