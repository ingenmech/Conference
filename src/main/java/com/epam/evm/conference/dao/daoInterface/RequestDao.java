package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Request;

import java.util.List;

public interface RequestDao extends Dao<Request>{

    List<Request> findAllRequestsByUserId(Long userId) throws DaoException;
}
