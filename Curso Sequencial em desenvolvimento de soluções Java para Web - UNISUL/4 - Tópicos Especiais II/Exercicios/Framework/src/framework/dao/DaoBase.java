package framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DaoBase {

	private Connection con;
	
	
	/**
	 * Construtor default.
	 * @throws Exception
	 */
	public DaoBase() throws Exception {
		con = ConexaoBanco.getInstance().getConnection();
	}
	
	
	/**
	 * M�todo abstrato que retorna o nome da tabela a ser consultada.
	 * @return Nome da tabela a ser consultada.
	 */
	protected abstract String getTableName();
	
	
	/**
	 * M�todo que recebe por par�metro um ResultSet e retorna um Objeto.
	 * @param rs ResultSet contendo as informa��es do banco.
	 * @return Objeto.
	 */
	protected abstract Object populaDomainBase(ResultSet rs) throws Exception;
	
	
	/**
	 * M�todo abstrato que pega a chame prim�ria da tabela.
	 * @return Chave prim�ria.
	 */
	protected abstract String getWherePrimaryKey();
	
	
	/**
	 * M�todo abstrato que monta o preparedStatement de
	 * Acordo com o tipo do objeto e a chave prim�ria.
	 * @param stm Prepared Statement.
	 * @param obj Objeto.
	 * @throws Exception
	 */
	protected abstract void populaPrimaryKeyStatement (PreparedStatement stm, Object obj) throws Exception;
	
	
	/**
	 * M�todo abstrato que monta o preparedStatement do objeto como um todo.
	 * @param stm Prepared Statement.
	 * @param obj Objeto.
	 * @throws Exception
	 */
	protected abstract void populaObjectStatment (PreparedStatement stm, Object obj) throws Exception;
	
	
	/**
	 * M�todo que retorna os poss�veis joins com outras tabelas.
	 * @return Joins poss�veis na busca.
	 */
	protected abstract String getJoins();
	
	
	/**
	 * M�todo abstrato que retorna o whereByExample.
	 * @param obj Objeto a ser buscado no banco.
	 * @return whereByExample.
	 */
	protected abstract String getWhereByExample(int cd, String ds);
	
	
	/**
	 * M�todo abstrato para pegar o nome da coluna que � PrimaryKey.
	 * @return Nome da coluna PrimayKey.
	 */
	protected abstract String getPrimaryKeyColumn();
	
	
	/**
	 * M�todo abstrato para pegar o nome de todas as
	 * colunas da tabela, exceto a que � PrimaryKey.
	 * @return String com o nome das colunas.
	 */
	protected abstract String getColumnsWithoutPrimaryKey();
	
	protected abstract List sqlPersolizable(Connection con) throws Exception;
	
	
	
	
	/**
	 * Metodo para buscar todos os cadastros de Pessoa no banco.
	 * @return Lista de pessoas cadastradas no banco.
	 * @throws Exception
	 */
	public List findAll() throws Exception {
		List list = new ArrayList();
		
		String sql = "SELECT * FROM " + getTableName() + getJoins();
		
		PreparedStatement stm = con.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		
		while (rs.next()) {
			list.add( populaDomainBase(rs) );
		}
		return list;
	}
	
	
	/**
	 * M�todo que efetua uma busca personalizada no banco.
	 * @param obj Objeto a ser buscado no banco.
	 * @return Lista de objetos que foram econtrados na pesquisa.
	 * @throws Exception
	 */
	public List findAllByExample(int cd, String ds) throws Exception {
		List list = new ArrayList();
		
		String sql = "SELECT * FROM " + getTableName() + " WHERE " + getWhereByExample(cd, ds);
		
		PreparedStatement stm = con.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		
		while (rs.next()) {
			list.add( populaDomainBase(rs) );
		}
		return list;
	}
	
	/**
	 * M�todo de busca personalizado para o relat�sio.
	 * @return Lista de resultado.
	 * @throws Exception
	 */
	public List findPersonalizable() throws Exception {
		return sqlPersolizable(con);
	}
	
	
	/**
	 * Metodo para apagar o cadastro de um determinado objeto no banco.
	 * @param obj Objeto a ser apagado do banco.
	 * @return Quantidade de registros afetados com a opera��o.
	 * @throws Exception
	 */
	public int delete(Object obj) throws Exception {
		String sql = "DELETE FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";    // getWherePrimaryKey();
		
		PreparedStatement stm = con.prepareStatement(sql);
		populaPrimaryKeyStatement(stm, obj);
		
		return stm.executeUpdate();
	}
	
	
	/**
	 * M�todo para inserir um cadastro
	 * @param ocj
	 * @return
	 * @throws Exception
	 */
	public int insert(Object obj) throws Exception {
		StringBuilder sql = new StringBuilder("INSERT INTO " + getTableName()
				+ " (" + getColumnsWithoutPrimaryKey() + " , " + getPrimaryKeyColumn() + ") VALUES ("); 
		
		int countColunas = (getColumnsWithoutPrimaryKey() + "," + getPrimaryKeyColumn()).split(",").length;
		
		for (int i = 0; i < countColunas-1; i++) {
			sql.append("?, ");
		}
		sql.append("?)");
		
		PreparedStatement stm = con.prepareStatement(sql.toString());
		populaObjectStatment(stm, obj);
		
		return stm.executeUpdate();
	}
	
	
	public int update(Object obj) throws Exception {
		StringBuilder sql = new StringBuilder("UPDATE " + getTableName() + " SET ");
		
		String[] colunas = getColumnsWithoutPrimaryKey().split(",");
		String colunaPrimaryKey = getPrimaryKeyColumn();
		
		for (int i = 0; i < colunas.length-1; i++) {
			sql.append(colunas[i] + " = ?, ");			
		}
		
		sql.append(colunas[colunas.length-1] + " = ? ");
	
		sql.append("WHERE " + colunaPrimaryKey + " = ?" );
		
		PreparedStatement stm = con.prepareStatement(sql.toString());
		populaObjectStatment(stm, obj);
		
		return stm.executeUpdate();
	}
	
	
	
	
	
	
}
 