package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.unisul.aula.trabalho.entidades.Endereco;


/**
 * Classe de neg�cio.
 * @author Fabio Dela Bruna / Marcio Oz�rio Teixeira.
 * @since 17/06/2008.
 */
public class EnderecoModel implements IEnderecoModel{

	//attributes
	
	private ArrayList<Endereco> enderecos;
	
	
	//constructors
	
	/**
	 * Construtor com par�metros
	 * @param enderecos - ArrayList de Endere�os.
	 */
	public EnderecoModel (ArrayList<Endereco> enderecos) {
		super();
		this.enderecos = enderecos;
	}


	//main methods
	
	@Override
	public void atualizaDadosEndereco(Endereco antigo, Endereco novo) {
		if (validaEndereco(novo)) {
			this.enderecos.remove(antigo);
			this.enderecos.add(novo);
		} else {
			msgErro();
		}
		
	}


	@Override
	public void cadastraEndereco(Endereco e) {
		if (validaEndereco(e)){
			this.enderecos.add(e);
		} else {
			msgErro();
		}
	}


	@Override
	public ArrayList<Endereco> mostraEnderecos() {
		return this.enderecos;
	}


	@Override
	public void removeEndereco(Endereco e) {
		this.enderecos.remove(e);		
	}
	
	
	//m�todos auxiliares
	
	/**
	 * M�todo que verifica se os atributos obrigat�rios de um endere�o s�o v�lidos.
	 * @param e - Endere�o.
	 * @return true se os atributos s�o v�lidos ou false se n�o forem v�lidos.
	 */
	private boolean validaEndereco (Endereco e) {
		if (e == null)
			return false;
		if (e.getEstado() == null)
			return false;
		if (e.getCidade() == null)
			return false;
		if (e.getBairro() == null)
			return false;
		if (e.getRua() == null)
			return false;
		if (e.getNumero() <= 0)
			return false;
		if (e.getCep() == null)
			return false;
		
	return true;
	}
	
	/**
	 * Mostra uma menssagem de erro sempre que algum
	 * dos dados obrigat�rios estiver incorreto.
	 */
	private void msgErro(){
		JOptionPane.showMessageDialog(null, "DADOS DE CADASTRO INV�LIDOS!", "Erro de cadastro Endere�o", JOptionPane.ERROR_MESSAGE);
	}
}
