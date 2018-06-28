package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import br.unisul.aula.trabalho.entidades.Funcionario;

/**
 * Interface que define os m�todos de neg�cio da entidade Funcionario.
 * @author Fabio Dela Bruna / Marcio Oz�rio Teixeira.
 * @since 12/06/2008.
 */
public interface IFuncionarioModel {

	/**
	 * M�todo para cadastrar um funcion�rio.
	 * @param f - Funcion�rio a ser cadastrado.
	 */
	public abstract void cadastraFuncionario(Funcionario f);

	/**
	 * M�todo para remover o cadastro de um funcion�rio.
	 * @param f - Funcionario � remover.
	 */
	public abstract void removeFuncionario(Funcionario f);
	
	/**
	 * M�todo para atualizar os dados cadastrais de um Funcionario.
	 * @param antigo - Dados cadastrais antigos.
	 * @param novo - Dados cadastrais atualizados.
	 */
	public abstract void atualizaDadosFuncionario(Funcionario antigo, Funcionario novo);
	
	/**
	 * M�todo para efetuar uma busca por um funcion�rio espec�fico.
	 * @param nome - Nome do funcion�rio.
	 * @return ArrayList com os funcionarios que contem o mesmo nome.
	 */
	public abstract ArrayList<Funcionario> buscaFuncionario(String nome);
	
	/**
	 * M�todo que retorna todos os funcion�rios cadastrados.
	 * @return ArrayList de funcion�rios cadastrados.
	 */
	public abstract ArrayList<Funcionario> buscaFuncionario();
}
