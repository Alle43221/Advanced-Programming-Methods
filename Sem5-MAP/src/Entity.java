public abstract class Entity<ID>{
    public ID id;

    public Entity(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
