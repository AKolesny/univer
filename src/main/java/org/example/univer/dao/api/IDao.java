package org.example.univer.dao.api;

import java.util.List;

public interface IDao<T, ID>{
    long create(T item);

    List<T> getAll();

    T get(long id);

    void update(T item);

    void delete(ID id);
}
