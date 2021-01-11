<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.locale" var="loc"/>
<fmt:message bundle="${loc}" key="menu.conference" var="list"/>
<fmt:message bundle="${loc}" key="menu.archive" var="archive"/>
<fmt:message bundle="${loc}" key="menu.admin.create" var="create"/>
<fmt:message bundle="${loc}" key="menu.admin.requests" var="adminRequests"/>
<fmt:message bundle="${loc}" key="menu.user.requests" var="userRequests"/>
<fmt:message bundle="${loc}" key="menu.user.send" var="createRequest"/>
<fmt:message bundle="${loc}" key="menu.user.question" var="question"/>
<fmt:message bundle="${loc}" key="question.user.page.question" var="userQuestion"/>
<fmt:message bundle="${loc}" key="question.page.questions" var="questions"/>
<script type="text/javascript" language="JavaScript"
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<section>
    <div class="column-menu">
        <div id="buttons">
            <ul>
                <li>
                    <a class="button-menu" href="${pageContext.request.contextPath}/controller?command=getConferences">
                        ${list}</a>
                </li>
                <c:if test="${sessionScope.userRole eq 'ADMIN'}">
                    <li>
                        <a class="button-menu"
                           href="${pageContext.request.contextPath}/controller?command=adminCreate">${create}</a>
                    </li>
                    <li>
                        <a class="button-menu"
                           href="${pageContext.request.contextPath}/controller?command=adminGoToAcceptRequest">${adminRequests}</a>
                    </li>
                    <li>
                        <a class="button-menu"
                           href="${pageContext.request.contextPath}/controller?command=adminQuestionsPage">${questions}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.userRole eq 'USER'}">
                    <li>
                        <a class="button-menu"
                           href="${pageContext.request.contextPath}/controller?command=userCreateRequest">${createRequest}</a>
                    </li>
                    <li>
                        <a class="button-menu"
                           href="${pageContext.request.contextPath}/controller?command=userSentRequests">${userRequests}</a>
                    </li>
                    <li>
                        <a class="button-menu"
                           href="${pageContext.request.contextPath}/controller?command=userCreateQuestionPage">${question}</a>
                    </li>
                    <li>
                        <a class="button-menu"
                           href="${pageContext.request.contextPath}/controller?command=userQuestionsPage">${userQuestion}</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</section>
