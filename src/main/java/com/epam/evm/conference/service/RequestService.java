package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.*;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.*;
import com.epam.evm.conference.utils.FieldUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class RequestService {

    private final DaoHelperFactory factory;

    public RequestService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<RequestDto> findActualRequestsWithUsersSectionsConferences(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            RequestDtoDao requestDao = helper.createRequestDtoDao();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime actualDateTime = LocalDateTime.now(zoneId);
            return requestDao.findActualRequests(actualDateTime, limit, offset);
        } catch (DaoException e) {
            throw new ServiceException("Find all users requests error", e);
        }
    }

    public List<RequestDto> findAllRequestsByUserId(Long userId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {
            RequestDtoDao requestDao = helper.createRequestDtoDao();
            return requestDao.findAllRequestsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Find all users requests by id error", e);
        }
    }

    public Long countRows() throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            RequestPersistentDao requestDao = helper.createRequestPersistentDao();
            return requestDao.countRows();
        } catch (DaoException e) {
            throw new ServiceException("Count request rows error", e);
        }
    }

    public void saveRequest(Long sectionId, Long userId, String topic, RequestStatus status) throws ServiceException {

        if (!FieldUtils.isValidLength(topic, FieldUtils.MID_SIZE)) {
            throw new FieldValidationException("Field topic does not match format");
        }
        Request request = new Request(null, sectionId, userId, topic, status);

        try (DaoHelper helper = factory.create()) {
            RequestPersistentDao requestDao = helper.createRequestPersistentDao();
            requestDao.save(request);
        } catch (DaoException e) {
            throw new ServiceException("Save request error", e);
        }
    }

    public void updateRequestStatus(Long id, Long sectionId, Long userId, String topic, RequestStatus status) throws ServiceException {

        if (!FieldUtils.isValidLength(topic, FieldUtils.MID_SIZE)) {
            throw new FieldValidationException("Field topic does not match format");
        }
        Request request = new Request(id, sectionId, userId, topic, status);

        try (DaoHelper helper = factory.create()) {
            RequestPersistentDao dao = helper.createRequestPersistentDao();
            dao.save(request);
        } catch (DaoException e) {
            throw new ServiceException("Update request error", e);
        }
    }
}
