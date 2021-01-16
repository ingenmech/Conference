package com.epam.evm.conference.service;

import com.epam.evm.conference.dao.daoInterface.*;
import com.epam.evm.conference.dao.helper.DaoHelper;
import com.epam.evm.conference.dao.helper.DaoHelperFactory;
import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.exception.FieldValidationException;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.model.*;
import com.epam.evm.conference.validator.FieldUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            return requestDao.findEntityForPagination(limit, offset);
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

    public Long countRows() throws ServiceException {
        try (DaoHelper helper = factory.create()) {
            RequestDao requestDao = helper.createTopicDao();
            return requestDao.countRows();
        } catch (DaoException e) {
            throw new ServiceException("Count request rows error", e);
        }
    }

    public List<RequestDto> findAllRequestsDtoByUserId(Long userId) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao requestDao = helper.createTopicDao();
            SectionDao sectionDao = helper.createSectionDao();
            ConferenceDao conferenceDao = helper.createConferenceDao();
            UserDao userDao = helper.createUserDao();

            List<Request> requests = requestDao.findAllRequestsByUserId(userId);
            return createRequestsDtoList(requests, sectionDao, conferenceDao, userDao);

        } catch (DaoException e) {
            throw new ServiceException("Find all users requests error", e);
        }
    }

    public List<RequestDto> findAllRequestsDtoWithUsersSectionConferences(int limit, int offset) throws ServiceException {

        try (DaoHelper helper = factory.create()) {

            RequestDao requestDao = helper.createTopicDao();
            SectionDao sectionDao = helper.createSectionDao();
            ConferenceDao conferenceDao = helper.createConferenceDao();
            UserDao userDao = helper.createUserDao();

            List<Request> requests = requestDao.findEntityForPagination(limit, offset);
            return createRequestsDtoList(requests, sectionDao, conferenceDao, userDao);

        } catch (DaoException e) {
            throw new ServiceException("Find all users requests error", e);
        }
    }

    private List<RequestDto> createRequestsDtoList(List<Request> requests, SectionDao sectionDao,
                                                   ConferenceDao conferenceDao, UserDao userDao) throws DaoException, ServiceException {
        List<RequestDto> requestsDto = new ArrayList<>();
        for (Request request : requests) {
            Long sectionId = request.getSectionId();
            Optional<Section> sectionOptional = sectionDao.findById(sectionId);
            if (sectionOptional.isEmpty()) {
                throw new ServiceException("Cant find section");
            }
            Section section = sectionOptional.get();
            Long conferenceId = section.getConferenceId();
            Optional<Conference> conferenceOptional = conferenceDao.findById(conferenceId);
            if (conferenceOptional.isEmpty()) {
                throw new ServiceException("Cant find conference");
            }
            Conference conference = conferenceOptional.get();
            Long userId = request.getUserId();
            Optional<User> userOptional = userDao.findById(userId);
            if (userOptional.isEmpty()) {
                throw new ServiceException("Cant find user");
            }
            User user = userOptional.get();
            RequestDto requestDto = createRequestDto(request, conference, section, user);
            requestsDto.add(requestDto);
        }
        return requestsDto;
    }

    private RequestDto createRequestDto(Request request, Conference conference, Section section, User user) {
        Long id = request.getId();
        Long sectionId = request.getSectionId();
        Long userId = request.getUserId();
        String topic = request.getTopic();
        RequestStatus status = request.getStatus();

        String conferenceName = conference.getName();
        String sectionName = section.getName();
        SectionStatus sectionStatus = section.getStatus();
        String userLogin = user.getLogin();
        LocalDateTime dateTime = conference.getDate();
        return new RequestDto(id, sectionId, userId, topic, status,
                conferenceName, sectionName, userLogin, sectionStatus, dateTime);
    }

    public void saveRequest(Long sectionId, Long userId, String topic, RequestStatus status) throws ServiceException {

        if (!validator.isValidMediumLength(topic)) {
            throw new FieldValidationException("Field topic does not match format");
        }
        Request request = new Request(null, sectionId, userId, topic, status);

        try (DaoHelper helper = factory.create()) {
            RequestDao requestDao = helper.createTopicDao();
            requestDao.save(request);
        } catch (DaoException e) {
            throw new ServiceException("Save request error", e);
        }
    }

    public void updateRequestStatus(Long id, Long sectionId, Long userId, String topic, RequestStatus status) throws ServiceException {

        if (!validator.isValidMediumLength(topic)) {
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
