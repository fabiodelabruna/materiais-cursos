<html>
<head>
	<title>Erro</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="title" content="">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="language" content="pt-br">
	<meta name="robots" content="All">
	<meta name="copyright" content="">	
	<meta name="abstract" content="">
	<meta name="MSSmartTagsPreventParsing" content="true">
</head>
<body>

<%

	String erro = request.getParameter("erro");

	if (erro.equals("auth")) {
		%>
			<h2>Voc� n�o est� autorizado a acessar esta p�gina!</h2>	
		<%
	} else {
		%>
			<p><%=erro%></p>
		<%
	}

%>

<br>
<a href="index.jsp">In�cio</a>

</body>
</html>