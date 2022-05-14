<%@page import="database.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="database.repository.session.BookRepository"%>
<%@page import="java.sql.*" contentType="text/html;charset=utf-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
BookRepository br = new BookRepository();
//List<Book> books = br.selectAllBook();
pageContext.setAttribute("books", br.selectAllBook());
%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>** BOOK LIST **</title>
</head>
<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red">
	<form action="<%=request.getContextPath() %>/bookSearch.jsp" method="get">
	<table border="1" cellspacing="0" width="400" bordercolor="#9AD2F7" bordercolordark="white" bordercolorlight="#B9E0FA">
		<tr>
			<td width="150" height="20" bgcolor="#D2E9F9">
				<p align="center">
					<span style="font-size: 8pt;"><b>BOOKNAME</b></span>
				</p>
			</td>
			<td width="150" height="20" bgcolor="#D2E9F9">
				<p align="center">
					<span style="font-size: 8pt;"><b>PUBLISHER</b></span>
				</p>
			</td>
			<td width="50" height="20" bgcolor="#D2E9F9">
				<p align="center">
					<span style="font-size: 8pt;"><b>PRICE</b></span>
				</p>
			</td>
		</tr>
	<c:forEach var="b" items="${books}">
	<tr>
	    <td width="150" height="20">
             <p align="center"><span style="font-size:9pt;">
             <a href="bookview.jsp?bookId=${b.bookId}">
             <font face="돋움체" color="black">${b.bookName}</font>
             </a></span></p>
         </td>
	    <td width="150" height="20">
            <p align="center"><span style="font-size:9pt;">
            <font face="돋움체">${b.publisher}</font></span></p>
        </td>

        <td width="50" height="20">
            <p align="center"><span style="font-size:9pt;">
            <font face="돋움체">${b.price}</font></span></p>
        </td>
    </tr>
    </c:forEach>
</table>
	<table cellpadding="0" cellspacing="0" width="400" height="23">
	<tr>
		<td>
				<p align="right">
					<input type="text" name="pattern"/>
					<input type="submit" value="검색"/>
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<p align="right">
					<b><a href="bookInsertForm.jsp"><font size="3" face="돋움체" color="black">ADD</font></a></b>
					 <b><a href="booklist.jsp"><font size="3" face="돋움체" color="black">LIST</font></a></b>
				</p>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>