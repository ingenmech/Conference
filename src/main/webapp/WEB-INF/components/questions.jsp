<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="properties.locale" var="loc" />
    <fmt:message bundle="${loc}" key="accept.request.table.user" var="user" />
    <fmt:message bundle="${loc}" key="question.page.question" var="userQuestion" />
    <fmt:message bundle="${loc}" key="question.page.create" var="createQuestion" />
    <fmt:message bundle="${loc}" key="question.page.send" var="sendMessage" />
</head>
<body>
  <section class="column-main">
      <div class="table">
         <table>
            <tr>
               <th class="col-15">${user}</th>
               <th class="col-70">${userQuestion}</th>
               <th class="col-15"></th>
           </tr>
           <c:forEach var="question" items="questionsList">
           <tr>
               <td>${question.userLogin}</td>
               <td>${question.content}</td>
               <td>
                 <form method="POST" action="${pageContext.request.contextPath}/controller">
                   <input type="hidden" name="command" value="allUsersMessagePage" />
                   <input type="hidden" name="questionId" value="${question.id}">
                   <input type="hidden" name="questionContent" value="${question.content}">
                   <div  class="show-message">
                        <input type="submit" value="${sendMessage}">
                   </div>
                 </form>
               </td>
           </tr>
       </c:forEach>
       <c:if test="${sessionScope.userRole eq 'USER'}" >
       <tr>
       	  <td></td>
       		<td>
       		   <form method="POST" action="${pageContext.request.contextPath}/controller">
       		     <input type="hidden" name="command" value="userCreateQuestion" />
       			   <div class="show-message">
       			      <input type="submit" value="${createQuestion}">
       			   </div>
       		   </form>
       		</td>
       </tr>
       </c:if>
   </table>
</div>
</section>
</body>
</html>