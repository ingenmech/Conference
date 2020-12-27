package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Conference;

import java.util.List;

public interface ConferenceDao extends Dao<Conference>{

    List<Conference> findConferenceWithLimit(int limit, int offset) throws DaoException;

}
