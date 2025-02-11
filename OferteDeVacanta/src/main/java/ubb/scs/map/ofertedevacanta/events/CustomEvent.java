package ubb.scs.map.ofertedevacanta.events;

import javafx.event.Event;
import javafx.event.EventType;
import ubb.scs.map.ofertedevacanta.domain.Hobbies;

public class CustomEvent extends Event implements ubb.scs.map.ofertedevacanta.events.Event {
    private Hobbies hobby;
    private Long userId;
    public static final EventType<CustomEvent> MY_CUSTOM_EVENT = new EventType<>(Event.ANY, "MY_CUSTOM_EVENT");

    public Hobbies getHobby() {
        return hobby;
    }

    public Long getUserId() {
        return userId;
    }

    public CustomEvent(Hobbies hobby, Long userId) {
        super(MY_CUSTOM_EVENT);
        this.hobby = hobby;
        this.userId = userId;
    }
}
