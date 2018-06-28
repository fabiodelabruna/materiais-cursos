package compiladorLMS.analise_lexica;

import java.util.Arrays;
import java.util.List;

/**
 * Interface que define as contantes que ser�o
 * utilizadas pela an�lise l�xica.
 * 
 * @author Fabio Dela Bruna, M�rcio Oz�rio Teixeira.
 *
 */
public interface DefinicoesLexicas {

	// Constantes

	/**
	 * Tabela de todos os s�mbolos que o analizado l�xico
	 * deve reconhecer de acordo com a gram�tica definida.
	 */
	public final List<String> SYMBOLS_TABLE = Arrays.asList(
			"PROGRAM", "LABEL", "CONST", "VAR", "PROCEDURE", "BEGIN", "END", "INTEGER", 
			"ARRAY", "OF", "CALL", "GOTO", "IF", "THEN", "ELSE", "WHILE", "DO", "REPEAT", 
			"UNTIL", "READLN", "WRITELN", "OR", "AND", "NOT", "IDENTIFICADOR", "INTEIRO", 
			"FOR", "TO", "CASE", "+", "-", "*", "/", "[", "]", "(", ")", ":=", ":", "=", 
			">", ">=", "<", "<=", "<>", ",", ";", "LITERAL", ".", "..", "$");

	/**
	 * Tabela contendo apenas as palavras reservadas.
	 */
	public final List<String> RESERVED_WORDS_TABLE = Arrays.asList(
			"PROGRAM", "LABEL", "CONST", "VAR", "PROCEDURE", "BEGIN", "END", "INTEGER", 
			"ARRAY", "OF", "CALL", "GOTO", "IF", "THEN", "ELSE", "WHILE", "DO", "REPEAT", 
			"UNTIL", "READLN", "WRITELN", "FOR", "TO", "CASE");

	/**
	 * Tabela contendo apensa os operadores AND, NOT, OR,
	 * que tamb�m s�o palavras reservadas.
	 */
	public final List<String> OPERATORS_TABLE = Arrays.asList("AND", "NOT", "OR");

	/**
	 * Constante para o tipo de token IDENTIFICADOR.
	 */
	public final String IDENTIFICADOR = "IDENTIFICADOR";

	/**
	 * Constante para o tipo de token PALAVRA RESERVADA.
	 */
	public final String PALAVRA_RESERVADA = "PALAVRA RESERVADA";
	
	/**
	 * Constante para o tipo de token OPERADOR.
	 */
	public final String OPERADOR = "OPERADOR";

	/**
	 * Constante para o tipo de token INTEIRO.
	 */
	public final String INTEIRO = "INTEIRO";
	
	/**
	 * Constante para o tipo de token LITERAL.
	 */
	public final String LITERAL = "LITERAL";
	
	/**
	 * Constante para o tipo de token SIMBOLO ESPECIAL.
	 */
	public final String SIMBOLO_ESPECIAL = "S�MBOLO ESPECIAL";
	
	/**
	 * Constante para o tipo de token FINAL DE ARQUIVO.
	 */
	public final String FINAL_DE_ARQUIVO = "FINAL DE ARQUIVO";
	
	
}
