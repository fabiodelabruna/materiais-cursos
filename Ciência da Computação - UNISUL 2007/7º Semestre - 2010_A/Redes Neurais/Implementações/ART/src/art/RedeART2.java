package art;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe que implementa uma Rede ART.
 * Essa rede possui 10 clusters e o par�metro de vigil�ncia � 0,4
 * @author Fabio Dela Bruna
 * 
 */
public class RedeART2 {

	/**
	 * Matriz de pesos (bottom-up).
	 */
	private double[][] b;

	/**
	 * Matriz de pesos (top-down).
	 */
	private double[][] t;

	/**
	 * Vetor de entrada bin�rio.
	 */
	private int[] s;

	/**
	 * Vetor de ativa��o para a camada F1(b).
	 */
	private double[] x;

	/**
	 * Vetor de ativa��o para a camada F2(t).
	 */
	private double[] y;

	/**
	 * Par�metro de vigil�ncia.
	 */
	private double p;

	/**
	 * Par�metro para ajuste dos pesos bottom-up.
	 */
	private int L;

	/**
	 * Vetor de arquivos de treinamento.
	 */
	private File[] arquivos;

	/**
	 * Quantidade de clusters.
	 */
	private final int QTD_CLUSTERS = 15;

	/**
	 * Tamanho dos vetores de entrada.
	 */
	private final int TAM_VETOR_ENTRADA = 63;

	/**
	 * N�mero de �pocas que a rede ir� executar.
	 */
	private final int QTD_EPOCAS = 200;


	/**
	 * Construtor padr�o da classe RedeART1.
	 * Inicializa os par�metros necess�rios.
	 */
	public RedeART2() {
		L = 2; // inicializa par�metro para ajuste de pesos ( L > 1 ).
		p = 0.7; // inicializa par�metro de vigil�ncia ( 0 < p <= 1 ).
		b = new double[ TAM_VETOR_ENTRADA ][ QTD_CLUSTERS ];
		t = new double[ QTD_CLUSTERS ][ TAM_VETOR_ENTRADA ];
		x = new double[ TAM_VETOR_ENTRADA ];
		y = new double[ QTD_CLUSTERS ];

		arquivos = new File( "arquivos/treinamento" ).listFiles();

		// inicializa os pesos da matriz bottom-up.
		for ( int i = 0; i < b.length; i++ ) {
			for ( int j = 0; j < b[ i ].length; j++ ) {
				b[ i ][ j ] = L / ( L - 1 + TAM_VETOR_ENTRADA );
			}
		}

		// inicializa pesos da matriz top-down.
		for ( int i = 0; i < t.length; i++ ) {
			for ( int j = 0; j < t[ i ].length; j++ ) {
				t[ i ][ j ] = 1;
			}
		}
	}

	/**
	 * Faz o treinamento da RedeART1.
	 */
	public void treinaRede() throws IOException {

		// condi��o de parada: ser� executada 200 �pocas.
		for ( int epoca = 0; epoca < QTD_EPOCAS; epoca++ ) {

			// para cada vetor de treinamento fa�a...
			for ( int count = 0; count < arquivos.length; count++ ) {

				s = leArquivo( arquivos[ count ] );
				cleanY(); // zera o vetor y.

				// calcula a soma dos componentes do vetor x - ||x||
				double somaComponentesS = 0;
				for ( int i = 0; i < s.length; i++ )
					somaComponentesS += s[ i ];


				// para cada cluster...
				for ( int j = 0; j < y.length; j++ ) {

					// para cada unidade do vetor de entrada
					for ( int i = 0; i < x.length; i++ ) {

						x[ i ] = s[ i ];
						y[ j ] = b[ i ][ j ] * x[ i ];

					}
				}

				int J = -1; // �ndice do cluster no qual ser� feito a atualiza��o de pesos.
				double somaComponentesX = 0;
				boolean reset = true;

				// enquanto condi��o de reset for satisfeita...
				while ( reset ) {

					J = findIndiceMax( y ); // encontra o indice com maior valor

					if ( J > -1 ) { // testa se ainda h� algum nodo n�o inibido.

						somaComponentesX = 0;
						for ( int i = 0; i < x.length; i++ ) {
							x[ i ] = s[ i ] * t[ J ][ i ];
							somaComponentesX += x[ i ];
						}

						// se for menor que o par�metro de vigil�ncia, ent�o ativa mecanismo de reset.
						if ( ( somaComponentesX / somaComponentesS ) < p ) {
							y[ J ] = -1; // inibir nodo J
							reset = true;
						} else {
							reset = false;
						}

					} else {
						break;
					}

				}

				// se restou algum nodo n�o inibido.
				if ( J > -1 ) {
					atualizaPesosBottomUp( J, somaComponentesX );
					atualizaPesosTopDown( J );
				}
			}
		}
	}


