


/**
 * Classe que representa um aeroporto.
 * @author Fabio Dela Bruna, M�rcio Oz�rio.
 * @since 01/04/2008.
 */
public class Aeroporto {

	
	// Atributos
	
	private String nome;
	private String cidade;

	
	// Construtores
	
	/**
	 * Construtor default.
	 */
	public Aeroporto(){
		this("n�o cadastrado","n�o cadastrado");
	}
	
	/**
	 * Construtor com parametros (com dados do aeroporto);
	 * @param nome - Nome;
	 * @param cidade - Cidade;
	 */
	public Aeroporto(String nome, String cidade){
		this.nome = nome;
		this.cidade = cidade;
	}
	

	// getters and setters
	
	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
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

	
	// equals e toString
	
	/**
	 * M�todo para verificar se determinado aeroporto j� est� cadastrado.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Aeroporto) {
			Aeroporto aeroporto = (Aeroporto) obj;
			return this.nome == aeroporto.nome;
		}
		return false;
	}

	/**
	 * M�todo para mostrar os aeroportos cadastrados.
	 */
	public String toString() {
		return this.nome + " (" + this.cidade+")";
	}
}