<%
	String erro = request.getParameter("msg");
%>

<h3>Erro!</h3>
<p>N�o � poss�vel efetuar a opera��o de exclus�o:
<br><%=erro%></p>
<br>
<a href="javascript:history.back()">Voltar</a>
