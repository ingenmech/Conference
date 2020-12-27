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
    <fmt:message bundle="${loc}" key="accept.request.status" var="requestStatus" />
    <fmt:message bundle="${loc}" key="accept.request.table.conference" var="conference" />
    <fmt:message bundle="${loc}" key="accept.request.table.section" var="section" />
    <fmt:message bundle="${loc}" key="accept.request.table.topic" var="topic" />
    <fmt:message bundle="${loc}" key="accept.request.table.user" var="user" />
    <fmt:message bundle="${loc}" key="accept.request.accept" var="acceptButton" />
    <fmt:message bundle="${loc}" key="accept.request.reject" var="rejectButton" />
    <fmt:message bundle="${loc}" key="accept.request.remove" var="removeButton" />
</head>
<body>
   <section class="column-main">
       <div class="table">
         <table>
           <tr>
               <th>${requestStatus}</th>
               <th>${conference}</th>
               <th>${section}</th>
               <th>${topic}</th>
               <th>${user}</th>
               <th></th>
           </tr>
        <c:forEach var="request" items="${requestList}">
           <tr>
             <td>${request.status}</td>
             <td>${request.conferenceName}</td>
             <td>${request.sectionName}</td>
             <td>${request.topic}</td>
             <td>${request.userLogin}</td>
             <td>
              <form>
                    <input type="hidden" name="requestId" value="${request.id}" >
                    <input type="hidden" name="userId" value="${request.userId}" >
                    <input type="hidden" name="sectionId" value="${request.sectionId}" >
                    <input type="hidden" name="topic" value="${request.topic}" >
                    <c:set var="status" value="${request.status}" />
                <div  class="dropdown-action ">
                     <a href="javascript:void(0)" class="dropbtn-action">>>></a>
                  <div class="dropdown-content-action">
                        <c:if test="${ status eq 'CONSIDERING'}" var="isConsidering">
                        <input type="submit" formmethod="POST" formaction="${pageContext.request.contextPath}/controller?command=adminAcceptRequest" value="${acceptButton}">
                        <input type="submit" formmethod="POST" formaction="${pageContext.request.contextPath}/controller?command=adminRejectRequest" value="${rejectButton}">
                    </c:if>
                    <c:if test="${ isConsidering eq 'false' }">
                    <input type="submit" formmethod="POST" formaction="${pageContext.request.contextPath}/controller?command=adminRemoveRequest" value="${removeButton}">
                    </c:if>
                  </div>
                </div>
              </form>
            </td>
          </tr>
        </c:forEach>
</table>
</div>
</section>
</body>
</html>