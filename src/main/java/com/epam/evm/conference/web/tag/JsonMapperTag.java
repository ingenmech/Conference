package com.epam.evm.conference.web.tag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class JsonMapperTag extends TagSupport {

    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public int doStartTag() throws JspException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String jsonObject = mapper.writeValueAsString(object);
            pageContext.getOut().write(jsonObject);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
