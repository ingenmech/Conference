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
<fmt:message bundle="${loc}" key="accept.request.accept" var="acceptButton"/>
<fmt:message bundle="${loc}" key="accept.request.reject" var="rejectButton"/>
<fmt:message bundle="${loc}" key="accept.request.remove" var="removeButton"/>
<fmt:message bundle="${loc}" key="section.list.status" var="sectionStatus"/>
<fmt:message bundle="${loc}" key="paging.list.from" var="from"/>
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
                <th>${user}</th>
                <th class="col-5"></th>
                <th class="col-5"></th>
            </tr>
            <%--@elvariable id="requestList" type="java.util.List"--%>
            <c:forEach var="request" items="${requestList}" varStatus="reqStatus">
                <c:set var="isBefore">
                    <ctg:is-before-date dateTime="${request.conferenceDate}"/>
                </c:set>
                <tr>
                    <c:if test="${ request.sectionStatus ne 'DEPRECATED' and isBefore }" var="isActual">
                        <td>
                            <fmt:message bundle="${loc}" key="request.status.${request.status}"/>
                        </td>
                        <td>${request.conferenceName}</td>
                        <td>${request.sectionName}</td>
                        <td>${request.topic}</td>
                        <td>${request.userLogin}</td>
                        <c:set var="status" value="${request.status}"/>
                        <td>
                            <c:if test="${ status ne 'DEPRECATED'}">
                                <form method="POST"
                                      action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="adminAcceptRequest">
                                    <input type="hidden" name="requestId" value="${request.id}">
                                    <input type="hidden" name="userId" value="${request.userId}">
                                    <input type="hidden" name="sectionId" value="${request.sectionId}">
                                    <input type="hidden" name="topic" value="${request.topic}">
                                    <c:if test="${ status ne 'ACCEPTED'}" var="isConsidering">
                                        <input type="image" name="submit" alt="accept" style="width: 30px;"
                                               src="${pageContext.request.contextPath}/static/img/check-circle-regular.svg"/>
                                    </c:if>
                                    <c:if test="${ status eq 'ACCEPTED'}" var="isConsidering">
                                        <input type="image" disabled="disabled" alt="" style="width: 30px;"
                                               src="${pageContext.request.contextPath}/static/img/no-image.svg"/>
                                    </c:if>
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${ status ne 'DEPRECATED'}">
                                <form method="POST"
                                      action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="command" value="adminRejectRequest">
                                    <input type="hidden" name="requestId" value="${request.id}">
                                    <input type="hidden" name="userId" value="${request.userId}">
                                    <input type="hidden" name="sectionId" value="${request.sectionId}">
                                    <input type="hidden" name="topic" value="${request.topic}">
                                    <c:if test="${ status eq 'REJECTED'}" var="isConsidering">
                                        <input type="image" disabled="disabled" alt="" style="width: 30px;"
                                               src="${pageContext.request.contextPath}/static/img/no-image.svg"/>
                                    </c:if>
                                    <c:if test="${ status ne 'REJECTED'}">
                                        <input type="image" name="submit" alt="reject" style="width: 30px;"
                                               src="${pageContext.request.contextPath}/static/img/times-circle-regular.svg"/>
                                    </c:if>
                                </form>
                            </c:if>
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
                        <td class="deprecated">${request.userLogin}</td>
                        <td></td>
                        <td></td>
                    </c:if>
                </tr>
                <c:set var="elemStatus" value="${reqStatus.count}"/>
            </c:forEach>
        </table>
    </div>
    <%--@elvariable id="pageNumber" type="int"--%>
    <%--@elvariable id="totalPage" type="int"--%>
    <%--@elvariable id="pageMessage" type="java.lang.String"--%>
    <%--@elvariable id="from" type="java.lang.String"--%>
    <c:if test="${pageNumber > totalPage and elemStatus == 0}" var="isExist">
        <div class="empty-page-message">
            <fmt:message bundle="${loc}" key="paging.list.${pageMessage}"/>
        </div>
    </c:if>
    <c:if test="${pageNumber <= totalPage or elemStatus > 0}" var="isExist">
        <div class="paging">
                <%--@elvariable id="pageNumber" type="int"--%>
                <%--@elvariable id="elementNumber" type="int"--%>
                <%--@elvariable id="totalPage" type="int"--%>
            <form>
                <input type="hidden" name="command" value="adminGoToAcceptRequest">
                <input type="hidden" name="pageNumber" value=${pageNumber}>
                <input type="hidden" name="direction" value="previous">
                <input type="hidden" name="totalPage" value="${totalPage}">
                <div class="paging-comp">
                    <c:if test="${pageNumber > 1}">
                        <button type="submit" formmethod="GET"
                                formaction="${pageContext.request.contextPath}/controller"
                                class="paging-button"><
                        </button>
                    </c:if>
                    <c:if test="${pageNumber eq 1}">
                        <button type="submit" disabled="disabled" class="disable-button"><</button>
                    </c:if>
                </div>
            </form>
            <div class="page-number">${pageNumber} ${from} ${totalPage}</div>
            <form>
                <input type="hidden" name="command" value="adminGoToAcceptRequest">
                <input type="hidden" name="pageNumber" value=${pageNumber}>
                <input type="hidden" name="direction" value="next">
                <input type="hidden" name="totalPage" value="${totalPage}">
                <div class="paging-comp">
                    <c:if test="${pageNumber < totalPage}" var="isExist">
                        <button type="submit" formmethod="GET"
                                formaction="${pageContext.request.contextPath}/controller"
                                class="paging-button">>
                        </button>
                    </c:if>

                    <c:if test="${isExist eq 'false'}">
                        <button type="submit" disabled="disabled" class="disable-button">></button>
                    </c:if>
                </div>
            </form>
        </div>
    </c:if>
</section>
