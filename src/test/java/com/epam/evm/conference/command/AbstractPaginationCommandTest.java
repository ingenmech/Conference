package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.web.RequestContent;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPaginationCommandTest<T> {

    private final static String PAGE_NUMBER = "pageNumber";
    private final static String DIRECTION = "direction";
    private final static String PAGE_SIZE = "pageSize";
    private final static String TOTAL_PAGE = "totalPage";
    private final static String MESSAGE = "pageMessage";
    private final static String EMPTY_PAGE = "empty";

    private final List<T> list;
    private final String keyList;

    protected AbstractPaginationCommandTest(List<T> list, String keyList) {
        this.list = list;
        this.keyList = keyList;
    }

    protected abstract Command createCommand() throws ServiceException;

    @Test
    public void testExecuteShouldSetPageNumberWhenOpenFirstPage() throws ServiceException {
        //given
        Command command = createCommand();
        Map<String, String[]> requestParams = new HashMap<>();
        requestParams.put(PAGE_NUMBER, new String[]{null});
        requestParams.put(DIRECTION, new String[]{null});
        requestParams.put(PAGE_SIZE, new String[]{"6"});
        RequestContent content = new RequestContent(requestParams, new HashMap<>());

        RequestContent expectedContent = new RequestContent(requestParams, new HashMap<>());
        expectedContent.setAttribute(PAGE_NUMBER, 1);
        expectedContent.setAttribute(TOTAL_PAGE, 2);
        expectedContent.setAttribute(keyList, list);
        expectedContent.setAttribute(PAGE_SIZE, 6);
        //when
        command.execute(content);
        //then
        Assert.assertEquals(expectedContent, content);
    }

    @Test
    public void testExecuteShouldSetPageNumberWhenPushNextPage() throws ServiceException {
        //given
        Map<String, String[]> requestParams = new HashMap<>();
        requestParams.put(PAGE_NUMBER, new String[]{"1"});
        requestParams.put(DIRECTION, new String[]{"next"});
        requestParams.put(TOTAL_PAGE, new String[]{"2"});
        requestParams.put(PAGE_SIZE, new String[]{"6"});
        RequestContent actualContent = new RequestContent(requestParams, new HashMap<>());

        RequestContent expectedContent = new RequestContent(requestParams, new HashMap<>());
        expectedContent.setAttribute(PAGE_NUMBER, 2);
        expectedContent.setAttribute(TOTAL_PAGE, 2);
        expectedContent.setAttribute(keyList, list);
        expectedContent.setAttribute(PAGE_SIZE, 6);
        Command command = createCommand();
        //when
        command.execute(actualContent);
        //then
        Assert.assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testExecuteShouldSetPageNumberWhenPushPreviousPage() throws ServiceException {
        //given
        Command command = createCommand();
        Map<String, String[]> requestParams = new HashMap<>();
        requestParams.put(PAGE_NUMBER, new String[]{"2"});
        requestParams.put(DIRECTION, new String[]{"previous"});
        requestParams.put(PAGE_SIZE, new String[]{"6"});
        RequestContent content = new RequestContent(requestParams, new HashMap<>());

        RequestContent expectedContent = new RequestContent(requestParams, new HashMap<>());
        expectedContent.setAttribute(PAGE_NUMBER, 1);
        expectedContent.setAttribute(TOTAL_PAGE, 2);
        expectedContent.setAttribute(keyList, list);
        expectedContent.setAttribute(PAGE_SIZE, 6);
        //when
        command.execute(content);
        //then
        Assert.assertEquals(expectedContent, content);
    }

    @Test
    public void testExecuteShouldSetPageNumberWhenOpenPreviousPageOnFirst() throws ServiceException {
        //given
        Command command = createCommand();
        Map<String, String[]> requestParams = new HashMap<>();
        requestParams.put(PAGE_NUMBER, new String[]{"1"});
        requestParams.put(DIRECTION, new String[]{"previous"});
        requestParams.put(PAGE_SIZE, new String[]{"6"});
        RequestContent content = new RequestContent(requestParams, new HashMap<>());

        RequestContent expectedContent = new RequestContent(requestParams, new HashMap<>());
        expectedContent.setAttribute(PAGE_NUMBER, 1);
        expectedContent.setAttribute(TOTAL_PAGE, 2);
        expectedContent.setAttribute(keyList, list);
        expectedContent.setAttribute(PAGE_SIZE, 6);
        //when
        command.execute(content);
        //then
        Assert.assertEquals(expectedContent, content);
    }

    @Test
    public void testExecuteShouldReturnEmptyPageMessageWhenPageNumberLargerThenTotalPage() throws ServiceException {
        //given
        Command command = createCommand();
        Map<String, String[]> requestParams = new HashMap<>();
        requestParams.put(PAGE_NUMBER, new String[]{"1"});
        requestParams.put(DIRECTION, new String[]{"previous"});
        requestParams.put(PAGE_SIZE, new String[]{"6"});
        RequestContent content = new RequestContent(requestParams, new HashMap<>());

        RequestContent expectedContent = new RequestContent(requestParams, new HashMap<>());
        expectedContent.setAttribute(PAGE_NUMBER, 1);
        expectedContent.setAttribute(TOTAL_PAGE, 2);
        expectedContent.setAttribute(keyList, list);
        expectedContent.setAttribute(PAGE_SIZE, 6);
        //when
        command.execute(content);
        //then
        Assert.assertEquals(expectedContent, content);
    }

    @Test
    public void testExecuteShouldSetPageNumberWhenPageNumberIsNegativeAndDirectionPrevious() throws ServiceException {
        //given
        Command command = createCommand();
        Map<String, String[]> requestParams = new HashMap<>();
        requestParams.put(PAGE_NUMBER, new String[]{"-1"});
        requestParams.put(DIRECTION, new String[]{"previous"});
        requestParams.put(PAGE_SIZE, new String[]{"6"});
        RequestContent content = new RequestContent(requestParams, new HashMap<>());

        RequestContent expectedContent = new RequestContent(requestParams, new HashMap<>());
        expectedContent.setAttribute(PAGE_NUMBER, 1);
        expectedContent.setAttribute(TOTAL_PAGE, 2);
        expectedContent.setAttribute(keyList, list);
        expectedContent.setAttribute(PAGE_SIZE, 6);
        //when
        command.execute(content);
        //then
        Assert.assertEquals(expectedContent, content);
    }

    @Test
    public void testExecuteShouldSetPageNumberWhenPageNumberIsNegativeAndDirectionNext() throws ServiceException {
        //given
        Command command = createCommand();
        Map<String, String[]> requestParams = new HashMap<>();
        requestParams.put(PAGE_NUMBER, new String[]{"3"});
        requestParams.put(DIRECTION, new String[]{"next"});
        requestParams.put(TOTAL_PAGE, new String[]{"2"});
        requestParams.put(PAGE_SIZE, new String[]{"6"});
        RequestContent content = new RequestContent(requestParams, new HashMap<>());

        RequestContent expectedContent = new RequestContent(requestParams, new HashMap<>());
        expectedContent.setAttribute(PAGE_NUMBER, 4);
        expectedContent.setAttribute(TOTAL_PAGE, 2);
        expectedContent.setAttribute(MESSAGE, EMPTY_PAGE);
        expectedContent.setAttribute(PAGE_SIZE, 6);
        //when
        command.execute(content);
        //then
        Assert.assertEquals(expectedContent, content);
    }
}
