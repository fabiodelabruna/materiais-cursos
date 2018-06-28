
/**
 * Classe que representa v�os de determinadas companhias.
 * @author Fabio Dela Bruna.
 * @since 30/03/2008.
 */
public class Voo {

	
	// Atributos
	
	private int numero;
	private String data;
	private Aeroporto origem;
	private Aeroporto destino;
	private Aviao aviao;
	
	
	// Construtores

	/**
	 * Construtor com par�metros (com dados do Voo).
	 * @param numero - N�mero do v�o;
	 * @param data - Data;
	 * @param origem - Origem;
	 * @param destino - Destino;
	 * @param aviao - Avi�o;
	 */
	public Voo(int numero, String data, Aeroporto origem, Aeroporto destino, Aviao aviao) {
		super();
		this.numero = numero;
		this.data = data;
		this.origem = origem;
		this.destino = destino;
		this.aviao = aviao;
	}

	
	// Getters and Setters

	/**
	 * @return the aviao
	 */
	public Aviao getAviao() {
		return aviao;
	}

	/**
	 * @param aviao the aviao to set
	 */
	public void setAviao(Aviao aviao) {
		this.aviao = aviao;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the destino
	 */
	public Aeroporto getDestino() {
		return destino;
	}

	/**
	 * @param destino the destino to set
	 */
	public void setDestino(Aeroporto destino) {
		this.destino = destino;
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
	 * @return the origem
	 */
	public Aeroporto getOrigem() {
		return origem;
	}

	/**
	 * @param origem the origem to set
	 */
	public void setOrigem(Aeroporto origem) {
		this.origem = origem;
	}

	
	// equals e toString
	
	/**
	 * M�todo que verifica se um v�o j� esta cadastrado.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Voo) {
			Voo voo = (Voo) obj;
			return this.numero == voo.numero;
		}
		return false;
	}
	
	/**
	 * M�todo toString para mostrar os v�os.
	 */
	public String toString() {
		return "-- VOO: N�mero " + this.numero + " --\nOrigem: " + this.origem
				+ "\nDestino: " + this.destino + "\nData: "
				+ this.data + "\nAcentos livres: " + getAviao().getAcentosLivres() + "\n\n";
	}
}