package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import br.unisul.aula.trabalho.dto.Quarto;

/**
 * Interface que define os m�todos de neg�cio da entidade Quarto.
 * @author Fabio Dela Bruna / M�rcio Teixeira Oz�rio.
 * @since 14/05/2008.
 */
public interface IQuartoModel {

	/**
	 * M�todo para cadastrar um determinado quarto no hotel.
	 * @param q - quarto a ser cadastrado.
	 * @throws IllegalArgumentException
	 */
	public abstract void cadastraQuarto(Quarto q)
			throws IllegalArgumentException;
	
	/**
	 * M�todo para remover um determinado quarto do hotel.
	 * @param q - quarto a ser removido.
	 */
	public abstract void removeQuarto(Quarto q);
	
	/**
	 * M�todo que mostra todos os quartos cadastrados.
	 * @return - ArrayList de Quartos.
	 */
	public abstract ArrayList<Quarto> mostraQuartos();
}
