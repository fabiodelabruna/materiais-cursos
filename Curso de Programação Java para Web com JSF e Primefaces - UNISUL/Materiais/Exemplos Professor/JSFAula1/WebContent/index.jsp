<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>P�gina Principal</title>
</head>
<body>
<h1>Cabe�alho</h1>

<jsp:include page="menu.jsp"/>

<%
	String pag = request.getParameter("pag");
	
	if(pag != null){
		pag += ".jsp";
		%>
		<jsp:include page="<%=pag%>"/>
		<%
	}else{
		out.println("P�gina inicial");
	}
%>
</body>
</html>