<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cp" uri="/WEB-INF/tld/parserLocalDateTime.tld" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.locale" var="loc"/>
<fmt:message bundle="${loc}" key="accept.request.table.user" var="user"/>
<fmt:message bundle="${loc}" key="question.page.question" var="userQuestion"/>
<fmt:message bundle="${loc}" key="message.empty.message" var="emptyMessage"/>
<fmt:message bundle="${loc}" key="accept.request.table.conference" var="conference"/>
<fmt:message bundle="${loc}" key="paging.list.from" var="from"/>
<c:set var="query" value="${pageContext.request.queryString}"/>
<c:if test="${query ne 'command=en' and query ne 'command=ru' and query ne 'command=by'}">
    <c:set var="page" value="${query}" scope="session"/>
</c:if>
<section class="column-main">
    <div class="table">
        <table>
            <tr>
                <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                    <th class="col-10">${user}</th>
                    <th class="col-20">${conference}</th>
                    <th class="col-60">${userQuestion}</th>
                    <th class="col-10"></th>
                </c:if>
                <c:if test="${sessionScope.userRole eq 'USER'}">
                    <th class="col-20">${conference}</th>
                    <th class="col-70">${userQuestion}</th>
                    <th class="col-10"></th>
                </c:if>
            </tr>
            <%--@elvariable id="questionsList" type="java.util.List"--%>
            <c:if test="${not empty questionsList}">
                <c:forEach var="question" items="${questionsList}" varStatus="status">
                    <tr>
                        <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                            <td>${question.userLogin}</td>
                        </c:if>
                        <td>${question.conferenceName}</td>
                        <td>${question.content}</td>
                        <td>
                            <form method="GET" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="allUsersMessagePage"/>
                                <input type="hidden" name="questionId" value="${question.id}">
                                <input type="hidden" name="questionContent" value="${question.content}">
                                <input type="hidden" name="conferenceName" value="${question.conferenceName}">
                                <div class="show-message">
                                    <input type="image" name="submit" alt="messages" style="width: 30px;"
                                           src="${pageContext.request.contextPath}/static/img/comments-regular.svg"/>
                                </div>
                            </form>
                        </td>
                    </tr>
                    <c:set var="elemStatus" value="${status.count}"/>
                </c:forEach>
            </c:if>
            <%--@elvariable id="isEmpty" type="boolean"--%>
            <c:if test="${isEmpty eq 'false'}">
                <tr>
                    <td></td>
                    <td>${emptyMessage}</td>
                </tr>
            </c:if>
        </table>
    </div>
    <c:if test="${sessionScope.userRole eq 'ADMIN'}">
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
                    <%--@elvariable id="pageSize" type="int"--%>
                    <%--@elvariable id="pageNumber" type="int"--%>
                    <%--@elvariable id="elementNumber" type="int"--%>
                    <%--@elvariable id="totalPage" type="int"--%>
                <form>
                    <input type="hidden" name="command" value="adminQuestionsPage">
                    <input type="hidden" name="pageNumber" value=${pageNumber}>
                    <input type="hidden" name="direction" value="previous">
                    <input type="hidden" name="totalPage" value="${totalPage}">
                    <input type="hidden" name="pageSize" value="${pageSize}">
                    <div class="paging-comp">
                        <c:if test="${pageNumber > 1}">
                            <input type="image" name="submit" alt="next" style="width: 15px;"
                                   src="${pageContext.request.contextPath}/static/img/chevron-left-solid.svg"/>
                        </c:if>
                        <c:if test="${pageNumber eq 1}">
                            <input disabled="disabled" type="image" name="submit" alt="next" style="width: 15px;"
                                   src="${pageContext.request.contextPath}/static/img/chevron-left-solid.svg"/>
                        </c:if>
                    </div>
                </form>
                <div class="page-number">${pageNumber} ${from} ${totalPage}</div>
                <form>
                    <input type="hidden" name="command" value="adminQuestionsPage">
                    <input type="hidden" name="pageNumber" value=${pageNumber}>
                    <input type="hidden" name="direction" value="next">
                    <input type="hidden" name="totalPage" value="${totalPage}">
                    <input type="hidden" name="pageSize" value="${pageSize}">
                    <div class="paging-comp">
                        <c:if test="${pageNumber < totalPage}" var="isExist">
                            <input type="image" name="submit" alt="next" style="width: 15px;"
                                   src="${pageContext.request.contextPath}/static/img/chevron-right-solid.svg"/>
                        </c:if>
                        <c:if test="${ not isExist }">
                            <input disabled="disabled" type="image" name="submit" alt="next" style="width: 15px;"
                                   src="${pageContext.request.contextPath}/static/img/chevron-right-solid.svg"/>
                        </c:if>
                    </div>
                </form>
            </div>
        </c:if>
    </c:if>
</section>
