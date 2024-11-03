package ubb.scs.map.domain;

/**
 * This is a generic class representing an entity with an identifier (ID).
 * The class is designed to be reusable for different types of entities
 * by specifying the type of the identifier (ID) when the class is instantiated.
 *
 * @param <ID> The type of the identifier for the entity. It could be any object,
 *             such as Integer, String, UUID, etc.
 */
public class Entity<ID>  {

    /// Private field to store the identifier of the entity.
    private ID id;

    /**
     * default parameterless constructor
     */
    public Entity() {}

    /**
     * Default constructor
     * @param id the id of the entity to be created
     */
    public Entity(ID id) {
        this.id = id;
    }

    /**
     * Getter method to retrieve the ID of the entity.
     *
     * @return The ID of the entity.
     */
    public ID getId() {
        return id;
    }

    /**
     * Setter method to assign or update the ID of the entity.
     *
     * @param id The new ID to be assigned to the entity.
     */
    public void setId(ID id) {
        this.id = id;
    }
}