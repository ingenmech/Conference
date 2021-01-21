package com.epam.evm.conference.dao.daoInterface;

import com.epam.evm.conference.exception.DaoException;
import com.epam.evm.conference.model.RequestDto;

import java.util.List;

public interface RequestDtoDao extends Dao<RequestDto> {
    List<RequestDto> findAllRequestsByUserId(Long userId) throws DaoException;
}
