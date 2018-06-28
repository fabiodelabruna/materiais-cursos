package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.unisul.aula.trabalho.entidades.Funcionario;
import br.unisul.aula.trabalho.util.CPFValidator;


/**
 * Classe de neg�cio.
 * @author Fabio Dela Bruna / Marcio Oz�rio Teixeira.
 * @since 17/06/2008.
 */
public class FuncionarioModel implements IFuncionarioModel{
	
	//attributes
	
	private ArrayList<Funcionario> funcionarios;
	
	
	//constructors
	
	/**
	 * Construtor com par�metros.
	 * @param funcionarios - ArrayList de Funcionarios.
	 */
	public FuncionarioModel (ArrayList<Funcionario> funcionarios) {
		super();
		this.funcionarios = funcionarios;
	}


	//main methods
	
	@Override
	public void atualizaDadosFuncionario(Funcionario antigo, Funcionario novo) {
		if (validaFuncionario(novo)) {
			novo.setCodigo(antigo.getCodigo());
			this.funcionarios.remove(antigo);
			this.funcionarios.add(novo);
		} else {
			msgErro();
		}
	}

	@Override
	public ArrayList<Funcionario> buscaFuncionario(String nome) {
		ArrayList<Funcionario> f = new ArrayList<Funcionario>();
		
		for (int i = 0; i < this.funcionarios.size(); i++) {
			if (this.funcionarios.get(i).getNome().contains(nome))
				f.add(this.funcionarios.get(i));
		}
		return f;
	}

	@Override
	public void cadastraFuncionario(Funcionario f) {
		if (validaFuncionario(f)) {
			this.funcionarios.add(f);
		} else {
			msgErro();
		}
	}

	@Override
	public ArrayList<Funcionario> buscaFuncionario() {
		return this.funcionarios;
	}

	@Override
	public void removeFuncionario(Funcionario f) {
		this.funcionarios.remove(f);	
	}

	
	//m�todos auxiliares
	
	/**
	 * M�todo que verifica se os atributos obrigat�rios de um ciente s�o v�lidos.
	 * @param c - Cliente.
	 * @return true se os atributos s�o v�lidos ou false se n�o forem v�lidos.
	 */
	private boolean validaFuncionario (Funcionario f) {
		if (f == null)
			return false;
		if (f.getNome() == null)
			return false;
		if (f.getSobrenome() == null)
			return false;
		if (f.getFone1() == null)
			return false;
		if (CPFValidator.validaCpf(f.getCpf()) == false)
			return false;
	
	return true;
	}
	
	/**
	 * Mostra uma menssagem de erro sempre que algum
	 * dos dados obrigat�rios estiver incorreto.
	 */
	private void msgErro(){
		JOptionPane.showMessageDialog(null, "DADOS DE CADASTRO INV�LIDOS!", "Erro de cadastro Funcion�rio", JOptionPane.ERROR_MESSAGE);
	}
	
}
