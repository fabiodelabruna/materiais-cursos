package br.unisul.aula.trabalho.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.unisul.aula.trabalho.dao.ConPooling;
import br.unisul.aula.trabalho.dto.Cliente;
import br.unisul.aula.trabalho.dto.Endereco;
import br.unisul.aula.trabalho.util.CPFValidator;

/**
 * Classe de neg�cio.
 * 
 * @author Fabio Dela Bruna / Marcio Teixeira Oz�rio.
 * @since 13/05/2008.
 */
public class ClienteModel implements IClienteModel {

	private static ConPooling cn = ConPooling.getInstance();
	private ArrayList<Cliente> clientes;

	/**
	 * Construtor com par�metros.
	 * @param clientes - ArrayList de Clientes
	 */
	public ClienteModel(ArrayList<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

	@Override
	public void atualizaDadosCliente(Cliente antigo, Cliente novo)
			throws IllegalArgumentException {
		validaCliente(novo);
		antigo = novo;
	}

	@Override
	public Cliente buscaCliente(String nome, String sobrenome) {
		if ((nome != null && nome.length() != 0) && sobrenome == null) {
			for (Cliente c : this.clientes) {
				if (c.getNome().equals(nome)) {
					return c;
				}
			}
		} else if (sobrenome != null && sobrenome.length() != 0) {
			for (Cliente c : this.clientes) {
				if (c.getSobrenome().equals(sobrenome)) {
					return c;
				}
			}
		} else if ((sobrenome != null && sobrenome.length() != 0)
				&& (nome != null && nome.length() != 0)) {
			for (Cliente c : this.clientes) {
				if (c.getSobrenome().equals(sobrenome)
						&& c.getNome().equals(nome)) {
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public boolean cadastraCliente(Cliente c) throws IllegalArgumentException {
		
		boolean cadastrado = false;
		
		validaCliente(c);
		
		Connection con = cn.getConnection();
		
		try {
			
			String sql = "INSERT INTO CLIENTE (NOME, SOBRENOME, DATA_NASCIMENTO, CPF, TELEFONE1, TELEFONE2, COD_ENDERECO, COD_ESTADIA)" +
							"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, c.getNome());
			statement.setString(2, c.getSobrenome());			
			statement.setString(3, c.getDataNascimento());
			statement.setString(4, c.getCpf());
			statement.setString(5, c.getFone1());
			
			int codigo = pegaCodigoEndereco(c.getEndereco());
			statement.setInt(6, codigo);
			
			statement.execute();
			
			cadastrado = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return cadastrado;
	}

	@Override
	public ArrayList<Cliente> mostraClientes() {

		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		String sql = "SELECT C.CODIGO, C.NOME, C.SOBRENOME, C.CPF, C.DATA_NASCIMENTO, C.TELEFONE1, C.TELEFONE2" +
						"E."
				"FROM CLIENTE C, ENDERECO E";
	}

	@Override
	public void removeCliente(Cliente c) {
		this.clientes.remove(c);
	}
	 
	
	/**
	 * M�todo para pegar o c�digo de um endereco determinado.
	 * @param e Endereco em quest�o.
	 * @return c�digo do endereco.
	 */
	private int pegaCodigoEndereco(Endereco e) {
		int codigo = 0;
		Connection con = cn.getConnection();
		
		try {
			
			String sql = "SELECT CODIGO FROM PESSOA WHERE NUMERO = " + e.getNumero() + ", RUA = " + e.getRua() +
							", BAIRRO = " + e.getBairro() +	", CIDADE = " + e.getCidade() + ", ESTADO = " + e.getEstado();
			
			Statement stm = con.createStatement();
			
			ResultSet rs = stm.executeQuery(sql);
			
			codigo = rs.getInt("codigo");
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return codigo;
	}

	/**
	 * M�todo que verifica se os atributos obrigat�rios de um ciente s�o v�lidos.
	 * @param c Cliente em quest�o.
	 * @throws IllegalArgumentException
	 */
	private void validaCliente(Cliente c) throws IllegalArgumentException {
		if (c.getNome() == null || c.getNome().trim().length() == 0)
			throw new IllegalArgumentException("Nome incorreto!");
		if (c.getSobrenome() == null || c.getSobrenome().trim().length() == 0)
			throw new IllegalArgumentException("Sobrenome incorreto!");
		if (CPFValidator.validaCpf(c.getCpf()) == false)
			throw new IllegalArgumentException("CPF inv�lido!");
		if (c.getFone1() == null)
			throw new IllegalArgumentException("Telefone inv�lido!");
		if (c.getEndereco() == null)
			throw new IllegalArgumentException("Endere�o inv�lido!");

		if (c.getEndereco().getEstado() == null
				|| c.getEndereco().getEstado().trim().length() == 0)
			throw new IllegalArgumentException("Estado inv�lido!");
		if (c.getEndereco().getCidade() == null
				|| c.getEndereco().getCidade().trim().length() == 0)
			throw new IllegalArgumentException("Cidade inv�lida!");
	}

}
