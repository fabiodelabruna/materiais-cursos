//
// Realiza an�lise para verificar se dois grafos s�o isommorfos
//
public class IsomorphAnalysis {

	private int aAdjGraph1[][];
	private int aAdjGraph2[][];
	private int aVertexRelationshipMap[];
	private boolean bIsIsormorph;
	private boolean bIsAnalysisComplete;

	public IsomorphAnalysis( int aAdjGraph1[][], int aAdjGraph2[][] ) {	

	this.aAdjGraph1 = aAdjGraph1;
		this.aAdjGraph2 = aAdjGraph2;
		this.aVertexRelationshipMap = new int[ aAdjGraph1.length ];
		this.bIsIsormorph = false;
		this.bIsAnalysisComplete = false;

		// Relecionamento padr�o de vertices para grafos isomorfos....
		for ( int i = 0; i < aAdjGraph1.length; i++ )
			this.aVertexRelationshipMap[i] = i;

	}

	public boolean getResult() {

		// N�o realizou analise?
		if ( !this.bIsAnalysisComplete ) {

			// Matrizes de ajac�ncia n�o s�o quadradas?
			if (( this.aAdjGraph1.length != this.aAdjGraph1[0].length ) || ( this.aAdjGraph2.length != this.aAdjGraph2[0].length ))
				this.bIsIsormorph = false;

			// Grafos possui n�mero diferente de vertices?
			else if ( this.aAdjGraph1.length != this.aAdjGraph2.length )
				this.bIsIsormorph = false;

			// Grafos s�o identicos?
			else if ( this.CompareMatrix( this.aAdjGraph1, this.aAdjGraph2 ))
				this.bIsIsormorph = true;

			// Reliza analise atrav�s de permuta��es...
			else
				this.bIsIsormorph = this.PermutationAnalysis();

			this.bIsAnalysisComplete = true;

		}

		return this.bIsIsormorph;

	}

	//
	// getVertexRelationshipMap - Obt�m vetor com a rela��o de vertices ( v1 --> f(x)); 
	public int[] getVertexRelationshipMap() {

		// Ainda n�o realizou an�lise?
		if ( !this.bIsAnalysisComplete )
			this.getResult();

		return this.aVertexRelationshipMap;

	}

	//
	// PermutationAnalysis - Realiza permuta��es na aAdjGraph2 para tentar obter rela��o entre vertices.
	private boolean PermutationAnalysis() {

		// Obt�m Gerador de permuta��es
		PermutationGenerator aPermut = new PermutationGenerator( this.aAdjGraph2.length );

		// Enquanto houver permuta��es...
		while ( aPermut.hasMore()) {

			// Obt�m proxima permuta��o
			int aIndexes[] = aPermut.getNext();

			// Obt�m uma c�pia temporaria da matrix que ser� modificada
			int aTempAnalisysMatrix[][] = this.cloneMatrix( this.aAdjGraph2 );

			// Troca as linhas reais pelas linhas da  permuta��o atual.
			this.ChangeMatrixRows( aTempAnalisysMatrix, aIndexes );

			// Troca as Colunas reais pelas colunas da permuta��o atual.
			this.ChangeMatrixCols( aTempAnalisysMatrix, aIndexes );

			// Compara as matrizes
			if ( this.CompareMatrix( this.aAdjGraph1, aTempAnalisysMatrix )) {

				this.aVertexRelationshipMap = aIndexes;
				return true;

			}
		}

		return false;

	}

	// 
	// ChangeMatrixRows - Troca linhas de uma matrix
	private void ChangeMatrixRows( int aMatrix[][], int aIndexes[] ) {

		int aSourceMatrix[][] = this.cloneMatrix( aMatrix);
		for ( int x = 0; x < aMatrix.length; x++ )
			for ( int y = 0; y < aMatrix.length; y++ )
				aMatrix[x][y] = aSourceMatrix[aIndexes[x]][y];


	}

	// 
	// ChangeMatrixCols - Troca colunas de uma matrix
	private void ChangeMatrixCols( int aMatrix[][], int aIndexes[] ) {

		int aSourceMatrix[][] = this.cloneMatrix( aMatrix);
		for ( int y = 0; y < aMatrix.length; y++ )
			for ( int x = 0; x < aMatrix.length; x++ )
				aMatrix[x][y] = aSourceMatrix[x][aIndexes[y]];

	}

	// 
	// CompareMatrix - compara duas matrizes
	private boolean CompareMatrix( int aMatrix1[][], int aMatrix2[][] ) {

		for ( int x = 0; x < aMatrix1.length; x++ ) {
			for ( int y = 0; y < aMatrix2.length; y++ ) {

				if ( aMatrix1[x][y] != aMatrix2[x][y] )
					return false;

			}
		}

		return true;

	}

	//
	// cloneMatrix - Cria nova inst�ncia identica a uma matrix fonte.
	private int[][] cloneMatrix( int aMatrix[][] ) {
		
		int aReturn[][] = new int[ aMatrix.length ][ aMatrix.length ];
		for ( int x = 0; x < aMatrix.length; x++ )
			for ( int y = 0; y < aMatrix.length; y++ )
				aReturn[x][y] = aMatrix[x][y];

		return aReturn;

	}
}

