<%@page import="database.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="database.repository.session.BookRepository"%>
<%@ page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%
int bookId;
String bookName;
String publisher;
int pirce;
%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>** BookInsetForm **</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/bookInsert.jsp" method="get">
	<table border="1" cellspacing="0" width="400" bordercolor="#9AD2F7"
		bordercolordark="white" bordercolorlight="#B9E0FA">
		<tr>
			<td width="150" height="23">
				<p align="center">
					<span style="font-size: 9pt;">BOOKID</span>
				</p>
			</td>
			<td width="513">
				<p>
					<input type="text" name="bookid" />
				</p>
			</td>
		</tr>
		<tr>
			<td width="150" height="23">
				<p align="center">
					<span style="font-size: 9pt;">BOOKNAME</span>
				</p>
			</td>
			<td width="513">
				<p>
					<input type="text" name="bookname">
				</p>
			</td>
		</tr>
		<tr>
			<td width="150" height="23">
				<p align="center">
					<span style="font-size: 9pt;">PUBLISHER</span>
				</p>
			</td>
			<td width="513">
				<p>
					<input type="text" name="publisher">
				</p>
			</td>
		</tr>
		<tr>
			<td width="150" height="23">
				<p align="center">
					<span style="font-size: 9pt;">PRICE</span>
				</p>
			</td>
			<td width="513">
				<p>
					<input type="text" name="price">
				</p>
			</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" width="400" height="23">
		<tr>
			<td width="150">
				<p align="right">
					<input type="submit" value="추가">
				</p>
			</td>
		</tr>
		<tr>
			<td width="150">
				<p align="right">
					<span style="font-size: 9pt;"> <a href="booklist.jsp?">
							<font color="black">LIST</font>
					</a></span>
				</p>
			</td>
		</tr>
	</table>
	</form>
</body>

</html>