package org.example.univer.dao.api;

import java.util.List;

public interface IDao<T, ID>{
    void create(T item);
    List<T> get();
    T update(ID id, T item);
    void delete(ID id);
}
