package com.epam.evm.conference.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeCompareTag extends TagSupport {

    private LocalDateTime dateTime;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int doStartTag() throws JspException {

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime actualDateTime = LocalDateTime.now(zoneId);
        boolean isBefore = actualDateTime.isBefore(dateTime);
        try {
            pageContext.getOut().write(String.valueOf(isBefore));
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
