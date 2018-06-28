package br.unisul.grafos.classes;
import java.util.ArrayList;

/**
 * Classe Java para representar um V�rtice.
 * @author Fabio Dela Bruna, Marcio Oz�rio Teixeira.
 */
public class Vertice {
	
	// Atributos
	
	/**
	 * R�tulo do v�rtice (nome/descri��o). 
	 */
	private String descricao;

	/**
	 * Array de v�rtices que s�o adjacentes.
	 */
	private ArrayList<Aresta> arestas;
	
	
	// Construtores
	
	/**
	 * Construtor default.
	 */
	public Vertice() {
		super();
		this.arestas = new ArrayList<Aresta>();
	}

	/**
	 * Construtor com par�metros.
	 * @param descricao
	 * @param vertices
	 */
	public Vertice(String descricao) {
		super();
		this.descricao = descricao;
		this.arestas = new ArrayList<Aresta>();
	}
	
	
	// M�todos equals e toString
	
	/**
	 * Sobrescrita do m�todo equals.
	 * @param obj Objeto a ser comparado.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Vertice) {
			Vertice vertice = (Vertice)obj;
			return this.getDescricao().equals( vertice.getDescricao() );
		}
		return false;
	}
	
	/**
	 * Sobrescrita do m�todo toString.
	 */
	public String toString() {
		return this.getDescricao();
	}
	

	// Getters e Setters
	
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the vertices
	 */
	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setAresta(ArrayList<Aresta> arestas) {
		this.arestas = arestas;
	}
	

}
