package by.etc.finaltask.controller.listener;

import by.etc.finaltask.dao.connector.ConnectionPool;
import by.etc.finaltask.dao.connector.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitializationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            throw new ConnectionPoolException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.dispose();
    }
}
