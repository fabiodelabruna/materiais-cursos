
/**
 * Classe que representa um endere�o.
 * @author Fabio Dela Bruna.
 * @since 10/04/2008.
 */
public class Endereco {

	
	// Atributos
	
	private String estado;
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	private int cep;

	
	// Construtores
	
	/**
	 * Construtor default.
	 */
	public Endereco() {
		this("", "", "", "", 0, 0);
	}
	
	/**
	 * Construtor com par�metros.
	 * @param estado - Estado;
	 * @param cidade - Cidade;
	 * @param bairro - Bairo;
	 * @param rua - Rua;
	 * @param numero - N�mero da casa;
	 * @param cep - CEP.
	 */
	public Endereco(String estado, String cidade, String bairro, String rua, int numero, int cep) {
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
	}

	
	// Getters and Setters
	
	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the cep
	 */
	public int getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(int cep) {
		this.cep = cep;
	}

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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the rua
	 */
	public String getRua() {
		return rua;
	}

	/**
	 * @param rua the rua to set
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}

	
	// equals e toString
	
	/**
	 * Verifica se j� h� um determinado endere�o cadastrado.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Endereco) {
			Endereco endereco = (Endereco) obj;
			return (this.cep == endereco.cep);
		}
		return false;
	}

	/**
	 * M�todo toString para mostrar o Endere�o;
	 */
	public String toString() {
		return "\nRua: " + this.rua + ",	N�mero: " + this.numero + ",	CEP: " + this.cep + "\nBairro: " + this.getBairro() + "\nCidade: " + this.getCidade() + "\nEstado: " + this.getEstado();
	}
}