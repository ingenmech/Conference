<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Error Page</title>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="properties.locale" var="loc" />
<fmt:message bundle="${loc}" key="error.page.message" var="error"/>
</head>
<body>
  <div>
    <jsp:include page="../components/header.jsp" />
  </div>
  <div class="errorMessage">
     <h3>${error}</h3>
     <h4>${requestScope.userErrorMessage}</h4>
  </div>
  <div>
     Request from ${pageContext.errorData.requestURI} is failed
  <br/>
     Servlet name: ${pageContext.errorData.servletName}
  <br/>
     Status code: ${pageContext.errorData.statusCode}
  <br/>
     Exception: ${pageContext.exception}
  <br/>
     Message from exception: ${pageContext.exception.message}
  </div>
</body>
</html>