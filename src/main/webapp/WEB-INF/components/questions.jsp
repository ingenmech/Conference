<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
	<fmt:setLocale value="${sessionScope.locale}" />
	<fmt:setBundle basename="properties.locale" var="loc" />
  <fmt:message bundle="${loc}" key="accept.request.table.user" var="user" />
  <fmt:message bundle="${loc}" key="question.page.question" var="userQuestion" />
  <fmt:message bundle="${loc}" key="message.empty.message" var="emptyMessage" />
  <c:set var="query" value="${pageContext.request.queryString}"/>
  <c:if test="${query ne 'command=en' and query ne 'command=ru' and query ne 'command=by'}">
    <c:set var="page" value="${query}" scope="session" />
  </c:if>
  <section class="column-main">
      <div class="table">
         <table>
            <tr>
            <c:if test="${sessionScope.userRole eq 'ADMIN'}" >
               <th class="col-15">${user}</th>
               <th class="col-75">${userQuestion}</th>
               <th class="col-10"></th>
            </c:if>
            <c:if test="${sessionScope.userRole eq 'USER'}" >
                <th class="col-90">${userQuestion}</th>
                <th class="col-10"></th>
                </c:if>
           </tr>
     <c:if test="${not empty questionsList}" >
        <c:forEach var="question" items="${questionsList}" varStatus="status">
           <tr>
              <c:if test="${sessionScope.userRole eq 'ADMIN'}" >
               <td>${question.userLogin}</td>
              </c:if>
               <td>${question.content}</td>
               <td>
                 <form method="GET" action="${pageContext.request.contextPath}/controller">
                      <input type="hidden" name="command" value="allUsersMessagePage" />
                      <input type="hidden" name="questionId" value="${question.id}">
                      <input type="hidden" name="questionContent" value="${question.content}">
                   <div  class="show-message">
                    <input type="image" name="submit" border="0" alt="messages" style="width: 30px;" src="${pageContext.request.contextPath}/static/img/comments-regular.svg"/>
                   </div>
                 </form>
               </td>
           </tr>
           <c:set var="elemStatus" value="${status.count}" />
        </c:forEach>
     </c:if>
       <c:if test="${isEmpty eq 'false'}">
                 <tr>
                 <td></td>
                 <td>${emptyMessage}</td>
                 </tr>
       </c:if>
   </table>
</div>
<c:if test="${sessionScope.userRole eq 'ADMIN'}">
 <div class="paging">
    <form>
    <input type="hidden" name="command" value="adminQuestionsPage">
    <input type="hidden" name="pageNumber"  value=${pageNumber}>
    <input type="hidden" name="direction"  value="previous">
    <div class="paging-comp">
      <c:if test="${pageNumber > 1}">
      <button type="submit" formmethod="GET" formaction="${pageContext.request.contextPath}/controller" class="paging-button"><</button>
      </c:if>
      <c:if test="${pageNumber eq 1}">
      <button type="submit" disabled="disabled" class="disable-button"><</button>
      </c:if>
    </div>
    </form>
    <div class="page-number">
      <li>${pageNumber}</li>
    </div>
    <form>
       <input type="hidden" name="command" value="adminQuestionsPage">
       <input type="hidden" name="pageNumber"  value=${pageNumber}>
       <input type="hidden" name="direction"  value="next">
      <div class="paging-comp">
      <c:set var="elemNumber" value="${elementNumber}" />
      <c:if test="${elemStatus eq elemNumber}" var="isExist"> 
        <button type="submit" formmethod="GET" formaction="${pageContext.request.contextPath}/controller" class="paging-button">></button>
      </c:if>
      <c:if test="${isExist eq 'false'}"> 
        <button type="submit" disabled="disabled" class="disable-button">></button>
      </c:if>
      </div>
    </form>
  </div>
 </c:if>
</section>
