<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <c:set var="page" value="main" scope="session" />
</head>
<body>
  <div>
    <jsp:include page="../components/header.jsp" />
  </div>
  <div>
    <jsp:include page="../components/menu.jsp"/>
  </div>
  <div class="column-main">
       <p>Var ${sessionScope.var}</p>
       <p>VarTwo ${sessionScope.varTwo}</p>
  </div>
  <div>
    <jsp:include page="../components/footer.jsp" />
  </div>
</body>
</html>