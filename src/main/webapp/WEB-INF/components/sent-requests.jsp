<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/isBeforeActualDate.tld" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.locale" var="loc"/>
<fmt:message bundle="${loc}" key="accept.request.status" var="requestStatus"/>
<fmt:message bundle="${loc}" key="accept.request.table.conference" var="conference"/>
<fmt:message bundle="${loc}" key="accept.request.table.section" var="section"/>
<fmt:message bundle="${loc}" key="accept.request.table.topic" var="topic"/>
<fmt:message bundle="${loc}" key="accept.request.table.user" var="user"/>
<fmt:message bundle="${loc}" key="accept.request.status" var="requestStatus"/>
<fmt:message bundle="${loc}" key="accept.request.remove" var="removeButton"/>
<fmt:message bundle="${loc}" key="section.list.status" var="sectionStatus"/>
<fmt:message bundle="${loc}" key="request.status.message" var="messageForUser"/>
<fmt:message bundle="${loc}" key="request.status.second.message" var="secondMessageForUser"/>
<c:set var="query" value="${pageContext.request.queryString}"/>
<c:if test="${query ne 'command=en' and query ne 'command=ru' and query ne 'command=by'}">
    <c:set var="page" value="${query}" scope="session"/>
</c:if>
<section class="column-main">
    <div class="table">
        <table>
            <tr>
                <th>${requestStatus}</th>
                <th>${conference}</th>
                <th>${section}</th>
                <th>${topic}</th>
                <th class="col-10"></th>
            </tr>
            <%--@elvariable id="userRequestList" type="java.util.List"--%>
            <c:forEach var="request" items="${userRequestList}">
                <c:set var="isBefore">
                    <ctg:is-before-date dateTime="${request.conferenceDate}"/>
                </c:set>
                <tr>
                    <c:if test="${ request.sectionStatus eq 'ACTUAL' and isBefore}" var="isActual">
                        <c:if test="${request.status ne 'REJECTED'}">
                            <td>
                                <fmt:message bundle="${loc}" key="request.status.${request.status}"/>
                            </td>
                        </c:if>
                        <c:if test="${ request.status eq 'REJECTED'}">
                            <td>
                                <fmt:message bundle="${loc}" key="request.status.${request.status}"/>
                                <img alt="messages" title="${messageForUser}" style="width: 14px;"
                                     src="${pageContext.request.contextPath}/static/img/info-circle-solid.svg"/>
                            </td>
                        </c:if>
                        <td>${request.conferenceName}</td>
                        <td>${request.sectionName}</td>
                        <td>${request.topic}</td>
                        <td>
                            <form method="POST" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="userRemoveRequest"/>
                                <input type="hidden" name="requestId" value=${request.id}>
                                <input type="hidden" name="userId" value="${request.userId}">
                                <input type="hidden" name="sectionId" value="${request.sectionId}">
                                <input type="hidden" name="topic" value="${request.topic}">
                                <c:if test="${ request.status ne 'DEPRECATED' and request.status ne 'REJECTED'}">
                                    <div class="show-message">
                                        <input type="image" name="submit" alt="messages" style="width: 30px;"
                                               src="${pageContext.request.contextPath}/static/img/times-circle-regular.svg"/>
                                    </div>
                                </c:if>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${ not isActual }">
                        <c:if test="${ isBefore }">
                            <td class="deprecated">
                                    ${sectionStatus}
                                <img src="${pageContext.request.contextPath}/static/img/info-circle-solid.svg"
                                     alt="${sectionStatus}" title="${secondMessageForUser}" style="width: 14px;">
                            </td>
                        </c:if>
                        <c:if test="${ not isBefore }">
                            <td class="deprecated">
                                <fmt:message bundle="${loc}" key="request.status.${request.status}"/>
                            </td>
                        </c:if>
                        <td class="deprecated">${request.conferenceName}</td>
                        <c:if test="${ isBefore }">
                            <td class="deprecated" style="text-decoration-line: line-through;">
                                    ${request.sectionName}
                                <img src="${pageContext.request.contextPath}/static/img/info-circle-solid.svg"
                                     alt="${sectionStatus}" title="${sectionStatus}" style="width: 14px;">
                            </td>
                        </c:if>
                        <c:if test="${ not isBefore }">
                            <td class="deprecated">${request.sectionName}</td>
                        </c:if>
                        <td class="deprecated">${request.topic}</td>
                        <td></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
