<%@page import="br.unisul.transportadora.bean.UsuarioBean"%>

<%
	UsuarioBean usuarioTemp1 = (UsuarioBean)request.getSession().getAttribute("logado");
	if (usuarioTemp1 == null || !usuarioTemp1.getTipo().equals(2)) {
		response.sendRedirect("index.jsp?id=restrito");
	}
%>

<h3>�rea administrativa</h3>
<p>Logado como administrador, voc� tem acesso total ao sistema. 
� poss�vel listar todos os clientes cadastrados, al�m das 
transportadoras, dos caminh�es, dos fretes solicitados 
e tamb�m das d�vidas que as pessoas que acessam os site enviam.</p>
<p>Voc� ainda pode cadastrar novos caminh�es e transportadoras.</p>