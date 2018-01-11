package com.khelenyuk.dao.mysql.impl;

import com.khelenyuk.dao.CrudDAO;

import java.util.List;

public class CrudDaoImpl<T> implements CrudDAO<T> {
    @Override
    public List<T> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T newEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(int oldId, T newEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }
}
