<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:set var="page" value="userSentRequests" scope="session"/>
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
                <tr>
                    <c:if test="${ request.sectionStatus ne 'DEPRECATED'}">
                        <td>
                            <fmt:message bundle="${loc}" key="request.status.${request.status}"/>
                        </td>
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
                                <c:if test="${ request.status ne 'DEPRECATED'}">
                                    <div class="show-message">
                                        <input type="image" name="submit" alt="messages" style="width: 30px;"
                                               src="${pageContext.request.contextPath}/static/img/times-circle-regular.svg"/>
                                    </div>
                                </c:if>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${ request.sectionStatus eq 'DEPRECATED'}">
                            <td class="deprecated">
                                <fmt:message bundle="${loc}" key="request.status.${request.status}"/>
                            </td>
                            <td class="deprecated">${request.conferenceName}</td>
                            <td class="deprecated" style="text-decoration-line: line-through;">
                                    ${request.sectionName}
                                <img src="${pageContext.request.contextPath}/static/img/times-circle-solid.svg"
                                     alt="${sectionStatus}" title="${sectionStatus}" style="width: 13px;">
                            </td class="deprecated">
                            <td class="deprecated">${request.topic}</td>
                            <td></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
