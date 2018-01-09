package com.khelenyuk.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static final String DATASOURCE_NAME = "jdbc/dbpool";
    private static DataSource dataSource;
    static{
        try {
            Context initContext = new InitialContext();
            Context context = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) context.lookup(DATASOURCE_NAME);
        } catch (NamingException e1) {
            e1.printStackTrace();
        }
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

}
