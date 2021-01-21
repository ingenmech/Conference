package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Section;

import java.util.List;

public interface SectionPersistentDao extends PersistentDao<Section> {

    List<Section> findSectionsByConferenceId(Long conferenceId) throws DaoException;
    List<Section> findActualSections() throws DaoException;

}
