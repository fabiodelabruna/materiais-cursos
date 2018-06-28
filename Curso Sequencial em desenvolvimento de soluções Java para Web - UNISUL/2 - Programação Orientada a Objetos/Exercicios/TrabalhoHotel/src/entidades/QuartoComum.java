package entidades;

/**
 * Classe que representa um Quarto do tipo Comum.
 * @author Fabio Dela Bruna / Carlos Henrique S�
 *
 */
public class QuartoComum extends Quarto {
	
	//atributos
	
	private static final String TIPO = "COMUM";

	
	//construtores
	
	/**
	 * Construtor default.
	 */
	public QuartoComum() {
		super();
	}
	
	/**
	 * Construtor com par�metros.
	 */
	public QuartoComum(int qtdPessoas, double valorDiaria) {
		super(qtdPessoas, valorDiaria);
	}
	
	
	//m�todo principal
	
	@Override
	public String getTipoQuarto(){
		return TIPO;
	}
	
	
	//toString
	
	public String toString(){
		return String.format("Tipo Quarto: %s\n%s\n", TIPO, super.toString());
	}

}
