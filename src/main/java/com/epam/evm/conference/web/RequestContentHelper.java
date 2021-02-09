package com.epam.evm.conference.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestContentHelper {

    private final static String AMPERSAND = "&";
    private final static String AMPERSAND_REPLACEMENT = "&amp;";
    private final static String LESS_THAN = "<";
    private final static String LESS_THAN_REPLACEMENT = "&lt;";
    private final static String GREATER_THAN = ">";
    private final static String GREATER_THAN_REPLACEMENT = "&gt;";

    public RequestContent create(HttpServletRequest request) {
        Map<String, String[]> requestParameters = normalizeParameters(request.getParameterMap()); //request.getParameterMap();
        Map<String, Object> sessionAttributes = createSessionAttributes(request);
        return new RequestContent(requestParameters, sessionAttributes);
    }

    private Map<String, String[]> normalizeParameters(Map<String, String[]> requestParameters) {
        Map<String, String[]> normalizedParameters = new HashMap<>();
        for (String key : requestParameters.keySet()) {
            String[] normalizedParams = fillParams(requestParameters.get(key));
            normalizedParameters.put(key, normalizedParams);
        }
        return normalizedParameters;
    }

    private String[] fillParams(String[] params) {
        if (params == null) {
            return new String[0];
        }
        String[] normalizedParams = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                normalizedParams[i] = params[i]
                        .replace(AMPERSAND, AMPERSAND_REPLACEMENT)
                        .replace(LESS_THAN, LESS_THAN_REPLACEMENT)
                        .replace(GREATER_THAN, GREATER_THAN_REPLACEMENT);
            }
        }
        return normalizedParams;
    }

    private Map<String, Object> createSessionAttributes(HttpServletRequest request) {
        Map<String, Object> requestSessionAttributes = new HashMap<>();
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();

        while (sessionAttributeNames.hasMoreElements()) {
            String key = sessionAttributeNames.nextElement();
            Object value = session.getAttribute(key);
            requestSessionAttributes.put(key, value);
        }
        return requestSessionAttributes;
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
