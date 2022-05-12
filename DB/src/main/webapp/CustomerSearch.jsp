<%@page import="database.model.Customer"%>
<%@page import="java.util.List"%>
<%@page import="database.repository.session.CustomerRepository"%>
<%@ page import="java.sql.*" contentType="text/html;charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	CustomerRepository cr = new CustomerRepository();
	List<Customer> customer = cr.selectAllCustomer();
	String name = request.getParameter("name");
	int index = 0;
%>
<form action="<%=request.getContextPath() %>/CustomerUpdate.jsp" method="post">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>** <%=name%> **
</title>
</head>
고객 검색 결과 : <%=name%>
<br>
<body bgcolor="white" text="black" link="blue" vlink="purple"
	alink="red">
	<%
	for (int i = 0; i < customer.size(); i++) {
	%>
	<%
	if (customer.get(i).getName().equals(name)) {
		index = i;
	%>
	<table border="1" cellspacing="0" width="400" bordercolor="#9AD2F7"
		bordercolordark="white" bordercolorlight="#B9E0FA">
		<tr>
			<td width="150" height="23">
				<p align="center">
					<span style="font-size: 9pt;">NAME</span>
				</p>
			</td>
			<td width="513">
				<p>
					<span style="font-size: 9pt;">
					<input type="hidden" name="name" value="<%=customer.get(i).getName()%>"><%=customer.get(i).getName()%></input>
					</span>	
				</p>
			</td>
		</tr>
		<tr>
			<td width="150" height="23">
				<p align="center">
					<span style="font-size: 9pt;">ADDRESS</span>
				</p>
			</td>
			<td width="513">
				<p>
					<span style="font-size: 9pt;">
					<input type="text" name="address"
						value="<%=customer.get(i).getAddress()%>"></input>
						</span>
				</p>
			</td>
		</tr>
		<tr>
			<td width="150" height="23">
				<p align="center">
					<span style="font-size: 9pt;">PHONE</span>
				</p>
			</td>
			<td width="513">
				<p>
					<span style="font-size: 9pt;"><%=customer.get(i).getPhone()%></span>
				</p>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<%
		}
	%>
	<table cellpadding="0" cellspacing="0" width="400" height="23">
			<tr>
			<td width="150">
				<p align="right">
					<input type="submit" value="주소변경">
				</p>
			</td>
		</tr>
	</table>
	</body>
	</html>