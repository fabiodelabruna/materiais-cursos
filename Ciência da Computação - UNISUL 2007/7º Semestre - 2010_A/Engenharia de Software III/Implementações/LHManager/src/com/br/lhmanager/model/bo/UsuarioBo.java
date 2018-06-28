package com.br.lhmanager.model.bo;

import java.util.ArrayList;

import com.br.lhmanager.controller.entitys.Promocao;
import com.br.lhmanager.controller.entitys.Usuario;
import com.br.lhmanager.model.dao.PromocaoDAO;
import com.br.lhmanager.model.dao.UsuarioDAO;

public class UsuarioBo {

	UsuarioDAO dao = new UsuarioDAO();
	
	public String validaCadastro(Usuario u){
		if(u.getNome().trim().equals("")){
			return "Campo [NOME] � obrigat�rio";
		}
		if(u.getTelefone().trim().equals("")){
			return "Campo [TELEFONE] � obrigat�rio";
		}
		if(u.getLogin().trim().length() < 4){
			return "Campo [LOGIN] necessita m�nimo de 5 caracteres";
		}
		if(u.getSenha().trim().length() < 4){
			return "Campo [SENHA] necessita m�nimo de 5 caracteres";
		}
		return "";
	}
	
	public String inserir(Usuario u){
		String msg = validaCadastro(u);
		if(msg.equals("")){
			dao.inserir(u);
			msg = "Cadastro Confirmado";
		}
		return msg;
	}
	
	public String alterar(Usuario u){
		String msg = validaCadastro(u);
		if(msg.equals("")){
			dao.alterar(u);
			msg = "Altera��o Confirmada";
		}
		return msg;
	}
	
	public String excluir(Usuario u){
		dao.excluir(u);
		return "Exclus�o confirmada";
	}
	
//	public ArrayList<UsuarioEntity> listaTodosUsuario(){
//		return dao.consultar();
//	}
		
	
}
