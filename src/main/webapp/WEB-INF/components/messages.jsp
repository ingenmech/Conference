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
</head>
<body>
  <section class="column-main">
      <div class="table">
         <table>
            <tr>
               <th class="col-15">${user}</th>
               <th class="col-70">${questionContent}</th>
               <th class="col-15"></th>
           </tr>
         <c:forEach var="message" items="messagesList">
           <tr>
               <td>${message.userLogin}</td>
               <td>${message.content}</td>
               <td>
                   <div  class="show-message">
                        <input type="submit" value="Send">
                   </div>
               </td>
           </tr>
         </c:forEach>
       <tr>
       	    <td></td>
       	    <td>
       	    	<div class="container">
       	    	   <form method="POST" action="${pageContext.request.contextPath}/controller">
       	    	      <input type="hidden" name="command" value="message" />
       	    	      <input type="hidden" name="questionId" value="${questionId}">
       					<div class="row">
       					    <div >
       						   <label for="subject">Message</label>
       					    </div>
       					    <div class="text">
       							<textarea id="subject" name="subject" placeholder="Write something.." style="height:150px"></textarea>
       	 				    </div>
       	        		</div>
       					<div class="row">
       					<input type="submit" value="${sendMessage}">
       					</div>
       				</form>
       			</div>
       	    </td>
       </tr>
   </table>
</div>
</section>
</body>
</html>