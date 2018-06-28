package framework.service;

import java.util.List;

import framework.dao.DaoBase;

public class ServiceBase {
	
	private DaoBase daoBase;

	
	/**
	 * Construtor que recebe a classe DaoBase por par�metro.
	 * @param newDaoBase Classe DaoBase.
	 * @throws Exception
	 */
	public ServiceBase(DaoBase newDaoBase) throws Exception {
		setDaoBase(newDaoBase);
	}
	
	
	
	/**
	 * M�todo que efetua a cahamada do m�todo para listar os objetos
	 * cadastrados no banco e envia esses dados para a interface.
	 * @return Lista de objetos cadastadas.
	 * @throws Exception
	 */
	public List findAll () throws Exception {
		return getDaoBase().findAll();
	}
	
	
	/**
	 * Este m�todo efetua uma busca personalizada.
	 * @param cd C�digo
	 * @param ds Descri��o.
	 * @return Lista de registros encontrados.
	 * @throws Exception
	 */
	public List findAllByExample(int cd, String ds) throws Exception {
		return getDaoBase().findAllByExample(cd, ds);
	}
	
	
	/**
	 * M�todo que apaga um determinado objeto do banco.
	 * @param pessoa
	 * @return
	 * @throws Exception
	 */
	public boolean delete (Object obj) throws Exception {
		return (getDaoBase().delete(obj) == 1);
	}
	
	
	/**
	 * Este m�todo insere um novo registro no banco.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean insert (Object obj) throws Exception {
		return (getDaoBase().insert(obj) == 1);
	}
	
	
	/**
	 * Este m�todo atualiza um cadastro espec�fico.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public boolean update (Object obj) throws Exception {
		return (getDaoBase().update(obj) == 1);
	}
	
	
	public List findPersonalizable () throws Exception {
		return (getDaoBase().findPersonalizable());
	}
	
	

	
	// Getters e Setters

	public DaoBase getDaoBase() {
		return daoBase;
	}

	public void setDaoBase(DaoBase daoBase) {
		this.daoBase = daoBase;
	}
	

}
