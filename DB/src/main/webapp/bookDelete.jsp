<%@page import="database.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="database.repository.session.BookRepository"%>
<%@page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
BookRepository br = new BookRepository();
int bookId = Integer.valueOf((String)session.getAttribute("bookId"));

if(br.delete(bookId)) {
%>
<script>
	alert("도서 삭제에 성공하였습니다!");
</script>
<%
}
response.sendRedirect(request.getContextPath()+"/booklist.jsp?");
%>