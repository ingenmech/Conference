package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Section;

import java.util.List;

/**
 * The {@code SectionPersistentDao} interface extends PersistentDao interface
 * and represents specific method signature for find sections in database.
 *
 * @version 1.0
 */
public interface SectionPersistentDao extends PersistentDao<Section> {

    List<Section> findSectionsByConferenceId(Long conferenceId) throws DaoException;

    List<Section> findActualSections() throws DaoException;

}
