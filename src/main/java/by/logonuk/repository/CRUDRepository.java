package by.logonuk.repository;

public interface CRUDRepository<K, T> {

    /*
     * Finding a record in the database by id.
     * Throws: NoSuchEntityException
     */
    T findById(K id);

    // Creating a database entry
    T create(T object);

    // Update a database entry
    T update(T object);

    // Deleting a database entry
    K delete(K id);
}
