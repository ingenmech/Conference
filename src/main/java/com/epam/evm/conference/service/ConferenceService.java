package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.ConferenceDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperCreator;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Conference;

public class ConferenceService {

    private final DaoHelperCreator factory;

    public ConferenceService(DaoHelperCreator factory) {
        this.factory = factory;
    }

    public void save(Conference conference) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            helper.startTransaction();
            ConferenceDao dao = helper.createConferenceDao();
            dao.save(conference);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException("Save error conference", e);
        }
    }
}
