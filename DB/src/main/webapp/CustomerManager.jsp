<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>고객 관리</title></head>
<body>

<form action="<%= request.getContextPath() %>/CustomerSearch.jsp" method="get">
이름 : <input type="text" name="name" size="10">

<br/><input type="submit" value="검색">
</form>

</body>
</html>