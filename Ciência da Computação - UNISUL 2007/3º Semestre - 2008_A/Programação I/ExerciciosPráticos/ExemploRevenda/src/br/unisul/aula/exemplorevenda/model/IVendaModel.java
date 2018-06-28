package br.unisul.aula.exemplorevenda.model;

import java.util.ArrayList;

import br.unisul.aula.exemplorevenda.entidade.Venda;

/**
 * Interface que estabelece os m�todos de neg�cio da entidade Venda.
 * @author Fabio Dela Bruna
 * @since 04/05/2008
 */
public interface IVendaModel {
	
	/**
	 * Efetua uma venda.
	 * @param v - Venda.
	 */
	public abstract void efetuaVenda(Venda v);
	
	/**
	 * Faz uma busca por uma determinada venda;
	 * @param codigo - C�digo da venda.
	 * @param cliente - Cliente comprador.
	 * @return Venda encontrada ou null se n�o encontrado.
	 */
	public abstract Venda buscaVenda(int codigo, String cliente);
	
	/**
	 * Mostra todas as vendas efetuadas.
	 * @return ArrayList de Vendas.
	 */
	public abstract ArrayList<Venda> mostraVendas();	

}
