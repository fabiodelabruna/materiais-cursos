package com.br.lhmanager.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe que implementa a interface ICrudBase respons�vel por persistir uma
 * impress�o a base de dados.
 * 
 * @author M�rcio Oz�rio Teixeira
 * @since 04/05/2010.
 */
public class ImpressaoDAO implements ICrudBase {

	@Override
	public void alterar(Object obj) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList consultar() {
		return null;
	}

	@Override
	public Object findById( Object obj ) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void excluir(Object obj) {
		
	}

	@Override
	public void inserir(Object obj) {
		
	}

}
