package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.RequestDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;

public class UpdateService {

    private final DaoHelperFactory factory;

    public UpdateService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public void updateRequestStatus(Request request) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao dao = helper.createTopicDao();
            dao.save(request);

        } catch (DaoException e) {
            throw new ServiceException("Update request error", e);
        }
    }

}
