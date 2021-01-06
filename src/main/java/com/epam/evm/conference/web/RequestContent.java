package com.epam.evm.conference.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestContent {

    private final Map<String, String[]> parameters;
    private final Map<String, Object> attributes;
    private final Map<String, Object> sessionAttributes;
    private boolean isSessionInvalidate;

    public RequestContent(Map<String, String[]> parameters, Map<String, Object> sessionAttributes) {
        this.parameters = parameters;
        this.sessionAttributes = sessionAttributes;
        attributes = new HashMap<>();
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public Set<String> keySetAttribute() {
        return attributes.keySet();
    }

    public void setSessionAttribute(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    public Set<String> keySetSessionAttribute() {
        return sessionAttributes.keySet();
    }

    public void setSessionInvalidate(boolean isSessionInvalidate) {
        this.isSessionInvalidate = isSessionInvalidate;
    }

    public boolean isSessionInvalidate() {
        return isSessionInvalidate;
    }

    public String[] getParameterValues(String key) {
        if (!parameters.containsKey(key)) {
            return null;
        }
        return parameters.get(key);
    }

    public String getParameter(String key) {
        if (!parameters.containsKey(key)) {
            return null;
        }
        return parameters.get(key)[0];
    }
}
