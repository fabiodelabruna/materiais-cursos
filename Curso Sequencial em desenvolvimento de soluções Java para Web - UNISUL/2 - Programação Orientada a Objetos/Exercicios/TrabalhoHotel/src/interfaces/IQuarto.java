package interfaces;

import java.util.ArrayList;

import entidades.Quarto;


/**
 * Interface que define os m�todos de neg�cio da entidade Quarto.
 * @author Fabio Dela Bruna / Carlos Henrique S�
 */
public interface IQuarto {

	/**
	 * M�todo para cadastrar um determinado quarto no hotel.
	 * @param q - quarto a ser cadastrado.
	 */
	public abstract void cadastraQuarto(Quarto q);
	
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
