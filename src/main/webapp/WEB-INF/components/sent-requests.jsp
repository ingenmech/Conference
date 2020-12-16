<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="properties.locale" var="loc" />
	<fmt:message bundle="${loc}" key="accept.request.status" var="requestStatus" />
    <fmt:message bundle="${loc}" key="accept.request.table.conference" var="conference" />
    <fmt:message bundle="${loc}" key="accept.request.table.section" var="section" />
    <fmt:message bundle="${loc}" key="accept.request.table.topic" var="topic" />
    <fmt:message bundle="${loc}" key="accept.request.table.user" var="user" />
	<fmt:message bundle="${loc}" key="accept.request.status" var="requestStatus" />
	<fmt:message bundle="${loc}" key="accept.request.remove" var="removeButton" />
</head>
<body>
		<section class="column-main">
		    <section class="column-main">
            	 <div class="table">
                	  <table>
                		   <tr>
                                <th>${requestStatus}</th>
                                <th>${conference}</th>
                                <th>${section}</th>
                                <th>${topic}</th>
                                <th></th>
                           </tr>
                		   <c:forEach var="topic" items="${userTopicList}">
                			  <form method="POST">
                				<tr>
                				   <td>${topic.status}</td>
                				   <td>${topic.conference.name}</td>
                				   <td>${topic.section.name}</td>
                				   <td>${topic.name}</td>
                				   <td>
                				       <input type="hidden" name="topicId" value=${topic.id}>
                						<div  class="dropdown-action ">
                							<a href="javascript:void(0)" class="dropbtn-action">>>></a>
                							<div class="dropdown-content-action">
                                            <input type="submit" formaction="${pageContext.request.contextPath}/controller?command=adminRemoveRequest" value="${removeButton}">
                							</div>
                						</div>
                				   </td>
                				</tr>
                		      </form>
                		   </c:forEach>
                	  </table>
                 </div>
        </section>
</body>
</html>