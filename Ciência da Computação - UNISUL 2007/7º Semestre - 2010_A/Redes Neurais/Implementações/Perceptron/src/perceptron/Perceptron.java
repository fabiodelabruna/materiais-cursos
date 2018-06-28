package perceptron;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Classe que representa o algoritmos de rede neural "PERCEPTRON". 
 * Cont�m m�todos para efetuar o treinamento e o teste da rede.
 * @author  Fabio Dela Bruna
 */
public class Perceptron {

	// Declara��o de vari�veis.

	/**
	 * Matriz de pesos.
	 */
	private int[][] W;

	/**
	 * Vetor de entrada.
	 */
	private int[] X;

	/**
	 * Vetor de sa�da.
	 */
	private int[] Y;

	/**
	 * Vetor de arquivos de treinamentos (padr�es).
	 */
	private File[] arquivos;
	
	/**
	 * Matriz de sa�da para cada letra.
	 */
	private static final int[][] T = 
		{ 
			{  1, -1, -1, -1, -1, -1, -1 }, 
			{ -1,  1, -1, -1, -1, -1, -1 }, 
			{ -1, -1,  1, -1, -1, -1, -1 }, 
			{ -1, -1, -1,  1, -1, -1, -1 }, 
			{ -1, -1, -1, -1,  1, -1, -1 }, 
			{ -1, -1, -1, -1, -1,  1, -1 }, 
			{ -1, -1, -1, -1, -1, -1,  1 } 
		};

	/**
	 * Constante que representa a quantidade de neur�nios de entrada.
	 */
	private static final int QTD_NEURONIOS_ENTRADA = 63;

	/**
	 * Constante que representa a quantidade de neur�nios de sa�da.
	 */
	private static final int QTD_NEURONIOS_SAIDA = 7;

	/**
	 * Constante que representa o LIMIAR.
	 */
	private static final int LIMIAR = 0;

	/**
	 * Constante que representa o ALPHA.
	 */
	private static final int ALPHA = 1;

	// Fim da declara��o de vari�veis.



	/**
	 * Construtor da classe Perceptron. 
	 * Inicializa vetores e pega arquivos de treinamento.
	 */
	public Perceptron() {

		// pega os arquivos de treinamento no diret�rio de armazenamento
		arquivos = new File( "arquivos/treinamento" ).listFiles();

		// inicializa vetores
		W = new int[ QTD_NEURONIOS_ENTRADA ] [ QTD_NEURONIOS_SAIDA ];
		X = new int[ QTD_NEURONIOS_ENTRADA ];
		Y = new int[ QTD_NEURONIOS_SAIDA ];

	}


	/**
	 * Efetua o aprendizado da rede neural.
	 */
	public void iniciaAprendizado() throws IOException {

		// PASSO 1 - Inicializar pesos
		for ( int row = 0; row < W.length; row++ ) { // loop para as 7 linhas
			for ( int column = 0; column < W[ row ].length; column ++ ) { // loop para as 63 colunas
				W[ row ][ column ] = 0;
			}
		} // FIM DO PASSO 1

		boolean alterouPesos = true; // inicializa com true para entrar no la�o
		while ( alterouPesos ) { // enquanto alterar pesos, continua... (PASSO 5)

			alterouPesos = false; // valor setado para false, pois s� ser� true, se altera algum peso

			// PASSO 2 = Para cada vetor de treinamento fa�a...
			for ( int count = 0; count < arquivos.length; count++ ) { // percorre todos os vetores de treinamento

				leArquivo( arquivos[ count ] ); // l� dados de cada arquivo de treinamento e preenche X 

				// PASSO 3 = Calcular resposta da rede
				for ( int j = 0; j < QTD_NEURONIOS_SAIDA; j++ ) {

					for ( int i = 0; i < QTD_NEURONIOS_ENTRADA; i++ ) {
						Y[ j ] += X[ i ] * W[ i ][ j ];
					}

					if ( Y[ j ] > LIMIAR ) {  // y-in > limiar
						Y[ j ] = 1;
					} else if ( Y[ j ] < ( LIMIAR * -1 ) ) {  // y-in < -limiar
						Y[ j ] = -1;
					} else {  // -limiar <= y-in <= limiar
						Y[ j ] = 0;
					}

					// PASSO 4 = Ajustar pesos
					if ( Y[ j ] != T[ count/3 ][ j ] ) {
						for ( int i = 0; i < QTD_NEURONIOS_ENTRADA; i++ ) {
							W[ i ][ j ] = W[ i ][ j ] + ALPHA * T[ count/3 ][ j ] * X[ i ];
						}
						alterouPesos = true;
					} // FIM DO PASSO 4

				} // FIM DO PASSO 3
			} // FIM DO PASSO 2
		} // FIM DO PASSO 5
	}


	/**
	 * M�todo utilizado para a fase de testes.
	 * @param arquivo Arquivo de teste.
	 * @return Valor inteiro que representa a letra reconhecida de acordo 
	 * com o vetor de sa�das T. Se nenhuma letra for reconhecida retorna -1.
	 */
	public int testarRede( File arquivo ) throws IOException {

		int result = -1;

		leArquivo( arquivo );

		for ( int j = 0; j < QTD_NEURONIOS_SAIDA; j++ ) {
			for ( int i = 0; i < QTD_NEURONIOS_ENTRADA; i++ ) {
				Y[ j ] += X[ i ] * W[ i ][ j ];
			}

			if ( Y[ j ] > LIMIAR ) {  // y-in > limiar
				Y[ j ] = 1;
			} else if ( Y[ j ] < ( LIMIAR * -1 ) ) {  // y-in < -limiar
				Y[ j ] = -1;
			} else {  // -limiar <= y-in <= limiar
				Y[ j ] = 0;
			}
		}

		for (int i = 0; i < QTD_NEURONIOS_SAIDA; i++) {
			if ( comparaVetores( Y , T[ i ] ) ) { // compara com cada linha do vetor
				result = i;
				break;
			}
		}
		return result;
	}


	/**
	 * L� o conte�do de um determinado arquivo, utilizando a classe java.io.FileReader.
	 * @param arquivo Arquivo a ser lido.
	 */
	private void leArquivo( File arquivo ) throws IOException {
		FileReader reader = null;
		int count = 0;
		char c;

		reader = new FileReader( arquivo ); // cria FileReader passando o arquivo por par�metro

		while ( count != QTD_NEURONIOS_ENTRADA ) {

			c = ( char )reader.read();

			if ( c == '\n' || c == '\r' ) { // ignora caracter de retorno e nova linha
				// n�o faz nada;
			} else {

				if ( c == '.' ) {			// se for ponto
					X[ count ] = -1;		// ent�o atribui -1
					count++;
				} else if ( c == '#' ) {	// se for cerquilha
					X[ count ] = 1;			// ent�o atribui 1
					count++;
				} else {					// sen�o...
					X[ count ] = 0;			// atribui 0
					count++;
				}

			}
		}
		reader.close(); // fecha o arquivo
	}


	/**
	 * Compara os dois vetores. Os dois vetores s�o considerados
	 * iguais se cont�m a mesma quantidade de elementos e se
	 * todos os pares de elementos dos vetores s�o iguais.
	 */
	private static boolean comparaVetores( int[] a, int[] b ) {
		return Arrays.equals( a , b );
	}
	
	
	/**
	 * Retorna a matriz de pesos.
	 */
	public int[][] getMatrizPesos() {
		return W;
	}


}

