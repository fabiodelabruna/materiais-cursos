<%@page import="br.unisul.transportadora.bean.UsuarioBean"%>
<%@page import="br.unisul.transportadora.bo.UsuarioBo"%>
<%@page import="java.util.List"%>
<%@page import="br.unisul.transportadora.bean.UsuarioBean"%>

<%
	UsuarioBean usuarioTemp1 = (UsuarioBean)request.getSession().getAttribute("logado");
	if (usuarioTemp1 == null || !usuarioTemp1.getTipo().equals(2)) {
		response.sendRedirect("index.jsp?id=restrito");
	}
%>

<h3>Clientes cadastrados:</h3>

<%
	UsuarioBo userBo = new UsuarioBo();
	List listaUsers = userBo.findAllById(1); // 1 porque � para mostrar apenas os clientes.

	if (listaUsers.size() > 0) {
		
		for (int i = 0; i < listaUsers.size(); i++) {
			UsuarioBean user = (UsuarioBean)listaUsers.get(i);
			
			%>
			
			<table cellspacing="0"	summary="Tabela com duas colunas contendo na coluna direita os cabe�alhos e na esquerda a decri��o">
				<tr class="impar">
					<th>C�digo:</th>
					<td><%=user.getCodigo()%></td>
				</tr>
				<tr>
					<th>Nome:</th>
					<td><%=user.getNome()%></td>
				</tr>
				<tr class="impar">
					<th>Email:</th>
					<td><%=user.getEmail()%></td>
				</tr>	
				<tr>
					<th>Endere�o:</th>
					<td><%=user.getEndereco()%></td>
				</tr>	
				<tr class="impar">
					<th>Cidade:</th>
					<td><%=user.getCidade()%></td>
				</tr>
				<tr>
					<th>UF:</th>
					<td><%=user.getUf()%></td>
				</tr>
				<tr class="impar">
					<th> </th>
					<td align="right"><a href="excluirUser?id=<%=user.getCodigo()%>"><img src="imagens/excluir.gif"></a></td>
				</tr>
			
			</table>
		<%
		}
	} else {
		%>
			<p>N�o h� clientes cadastrados.</p>
			<a href="index.jsp?id=home-admin">In�cio</a>
		<%
	}
%>
