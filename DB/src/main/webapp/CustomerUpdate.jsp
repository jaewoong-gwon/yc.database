<%@page import="database.model.Customer"%>
<%@page import="java.util.List"%>
<%@page import="database.repository.session.CustomerRepository"%>
<%@ page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
CustomerRepository cr = new CustomerRepository();
List<Customer> customer = cr.selectAllCustomer();
String address = request.getParameter("address");
String name = request.getParameter("name");
if(cr.update(address)){
%>
<script>
	alert("주소 변경에 성공하였습니다!");
	history.go(-1);
</script>
<%
	}
%>


