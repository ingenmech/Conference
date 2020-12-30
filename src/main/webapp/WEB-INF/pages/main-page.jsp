<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  <div>
    <jsp:include page="../components/header.jsp" />
  </div>
  <div>
    <jsp:include page="../components/menu.jsp"/>
  </div>
  <div class="column-main">
     <p>Login ${sessionScope.login}</p>
     <p>Role ${sessionScope.userRole}</p>
     <p>Locale ${sessionScope.locale}</p>
     <p>Var ${sessionScope.var}</p>
     <p>VarTwo ${sessionScope.varTwo}</p>
  </div>
  <div>
    <jsp:include page="../components/footer.jsp" />
  </div>
</body>
</html>