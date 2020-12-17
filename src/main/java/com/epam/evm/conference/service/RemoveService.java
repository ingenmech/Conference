package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.RequestDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;

public class RemoveService {

    private final DaoHelperFactory factory;

    public RemoveService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public void removeRequestById(Long id) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao dao = helper.createTopicDao();
            dao.removeById(id);

        } catch (DaoException e) {
            throw new ServiceException("Remove request error", e);
        }

    }
}
