<%@page import="br.unisul.transportadora.bean.UsuarioBean"%>

<%
	UsuarioBean usuario1 = (UsuarioBean) request.getSession().getAttribute("logado");
%>

<h3>Acesso restrito</h3>

<table>
	<tr><td><p>Ol� <span><%=usuario1.getNome()%></span>,</p><td></tr>
	<tr><td><p>Voc� est� logado como cliente.</p><td></tr>
	<tr><td><a href="/transportadora/logout">Logout</td></tr>
</table>