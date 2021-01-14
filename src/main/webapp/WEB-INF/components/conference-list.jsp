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
<fmt:message bundle="${loc}" key="accept.request.table.conference" var="conference"/>
<fmt:message bundle="${loc}" key="accept.request.table.section" var="section"/>
<fmt:message bundle="${loc}" key="accept.request.table.date" var="date"/>
<fmt:message bundle="${loc}" key="date.time.format" var="dateTimeFormat"/>
<fmt:message bundle="${loc}" key="menu.user.send" var="createRequest"/>
<fmt:message bundle="${loc}" key="menu.user.question" var="question"/>
<fmt:message bundle="${loc}" key="section.list.status" var="sectionStatus"/>
<fmt:message bundle="${loc}" key="paging.list.from" var="from"/>
<c:set var="query" value="${pageContext.request.queryString}"/>
<c:if test="${query ne 'command=en' and query ne 'command=ru' and query ne 'command=by'}">
    <c:set var="page" value="${query}" scope="session"/>
</c:if>
<c:set var="returnEditPage" value="${page}" scope="session"/>
<c:set var="pageSize" value="7" scope="request"/>
<section class="column-main">
    <div class="table">
        <table>
            <tr>
                <c:if test="${ sessionScope.userRole eq 'ADMIN'}">
                    <th class="col-10"></th>
                </c:if>
                <c:if test="${ sessionScope.userRole eq 'USER'}">
                    <th class="col-5"></th>
                    <th class="col-5"></th>
                </c:if>
                <th class="col-15">${date}</th>
                <th class="col-35">${conference}</th>
                <th class="col-35">${section}</th>
            </tr>
            <%--@elvariable id="conferenceList" type="java.util.List"--%>
            <c:forEach var="conference" items="${conferenceList}" varStatus="confStatus">
                <tr>
                    <c:set var="isBefore">
                        <ctg:is-before-date dateTime="${conference.date}"/>
                    </c:set>
                    <c:if test="${ not isBefore and sessionScope.userRole eq 'ADMIN'  }">
                        <td style="text-align: center;">
                            <img src="${pageContext.request.contextPath}/static/img/times-circle-solid.svg"
                                 alt="${sectionStatus}" title="${sectionStatus}" style="width: 20px;">
                        </td>
                    </c:if>
                    <c:if test="${ not isBefore and sessionScope.userRole eq 'USER' }">
                        <td>
                            <img src="${pageContext.request.contextPath}/static/img/no-image.svg"
                                 alt="${sectionStatus}" title="${sectionStatus}" style="width: 20px;">
                        </td>
                        <td style="text-align: center;">
                            <img src="${pageContext.request.contextPath}/static/img/times-circle-solid.svg"
                                 alt="${sectionStatus}" title="${sectionStatus}" style="width: 20px;">
                        </td>
                    </c:if>
                    <c:if test="${ sessionScope.userRole eq 'ADMIN' and isBefore }">
                        <td>
                            <form method="GET" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="adminUpdateConferencePage"/>
                                <input type="hidden" name="conferenceId" value="${conference.id}">
                                <div class="show-message">
                                    <input type="image" name="submit" alt="edit" style="width: 25px;"
                                           src="${pageContext.request.contextPath}/static/img/edit-regular.svg"/>
                                </div>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${ sessionScope.userRole eq 'USER' and isBefore }">
                        <td>
                            <form method="GET" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="userCreateRequest"/>
                                <input type="hidden" name="conferenceId" value="${conference.id}">
                                <div class="show-message">
                                    <input type="image" name="submit" title="${createRequest}" alt="request"
                                           style="width: 20px;"
                                           src="${pageContext.request.contextPath}/static/img/file-alt-regular.svg"/>
                                </div>
                            </form>
                        </td>
                        <td>
                            <form method="GET" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="userCreateQuestionPage"/>
                                <input type="hidden" name="conferenceId" value="${conference.id}">
                                <div class="show-message">
                                    <input type="image" name="submit" alt="question" title="${question}"
                                           style="width: 25px;"
                                           src="${pageContext.request.contextPath}/static/img/question-circle-regular.svg"/>
                                </div>
                            </form>
                        </td>
                    </c:if>

                    <td>
                        <cp:parse-local-date pattern="${dateTimeFormat}" dateTime="${conference.date}"/>
                    </td>
                    <td>${conference.name}</td>
                    <td>
                        <c:forEach var="section" items="${conference.sections}" varStatus="status">

                            <c:if test="${ section.status eq 'ACTUAL'}">
                                <li>
                                        ${status.count}. ${section.name}
                                </li>
                            </c:if>
                            <c:if test="${ section.status eq 'DEPRECATED'}">
                                <li style="color: dimgray; text-decoration-line: line-through;">
                                        ${status.count}. ${section.name}
                                    <img src="${pageContext.request.contextPath}/static/img/times-circle-solid.svg"
                                         alt="${sectionStatus}" title="${sectionStatus}" style="width: 13px;">
                                </li>
                            </c:if>

                        </c:forEach>
                    </td>
                </tr>
                <c:set var="elemStatus" value="${confStatus.count}"/>
            </c:forEach>
        </table>
    </div>
    <div>
        <c:if test="${pageNumber > totalPage}" var="isExist">
            <fmt:message bundle="${loc}" key="paging.list.${pageMessage}"/>
        </c:if>
    </div>
    <div class="paging">
        <%--@elvariable id="pageNumber" type="int"--%>
        <%--@elvariable id="elementNumber" type="int"--%>
        <%--@elvariable id="totalPage" type="int"--%>
        <form>
            <input type="hidden" name="command" value="getConferences">
            <input type="hidden" name="pageNumber" value=${pageNumber}>
            <input type="hidden" name="direction" value="previous">
            <input type="hidden" name="totalPage" value="${totalPage}">
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
        <div class="page-number">${pageNumber} ${from} ${totalPage}</div>
        <form>
            <input type="hidden" name="command" value="getConferences">
            <input type="hidden" name="pageNumber" value=${pageNumber}>
            <input type="hidden" name="direction" value="next">
            <input type="hidden" name="totalPage" value="${totalPage}">
            <div class="paging-comp">
                <c:if test="${pageNumber < totalPage}" var="isExist">
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
