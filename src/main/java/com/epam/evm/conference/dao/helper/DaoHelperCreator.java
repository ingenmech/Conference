package com.epam.evm.conference.dao.helper;

import com.epam.evm.conference.connection.ConnectionPool;

public class DaoHelperCreator {

    public DaoHelper create() {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
