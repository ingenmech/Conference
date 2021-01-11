package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.RequestDao;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.Request;
import com.epam.evm.conference.model.RequestStatus;
import com.epam.evm.conference.validator.FieldUtils;

import java.util.List;

public class RequestService {

    private final DaoHelperFactory factory;
    private final FieldUtils validator;

    public RequestService(DaoHelperFactory factory, FieldUtils validator) {
        this.factory = factory;
        this.validator = validator;
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

    public void saveRequest(Long sectionId, Long userId, String topic, RequestStatus status) throws ServiceException {

        if (!validator.isValidMediumLength(topic)){
            throw new FieldValidationException("Field topic does not match format");
        }

        Request request = new Request(null, sectionId, userId,  topic, status);

        try (DaoHelper helper = factory.create()) {
            RequestDao requestDao = helper.createTopicDao();
            requestDao.save(request);
        } catch (DaoException e) {
            throw new ServiceException("Save request error", e);
        }
    }

    public void updateRequestStatus(Long id, Long sectionId, Long userId, String topic, RequestStatus status) throws ServiceException {

        if (!validator.isValidMediumLength(topic)){
            throw new FieldValidationException("Field topic does not match format");
        }

        Request request = new Request(id, sectionId, userId, topic, status);

        try (DaoHelper helper = factory.create()) {
            RequestDao dao = helper.createTopicDao();
            dao.save(request);
        } catch (DaoException e) {
            throw new ServiceException("Update request error", e);
        }
    }
}
