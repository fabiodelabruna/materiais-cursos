package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import br.unisul.aula.trabalho.entidades.Aluguel;
import br.unisul.aula.trabalho.entidades.Cliente;
import br.unisul.aula.trabalho.entidades.Veiculo;

/**
 * Interface que define os m�todos de neg�cio da entidade Aluguel.
 * @author Fabio Dela Bruna / Marcio Oz�rio Teixeira.
 * @since 12/06/2008.
 */
public interface IAluguelModel {

	/**
	 * M�todo para cadastrar um determinado aluguel.
	 * @param a - Aluguel a ser cadastrado.
	 */
	public abstract void cadastraAluguel(Aluguel a);
	
	/**
	 * M�todo que returna uma lista de todos os alugueis de um determinado ve�culo.
	 * @param v - Ve�culo a ser buscado.
	 * @return ArrayList de aluguesi de um determinado ve�culo.
	 */
	public abstract ArrayList<Aluguel> buscaAluguel(Veiculo v);
	
	/**
	 * M�todo que returna uma lista de todos os alugueis de um determinado ve�culo.
	 * @param c - Cliente a ser buscado.
	 * @return ArrayList de alugueis de um determinado Cliente.
	 */
	public abstract ArrayList<Aluguel> buscaAluguel(Cliente c);
	
	/**
	 * Metodo que mostra um hist�rico de todos os alugueis j� efetuados.
	 * @return ArrayList com o hist�rico de alugueis efetuados.
	 */
	public abstract ArrayList<Aluguel> buscaAluguel();
	
	
}
