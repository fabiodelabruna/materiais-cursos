package entidades;

/**
 * Classe que representa um quarto. 
 * @author Fabio Dela Bruna / Carlos Henrique S�
 */
public abstract class Quarto {
	
	//atributos
	
	private int qtdPessoas;
	private double valorDiaria;
	
	
	//construtores
	
	/**
	 * Construtor default.
	 */
	public Quarto() {
		super();
	}


	/**
	 * Construtor com par�metros.
	 * @param qtdPessoas - Quantidades de pessoas.
	 * @param valorDiaria - Valor da Di�ria.
	 */
	public Quarto(int qtdPessoas, double valorDiaria) {
		super();
		this.qtdPessoas = qtdPessoas;
		this.valorDiaria = valorDiaria;
	}

	
	//m�todos principais

	/**
	 * M�todo para pegar o tipo do quarto.
	 */
	public abstract String getTipoQuarto();
	
	
	//getters e setters
	
	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	
	//toString
	
	public String toString(){
		return String.format("Qtd Pessoas: %s\nValor Di�ria: %s", this.qtdPessoas, this.valorDiaria );
	}
	

}