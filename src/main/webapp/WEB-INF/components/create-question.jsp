<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cp" uri="/WEB-INF/tld/parserLocalDateTime.tld" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/isBeforeActualDate.tld" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.locale" var="loc"/>
<fmt:message bundle="${loc}" key="accept.request.table.user" var="user"/>
<fmt:message bundle="${loc}" key="question.page.question" var="userQuestion"/>
<fmt:message bundle="${loc}" key="message.page.placeholder" var="holder"/>
<fmt:message bundle="${loc}" key="question.page.send" var="send"/>
<fmt:message bundle="${loc}" key="request.choose.conference" var="chooseConference"/>
<fmt:message bundle="${loc}" key="date.time.format" var="dateTimeFormat"/>
<c:set var="page" value="userCreateQuestionPage" scope="session"/>
<section class="column-main">
    <div class="send-request">
        <div class="container">
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="userCreateQuestion"/>
                <label for="conference">${chooseConference}</label>
                <select id="conference" name="conferenceId">
                    <c:forEach var="conferences" items="${conferenceList}" varStatus="status">
                        <c:set var="isBefore">
                            <ctg:is-before-date dateTime="${conferences.date}"/>
                        </c:set>
                        <c:if test="${ isBefore }">
                            <c:if test="${ not empty selectedConferenceId and selectedConferenceId eq conferences.id}" var="isSelected">
                                <option selected="selected" value="${conferences.id}">
                                    <cp:parse-local-date pattern="${dateTimeFormat}" dateTime="${conferences.date}"/> -
                                    <c:out value="${conferences.name}"/>
                                </option>
                            </c:if>
                            <c:if test="${ isSelected eq 'false'}">
                                <option value="${conferences.id}">
                                    <cp:parse-local-date pattern="${dateTimeFormat}" dateTime="${conferences.date}"/> -
                                    <c:out value="${conferences.name}"/>
                                </option>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </select>
                <div class="row">
                    <div>
                        <label for="content">${userQuestion}</label>
                    </div>
                    <div class="text">
                        <textarea id="content" name="content" placeholder="${holder}" style="height:100px"
                                  maxlength="150" required></textarea>
                    </div>
                </div>
                <div class="row">
                    <input type="submit" value="${send}">
                </div>
            </form>
        </div>
    </div>
</section>
