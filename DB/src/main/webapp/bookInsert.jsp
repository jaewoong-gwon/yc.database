<%@page import="database.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="database.repository.session.BookRepository"%>
<%@page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
BookRepository br = new BookRepository();
Book book = new Book(Integer.valueOf((String)request.getParameter("bookid")),
					 request.getParameter("bookname"),
					 request.getParameter("publisher"),
					 Integer.valueOf((String)request.getParameter("price")));
if (br.insert(book)) { 
%>
<script>
	alert("도서 등록에 성공하였습니다!");
</script>
<%
  }
response.sendRedirect(request.getContextPath()+"/booklist.jsp?");
%>




