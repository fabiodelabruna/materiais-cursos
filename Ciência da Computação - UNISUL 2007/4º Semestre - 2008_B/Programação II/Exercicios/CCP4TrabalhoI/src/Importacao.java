import java.io.Serializable;

/**
 * Classe que representa o objeto de cada linha dos textos dos arquivos txt`s.
 * @author M�rcio Oz�rio Teixeira / Edu Schmoller.
 * @since 01/09/2008.
 */
public class Importacao implements Serializable{

	private static final long serialVersionUID = 1L;
	private String livro;
	private int capitulo;
	private int vers�culo;
	private String texto;

	/**
	 * Construtor padr�o
	 */
	public Importacao() {
		super();
	}

	/**
	 * Construtor com par�metros
	 * @param nomeLivro
	 * @param capitulo
	 * @param vers�culo
	 * @param texto
	 */
	public Importacao(String nomeLivro, int capitulo, int vers�culo,
			String texto) {
		super();
		this.livro = nomeLivro;
		this.capitulo = capitulo;
		this.vers�culo = vers�culo;
		this.texto = texto;
	}

	
	/**
	 * Getters e Setters
	 */
	
	public String getNomeLivro() {
		return livro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.livro = nomeLivro;
	}

	public int getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(int capitulo) {
		this.capitulo = capitulo;
	}

	public int getVers�culo() {
		return vers�culo;
	}

	public void setVers�culo(int vers�culo) {
		this.vers�culo = vers�culo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	
}
