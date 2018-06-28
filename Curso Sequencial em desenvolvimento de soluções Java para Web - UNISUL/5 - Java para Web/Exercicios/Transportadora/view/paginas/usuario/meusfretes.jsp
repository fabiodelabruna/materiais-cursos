<%@page import="br.unisul.transportadora.bean.FreteBean"%>
<%@page import="br.unisul.transportadora.bo.FreteBo"%>
<%@page import="br.unisul.transportadora.bean.UsuarioBean"%>
<%@page import="br.unisul.transportadora.bean.TransportadoraBean"%>
<%@page import="br.unisul.transportadora.bo.TransportadoraBo"%>
<%@page import="br.unisul.transportadora.bo.CaminhaoBo"%>
<%@page import="br.unisul.transportadora.bean.CaminhaoBean"%>
<%@page import="java.util.List"%>

<%
	UsuarioBean usuarioTemp1 = (UsuarioBean)request.getSession().getAttribute("logado");
	if (usuarioTemp1 == null || !usuarioTemp1.getTipo().equals(1)) {
		response.sendRedirect("index.jsp?id=restrito");
	}
%>


<h3>Meus Fretes:</h3>

<%
	UsuarioBean usuarioLogado = (UsuarioBean)request.getSession().getAttribute("logado");
	TransportadoraBo transpBo = new TransportadoraBo();
	CaminhaoBo camBo = new CaminhaoBo();
	FreteBo freBo = new FreteBo();
	
	List listaFretes = freBo.findByCliente(usuarioLogado);

	if (listaFretes.size() > 0) {
		
		for (int i = 0; i < listaFretes.size(); i++) {
			FreteBean frete = (FreteBean)listaFretes.get(i);
			TransportadoraBean transp = transpBo.findById(frete.getCodigoTransportadora());
			CaminhaoBean cam = camBo.findById(frete.getCodigoCaminhao());
			
			%>
			
			<table cellspacing="0"	summary="Tabela com duas colunas contendo na coluna direita os cabe�alhos e na esquerda a decri��o">
				<tr class="impar">
					<th>C�digo:</th>
					<td><%=frete.getCodigo()%></td>
				</tr>
				<tr>
					<th>Quilometragem:</th>
					<td><%=frete.getQuilometragem()%></td>
				</tr>
				<tr class="impar">
					<th>Valor:</th>
					<td><%=frete.getValor()%></td>
				</tr>	
				<tr>
					<th>Transportadora:</th>
					<td><%=transp.getNome()%></td>
				</tr>	
				<tr class="impar">
					<th>Caminh�o:</th>
					<td><%=cam.getDescricao()%></td>
				</tr>
				<tr>
					<th></th>
					<td align="right">
						<a href="index.jsp?id=alt-frete&cod=<%=frete.getCodigo()%>"><img src="imagens/editar.gif" alt="editar"></a>
						<a href="excluirFrete?id=<%=frete.getCodigo()%>"><img src="imagens/excluir.gif" alt="excluir"></a>
					</td>
				</tr>
			</table>
		<%
		}
	} else {
		%>
			<p>Voc� n�o t�m fretes cadastrados.</p>
			<a href="index.jsp?id=home-cliente">In�cio</a>
		<%
	}
%>
