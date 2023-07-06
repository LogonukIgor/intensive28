package by.logonuk.service;

import jakarta.servlet.http.HttpServletRequest;

public interface ServiceInterface<K, T> {

    /*
     * Finding a record in the database by id.
     * Throws: NoSuchEntityException
     */
    T findById(K id);

    // Creating a database entry
    T create(HttpServletRequest request);

    // Update a database entry
    T update(HttpServletRequest request);

    // Deleting a database entry
    K delete(K id);

}