	/**
	 * Efetua o teste da rede, recebendo por par�metro um vetor de arquivos de teste.
	 */
	public String testaRede( File[] files ) throws IOException {

		String[] arquivosPorCluster = new String[ QTD_CLUSTERS ];
		for ( int i = 0; i < arquivosPorCluster.length; i++ ) {
			arquivosPorCluster[ i ] = "";
		}

		String resultadoDoTeste = "..: RESULTADO DO TESTE :..\n\n";

		// AQUI COME�A O TESTE...

		// para cada vetor de treinamento fa�a...
		for ( int count = 0; count < files.length; count++ ) {

			s = leArquivo( files[ count ] );
			cleanY(); // zera o vetor y.

			// calcula a soma dos componentes do vetor x - ||x||
			double somaComponentesS = 0;
			for ( int i = 0; i < s.length; i++ )
				somaComponentesS += s[ i ];


			// para cada cluster...
			for ( int j = 0; j < y.length; j++ ) {

				// para cada unidade do vetor de entrada
				for ( int i = 0; i < x.length; i++ ) {

					x[ i ] = s[ i ];
					y[ j ] = b[ i ][ j ] * x[ i ];

				}
			}

			int J = -1; // �ndice do cluster no qual ser� feito a atualiza��o de pesos.
			double somaComponentesX = 0;
			boolean reset = true;

			// enquanto condi��o de reset for satisfeita...
			while ( reset ) {

				J = findIndiceMax( y ); // encontra o indice com maior valor

				if ( J > -1 ) { // testa se ainda h� algum nodo n�o inibido.

					somaComponentesX = 0;
					for ( int i = 0; i < x.length; i++ ) {
						x[ i ] = s[ i ] * t[ J ][ i ];
						somaComponentesX += x[ i ];
					}

					// se for menor que o par�metro de vigil�ncia, ent�o ativa mecanismo de reset.
					if ( ( somaComponentesX / somaComponentesS ) < p ) {
						y[ J ] = -1; // inibir nodo J
						reset = true;
					} else {
						reset = false;
					}

				} else {
					break;
				}

			}

			// AQUI TERMINA O TESTE...
			if ( J > -1 )
				arquivosPorCluster[ J ] += files[ count ].getName() + "   "; // adiciona o arquivo ao cluster vencedor.
		}

		// termina de formular um resultado para ser impresso na tela
		for ( int i = 0; i < arquivosPorCluster.length; i++ ) {
			if ( arquivosPorCluster[ i ].equals( "" ) )
				resultadoDoTeste += "CLUSTER " + i + ":   NENHUM ARQUIVO\n";
			else
				resultadoDoTeste += "CLUSTER " + i + ":   " + arquivosPorCluster[ i ] + "\n";
		}
		return resultadoDoTeste;
	}


	/**
	 * Atualiza os pesos da matriz top-down.
	 * @param j �ndice da linha a ser atualizada.
	 */
	private void atualizaPesosTopDown( int j ) {
		for ( int i = 0; i < t[ j ].length; i++ ) {
			t[ j ][ i ] = x[ i ];
		}
	}


	/**
	 * Atualiza os pesos da matriz bottom-up.
	 * @param j �ndice da coluna a ser atualizada.
	 * @param soma Soma dos componentes do vetor x.
	 */
	private void atualizaPesosBottomUp( int j, double soma ) {
		for ( int i = 0; i < b.length; i++ ) {
			b[ i ][ j ] = ( L * x[ i ] ) / ( L - 1 + soma );
		}
	}

	/**
	 * Procura pelo �ndice do maior valor num vetor.
	 * Retorna o �ndice do menor elemento encontrado.
	 */
	private int findIndiceMax( double[] vetor ) {
		double max = vetor[ 0 ];
		int indice = 0;
		for ( int i = 1; i < vetor.length; i++ ) {
			if ( vetor[ i ] > max ) {
				max = vetor[ i ];
				indice = i;
			}
		}
		if ( vetor[ indice ] == -1 )
			return -1;

		return indice;
	}


	/**
	 * Seta ativa��es de todas as unidades F2 para zero.
	 */
	private void cleanY() {
		for ( int i = 0; i < x.length; i++ ) {
			x[ i ] = 0;
		}
	}


	/**
	 * L� o conte�do de um determinado arquivo, utilizando a classe java.io.FileReader.
	 * Retorna uma matriz de inteiros contendo os dados lidos do arquivo.
	 * @param arquivo Arquivo a ser lido.
	 * @param qtd Quantidade de bits que ser�o lidos de um arquivo.
	 */
	private int[] leArquivo( File arquivo ) throws IOException {
		FileReader reader = null;
		int[] retorno = new int[ TAM_VETOR_ENTRADA ];
		int count = 0;
		char c;

		reader = new FileReader( arquivo ); // cria FileReader passando o arquivo por par�metro

		while ( count != TAM_VETOR_ENTRADA ) {

			c = (char ) reader.read();

			if ( c == '\n' || c == '\r' ) { // ignora caracter de nova linha e retorno
				; // n�o faz nada;
			} else {

				// if ( c == '.' ) { // se for ponto
				// retorno[ count ] = - 1; // ent�o atribui -1
				// } else if ( c == '#' ) { // se for cerquilha
				// retorno[ count ] = 1; // ent�o atribui 1
				// } else { // sen�o...
				// retorno[ count ] = 0; // atribui 0
				// }
				if ( c == '.' ) { // se for ponto
					retorno[ count ] = 0; // ent�o atribui -1
				} else if ( c == '#' ) { // se for cerquilha
					retorno[ count ] = 1; // ent�o atribui 1
				}
				count++;
			}
		}
		reader.close(); // fecha o arquivo
		return retorno;
	}

}
