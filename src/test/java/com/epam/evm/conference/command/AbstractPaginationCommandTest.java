package com.epam.evm.conference.command;

import com.epam.evm.conference.exception.ServiceException;
import com.epam.evm.conference.web.RequestContent;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPaginationCommandTest {
//
//    private final static String PAGE_NUMBER = "pageNumber";
//    private final static String DIRECTION = "direction";
//    private final static Integer EXPECTED_PAGE_NUMBER = 1;
//
//    protected abstract Command createCommand() throws ServiceException;
//
//    @Test
//    public void testExecuteShouldSetPageNumberWhenOpenFirstPage() throws ServiceException {
//        //when
//        Command command = createCommand();
//        Map<String, String[]> requestParams = new HashMap<>();
//        requestParams.put(PAGE_NUMBER, new String[]{null});
//        requestParams.put(DIRECTION, new String[]{null});
//        RequestContent content = new RequestContent(requestParams, new HashMap<>());
//        //when
//        command.execute(content);
//        Integer actualPageNumber = (Integer) content.getAttribute(PAGE_NUMBER);
//        //then
//        Assert.assertEquals(EXPECTED_PAGE_NUMBER, actualPageNumber);
//    }
//
//    @Test
//    public void testExecuteShouldSetPageNumberWhenPushNextPage() throws ServiceException {
//        //when
//        Command command = createCommand();
//        Map<String, String[]> requestParams = new HashMap<>();
//        requestParams.put(PAGE_NUMBER, new String[]{"1"});
//        requestParams.put(DIRECTION, new String[]{"next"});
//        RequestContent content = new RequestContent(requestParams, new HashMap<>());
//        Integer expectedPageNumber = 2;
//        //when
//        command.execute(content);
//        Integer actualPageNumber = (Integer) content.getAttribute(PAGE_NUMBER);
//        //then
//        Assert.assertEquals(expectedPageNumber, actualPageNumber);
//    }
//
//    @Test
//    public void testExecuteShouldSetPageNumberWhenPushPreviousPage() throws ServiceException {
//        //when
//        Command command = createCommand();
//        Map<String, String[]> requestParams = new HashMap<>();
//        requestParams.put(PAGE_NUMBER, new String[]{"2"});
//        requestParams.put(DIRECTION, new String[]{"previous"});
//        RequestContent content = new RequestContent(requestParams, new HashMap<>());
//        //when
//        command.execute(content);
//        Integer actualPageNumber = (Integer) content.getAttribute(PAGE_NUMBER);
//        //then
//        Assert.assertEquals(EXPECTED_PAGE_NUMBER, actualPageNumber);
//    }
//
//    @Test
//    public void testExecuteShouldSetPageNumberWhenOpenPreviousPageOnFirst() throws ServiceException {
//        //when
//        Command command = createCommand();
//        Map<String, String[]> requestParams = new HashMap<>();
//        requestParams.put(PAGE_NUMBER, new String[]{"1"});
//        requestParams.put(DIRECTION, new String[]{"previous"});
//        RequestContent content = new RequestContent(requestParams, new HashMap<>());
//        //when
//        command.execute(content);
//        Integer actualPageNumber = (Integer) content.getAttribute(PAGE_NUMBER);
//        //then
//        Assert.assertEquals(EXPECTED_PAGE_NUMBER, actualPageNumber);
//    }
//
//    @Test
//    public void testExecuteShouldSetPageNumberWhenPageNumberIsNegativeAndDirectionPrevious() throws ServiceException {
//        //when
//        Command command = createCommand();
//        Map<String, String[]> requestParams = new HashMap<>();
//        requestParams.put(PAGE_NUMBER, new String[]{"-1"});
//        requestParams.put(DIRECTION, new String[]{"previous"});
//        RequestContent content = new RequestContent(requestParams, new HashMap<>());
//        //when
//        command.execute(content);
//        Integer actualPageNumber = (Integer) content.getAttribute(PAGE_NUMBER);
//        //then
//        Assert.assertEquals(EXPECTED_PAGE_NUMBER, actualPageNumber);
//    }
//
//    @Test
//    public void testExecuteShouldSetPageNumberWhenPageNumberIsNegativeAndDirectionNext() throws ServiceException {
//        //when
//        Command command = createCommand();
//        Map<String, String[]> requestParams = new HashMap<>();
//        requestParams.put(PAGE_NUMBER, new String[]{"-1"});
//        requestParams.put(DIRECTION, new String[]{"next"});
//        RequestContent content = new RequestContent(requestParams, new HashMap<>());
//        //when
//        command.execute(content);
//        Integer actualPageNumber = (Integer) content.getAttribute(PAGE_NUMBER);
//        //then
//        Assert.assertEquals(EXPECTED_PAGE_NUMBER, actualPageNumber);
//    }
}
