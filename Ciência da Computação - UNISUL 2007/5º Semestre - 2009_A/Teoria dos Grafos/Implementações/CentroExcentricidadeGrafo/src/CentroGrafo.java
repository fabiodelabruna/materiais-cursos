import javax.swing.JOptionPane;


public class CentroGrafo  {
	
	private Grafo grafo;
	private double[][] matriz;

	
	
	public CentroGrafo() {
		this.grafo = new Grafo();
		int tamanho = grafo.getVertices().size();
		this.matriz = new double[tamanho][tamanho];
	}

	public CentroGrafo(Grafo grafo) {
		super();
		this.grafo = grafo;
		int tamanho = grafo.getVertices().size();
		this.matriz = new double[tamanho][tamanho];
	}
	
	
	
	/**
	 * M�todo que efetua a an�lise de adjac�ncia
	 * e monta a matriz de adjac�ncia.
	 */
	public double[][] grafoValorado() {
		
		for (int i = 0; i < matriz.length; i++) {
			
			Vertice verticeEmQuestao = grafo.getVertices().get(i);
			
			for (int j = 0; j < matriz.length; j++) {
				
				Vertice verticeTemp = grafo.getVertices().get(j);
				boolean isAdjacent = false;
				Aresta arestaTemp = null;
				
				// inicia a verifica��o de adjac�ncia.
				for (int k = 0; k < verticeEmQuestao.getArestas().size(); k++) {
					
					if ((verticeEmQuestao.getArestas().get(k).getVerticeOrigem().equals(verticeEmQuestao)
							&& verticeEmQuestao.getArestas().get(k).getVerticeDestino().equals(verticeTemp)) ||
							
							verticeEmQuestao.getArestas().get(k).getVerticeOrigem().equals(verticeTemp) &&
							verticeEmQuestao.getArestas().get(k).getVerticeDestino().equals(verticeEmQuestao)) {
						
						arestaTemp = verticeEmQuestao.getArestas().get(k);
						isAdjacent = true;
						break; // interrompe o la�o assim que encontrar a adjac�ncia entre os v�rtices.
					} else {
						isAdjacent = false;
					}
				} // fim da verifica��o de adjac�ncia.
				
				if (isAdjacent) {
					matriz[j][i] = arestaTemp.getPeso();
				} else {
					matriz[j][i] = 0;
				}
			}
		}
		return matriz;
	}

	/**
	 * M�todo que retorna um vertor com as excentricidades.
	 */
	private double[] getExcentricidade() {
		
		this.matriz = grafoValorado();
		
		double[] excentricidades = new double[this.grafo.getVertices().size()];
		double aux = 0.0;
		
		for (int i = 0; i < matriz.length; i++) {
			
			for (int j = 0; j < matriz.length; j++) {
			
				if (matriz[j][i] > aux) {
					aux = matriz[j][i];
				}
				
			}
			
			excentricidades[i] = aux;
			aux = 0.0;
			
		}
		return excentricidades;
	}
	
	
	/**
	 * M�todo que retorna o centro do grafo.
	 */
	public void calculaCentroRaioGrafo() {
		System.out.println("Excentricidades");
		double[] excentricidades = getExcentricidade();
		double centro = excentricidades[0];
		int auxCentro = 0;
		System.err.print(excentricidades[0] + "\t");
		
		for (int i = 1; i < excentricidades.length; i++) {
			System.err.print(excentricidades[i] + "\t");
			if (excentricidades[i] < centro) {
				centro = excentricidades[i];
				auxCentro = i;
			}
			
		}
		JOptionPane.showMessageDialog(null, "Centro: " + grafo.getVertices().get(auxCentro));
		JOptionPane.showMessageDialog(null, "Raio: " + centro);
	}
	
	
	
	/**
	 * Retorna a matriz de adjac�ncia.
	 */
	public double[][] getMatrizAdjacencia() {
		return grafoValorado();
	}
	
	
	
}

