package ubb.scs.map.template.domain;

public class Animal extends Entity <Integer>{
    private String name;
    private int centerId;
    private Type type;

    public String getType() {
        return type.toString();
    }

    public void setCenterId(Integer integer) {
        centerId = integer;
    }

    public enum Type{
        DOG, CAT, PARROT
    }

    public Animal(int Id, String name, int centerId, Type type) {
        this.name = name;
        this.centerId = centerId;
        this.type = type;
        super.setId(Id);
    }

    public String getName() {
        return name;
    }


    public int getCenterId() {
        return centerId;
    }


    @Override
    public String toString() {
        return name + ' ' +type;
    }
}
