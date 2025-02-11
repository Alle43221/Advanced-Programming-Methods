package ubb.scs.map.template.domain;

public class AdoptionCenter extends Entity<Integer>{
    private String name;
    private String location;
    private int capacity;

    public AdoptionCenter(int id, String name, String location, int capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        super.setId(id);
    }

    public String getName() {
        return name;
    }


    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

}
