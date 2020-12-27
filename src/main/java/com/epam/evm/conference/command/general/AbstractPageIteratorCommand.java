package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.service.FindService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class AbstractPageIteratorCommand<T> implements Command {

    private final String page;
    private final String keyList;
    private final int elementsLimitNumber;
    private final static String PAGE_NUMBER = "pageNumber";
    private final static String ELEMENTS_NUMBER = "elementNumber";
    private final static String DIRECTION = "direction";
    private final static String PREVIOUS = "previous";
    private final static String NEXT = "next";

    protected AbstractPageIteratorCommand(String page, String keyList, int elementsLimitNumber){
        this.page = page;
        this.keyList = keyList;
        this.elementsLimitNumber = elementsLimitNumber;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String pageNumberRow = request.getParameter(PAGE_NUMBER);
        int pageNumber = (pageNumberRow != null) ? Integer.parseInt(pageNumberRow) : 0;
        if (pageNumber < 0) {
            pageNumber = 0;
        }
        String direction = request.getParameter(DIRECTION);
        if (PREVIOUS.equals(direction) && pageNumber != 0){
            pageNumber-=2;
        }
        int offset = pageNumber * elementsLimitNumber;
        List<T> list = createService(request, offset);
        if (!list.isEmpty()) {
            request.setAttribute(keyList, list);
            pageNumber++;
        }

        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(ELEMENTS_NUMBER, elementsLimitNumber);

        return CommandResult.forward(page);
    }

    public abstract List<T> createService(HttpServletRequest request, int offset) throws ServiceException;
}
