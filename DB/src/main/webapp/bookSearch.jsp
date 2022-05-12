<%@page import="database.model.Book" %>
<%@page import="database.repository.session.BookRepository"%>
<%@page import="java.util.List"%>
<%@ page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String pattern = request.getParameter("pattern");
	BookRepository br = new BookRepository();
	List<Book> books = br.search(pattern);
%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>** BOOK SEARCH **</title>
</head>
<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red">
	<form action="<%=request.getContentType()%>/bookSearch.jsp">
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
		<%
		for (Book book : books) {
		%>
		<tr>
			<td width="150" height="20">
				<p>
					<span style="font-size: 9pt;"> <a
						href="bookview.jsp?bookid=<%=book.getBookId()%>"> <font
							face="돋움체" color="black"> <%=book.getBookName()%></font></a></span>
				</p>
			</td>
			<td width="150" height="20">
				<p align="center">
					<span style="font-size: 9pt;"> <font face="돋움체"><%=book.getPublisher()%></font></span>
				</p>
			</td>

			<td width="50" height="20">
				<p align="center">
					<span style="font-size: 9pt;"> <font face="돋움체"><%=book.getPrice()%></font></span>
				</p>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<table cellpadding="0" cellspacing="0" width="400" height="23">
		<tr>
			<td>
				<p align="right">
					<b><a href="bookInsertForm.jsp"> <font size="3" face="돋움체" color="black">ADD</font></a></b>
					 <b><a href="booklist.jsp"> <font size="3" face="돋움체" color="black">LIST</font></a></b>
				</p>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>