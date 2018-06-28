package br.unisul.grafos.classes;
/**
 * Interface que define os m�todos que comum das
 * classes de representa��o de grafos. Essas classes
 * ter�o obrigatoriamente que implementar esses m�todos.
 * @author Fabio Dela Bruna, M�rcio Oz�rio Teixeira.
 */
public interface ITiposDeGrafos {

	/**
	 * M�todo que monta a representa��o para grafos normais,
	 * ou seja, para grafos n�o-dirigidos e n�o-valorados.
	 * @return String contendo a representa��o do grafo.
	 */
	public String grafoNormal();

	/**
	 * M�todo que monta a representa��o para grafos dirigidos.
	 * @return String contendo a representa��o do grafo.
	 */
	public String grafoDirigido();
	
	/**
	 * M�todo que monta a representa��o para grafos valorados.
	 * @return String contendo a representa��o do grafo.
	 */
	public String grafoValorado();
	
	/**
	 * M�todo que monta a representa��o para grafos dirigidos e valorados.
	 * @return String contendo a representa��o do grafo.
	 */
	public String grafoDirigidoValorado();
}
