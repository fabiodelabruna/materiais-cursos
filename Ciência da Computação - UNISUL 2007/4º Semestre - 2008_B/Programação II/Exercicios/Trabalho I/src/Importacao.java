
import java.io.Serializable;

/**
 * Classe que representa um objeto de cada linha dos textos dos
 * arquivos .txt que ser�o lidos e posteriormente serializados.
 * 
 * @author Fabio Dela Bruna / Juliete Braga.
 * @since 26/08/2008.
 */
public class Importacao implements Serializable {

	// ATRIBUTOS
	
	private static final long serialVersionUID = 1L;
	private String nomeLivro;
	private byte capitulo;
	private byte versiculo;
	private String texto;
	
	
	// CONSTRUTORES
		
	/**
	 * Construtor default.
	 */
	public Importacao() {
		
	}

	/**
	 * Construtor com par�metros.
	 * 
	 * @param nomeLivro - Nome do livro.
	 * @param capitulo - N�mero do cap�tlo.
	 * @param versiculo - N�mero do vers�culo.
	 * @param texto - Textos.
	 */
	public Importacao(String nomeLivro, byte capitulo, byte versiculo,
			String texto) {
		super();
		this.nomeLivro = nomeLivro;
		this.capitulo = capitulo;
		this.versiculo = versiculo;
		this.texto = texto;
	}


	// GETTERS E SETTERS
	
	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public int getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(byte capitulo) {
		this.capitulo = capitulo;
	}

	public int getVersiculo() {
		return versiculo;
	}

	public void setVersiculo(byte versiculo) {
		this.versiculo = versiculo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	// TO STRING
	
	/**
	 * M�todo para definir como deve ser impresso os objetos do tipo Importa��o.
	 */
	public String toString() {
		return String.format("Livro: %s\nCap�tulo: %s\nVers�culo: %s\nTexto: %s",
				this.nomeLivro, this.capitulo, this.versiculo, this.texto);
	}

}
