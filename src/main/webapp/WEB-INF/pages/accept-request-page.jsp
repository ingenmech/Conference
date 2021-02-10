<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <c:set var="page" value="accept-request-page.jsp" scope="session"  />
</head>
<body>
 <div>
   <jsp:include page="../components/header.jsp" />
 </div>
 <div>
   <jsp:include page="../components/menu.jsp" />
 </div>
 <div>
    <jsp:include page="../components/accept-request.jsp" />
</div>
 <div>
    <jsp:include page="../components/footer.jsp" />
 </div>
</body>
</html>
