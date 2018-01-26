package com.khelenyuk.dao;

import java.util.List;

public interface CrudDao<T> {
    List<T> getAll();
    T get(int id);
    boolean add(T newEntity);
    boolean update(int oldId, T newEntity);
    boolean delete(int id);

}

