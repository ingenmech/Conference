package com.epam.evm.conference.command.general;

import com.epam.evm.conference.command.Command;
import com.epam.evm.conference.command.CommandResult;
import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.web.RequestContent;

import java.util.List;

public abstract class AbstractPaginationCommand<T> implements Command {

    private final static String PAGE_NUMBER = "pageNumber";
   // private final static String PAGE_SIZE = "pageSize";
    private final static String TOTAL_PAGE = "totalPage";
    private final static String DIRECTION = "direction";
    private final static String PREVIOUS = "previous";
    private final static String MESSAGE = "pageMessage";
    private final static String EMPTY_PAGE = "empty";
    private final static String NEXT = "next";

    private final String page;
    private final String keyList;
    private final int pageSize;

    protected AbstractPaginationCommand(String page, String keyList, int pageSize ) {
        this.page = page;
        this.keyList = keyList;
        this.pageSize = pageSize;
    }

    @Override
    public CommandResult execute(RequestContent content) throws ServiceException {

        String pageNumberRow = content.getParameter(PAGE_NUMBER);
        String direction = content.getParameter(DIRECTION);
       // String pageSizeRow = content.getParameter(PAGE_SIZE);
       // int pageSize = Integer.parseInt(pageSizeRow);
        int pageNumber = (pageNumberRow != null) ? Integer.parseInt(pageNumberRow) : 0;
        if (pageNumber < 0 || PREVIOUS.equals(direction) && pageNumber == 1) {
            pageNumber = 0;
        }
        if (PREVIOUS.equals(direction) && pageNumber != 0) {
            pageNumber -= 2;
        }

        int totalPage;
        if (pageNumber == 0 && pageSize != 0) {
            Long rowsSize = countRows();
            totalPage = (int) ((rowsSize + pageSize - 1) / pageSize);
            //totalPage = (int) Math.ceiling((double) imagesFound.Length / PageSize);
        } else {
            String totalPageRow = content.getParameter(TOTAL_PAGE);
            totalPage = Integer.parseInt(totalPageRow);
        }
        content.setAttribute(TOTAL_PAGE,totalPage);

        if (pageNumber <= totalPage) {
            int offset = pageNumber * pageSize;
            List<T> list = createService(content, pageSize, offset);
            content.setAttribute(keyList, list);
        } else {
            content.setAttribute(MESSAGE, EMPTY_PAGE);
        }
        pageNumber++;

        content.setAttribute(PAGE_NUMBER, pageNumber);
        return CommandResult.forward(page);
    }

    public abstract List<T> createService(RequestContent content, int limit, int offset) throws ServiceException;

    public abstract Long countRows() throws ServiceException;
}
