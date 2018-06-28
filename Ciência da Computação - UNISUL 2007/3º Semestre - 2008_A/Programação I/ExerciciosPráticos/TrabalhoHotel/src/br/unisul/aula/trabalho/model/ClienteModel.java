package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.unisul.aula.trabalho.entidades.Cliente;
import br.unisul.aula.trabalho.util.CPFValidator;

/**
 * Classe de neg�cio.
 * @author Fabio Dela Bruna / Marcio Teixeira Oz�rio.
 * @since 13/05/2008.
 */
public class ClienteModel implements IClienteModel {

	//atributos

	private ArrayList<Cliente> clientes;


	//construtores

	/**
	 * Construtor com par�metros.
	 * @param clientes - ArrayList de Clientes.
	 */
	public ClienteModel (ArrayList<Cliente> clientes){
		super();
		this.clientes = clientes;
	}


	//m�todos principais

	@Override
	public void atualizaDadosCliente(Cliente antigo, Cliente novo) {
		if(validaCliente(novo))
			antigo = novo;
		else
			msgErro();
	}

	@Override
	public Cliente buscaCliente(String nome, String sobrenome) {
		if((nome != null && nome.length() != 0) && sobrenome == null){
			for(Cliente c : this.clientes){
				if(c.getNome().equals(nome)){
					return c;
				}
			}
		}else if(sobrenome != null && sobrenome.length() != 0){
			for(Cliente c : this.clientes){
				if(c.getSobrenome().equals(sobrenome)){
					return c;
				}
			}
		}else if((sobrenome != null && sobrenome.length() != 0) && (nome != null && nome.length() != 0)){
			for(Cliente c : this.clientes){
				if(c.getSobrenome().equals(sobrenome) && c.getNome().equals(nome)){
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public void cadastraCliente(Cliente c) {
		if(validaCliente(c)) {
			this.clientes.add(c);
		} else { 
			msgErro();
		}
	}

	@Override
	public ArrayList<Cliente> mostraClientes() {
		return this.clientes;
	}

	@Override
	public void removeCliente(Cliente c) {
		this.clientes.remove(c);
	}


	//m�todos auxiliares

	/**
	 * M�todo que verifica se os atributos obrigat�rios de um ciente s�o v�lidos.
	 * @param c - Cliente.
	 * @return true se os atributos s�o v�lidos ou false se n�o forem v�lidos.
	 */
	private boolean validaCliente(Cliente c){
		if(c == null)
			return false;
		if(c.getNome() == null)
			return false;
		if(c.getSobrenome() == null)
			return false;
		if(CPFValidator.validaCpf(c.getCpf()) == false)
			return false;
		if(c.getFone1() == null)
			return false;

		return true;
	}
  


	/**
	 * Mostra uma menssagem de erro sempre que algum
	 * dos dados obrigat�rios estiver incorreto.
	 */
	private void msgErro(){
		JOptionPane.showMessageDialog(null, "DADOS DE CADASTRO INV�LIDOS!", "Erro de cadastro Cliente", JOptionPane.ERROR_MESSAGE);
	}


}
