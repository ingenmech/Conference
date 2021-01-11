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
<fmt:message bundle="${loc}" key="accept.request.accept" var="acceptButton"/>
<fmt:message bundle="${loc}" key="accept.request.reject" var="rejectButton"/>
<fmt:message bundle="${loc}" key="accept.request.remove" var="removeButton"/>
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
                <th>${user}</th>
                <th class="col-100px"></th>
            </tr>
            <c:forEach var="request" items="${requestList}" varStatus="reqStatus">
                <tr>
                    <td>
                        <fmt:message bundle="${loc}" key="request.status.${request.status}"/>
                    </td>
                    <td>${request.conferenceName}</td>
                    <td>${request.sectionName}</td>
                    <td>${request.topic}</td>
                    <td>${request.userLogin}</td>
                    <td>
                        <form>
                            <input type="hidden" name="requestId" value="${request.id}">
                            <input type="hidden" name="userId" value="${request.userId}">
                            <input type="hidden" name="sectionId" value="${request.sectionId}">
                            <input type="hidden" name="topic" value="${request.topic}">
                            <c:set var="status" value="${request.status}"/>
                            <c:if test="${ status ne 'DEPRECATED'}">
                                <div class="show-message-d">
                                    <c:if test="${ status ne 'ACCEPTED'}" var="isConsidering">
                                        <input type="image" name="submit" formmethod="POST"
                                               formaction="${pageContext.request.contextPath}/controller?command=adminAcceptRequest"
                                               border="0" alt="accept" style="width: 30px;"
                                               src="${pageContext.request.contextPath}/static/img/check-circle-regular.svg"/>
                                    </c:if>
                                    <c:if test="${ status eq 'ACCEPTED'}" var="isConsidering">
                                        <input type="image" disabled="disabled" style="width: 30px;"
                                               src="${pageContext.request.contextPath}/static/img/no-image.svg"/>
                                    </c:if>
                                    <c:if test="${ status ne 'REJECTED'}">
                                        <input type="image" name="submit" formmethod="POST"
                                               formaction="${pageContext.request.contextPath}/controller?command=adminRejectRequest"
                                               border="0" alt="reject" style="width: 30px;"
                                               src="${pageContext.request.contextPath}/static/img/times-circle-regular.svg"/>
                                    </c:if>
                                </div>
                            </c:if>
                        </form>
                    </td>
                </tr>
                <c:set var="elemStatus" value="${reqStatus.count}"/>
            </c:forEach>
        </table>
    </div>
    <div class="paging">
        <form>
            <input type="hidden" name="command" value="adminGoToAcceptRequest">
            <input type="hidden" name="pageNumber" value=${pageNumber}>
            <input type="hidden" name="direction" value="previous">
            <div class="paging-comp">
                <c:if test="${pageNumber > 1}">
                    <button type="submit" formmethod="GET" formaction="${pageContext.request.contextPath}/controller"
                            class="paging-button"><
                    </button>
                </c:if>
                <c:if test="${pageNumber eq 1}">
                    <button type="submit" disabled="disabled" class="disable-button"><</button>
                </c:if>
            </div>
        </form>
        <div class="page-number">
            <li>${pageNumber}</li>
        </div>
        <form>
            <input type="hidden" name="command" value="adminGoToAcceptRequest">
            <input type="hidden" name="pageNumber" value=${pageNumber}>
            <input type="hidden" name="direction" value="next">
            <div class="paging-comp">
                <c:set var="elemNumber" value="${elementNumber}"/>
                <c:if test="${elemStatus eq elemNumber}" var="isExist">
                    <button type="submit" formmethod="GET" formaction="${pageContext.request.contextPath}/controller"
                            class="paging-button">>
                    </button>
                </c:if>
                <c:if test="${isExist eq 'false'}">
                    <button type="submit" disabled="disabled" class="disable-button">></button>
                </c:if>
            </div>
        </form>
    </div>
</section>
