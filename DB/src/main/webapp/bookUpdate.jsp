<%@page import="database.model.Book"%>
<%@page import="java.util.List"%>
<%@page import="database.repository.session.BookRepository"%>
<%@page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
BookRepository br = new BookRepository();
Book book = new Book(Integer.valueOf((String)session.getAttribute("bookId")),
		 			 request.getParameter("bookName"),
					 request.getParameter("publisher"),
					 Integer.valueOf(request.getParameter("price")));
if(br.update(book)) {
	out.println("<script>alert('수정 완료');</script>");
}
response.sendRedirect(request.getContextPath()+ "/booklist.jsp?");
%>

