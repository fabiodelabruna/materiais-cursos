package br.unisul.cursojsf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "restrito", urlPatterns = "/restrito")
public class RestritoServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	if((String)req.getSession().getAttribute("login") != null){
		PrintWriter out = resp.getWriter();
		out.println("Bem vindo a area restrita");
	}else{
		resp.sendRedirect("login.html");
	}
	
	}
	
}
