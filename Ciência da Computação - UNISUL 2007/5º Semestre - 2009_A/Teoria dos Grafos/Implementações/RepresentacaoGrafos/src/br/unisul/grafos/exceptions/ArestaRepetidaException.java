package br.unisul.grafos.exceptions;

/**
 * Exce��o que ser� disparada quando o usu�rio tentar
 * inserir uma aresta que j� esteja inserida no grafo.
 * @author Fabio Dela Bruna, Marcio Oz�rio Teixeira.
 */
public class ArestaRepetidaException extends Exception {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Construtor default.
	 * @param reason String contendo a raz�o da exce��o ter sido disparada.
	 */
	public ArestaRepetidaException(String reason) {
		super(reason);
	}
	
}
