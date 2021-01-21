package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.RequestDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RequestDtoDao extends Dao<RequestDto> {
    List<RequestDto> findAllRequestsByUserId(Long userId) throws DaoException;
    List<RequestDto> findActualRequests(LocalDateTime actualDate, int limit, int offset) throws DaoException;
}
