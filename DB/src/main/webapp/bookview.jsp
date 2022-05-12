<%@page import="database.model.Book" %>
<%@page import="database.repository.session.BookRepository"%>
<%@page import="java.util.List"%>
<%@ page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%--
  Class.forName("oracle.jdbc.driver.OracleDriver");
  String url="jdbc:oracle:thin:@localhost:1521:xe";
	
	/* 오라클 XE 버전이 아닌 정식버전은 orcl을 입력한다. */
  Connection dbconn=DriverManager.getConnection(url, "c##madang", "madang");
  Statement stmt = dbconn.createStatement();
  String bookid=request.getParameter("bookid");
  ResultSet myResultSet=stmt.executeQuery("SELECT * FROM Book WHERE bookid='"+bookid+"'");
  if(myResultSet!=null){
   myResultSet.next();
--%>

<%
	request.setCharacterEncoding("utf-8");
	BookRepository br = new BookRepository();
	List<Book> books = br.selectAllBook();
	int bookid = Integer.parseInt(request.getParameter("bookid"));
	request.getAttribute("bookId");
%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>** Book VIEW **</title>
</head>
<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red">
<form action="<%=request.getContextPath() %>/bookUpdate.jsp" method="get">
<% for(int i = 0; i < books.size(); i++){ %>
<% if(books.get(i).getBookId().equals(bookid))	{ %>
<table border="1" cellspacing="0" width="400" bordercolor="#9AD2F7"
		bordercolordark="white" bordercolorlight="#B9E0FA">
   <tr>
	<td width="150" height="23">
	   <p align="center">
	   <span style="font-size:9pt ;">BOOKNAME</span></p>
	</td>
	<td width="513">
	   <p>
	   <input type="text" name="bookName" value="<%=books.get(i).getBookName()%>">
	   </p>
	</td>
   </tr>
   <tr>
	<td width="150" height="23">
	   <p align="center">
	   <span style="font-size:9pt ;">PUBLISHER</span></p>
	</td>
	<td width="513">
	   <p>
	   <input type="text" name="publisher" value="<%=books.get(i).getPublisher()%>">
	   </p>
	</td>
   </tr>
   <tr>
	<td width="150" height="23">
	   <p align="center">
	   <span style="font-size:9pt ;">PRICE</span></p>
	</td>
	<td width="513">
	   <p>
	   <input type="text" name="pirce" value="<%=books.get(i).getPrice()%>">
	   </p>
	</td>
   </tr>
</table>
<%
		}
%>
<%
	}
%>
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
	   <p align="right"><span style="font-size:9pt;">
	   <a href="booklist.jsp?">
	   <font color="black">LIST</font></a></span></p>
	</td>
   </tr>
</table>
<%--
  }
  stmt.close();
  dbconn.close();
--%>
</form>
</body>
</html>
