
public abstract class Pessoa {
	
	// atributos
	
	private String nome;
	private int idade;
	
	
	// construtor
	
	/**
	 * Construtor com par�metros.
	 * @param nome - Nome da Pessoa
	 * @param idade - Idade da Pessoa
	 */
	public Pessoa(String nome, int idade){
		super();
		this.nome = nome;
		this.idade = idade;
	}

	
	// getters e setters
	
	/**
	 * @return the idade
	 */
	public int getIdade() {
		return idade;
	}

	/**
	 * @param idade the idade to set
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	// m�todos
	
	/**
	 * M�todo que verifica se a pessoa em quest�o possui dependentes.
	 */
	public abstract boolean verificaDependentes();
	

}
