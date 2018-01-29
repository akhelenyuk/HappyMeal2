package com.khelenyuk.service;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.ActivityDao;
import com.khelenyuk.dao.factory.DaoFactory;
import com.khelenyuk.model.Activity;
import com.khelenyuk.service.factory.ServiceFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IActivityServiceTest {
    @InjectMocks
    private IActivityService activityServiceMock = ServiceFactory.getActivityService();
    @Mock
    private ActivityDao activityDaoMock = DaoFactory.getActivityMethods();

    @Mock
    private Activity activity;

    @Before
    public void init() {
        activity.setName("activity");
    }

    @Test
    public void checkActivityExist() throws SQLException {

        when(activityDaoMock.get("activity")).thenReturn(activity);

        assertTrue(activityServiceMock.checkActivityExist("activity"));


    }
}