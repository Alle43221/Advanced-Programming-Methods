package ubb.scs.map.faptebune.utils.events;


import ubb.scs.map.faptebune.domain.Persoana;

public class PersoanaEntityChangeEvent implements Event {
    private final ChangeEventType type;
    private final Persoana data;
    private Persoana oldData;

    public PersoanaEntityChangeEvent(ChangeEventType type, Persoana data) {
        this.type = type;
        this.data = data;
    }
    public PersoanaEntityChangeEvent(ChangeEventType type, Persoana data, Persoana oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Persoana getData() {
        return data;
    }

    public Persoana getOldData() {
        return oldData;
    }
}