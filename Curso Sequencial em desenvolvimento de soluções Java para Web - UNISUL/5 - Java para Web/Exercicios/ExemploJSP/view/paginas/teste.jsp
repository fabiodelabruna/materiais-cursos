<%!

	String nome = "Java";
	public void toUpper() {
		nome = nome.toUpperCase();
	}

%>

<html>
<head>
<title>Exemplo JSP</title>
</head>
<body>

<%toUpper();%>
Java em ma�usculo �: <%=nome%>

</body>
<html>