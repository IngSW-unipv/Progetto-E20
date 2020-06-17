package it.unipv.ingsw.progettoe20.server.database.dao;

import java.util.List;

/**
 * Interfaccia Dao.
 *
 * @param <T> paremetro di tipo
 */
public interface Dao<T> {

    T get(String id);

    List<T> getAll();

    void update(T t);

    void delete(T t);
}
