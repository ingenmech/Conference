package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.Topic;

import java.util.List;

public interface TopicDao extends Dao<Topic>{

    List<Topic> findAllTopicsByUserId(Long userId) throws DaoException;
}
