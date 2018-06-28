package compiladorLMS.analise_sintatica;

import java.util.ArrayList;

/**
 * Classe interna privada que representa uma regra da tabela de parse.
 * @author Fabio Dela Bruna, Marcio Oz�rio Teixeira.
 */
public class Regra {

	/**
	 * Armazena o inteiro correspondente � regra.
	 */
	private int regra;
	
	/**
	 * Armazena o c�digo do terminal correspondente.
	 */
	private int terminal;
	
	/**
	 * Armazena o c�digo do n�o-terminal correspondente.
	 */
	private int naoTerminal;
	
	/**
	 * Armazena as deriva��es � que essa regra leva.
	 */
	private ArrayList<Integer> derivacoes;


	/**
	 * Construtor que recebe os dados por par�metro.
	 */
	public Regra(int reg, int naoTerm, int term, ArrayList<Integer> der) {
		regra = reg;
		naoTerminal = naoTerm;
		terminal = term;
		derivacoes = der;
	}

	/**
	 * Define o formato de sa�da no console para os objetos do tipo Regra.
	 */
	public String toString() {
		return String.format("Regra: %s\tTerminal: %s\tN�o-Terminal: %s\n",
				regra, terminal, naoTerminal);
	}

	/**
	 * Retorna a regra correspondente.
	 */
	public int getRegra() {
		return regra;
	}

	/**
	 * Retorna o terminal que faz parte da regra.
	 */
	public int getTerminal() {
		return terminal;
	}

	/**
	 * Retorna o n�o-terminal que faz parte da regra.
	 */
	public int getNaoTerminal() {
		return naoTerminal;
	}
	
	/**
	 * Retorna as deriva�oes � que a regra leva.
	 */
	public ArrayList<Integer> getDerivacoes() {
		return derivacoes;
	}

}
