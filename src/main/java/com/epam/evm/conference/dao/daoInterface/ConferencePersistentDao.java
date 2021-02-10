package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Conference;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The {@code ConferencePersistentDao} interface extends PersistentDao interface
 * and represents specific method signatures for find conferences in database.
 *
 * @version 1.0
 */
public interface ConferencePersistentDao extends PersistentDao<Conference> {
    List<Conference> findActualConferences(LocalDateTime actualDate) throws DaoException;

    List<Conference> findActualConferencesForPagination(LocalDateTime actualDate, int limit, int offset) throws DaoException;

    Long countActualConference(LocalDateTime actualDate) throws DaoException;
}
