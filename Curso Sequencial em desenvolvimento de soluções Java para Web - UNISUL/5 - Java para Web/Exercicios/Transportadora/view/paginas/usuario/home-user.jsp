<%@page import="br.unisul.transportadora.bean.UsuarioBean"%>

<%
	UsuarioBean usuarioTemp1 = (UsuarioBean)request.getSession().getAttribute("logado");
	if (usuarioTemp1 == null || !usuarioTemp1.getTipo().equals(1)) {
		response.sendRedirect("index.jsp?id=restrito");
	}
%>


<h3>�rea restrita ao cliente</h3>

<p>Agora que voc� j� esta logado em nosso site,
voc� tem acesso a funcionalidades exclus�vas que
s� nossos clientes t�m, como por exemplo, solicitar fretes.</p>
<p>Fique � vontade para explorar ao m�ximo nosso site!</p>