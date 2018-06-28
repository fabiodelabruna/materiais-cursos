package br.unisul.transportadora.bo;

import java.sql.SQLException;
import java.util.List;

import br.unisul.transportadora.bean.TransportadoraBean;
import br.unisul.transportadora.dao.TransportadoraDao;

/**
 * Classe Java que efetua a comuni��o entre as 
 * entidades do tipo TransportadoraBean e a camada view.
 * @author Fabio Dela Bruna
 */
public class TransportadoraBo {


	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\ 
	 * Os M�todos a seguir fazem a comunica��o entre a camada "view" 
	 * e a camada "dao" para objetos do tipo TransportadoraBean.
	 *  
	 */

	public Integer getMaxValueFromCodigo() {
		TransportadoraDao dao = new TransportadoraDao();
		return dao.getMaxValueFromCodigo();
	}
	
	public TransportadoraBean findById(Integer codigo) {
		TransportadoraDao dao = new TransportadoraDao();
		return dao.findById(codigo);
	}

	public boolean insertTransportadora(TransportadoraBean transp) {
		TransportadoraDao dao = new TransportadoraDao();
		return dao.insertTransportadora(transp);
	}

	public boolean updateTransportadora(TransportadoraBean transp) {
		TransportadoraDao dao = new TransportadoraDao();
		return dao.updateTransportadora(transp);
	}
	
	public boolean deleteTransportadora(Integer codigo) throws SQLException {
		TransportadoraDao dao = new TransportadoraDao();
		return dao.deleteTransportadora(codigo);
	}
	
	public List<TransportadoraBean> findAllTransportadora() {
		TransportadoraDao dao = new TransportadoraDao();
		return dao.findAllTransportadora();
	}
	
	/*
	 * 
	 * Fim dos m�todos que efetuam a comunica��o...
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


}
