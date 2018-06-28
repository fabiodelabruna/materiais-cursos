package interfaces;

import java.util.ArrayList;

import entidades.Cliente;
import entidades.Estadia;
import entidades.Quarto;
import entidades.Reserva;


/**
 * Interface que define os m�todos de neg�cio da entidade Estadia.
 * @author Fabio Dela Bruna / Carlos Henrique S�
 */
public interface IEstadia {
	
	/**
	 * Efetua o cadastro de uma Estadia.
	 * @param e - Estadia a ser cadastrada.
	 */
	public abstract void cadastraEstadia(Estadia e);
	
	/**
	 * Gera uma estadia atrav�z de uma determinada reserva.
	 * @param r - Reserva.
	 */
	public abstract void geraEstadia(Reserva r);
	
	/**
	 * Efetua uma busca pelas estadias de determiando cliente.
	 * @param c - Cliente.
	 * @return ArrayList de estadias (se o cliente tiver estadias) ou null se n�o tiver.
	 */
	public abstract ArrayList<Estadia> buscaEstadia(Cliente c);
	
	/**
	 * Efetua uma busca pelas estadias que foram feitas para determinado quarto.
	 * @param q - Quarto.
	 * @return ArrayList de estadias (se o quarto tiver estadias feitas) ou null se n�o tiver.
	 */
	public abstract ArrayList<Estadia> buscaEstadia(Quarto q);
	
	/**
	 * Mostra o hist�rico de todas as estadias cadastradas.
	 * @return ArrayList de Estadias.
	 */
	public abstract ArrayList<Estadia> buscaEstadia();

}
