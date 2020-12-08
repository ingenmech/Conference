package com.epam.evm.conference.connection;

import com.epam.evm.conference.exception.ConnectionPoolException;
import com.epam.evm.conference.exception.DaoException;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final static int POOL_SIZE = 3;
    private final static AtomicBoolean INSTANCE_FLAG = new AtomicBoolean();
    private final static ReentrantLock INSTANCE_LOCK = new ReentrantLock();

    private static ConnectionPool instance = null;

    private final ReentrantLock connectionLock = new ReentrantLock();
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    private final Queue<ProxyConnection> availableConnection;
    private final Queue<ProxyConnection> connectionsInUse;


    private ConnectionPool() {
        this.availableConnection = new ArrayDeque<>();
        this.connectionsInUse = new ArrayDeque<>();
    }

    public static ConnectionPool getInstance() {

        if (!INSTANCE_FLAG.get()) {
            INSTANCE_LOCK.lock();
            // ConnectionPool local;
            try {
                if (!INSTANCE_FLAG.get()) {
                    // local = new ConnectionPool();
                    // local.init();
                    instance = new ConnectionPool();
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return instance;
    }

    private void init() {

        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection connection = createConnection();
            availableConnection.offer(connection);
        }
    }

    private ProxyConnection createConnection() {

        try {
            return connectionFactory.create();
        } catch (DaoException e) {
            throw new ConnectionPoolException("Can't create connection", e);
        }
    }

    public void returnConnection(ProxyConnection proxyConnection) {

        connectionLock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnection.offer(proxyConnection);
                connectionsInUse.remove(proxyConnection);
            }
        } finally {
            connectionLock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        if (!availableConnection.isEmpty()) {
            return availableConnection.poll();
        }

        try {
            ProxyConnection connection = connectionFactory.create();
            connectionsInUse.offer(connection);
            return connection;
        } catch (DaoException e) {
            throw new ConnectionPoolException("Can't create connection", e);
        }
    }

    public void shutDown() throws DaoException {

        availableConnection.addAll(connectionsInUse);
        connectionsInUse.clear();
        try {
            for (ProxyConnection connection : availableConnection) {
                connection.shutDown();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't close connection", e);
        }
    }
}
