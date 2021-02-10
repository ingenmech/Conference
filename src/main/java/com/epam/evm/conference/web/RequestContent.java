package com.epam.evm.conference.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestContent that = (RequestContent) o;
        if (isSessionInvalidate != that.isSessionInvalidate) {
            return false;
        }
        if (!Objects.equals(parameters, that.parameters)) {
            return false;
        }
        if (!Objects.equals(attributes, that.attributes)) {
            return false;
        }
        return Objects.equals(sessionAttributes, that.sessionAttributes);
    }

    @Override
    public int hashCode() {
        int result = parameters != null ? parameters.hashCode() : 0;
        result = 31 * result + attributes.hashCode();
        result = 31 * result + (sessionAttributes != null ? sessionAttributes.hashCode() : 0);
        result = 31 * result + (isSessionInvalidate ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequestContent{" +
                "parameters=" + parameters +
                ", attributes=" + attributes +
                ", sessionAttributes=" + sessionAttributes +
                ", isSessionInvalidate=" + isSessionInvalidate +
                '}';
    }
}
