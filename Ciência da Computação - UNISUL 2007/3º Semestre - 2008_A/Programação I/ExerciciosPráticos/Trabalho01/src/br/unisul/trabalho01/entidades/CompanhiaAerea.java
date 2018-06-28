
import java.util.ArrayList;


/**
 * Classe que representa uma companhia a�rea.
 * @author Fabio Dela Bruna, M�rcio Oz�rio.
 * @since 31/03/2008.
 */
public class CompanhiaAerea {

	
	// Atributos
	
	private String nome;
	private String sigla;
	private ArrayList<Aviao> avioes;
	private ArrayList<Voo> voos;

	
	// Construtores
	
	/**
	 * Construtor com parametros (com dados da companhia a�rea).
	 * @param nome - Nome da companhia;
	 * @param sigla - Sigla;
	 * @param avioes - Avi�es;
	 * @param voos - V�os;
	 */
	public CompanhiaAerea(String nome, String sigla){
		this.nome = nome;
		this.sigla = sigla;
		this.avioes = new ArrayList<Aviao>();
		this.voos = new ArrayList<Voo>();
	}
	
	
	// M�todos principais
	
	/**
	 * M�todo que cadastra um avi�o.
	 */
	public boolean addAviao(Aviao aviao){
		this.avioes.add(aviao);
		return true;
	}
	
	/**
	 * M�todo que cadastra um V�o.
	 */
	public boolean addVoo(Voo voo){
		this.voos.add(voo);
		return true;
	}
	
	/**
	 * M�todo que remove um avi�o.
	 */
	public boolean removeAviao(Aviao aviao){
		if(this.avioes.size()<1){
			return false;
		}
		for(int i=0; i<this.avioes.size(); i++){
			if(this.avioes.get(i).equals(aviao)){
				this.avioes.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * M�todo que remove um v�o.
	 * @param voo
	 * @return
	 */
	public boolean removeVoo(Voo voo){
		if(this.voos.size()<1){
			return false;
		}
		for(int i=0; i<this.voos.size(); i++){
			if(this.voos.get(i).equals(voo)){
				this.voos.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * M�todo que imprime os v�os cadastrados.
	 */
	public void imprimirVoos(){
		String temp = "## LISTA DE VOOS DA COMPANHIA AEREA: " + this.nome.toUpperCase() + "\n\n";
		for(int i = 0; i < voos.size(); i++){
			temp += voos.get(i) + "\n";
		}
		System.out.println(temp);
	}
	
	/**
	 * M�todo que imprime os avi�es cadastrados.
	 */
	public void imprimirAvioes(){
		String temp = "## LISTA DE AVIOES DA COMPANHIA AEREA " + this.nome.toUpperCase() + "\n\n";
		for(int i =0; i < avioes.size(); i++){
			temp += avioes.get(i) + "\n\n";
		}
		System.out.println(temp);
	}
	

	// Getters and Setters
	
	/**
	 * @return the avioes
	 */
	public ArrayList<Aviao> getAvioes() {
		return avioes;
	}

	/**
	 * @param avioes the avioes to set
	 */
	public void setAvioes(ArrayList<Aviao> avioes) {
		this.avioes = avioes;
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

	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the voos
	 */
	public ArrayList<Voo> getVoos() {
		return voos;
	}

	/**
	 * @param voos the voos to set
	 */
	public void setVoos(ArrayList<Voo> voos) {
		this.voos = voos;
	}
	
	
	// equals e toString
	
	/**
	 * M�todo que verifica se determinada companhia a�rea j� est� cadastrada.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof CompanhiaAerea) {
			CompanhiaAerea companhiaAerea = (CompanhiaAerea) obj;
			return this.nome == companhiaAerea.nome;
		}
		return false;
	}
	
	/**
	 * M�todo para mostrar as companhias a�reas.
	 */
	public String toString() {
		return "Nome: " + this.nome + "\nSigla " + this.sigla;
	}

}