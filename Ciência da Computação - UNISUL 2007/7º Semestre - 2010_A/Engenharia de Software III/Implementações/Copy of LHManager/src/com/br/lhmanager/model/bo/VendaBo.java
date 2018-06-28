package com.br.lhmanager.model.bo;

import java.util.ArrayList;

import com.br.lhmanager.controller.entitys.Promocao;
import com.br.lhmanager.controller.entitys.Venda;
import com.br.lhmanager.model.dao.PromocaoDAO;
import com.br.lhmanager.model.dao.VendaDAO;

public class VendaBo {

	VendaDAO dao = new VendaDAO();
	
	public String validaCadastro(Venda v){
		if(v.getDescricao().trim().equals("")){
			return "Campo [DESCRI��O] � obrigat�rio";
		}
		if(v.getImpressoes() == null && v.getProdutos() == null){
			return "Campo [IMPRESSOES] ou o campo [PRODUTOS] � obrigat�rio";
		}
		return "";
	}
	
	public String inserir(Venda v){
		String msg = validaCadastro(v);
		if(msg.equals("")){
			dao.inserir(v);
			msg = "Cadastro Confirmado";
		}
		return msg;
	}
	
	public String alterar(Venda v){
		String msg = validaCadastro(v);
		if(msg.equals("")){
			dao.alterar(v);
			msg = "Altera��o Confirmada";
		}
		return msg;
	}
	
	public String excluir(Venda v){
		dao.excluir(v);
		return "Exclus�o confirmada";
	}
	
//	public ArrayList<VendaEntity> listaTodasVendas(){
//		return dao.consultar();
//	}
		
}
