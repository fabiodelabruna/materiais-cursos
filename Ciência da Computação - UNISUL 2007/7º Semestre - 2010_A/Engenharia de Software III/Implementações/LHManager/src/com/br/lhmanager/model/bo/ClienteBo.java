package com.br.lhmanager.model.bo;

import java.util.ArrayList;

import com.br.lhmanager.controller.entitys.Cliente;
import com.br.lhmanager.model.dao.CampeonatoDAO;

public class ClienteBo {

	CampeonatoDAO dao = new CampeonatoDAO();
	
	public String validaCadastro(Cliente c){
		if(c.getCpf().trim().equals("")){
			return "Campo [CPF] � obrigat�rio";
		}
		if(c.getNome().trim().equals("")){
			return "Campo [NOME] � obrigat�rio";
		}
		if(c.getTelefone().trim().equals("")){
			return "Campo [TELEFONE] � obrigat�rio";
		}
		if(c.getEmail().trim().equals("")){
			return "Campo [EMAIL] � obrigat�rio";
		}
		if(c.getEndereco().trim().equals("")){
			return "Campo [ENDERE�O] � obrigat�rio";
		}
		if(c.getNascimento() == null){
			return "Campo [DATA DE NASCIMENTO] � obrigat�rio";
		}
		return "";
	}
	
	public String inserir(Cliente c){
		String msg = validaCadastro(c);
		if(msg.equals("")){
			dao.inserir(c);
			msg = "Cadastro Confirmado";
		}
		return msg;
	}
	
	public String alterar(Cliente c){
		String msg = validaCadastro(c);
		if(msg.equals("")){
			dao.alterar(c);
			msg = "Altera��o Confirmada";
		}
		return msg;
	}
	
	public String excluir(Cliente c){
		dao.excluir(c);
		return "Exclus�o confirmada";
	}
	
//	public ArrayList<ClienteEntity> listaTodosClientes(){
//		return dao.consultar();
//	}
	
}
