package br.unisul.grafos.classes;


/**
 * Classe Java que implementa um m�todo que gera o complemento
 * de um grafo, utilizando matriz de adjac�ncia.
 * @author Fabio Dela Bruna, Marcio Oz�rio Teixeira.
 */
public class ComplementoGrafo {

	// Atributos

	/**
	 * Objeto do tipo Grafo.
	 */
	private Grafo grafo;

	/**
	 * Matriz onde ser� montado os dados.
	 */
	private String[][] matriz;


	// Construtores

	/**
	 * Construtor default.
	 */
	public ComplementoGrafo() {
		this.grafo = new Grafo();
		int tamanho = grafo.getVertices().size() + 1;
		this.matriz = new String[tamanho][tamanho];
	}

	/**
	 * Construtor que recebe um objeto do tipo Grafo por par�metro.
	 * @param grafo Objeto do tipo Grafo.
	 */
	public ComplementoGrafo(Grafo grafo) {
		super();
		this.grafo = grafo;
		int tamanho = grafo.getVertices().size() + 1;
		this.matriz = new String[tamanho][tamanho];
	}


	//M�todos


	/**
	 * Este m�todo gera o complemento de um grafo.
	 * Ele faz o contr�rio da matriz de adjacencia,
	 * ou seja, se um v�rtice for adjacente ao outro,
	 * � adicionado o valor "0", senao, o valor "1".
	 */
	public String gerarComplemento() {
		montaMatrizBase();

		for (int i = 1; i < matriz.length; i++) {

			Vertice verticeEmQuestao = grafo.getVertices().get(i-1);

			for (int j = 1; j < matriz.length; j++) {

				Vertice verticeTemp = grafo.getVertices().get(j-1);
				boolean isAdjacent = false;

				if (verticeEmQuestao.getArestas().size() != 0) {

					// inicia a verifica��o de adjac�ncia.
					for (int k = 0; k < verticeEmQuestao.getArestas().size(); k++) {

						if ((verticeEmQuestao.getArestas().get(k).getVerticeOrigem().equals(verticeEmQuestao)
								&& verticeEmQuestao.getArestas().get(k).getVerticeDestino().equals(verticeTemp)) ||
								verticeEmQuestao.getArestas().get(k).getVerticeOrigem().equals(verticeTemp) &&
								verticeEmQuestao.getArestas().get(k).getVerticeDestino().equals(verticeEmQuestao)) {

							isAdjacent = false;
							break; // interrompe o la�o assim que encontrar a adjac�ncia entre os v�rtices.
							
						}else{

							// verificacao para saber se o vertice e um auto laco
							if(verticeEmQuestao.equals(verticeTemp)){
								isAdjacent = false;
								break;	
							} else{
								isAdjacent = true;
							}
						}
					} // fim da verifica��o de adjac�ncia.

				} else {
					if (verticeEmQuestao.equals(verticeTemp)) {
						isAdjacent = false;
					} else {
						isAdjacent = true;
					}
				}

				if (isAdjacent) {
					matriz[j][i] = "1";
				} else {
					matriz[j][i] = "0";
				}
			}
		}
		String result = ".:: MATRIZ DE ADJAC�NCIA ::.\n\nGrafo Complementar:\n\n";
		return result.concat(montaResultado());
	}


	// M�todos privados

	/**
	 * Monta a base da matriz de adjac�ncia,
	 * que s�o os v�rtices na horizontal e na vertical.
	 */
	private void montaMatrizBase() {
		matriz[0][0] = "#";
		for (int i = 0; i < grafo.getVertices().size(); i++) {
			matriz[i+1][0] = grafo.getVertices().get(i).getDescricao();
		}
		for (int i = 0; i < grafo.getVertices().size(); i++) {
			matriz[0][i+1] = grafo.getVertices().get(i).getDescricao();
		}
	}

	/**
	 * Monta o resultado da matriz de adjac�ncia
	 * em vari�vel do tipo literal.
	 * @return Resultado em uma literal.
	 */
	private String montaResultado() {
		String result = "";
		
		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < matriz.length; j++) {
				result += "\t";
				result += matriz[j][i];
			}
			result +="\t\n";
		}
		return result += "\n";
	}

}
