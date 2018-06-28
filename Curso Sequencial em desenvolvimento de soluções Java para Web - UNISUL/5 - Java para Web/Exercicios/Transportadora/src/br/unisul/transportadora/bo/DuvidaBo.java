package br.unisul.transportadora.bo;

import java.util.List;

import br.unisul.transportadora.bean.DuvidaBean;
import br.unisul.transportadora.dao.DuvidaDao;

/**
 * Classe que faz a liga��o entre a camada view 
 * e a camada dao, para os Objetos do tipo DuvidaBean.
 * @author Fabio Dela Bruna
 *
 */
public class DuvidaBo {

	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\ 
	 * Os M�todos a seguir fazem a comunica��o entre a camada "view" 
	 * e a camada "dao" para objetos do tipo CaminhaoBean.
	 *  
	 */

	
	public Integer getMaxValueFromCodigo() {
		DuvidaDao dao = new DuvidaDao();
		return dao.getMaxValueFromCodigo();
	}
	
	public void insertDuvida(DuvidaBean duv) {
		DuvidaDao dao = new DuvidaDao();
		dao.insertDuvida(duv);
	}
	
	public void deleteDuvida(Integer codigo) {
		DuvidaDao dao = new DuvidaDao();
		dao.deleteDuvida(codigo);
	}

	public List<DuvidaBean> findAllDuvida() {
		DuvidaDao dao = new DuvidaDao();
		return dao.findAllDuvida();
	}
	
	
	/*
	 * 
	 * Fim dos m�todos que efetuam a comunica��o...
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

}
