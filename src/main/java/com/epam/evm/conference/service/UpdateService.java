package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.TopicDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Topic;

public class UpdateService {

    private final DaoHelperFactory factory;

    public UpdateService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public void updateTopicStatus(Topic topic) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            TopicDao dao = helper.createTopicDao();
            dao.save(topic);

        } catch (DaoException e) {
            throw new ServiceException("Update topic error", e);
        }
    }

}
