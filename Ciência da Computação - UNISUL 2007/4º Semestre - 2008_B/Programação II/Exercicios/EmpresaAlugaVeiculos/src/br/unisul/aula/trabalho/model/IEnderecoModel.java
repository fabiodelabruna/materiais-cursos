package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import br.unisul.aula.trabalho.entidades.Endereco;

/**
 * Interface que define os m�todos de neg�cio da entidade Endereco.
 * @author Fabio Dela Bruna / Marcio Oz�rio Teixeira.
 * @since 12/06/2008.
 */
public interface IEnderecoModel {

	/**
	 * M�todo para cadastrar um determinado endere�o.
	 * @param e - Endere�o a ser cadastrado.
	 */
	public abstract void cadastraEndereco(Endereco e);
	
	/**
	 * M�todo para remover o cadastro de um endereco.
	 * @param e - Endereco a ser removido.
	 */
	public abstract void removeEndereco(Endereco e);
	
	/**
	 * M�todo para atualizar os dados cadastrais de um endereco.
	 * @param antigo - Dados cadastrais desatualizados.
	 * @param novo - Dados cadastrais atualizados.
	 */
	public abstract void atualizaDadosEndereco(Endereco antigo, Endereco novo);
	
	/**
	 * M�todo para mostrar todos os enderecos cadastrados.
	 * @return ArrayList de enderecos cadastrados.
	 */
	public abstract ArrayList<Endereco> mostraEnderecos();
}
