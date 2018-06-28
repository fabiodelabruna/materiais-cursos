package br.unisul.aula.interfaceXimplementacao;


/**
 * Interface que estabelece os m�todos da classe Manutencao.
 * @author Fabio Dela Bruna
 * @since 18/03/2008
 */
public interface IManutencao {
	
	
	/**
	 * Efetua o concerto do Pneu..
	 */
	public abstract void concertaPneu();
	
	
	/**
	 * Retorna se o ve�culo tem �leo ou n�o..
	 * @return String
	 */
	public abstract boolean verificaOleo();

}
