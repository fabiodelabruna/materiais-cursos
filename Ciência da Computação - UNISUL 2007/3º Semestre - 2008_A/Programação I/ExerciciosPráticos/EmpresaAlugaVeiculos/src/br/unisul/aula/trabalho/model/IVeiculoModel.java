package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import br.unisul.aula.trabalho.entidades.Veiculo;

/**
 * Interface que define os m�todos de neg�cio da entidade Veiculo.
 * @author Fabio Dela Bruna / Marcio Oz�rio Teixeira.
 * @since 12/06/2008.
 */
public interface IVeiculoModel {

	/**
	 * M�todo para cadastrar um ve�culo.
	 * @param v - Veiculo a ser cadastrado.
	 */
	public abstract void cadastraVeiculo(Veiculo v);
	
	/**
	 * M�todo para remover o cadastro de um ve�culo.
	 * @param v - Veiculo � ser removido.
	 */
	public abstract void removeVeiculo(Veiculo v);
	
	/**
	 * M�todo para atualizar os dados cadastrais de um determinado ve�culo.
	 * @param novo - Dados cadastrais atualizados.
	 * @param antigo - Dados cadastrais desatualizados.
	 */
	public abstract void atualizaDadosVeiculo(Veiculo novo, Veiculo antigo);
	
	/**
	 * M�todo que retorna todos os ve�culos cadastrados na concession�ria.
	 * @return ArrayList de ve�culos cadastrados.
	 */
	public abstract ArrayList<Veiculo> mostraVeiculos();
	
}
