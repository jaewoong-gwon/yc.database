<%@page import="org.apache.tomcat.util.http.fileupload.RequestContext"%>
<%@page import="database.model.Book" %>
<%@page import="database.repository.session.BookRepository"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	BookRepository br = new BookRepository();
	//List<Book> books = br.selectAllBook();
	pageContext.setAttribute("books", br.selectAllBook());
	//pageContext.setAttribute("bookId", request.getParameter("bookId"));
	session.setAttribute("bookId", request.getParameter("bookId"));
%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>** Book VIEW **</title>
</head>
<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red">
<form action="<%=request.getContextPath() %>/bookUpdate.jsp" method="get">
<c:forEach var="b" items="${books}">
<c:if test="${b.bookId == bookId}">
<table border="1" cellspacing="0" width="400" bordercolor="#9AD2F7" bordercolordark="white" bordercolorlight="#B9E0FA">
		<tr>
			<td width="150" height="20" bgcolor="#D2E9F9">
				<p align="center"><span style="font-size: 8pt;"><b>BOOKNAME</b></span></p>
			</td>
			<td width="150" height="20">
			<input type="text" name="bookName" value="${b.bookName}"/>
			</td>
		</tr>
		<tr>
			<td width="150" height="20" bgcolor="#D2E9F9">
			<p align="center"><span style="font-size: 8pt;"><b>PUBLISHER</b></span></p>
			</td>
			<td width="150" height="20">
			<input type="text" name="publisher" value="${b.publisher}"/>
			</td>
		</tr>
		<tr>
			<td width="50" height="20" bgcolor="#D2E9F9">
				<p align="center"><span style="font-size: 8pt;"><b>PRICE</b></span></p>
			</td>
			<td width="150" height="20">
			<input type="text" name=price value="${b.price}"/>
			</td>
		</tr>
</table>
</c:if>
</c:forEach>
<table cellpadding="0" cellspacing="0" width="400" height="23">
	<tr>
		<td>
			<p align="right">
					<input type="submit" value="수정"/>
				</p>
			</td>
		</tr>
   <tr>
	<td width="150">
	   <p align="right">
	   <b><a href="bookDelete.jsp?"><font size="3" face="돋움체" color="black">DELETE</font></a></b>
	   <b><a href="booklist.jsp?"><font size="3" face="돋움체" color="black">LIST</font></a></b>
	   </p>
	</td>
   </tr>
</table>
</form>
</body>
</html>
