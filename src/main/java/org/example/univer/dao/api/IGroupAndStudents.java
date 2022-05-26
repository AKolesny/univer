package org.example.univer.dao.api;

import java.util.List;

public interface IGroupAndStudents<T> {
    void create(T item);
    List<T> get();
    void delete(T item);
}
