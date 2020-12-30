package com.epam.evm.conference.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateParserTag extends TagSupport {

    private LocalDateTime dateTime;
    private String pattern;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setPattern(String pattern) {
        this.pattern =pattern;
    }

    @Override
    public int doStartTag() throws JspException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String dateTimeFormatted = formatter.format(dateTime);
        try {
            pageContext.getOut().write(dateTimeFormatted);
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
