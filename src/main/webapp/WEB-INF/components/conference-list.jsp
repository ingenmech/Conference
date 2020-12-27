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
  <fmt:message bundle="${loc}" key="accept.request.table.conference" var="conference" />
  <fmt:message bundle="${loc}" key="accept.request.table.section" var="section" />
  <fmt:message bundle="${loc}" key="accept.request.table.date" var="date" />
</head>
<body>
 <section class="column-main">
  <div class="table">
    <table>
       <tr>
         <th class="col-15">${date}</th>
         <th class="col-42">${conference}</th>
         <th class="col-42">${section}</th>
       </tr>
      <c:forEach var="conference" items="${conferenceList}" varStatus="confStatus">
        <tr>
          <td>${conference.date}</td>
          <td>${conference.name}</td>
          <td>
          <c:forEach var="section" items="${conference.sections}" varStatus="status" >
              <li>
                ${status.count}. ${section.name}
              </li>  
          </c:forEach>
          </td>
      </tr>
      <c:set var="elemStatus" value="${confStatus.count}" />
      </c:forEach>
    </table>
  </div>
  <div class="paging">
    <form>
    <input type="hidden" name="command" value="getConferences">
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
       <input type="hidden" name="command" value="getConferences">
       <input type="hidden" name="pageNumber"  value=${pageNumber}>
       <input type="hidden" name="direction"  value="next">
      <div class="paging-comp">
      <c:set var="elemNumber" value="${elementNumber}" />
      <c:if test="${elemStatus eq elemNumber}"> 
        <button type="submit" formmethod="GET" formaction="${pageContext.request.contextPath}/controller" class="paging-button">></button>
      </c:if>
      <c:if test="${elemStatus < elementNumber}"> 
        <button type="submit" disabled="disabled" class="disable-button">></button>
      </c:if>
      </div>
    </form>
  </div>
</section>
</body>
</html>