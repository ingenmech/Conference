<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.locale" var="loc"/>
<fmt:message bundle="${loc}" key="accept.request.table.user" var="user"/>
<fmt:message bundle="${loc}" key="question.page.question" var="userQuestion"/>
<fmt:message bundle="${loc}" key="message.page.placeholder" var="holder"/>
<fmt:message bundle="${loc}" key="question.page.send" var="send"/>
<c:set var="page" value="userCreateQuestionPage" scope="session"/>
<section class="column-main">
    <div class="send-request">
        <div class="container">
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="userCreateQuestion"/>
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
