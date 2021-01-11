<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.locale" var="loc"/>
<fmt:message bundle="${loc}" key="create.conference.name" var="conferenceName"/>
<fmt:message bundle="${loc}" key="create.section.name" var="sectionName"/>
<fmt:message bundle="${loc}" key="create.conference.date" var="confDate"/>
<fmt:message bundle="${loc}" key="create.conference.time" var="confTime"/>
<fmt:message bundle="${loc}" key="update.conference.update" var="conferenceUpdate"/>
<fmt:message bundle="${loc}" key="create.conference.holder" var="conferenceHolder"/>
<fmt:message bundle="${loc}" key="create.section.holder" var="sectionHolder"/>
<fmt:message bundle="${loc}" key="create.conference.message" var="message"/>
<c:set var="query" value="${pageContext.request.queryString}"/>
<c:if test="${query ne 'command=en' and query ne 'command=ru' and query ne 'command=by'}">
    <c:set var="page" value="${query}" scope="session"/>
</c:if>
<section class="column-main">
    <div class="accept">
        <form method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="adminUpdateConference"/>
            <div>
                <label for="name-conference">${conferenceName}</label>
                <input id="name-conference" type="text" name="conference" value="${conference.name}"
                       placeholder="${conferenceHolder}"
                       maxlength="150" required>
                <input id="id-conference" type="hidden" name="conferenceId" value="${conference.id}">
            </div>
            <div class="section">
                <label for="section">${sectionName}</label>
                <c:forEach var="section" items="${conference.sections}" varStatus="status">
                    <div>
                        <input id="id-section" type="hidden" name="sectionId" value="${section.id}">
                        <input id="section" type="text" name="section"
                               value="${section.name}" placeholder="${sectionHolder}"
                        maxlength="150" required>
                    </div>
                </c:forEach>
            </div>
            <div>
                <label for="date">${confDate}</label>
                <input id="date" type="date" name="date" value="${conferenceDate}" required>
                <label for="time">${confTime}</label>
                <input id="time" type="time" name="time" value="${conferenceTime}" required>
                <input id="sendConf" type="submit" value="${conferenceUpdate}">
            </div>
        </form>
    </div>
</section>
