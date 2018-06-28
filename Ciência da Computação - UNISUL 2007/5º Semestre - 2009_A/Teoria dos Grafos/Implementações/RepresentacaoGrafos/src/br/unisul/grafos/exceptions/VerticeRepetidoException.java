package br.unisul.grafos.exceptions;

/**
 * Exce��o que ser� disparada quando o usu�rio tentar
 * inserir um v�rtice que j� esteja inserido no grafo.
 * @author Fabio Dela Bruna, Marcio Oz�rio Teixeira.
 */
public class VerticeRepetidoException extends Exception {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Construtor default.
	 * @param reason String contendo a raz�o da exce��o ter sido disparada.
	 */
	public VerticeRepetidoException(String reason) {
		super(reason);
	}
	
}
