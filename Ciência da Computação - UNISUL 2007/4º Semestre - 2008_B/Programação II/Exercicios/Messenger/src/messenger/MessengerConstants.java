package messenger;

/**
 * Esta Interface define algumas constantes que ser�o utilizadas na apla��o.
 * @author Fabio Dela Bruna
 *
 */
public interface MessengerConstants {

	
	/**
	 * N�mero da porta para conectar no servidor.
	 */
	public static final int SERVER_PORT = 12345;
	
	
	/**
	 * IP no qual o servidor est� localizado.
	 */
	public static final String SERVER_IP = "127.0.0.1";
	
	
	/**
	 * Quantidade de clientes que podem esperar na fila para se conectarem.
	 */
	public static final int NUMBER_OF_ROWS = 100;
	
	
	/**
	 * String que cont�m o separador das mensagens.
	 */
	public static final String STRING_SEPARATOR = ">>> ";
	
	
	/**
	 * Define o limite de caracteres que podem ser inseridos nos enterFields.
	 */
	public static final int CHARS_LIMIT = 70;
	
	
}
