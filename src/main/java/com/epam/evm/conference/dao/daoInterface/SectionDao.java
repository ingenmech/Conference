package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Section;

import java.util.List;

public interface SectionDao extends Dao<Section> {

    List<Section> findSectionsByConferenceId(Long conferenceId) throws DaoException;

}
