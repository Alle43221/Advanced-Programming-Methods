package ubb.scs.map.template.utils.events;


import ubb.scs.map.template.domain.Request;

public class RequestEntityChangeEvent extends javafx.event.Event implements Event {
    private ChangeEventType type;
    private Request data;
    private String location;

    public RequestEntityChangeEvent(ChangeEventType type, Request data, String location) {
        super(ANY);
        this.type = type;
        this.data = data;
        this.location = location;
    }

    public Request getData() {
        return data;
    }

    public String getLocation() {
        return location;
    }

}