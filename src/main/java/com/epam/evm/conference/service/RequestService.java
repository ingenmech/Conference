package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.RequestDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;

import java.util.List;

public class RequestService {

    private final DaoHelperFactory factory;

    public RequestService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Request> findAllRequestsWithUsersSectionsConferences(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao requestDao = helper.createTopicDao();
            return  requestDao.findEntityForPagination(limit, offset);

        } catch (DaoException e) {
            throw new ServiceException("Find all users requests error", e);
        }
    }

    public List<Request> findAllRequestsByUserId(Long userId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao requestDao = helper.createTopicDao();
            return requestDao.findAllRequestsByUserId(userId);

        } catch (DaoException e) {
            throw new ServiceException("Find all users requests by id error", e);
        }
    }

    public void removeRequestById(Long id) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao dao = helper.createTopicDao();
            dao.removeById(id);

        } catch (DaoException e) {
            throw new ServiceException("Remove request error", e);
        }

    }

    public void saveRequest(Request request) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao requestDao = helper.createTopicDao();
            requestDao.save(request);

        } catch (DaoException e) {
            throw new ServiceException("Save request error", e);
        }
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
