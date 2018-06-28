package AnalisadorLexico.Nucleo;
/**
 * Classe de Tokens.
 * @author 	Hideraldo Lu�s Simon J�nior
 * 			Marco Aur�lio Lima Fernandes
 * 			Rodrigo Paschoal Jeremias
 * @since setembro de 2008
 *
 */
public class Token {

	private int codigo;
	private String nome, descricao;
	
	/**
	 * Construtor da classe
	 * @param codigo int - c�digo do Token
	 * @param nome String - nome do Token
	 * @param descricao String - descri��o do Token
	 */
	public Token(int codigo, String nome, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.nome = nome;
	}

	/**
	 * Retorna o c�digo do Token
	 * @return int
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Altera o c�digo do Token
	 * @return int - novo c�digo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna o nome do Token
	 * @return String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome do Token
	 * @param nome String - novo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna a descri��o do Token
	 * @return String
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Altera a descri��o do Token
	 * @param descricao String - nova descri��o
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
