package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.TopicDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;

public class RemoveService {

    private final DaoHelperFactory factory;

    public RemoveService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public void removeTopicById(Long id) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            TopicDao dao = helper.createTopicDao();
            dao.removeById(id);

        } catch (DaoException e) {
            throw new ServiceException("Remove topic error", e);
        }

    }
}
