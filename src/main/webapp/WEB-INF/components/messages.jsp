<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="properties.locale" var="loc"/>
<fmt:message bundle="${loc}" key="question.page.question" var="userQuestion"/>
<fmt:message bundle="${loc}" key="message.empty.message" var="emptyMessage"/>
<fmt:message bundle="${loc}" key="question.page.send" var="send"/>
<fmt:message bundle="${loc}" key="message.page.placeholder" var="holder"/>
<c:set var="page" value="allUsersMessagePage" scope="session"/>
<section class="column-main">
    <div class="table">
        <table>
            <tr>
                <th class="col-15" style="padding:15px 13px"><img
                        src="${pageContext.request.contextPath}/static/img/question-circle-regular.svg"
                        alt="${userQuestion}" style="width: 20px;"></th>
                <th class="col-85" style="padding:15px 13px">${questionContent}</th>
            </tr>
            <c:if test="${not empty messagesList}" var="isEmpty">
                <c:forEach var="message" items="${messagesList}">
                    <tr>
                        <td>${message.userLogin}</td>
                        <td>${message.content}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${isEmpty eq 'false'}">
                <tr>
                    <td></td>
                    <td>${emptyMessage}</td>
                </tr>
            </c:if>
            <tr>
                <td></td>
                <td>
                    <div class="container">
                        <form method="POST" action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="allUsersAddMessage"/>
                            <input type="hidden" name="questionId" value="${questionId}">
                            <input type="hidden" name="questionContent" value="${questionContent}">
                            <div class="row">
                                <div>
                                    <label for="content">${holder}</label>
                                </div>
                                <div class="text">
                                    <textarea id="content" name="content" placeholder="${holder}" style="height:100px"
                                              maxlength="300" required></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <input type="submit" value="${send}">
                            </div>
                        </form>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</section>
