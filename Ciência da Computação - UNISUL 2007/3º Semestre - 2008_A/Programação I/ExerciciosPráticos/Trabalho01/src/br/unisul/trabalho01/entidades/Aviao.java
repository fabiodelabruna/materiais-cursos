


import java.util.ArrayList;

/**
 * Classe que representa Avi�es de determinadas companhias a�reas.
 * @author Fabio Dela Bruna, M�rcio Oz�rio.
 * @since 31/03/2008.
 */
public class Aviao {

	
	// Atributos
	
	private String marca;
	private String modelo;
	private int ano_fabricacao;
	private int numero;
	private ArrayList<Assento> assentos;
	private int anoFabricacao;

	
	// Construtores

	/**
	 * Construtor com parametros (com dados do avi�o).
	 * @param marca - Marca do avi�o;
	 * @param modelo - Modelo;
	 * @param ano_fabricacao - Ano de fabrica��o;
	 * @param numero - N�mero do avi�o;
	 * @param assentos = Acentos.
	 */
	public Aviao(int numero, String marca, String modelo, int ano_fabricacao, int qtd_assentos) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano_fabricacao = ano_fabricacao;
		this.numero = numero;
		this.assentos = new ArrayList<Assento>();
		for(int i = 0; i < qtd_assentos; i++){
			this.assentos.add(new Assento(""+i));
		}
	} 

	
	//M�todos principais
	
	/**
	 * M�todo para reservar um acento em determinado avi�o.
	 */
	public boolean reservaAssento(){
		for(int i = 0; i < this.assentos.size(); i++){
			if(!this.assentos.get(i).estaReservado()){
				this.assentos.get(i).reservar();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * M�todo para verificar se h� vagas para reserva.
	 */
	public boolean temVaga(){
		for(int i = 0; i < this.assentos.size(); i++){
			if(!this.assentos.get(i).estaReservado()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * M�todo que retorna a quantidade de acentos Livres.
	 */
	public int getAcentosLivres(){
		int contador = 0;
		for(int i=0; i<this.assentos.size();i++){
			if(this.assentos.get(i).estaReservado() == false){
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * M�todo que retorna a quantidade de acentos Ocupados.
	 */
	public int getAcentosOcupados(){
		int contador = 0;
		for(int i=0; i<this.assentos.size();i++){
			if(this.assentos.get(i).estaReservado() == true){
				contador++;
			}
		}
		return contador;
	}
	
	/**
	 * M�todo que retorna a quantidade de acentos de determinado avi�o.
	 * @return
	 */
	public int getNumeroDeAcentos(){
		return this.assentos.size();
	}
	
	
	//Getters and Setters
	
	/**
	 * @return the ano_fabricacao
	 */
	public int getAno_fabricacao() {
		return ano_fabricacao;
	}

	/**
	 * @param ano_fabricacao the ano_fabricacao to set
	 */
	public void setAno_fabricacao(int ano_fabricacao) {
		this.ano_fabricacao = ano_fabricacao;
	}

	/**
	 * @return the assentos
	 */
	public ArrayList<Assento> getAssentos() {
		return assentos;
	}

	/**
	 * @param assentos the assentos to set
	 */
	public void setAssentos(ArrayList<Assento> assentos) {
		this.assentos = assentos;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
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
	

	//equals e toString
	
	/**
	 * M�todo para verificar se determinado avi�o j� est� cadastrado.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Aviao) {
			Aviao aviao = (Aviao) obj;
			return this.numero == aviao.numero;
		}
		return false;
	}

	/**
	 * M�todo para mostrar os avi�es cadastrados.
	 */
	public String toString() {
		return "Marca: " + this.marca + "\nModelo: " + this.modelo + "\nAno de Fabrica��o: " + this.ano_fabricacao + "\nN�mero: " + this.numero;
	}
	
}