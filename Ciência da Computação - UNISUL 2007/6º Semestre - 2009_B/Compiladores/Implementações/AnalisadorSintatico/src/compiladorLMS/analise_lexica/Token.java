package compiladorLMS.analise_lexica;

/**
 * Classe que representa um token da an�lise l�xica.
 * @author Fabio Dela Bruna, Marcio Oz�rio Teixeira.
 *
 */
public class Token {
	
	/**
	 * Representa o c�digo do token.
	 */
	private int codigo;
	
	/**
	 * Representa o tipo do token.
	 */
	private String tipo;

	/**
	 * Representa o token em quest�o ou o texto que foi
	 * digitado pelo usu�rio e reconhecido pela an�lise l�xica.
	 */
	private String token;
	
	
	
	/**
	 * Construtor que recebe os dados do token por par�metro.
	 */
	public Token( int codigo, String token, String tipo) {
		this.codigo = codigo;
		this.token = token;
		this.tipo = tipo;
	}
	
	
	/**
	 * Retorna o c�digo do token.
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Retorna o tipo do token.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Retorna o token digitado pelo usu�rio e reconhecido pela an�lise l�xica.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Seta o token digitado pelo usu�rio.
	 */
	public void setToken( String t ) {
		token = t;
	}
	
}
