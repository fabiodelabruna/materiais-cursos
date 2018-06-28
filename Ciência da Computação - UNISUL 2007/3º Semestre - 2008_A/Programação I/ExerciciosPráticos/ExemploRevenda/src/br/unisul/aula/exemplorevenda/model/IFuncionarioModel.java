package br.unisul.aula.exemplorevenda.model;

import java.util.ArrayList;

import br.unisul.aula.exemplorevenda.entidade.Funcionario;

/**
 * Interface que estabelece os m�todos de neg�cio da entidade Funcionario
 * @author Fabio Dela Bruna
 * @since 05/05/2008
 */
public interface IFuncionarioModel {

	/**
	 * Cadastra um funcion�rio na revenda.
	 * @param f - Funcion�rio.
	 */
	public abstract void cadastraFuncionario(Funcionario f);
	
	/**
	 * Remove um funcion�rio da revenda.
	 * @param f - Funcion�rio.
	 */
	public abstract void removeFuncionario(Funcionario f);
	
	/**
	 * Atualiza os dados cadastrais de um funcionario da revenda.
	 * @param antigo - Funcion�rio com dados desatualizados.
	 * @param novo - Funcion�rio com dados atualizados.
	 */
	public abstract void atualizaFuncionario(Funcionario antigo, Funcionario novo);
	
	/**
	 * Busca um funcion�rio espec�fico.
	 * @param nome - Nome do funcion�rio.
	 * @param cpf - Cpf do funcion�rio.
	 * @return Funcionario encontrado ou null se n�o encontrou.
	 */
	public abstract Funcionario buscaFuncionario(String nome, String cpf);
	
	/**
	 * Lista todos os funcion�rios da revenda.
	 * @return - Lista de Funcion�rios.
	 */
	public abstract ArrayList<Funcionario> mostraFuncionarios();
}

