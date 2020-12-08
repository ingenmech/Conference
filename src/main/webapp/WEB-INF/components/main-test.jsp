<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<section class="column-main">
		<div class="info">
		<table>
		  <c:forEach var="elem" items="${list}">
			 <tr>
				<td>
					<c:out value="${elem.topic}" />
				</td>
			 </tr>
		  </c:forEach>
		  </table>
	   	</div>
    </section>
</body>
</html>