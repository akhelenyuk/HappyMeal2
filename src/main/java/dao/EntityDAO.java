package dao;

import java.util.List;

public interface EntityDAO<T> {
    List<T> getAll();
    T get(int id);
    T get(String login);
    boolean add(T newObject);
    boolean update(int oldId, T newObject);
    int delete(int id);
}

