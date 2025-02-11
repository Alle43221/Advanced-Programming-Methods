package ubb.scs.map.faptebune.repository;

import ubb.scs.map.faptebune.domain.Entity;
import ubb.scs.map.faptebune.domain.validators.Validator;

import java.io.*;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * AbstractFileRepository is an abstract class that extends the InMemoryRepository and adds file-based
 * persistence. It handles saving, deleting, and updating entities in memory, as well as loading and
 * saving data to a file. It requires subclasses to define methods for converting between
 * lines in a file and entity objects.
 * @param <ID> The type of the entity's ID.
 * @param <E> The type of the entity being managed
 */
public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E>{
    private final String filename;

    /**
     * Constructor for AbstractFileRepository.
     *
     * @param validator The validator used to validate entities.
     * @param fileName The name of the file where entity data is stored.
     */
    public AbstractFileRepository(Validator<E> validator, String fileName) {
        super(validator);
        filename=fileName;
        loadData();
    }

    /**
     * Abstract method to convert a line from the file into an entity object.
     *
     * @param line The line from the file.
     * @return The corresponding entity object.
     */
    public abstract E lineToEntity(String line);

    /**
     * Abstract method to convert an entity object into a string line for writing to the file.
     *
     * @param entity The entity to be converted.
     * @return The string line representing the entity.
     */
    public abstract String entityToLine(E entity);

    /**
     * Overrides the save method from InMemoryRepository to include writing to the file.
     *
     * @param entity The entity to be saved.
     * @return The entity if it already exists, or null if the save was successful.
     */
    @Override
    public Optional<E> save(E entity) {
        Optional<E> saved=super.save(entity);
        if (saved.isEmpty())
            writeToFile();
        return saved;
    }

    /**
     * Writes all entities from memory to the file, overwriting the existing file.
     */
    private void writeToFile() {

        try  ( BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            entities.values().forEach(entity->{
                    String ent = entityToLine(entity);
                    try {
                        writer.write(ent);
                        writer.newLine();
                    }
                    catch (IOException e) {
                        throw new RepoException(e);
                    }
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Appends a single entity to the file.
     *
     * @param entity The entity to be written to the file.
     */
    private void writeToFile(E entity) {
        try  ( BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
                writer.write(entityToLine(entity));
                writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Loads the data from the file and populates the repository's in-memory storage.
     */
    private void loadData() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                E entity = lineToEntity(line);
                super.save(entity);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Overrides the delete method from InMemoryRepository to include writing to the file.
     *
     * @param id The ID of the entity to be deleted.
     * @return The deleted entity, or null if no entity with the given ID existed.
     */
    @Override
    public Optional<E> delete(ID id) {
        Optional<E> entity = super.delete(id);
        writeToFile();
        return entity;
    }

    /**
     * Overrides the update method from InMemoryRepository to include writing to the file.
     *
     * @param entity The entity to be updated.
     * @return The updated entity, or null if no such entity exists in memory.
     */
    @Override
    public Optional<E> update(E entity) {
        Optional<E> entity1 = super.update(entity);
        writeToFile();
        return entity1;
    }
}
