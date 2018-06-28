<%@page import="br.unisul.banco.bean.ProdutoBean"%>
<%@page import="java.util.List"%>

<%

List carrinhoCompras = (List)request.getSession().getAttribute("lista");

%>


<html>
<head>
<title>Carrinho de Compras</title>
</head>
<body>

<h1>Carrinho de compras:</h1>

<table border="1">

<tr>
	<th>C�digo</th>
	<th>Descri��o</th>
	<th>G�nero</th>
	<th>Valor</th>
	<th>Op��es</th>
</tr>

<%

for (int i = 0; i < carrinhoCompras.size(); i++) {
	ProdutoBean produto = (ProdutoBean)carrinhoCompras.get(i);
%>
	<tr>
		<td><%=produto.getCodigo()%></td>
		<td><%=produto.getDescricao()%></td>
		<td><%=produto.getGenero()%></td>
		<td><%=produto.getValor()%></td>
		<td></td>	
	</tr>

<%}%>



</table>

</body>
</html>