package br.unisul.transportadora.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unisul.transportadora.bean.CaminhaoBean;
import br.unisul.transportadora.dao.connection.ConexaoJDBC;

/**
 * Classe Java que efetua as opera��es no banco
 * de dados para a entidade Caminhao.
 * @author Fabio Dela Bruna
 */
public class CaminhaoDao {

	/**
	 * M�todo que retorna o maior valor no campo c�digo da tabela.
	 * @return Maior C�digo se houver algum registro na table, caso contr�rio, 0.
	 */
	public Integer getMaxValueFromCodigo() {
		
		String sql = "SELECT MAX(CD_CAM) FROM CAMINHAO";
		Connection con = ConexaoJDBC.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer codigo = null;
		
		try {
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				codigo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return codigo;
	}
	
	
	/**
	 * M�todo que retorna um objeto do tipo Caminh�oBean atrav�z do c�digo.
	 * @param codigo C�digo do caminh�o a ser buscado.
	 * @return Objeto do tipo CaminhaoBean se encontrado, ou null.
	 */
	public CaminhaoBean findById (Integer codigo) {
		
		String sql = "SELECT CD_CAM, DS_CAM FROM CAMINHAO WHERE CD_CAM = ?";
		Connection con = ConexaoJDBC.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		CaminhaoBean cam = null;
		
		try {
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				cam = new CaminhaoBean();
				cam.setCodigo( rs.getInt(1) );
				cam.setDescricao( rs.getString(2) );
			}
			return cam;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cam;
	}
	
	
	/**
	 * M�todo que insere o registro de um caminh�o no banco.
	 * @param cam Objeto do tipo CaminhaoBean a ser inserido.
	 * @return True se inserido com sucesso, caso contr�rio, False.
	 */
	public boolean insertCaminhao(CaminhaoBean cam) {
		
		String sql = "INSERT INTO CAMINHAO (CD_CAM, DS_CAM) VALUES (?, ?)";
		Connection con = ConexaoJDBC.getConnection();
		PreparedStatement ps = null;
		boolean retorno = false;
		
		try {
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, cam.getCodigo());
			ps.setString(2, cam.getDescricao());
			
			retorno = ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return retorno;
	}
	
	
	/**
	 * M�todo que atualiza um determinado registro no banco.
	 * @param cam Objeto do tipo CaminhaoBean atualizado.
	 * @return True se atualizado com sucesso, caso contr�rio, False.
	 */
	public boolean updateCaminhao(CaminhaoBean cam) {
		
		String sql = "UPDATE CAMINHAO SET DS_CAM = ? WHERE CD_CAM = ?";
		Connection con = ConexaoJDBC.getConnection();
		PreparedStatement ps = null;
		boolean retorno = false;
		
		try {
			
			ps = con.prepareStatement(sql);
			ps.setString(1, cam.getDescricao());
			ps.setInt(2, cam.getCodigo());
			
			retorno = ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return retorno;
	}
	

	/**
	 * M�todo que apaga um registro do banco atrav�z do c�digo (PK);
	 * @param codigo C�digo do registro a ser exclu�do.
	 * @return True se exclu�do com sucesso, caso contr�rio, False.
	 */
	public boolean deleteCaminhao(Integer codigo) throws SQLException {
		
		String sql = "DELETE FROM CAMINHAO WHERE CD_CAM = ?";
		Connection con = ConexaoJDBC.getConnection();
		PreparedStatement ps = null;
		boolean retorno = false;
		
		try {
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
				
			retorno = ps.execute();
			
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
	
	
	/**
	 * M�todo que retorna uma lista contendo todos os registros no banco.
	 * @return Lista com todos os registros de caminh�es.
	 */
	public List<CaminhaoBean> findAllCaminhao() {
		
		String sql = "SELECT CD_CAM, DS_CAM FROM CAMINHAO ORDER BY (DS_CAM)";
		Connection con = ConexaoJDBC.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CaminhaoBean> lista = new ArrayList<CaminhaoBean>();
		
		try {
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				CaminhaoBean cam = new CaminhaoBean();
				cam.setCodigo( rs.getInt(1) );
				cam.setDescricao( rs.getString(2) );
				
				lista.add(cam);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return lista;
	}
	
	
}
