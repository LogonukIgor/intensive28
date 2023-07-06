package by.logonuk.controller.converter;

public interface ConverterInterface<K, T> {

    /*
     * Takes a StringBuilder, parses into an object
     */
    K createRequestToEntity(StringBuilder sb);

    /*
     * Takes a StringBuilder, parses into an object
     */
    K updateRequestToEntity(StringBuilder sb);

    /*
     * Receives an object, converts to a response entity (to hide password and system information)
     */
    T entityToResponse(K entity);
}
