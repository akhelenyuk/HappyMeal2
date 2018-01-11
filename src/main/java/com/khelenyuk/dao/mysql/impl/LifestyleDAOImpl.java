package com.khelenyuk.dao.mysql.impl;

import com.khelenyuk.dao.LifestyleDAO;
import com.khelenyuk.model.Lifestyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LifestyleDAOImpl extends CrudDaoImpl<Lifestyle> implements LifestyleDAO {
    private static final Logger logger = LogManager.getLogger(LifestyleDAOImpl.class);
    private final String TABLE = "lifestyle";
    private String selectAll = "SELECT * FROM " + TABLE;




    @Override
    public Lifestyle get(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Lifestyle newEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(int oldId, Lifestyle newEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }
}
