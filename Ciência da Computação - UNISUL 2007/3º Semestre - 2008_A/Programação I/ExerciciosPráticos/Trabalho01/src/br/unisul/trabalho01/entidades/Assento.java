


/**
 * Classe que representa os acentos de um avi�o.
 * @author Fabio Dela Bruna, M�rcio Oz�rio.
 * @since 02/04/2008.
 */
public class Assento {

	
	// Atributos
	
	private String numero;
	private boolean reservado;

	
	// Construtor
	
	/**
	 * Construtor com parametros (com dados dos acentos).
	 * @param reservado - Assento reservado ou n�o;
	 * @param numero - N�mero do assento;
	 */
	public Assento(String numero) {
		this.reservado = false;
		this.numero = numero;
	}
	
	
	// M�todos principais
	
	/**
	 * M�todo para verificar se determinado
	 */
	public boolean estaReservado() {
		return this.reservado;
	}

	/**
	 * M�todo para reservar determinado assento do avi�o.
	 */
	public boolean reservar() {
		if (this.reservado == true) {
			return false;
		} else {
			this.reservado = true;
			return true;
		}
	}
	
	
	// getters and setters
	
	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the reservado
	 */
	public boolean isReservado() {
		return reservado;
	}

	/**
	 * @param reservado the reservado to set
	 */
	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}
	
	
	// equals e toString

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Assento) {
			Assento assento = (Assento) obj;
			return this.numero == assento.numero;
		}
		return false;
	}

	/**
	 * M�todo para mostrar os acentos.
	 */
	public String toString() {
		return "N�mero: " + this.numero + "\nReservado: " + (this.reservado?"sim":"n�o");
	}		
}