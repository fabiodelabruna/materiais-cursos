package br.unisul.aula.exemplorevenda.entidade;

/**
 * Entidade Moto.
 * @author Fabio Dela Bruna
 * @since 22/04/2008
 */
public class Moto extends Veiculo{

	// atributos
	
	private boolean partidaEletrica;

	
	// construtores
	
	/**
	 * Construtor default.
	 */
	public Moto() {
		super();		
	}

	/**
	 * Construtor com par�metros(com dados da moto).
	 * @param partidaEletrica - Partida El�trica / SIM ou N�O
	 */
	public Moto(boolean partidaEletrica) {
		super();
		this.partidaEletrica = partidaEletrica;
	}


	// getters e setters
	
	public boolean isPartidaEletrica() {
		return partidaEletrica;
	}

	public void setPartidaEletrica(boolean partidaEletrica) {
		this.partidaEletrica = partidaEletrica;
	}
	
	
	// toString
	
	public String toString(){
		return String.format("%s\nPartida El�trica: %s", super.toString(), partidaEletrica);
	}
	
	
}
