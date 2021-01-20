package com.epam.evm.conference.connection;

import com.epam.evm.conference.exception.ConnectionPoolException;
import com.epam.evm.conference.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final static int POOL_SIZE = 5;
    private final static AtomicBoolean CREATED = new AtomicBoolean();
    private final static ReentrantLock INSTANCE_LOCK = new ReentrantLock();
    private final static ReentrantLock CONNECTION_LOCK = new ReentrantLock();
    private final static Semaphore SEMAPHORE = new Semaphore(POOL_SIZE, true);

    private static ConnectionPool instance = null;

    private final ConnectionFactory connectionFactory = new ConnectionFactory();
    private final Queue<ProxyConnection> availableConnection;
    private final Queue<ProxyConnection> connectionsInUse;


    private ConnectionPool() {
        this.availableConnection = new ArrayDeque<>();
        this.connectionsInUse = new ArrayDeque<>();
    }

    public static ConnectionPool getInstance() {

        if (!CREATED.get()) {
            INSTANCE_LOCK.lock();
            ConnectionPool local;
            try {
                if (!CREATED.get()) {
                    local = new ConnectionPool();
                    local.init();
                    instance = local;
                    CREATED.set(true);
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return instance;
    }



    private void init() {

        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = connectionFactory.create();
            ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            availableConnection.offer(proxyConnection);
        }
    }


    public ProxyConnection getConnection() {

        CONNECTION_LOCK.lock();
        try {
            SEMAPHORE.acquire();

            ProxyConnection connection = availableConnection.poll();
            connectionsInUse.offer(connection);
            return connection;
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Connection is interrupted", e);
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    public void returnConnection(ProxyConnection proxyConnection) {

        CONNECTION_LOCK.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnection.offer(proxyConnection);
                connectionsInUse.remove(proxyConnection);
                SEMAPHORE.release();
            }
        } finally {
            CONNECTION_LOCK.unlock();
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
