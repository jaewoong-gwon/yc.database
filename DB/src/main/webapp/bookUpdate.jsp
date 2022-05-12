<%@page import="database.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="database.repository.session.BookRepository"%>
<%@page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
BookRepository br = new BookRepository();
String bookName = request.getParameter("bookName");
String publisher = request.getParameter("publisher");
int price = Integer.parseInt(request.getParameter("price"));

%>
<script>
	alert("도서 등록에 성공하였습니다!");
	history.go(-1);
</script>
