package it.unipv.ingsw.progettoe20.server.database.dao;

import java.util.List;

public interface Dao<T> {

    T get(String id);

    List<T> getAll();

    void update(T t);

    void delete(T t);
}
