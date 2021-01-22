package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.RequestDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The {@code RequestDtoDao} interface extends Dao interface
 * and represents specific method signature for find RequestDto in database.
 *
 * @version 1.0
 */
public interface RequestDtoDao extends Dao<RequestDto> {
    List<RequestDto> findAllRequestsByUserId(Long userId) throws DaoException;

    List<RequestDto> findActualRequests(LocalDateTime actualDate, int limit, int offset) throws DaoException;
}
