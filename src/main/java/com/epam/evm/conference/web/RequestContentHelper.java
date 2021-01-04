package com.epam.evm.conference.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestContentHelper {

    public RequestContent create(HttpServletRequest request){

        Map<String, String[]> requestParameters = request.getParameterMap();
        Map<String, Object> sessionAttributes = createSessionAttributes(request);

        return new RequestContent(requestParameters, sessionAttributes);
    }

    private Map<String, Object> createSessionAttributes(HttpServletRequest request) {
        Map<String, Object> requestSessionAttributes = new HashMap<>();
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();

        while (sessionAttributeNames.hasMoreElements()){
            String key = sessionAttributeNames.nextElement();
            Object value = session.getAttribute(key);
            requestSessionAttributes.put(key, value);
        }
        return  requestSessionAttributes;
    }

    public void initRequest(RequestContent content, HttpServletRequest request) {

        for (String key : content.keySetAttribute()) {
            request.setAttribute(key, content.getAttribute(key));
        }

        HttpSession session = request.getSession();
        if (!content.isSessionInvalidate()) {
            for (String key : content.keySetSessionAttribute()) {
                session.setAttribute(key, content.getSessionAttribute(key));
            }
        } else {
            session.invalidate();
        }
    }
}
