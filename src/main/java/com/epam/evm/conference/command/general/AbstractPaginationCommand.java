package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public abstract class AbstractPaginationCommand<T> implements Command {

    private final String page;
    private final String keyList;
    private final int elementsLimitNumber;
    private final static String PAGE_NUMBER = "pageNumber";
    private final static String ELEMENTS_NUMBER = "elementNumber";
    private final static String DIRECTION = "direction";
    private final static String PREVIOUS = "previous";
    private final static String NEXT = "next";

    protected AbstractPaginationCommand(String page, String keyList, int elementsLimitNumber) {
        this.page = page;
        this.keyList = keyList;
        this.elementsLimitNumber = elementsLimitNumber;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String pageNumberRow = content.getParameter(PAGE_NUMBER);
        String direction = content.getParameter(DIRECTION);

        int pageNumber = (pageNumberRow != null) ? Integer.parseInt(pageNumberRow) : 0;
        if (pageNumber < 0 || PREVIOUS.equals(direction) && pageNumber == 1) {
            pageNumber = 0;
        }
        if (PREVIOUS.equals(direction) && pageNumber != 0) {
            pageNumber -= 2;
        }
        int offset = pageNumber * elementsLimitNumber;
        List<T> list = createService(content, offset);
        if (!list.isEmpty()) {
            content.setAttribute(keyList, list);
            pageNumber++;
        }

        content.setAttribute(PAGE_NUMBER, pageNumber);
        content.setAttribute(ELEMENTS_NUMBER, elementsLimitNumber);

        return CommandResult.forward(page);
    }

    public abstract List<T> createService(RequestContent content, int offset) throws ServiceException;
}
