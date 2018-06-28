package br.unisul.grafos.classes;
import java.util.ArrayList;

import br.unisul.grafos.exceptions.ArestaRepetidaException;
import br.unisul.grafos.exceptions.VerticeRepetidoException;

/**
 * Classe Java para representar um grafo como um todo.
 * @author Fabio Dela Bruna, Marcio Oz�rio Teixeira.
 */
public class Grafo {

	// Atributos
	
	/**
	 * ArrayList de vertices que formam o grafo.
	 */
	private ArrayList<Vertice> vertices;
	
	
	// Construtores
	
	/**
	 * Construtor default.
	 * Apenas inicializa o ArrayList.
	 */
	public Grafo() {
		this.vertices = new ArrayList<Vertice>();
	}

	
	// M�todos

	/**
	 * M�todo utilizado para inserir um v�rtice ao grafo.
	 * 
	 * @param vertice V�rtice a ser inserido no grafo.
	 * @throws VerticeRepetidoException.
	 */
	public void adicionaVertice(Vertice vertice) throws VerticeRepetidoException {
		if (!this.vertices.contains(vertice)) {
			this.vertices.add(vertice);
		} else {
			throw new VerticeRepetidoException("Este v�rtice j� foi inserido no grafo!");
		}
	}
	

	/**
	 * M�todo utilizado para inserir uma aresta.
	 * Cada v�rtice possui um array de arestas que incidem a ele.
	 * A aresta n�o � inserida diretamento no grafo,
	 * mas sim no array de cada v�rtice em que ela � incide.
	 * 
	 * @param descricao R�tulo da aresta (descri��o/nome).
	 * @param vertice1 V�rtice de onde parte a aresta (origem).
	 * @param vertice2 V�rtice de destino da aresta.
	 * @param peso Peso de cada aresta.
	 */
	public void adicionaAresta(String descricao, Vertice vertice1,
			Vertice vertice2, String peso) throws ArestaRepetidaException {
		
		Aresta aresta = new Aresta(descricao, vertice1, vertice2, peso); // cria uma nova aresta com os devidos atributos.
	
		if (!(vertice1.getArestas().contains(aresta) && (vertice2.getArestas().contains(aresta)))) {
			vertice1.getArestas().add(aresta); // adiciona no array de arestas do vertice1, a aresta criada.
			vertice2.getArestas().add(aresta); // adiciona no array de arestas do vertice2, a aresta criada.
		} else {
			throw new ArestaRepetidaException("Esta aresta j� foi inseida no grafo.\nN�o � poss�vel adicionar m�ltiplas arestas!");
		}
		
	}
	
	
	
	// Getters e Setters
	
	/**
	 * @return the vertices
	 */
	public ArrayList<Vertice> getVertices() {
		return vertices;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(ArrayList<Vertice> vertices) {
		this.vertices = vertices;
	}
	
}
