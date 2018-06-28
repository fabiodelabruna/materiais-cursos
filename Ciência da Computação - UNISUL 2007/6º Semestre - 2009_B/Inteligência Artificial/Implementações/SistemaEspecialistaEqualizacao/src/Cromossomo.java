import java.util.ArrayList;


/**
 * Classe que representa um cromossomo.
 * @author Almir Hoepers, Fabio Dela Bruna, M�rcio Oz�rio Teixeira.
 */
public class Cromossomo implements Comparable<Cromossomo> {

	
	/**
	 * ArrayList que armazena o c�digo gen�tico do cromossomo.
	 */
	private ArrayList<Integer> codigoGenetico;
	
	/**
	 * Inteiro para armazenar o fitness de cada cromossomo.
	 */
	private int fitness;
	
	
	
	/**
	 * Construtor sem par�metros.
	 */
	public Cromossomo() {
		codigoGenetico = new ArrayList<Integer>();
		fitness = 0;
	}
	
	/**
	 * Construtor com par�metros.
	 * @param codigoGenetico ArrayList contendo o c�digo gen�tico.
	 * @param fitness Inteir contendo o fitness.
	 */
	public Cromossomo( ArrayList<Integer> codigoGenetico, int fitness ) {
		this.codigoGenetico = codigoGenetico;
		this.fitness = fitness;
	}

	
	// GETTERS AND SETTERS
	
	public ArrayList<Integer> getCodigoGenetico() {
		return codigoGenetico;
	}

	public void setCodigoGenetico( ArrayList<Integer> codigoGenetico ) {
		this.codigoGenetico = codigoGenetico;
	}
	
	public int getFitness() {
		return fitness;
	}

	public void setFitness( int fitness ) {
		this.fitness = fitness;
	}
	
	//
	
	
	/**
	 * Imprimi o c�digo gen�tico do cromossomo.
	 * @return String contendo o c�digo a ser impresso.
	 */
	public String toString() {
		String ret = "";
		
		for ( int i = 0; i < codigoGenetico.size(); i++ ) {
			ret += codigoGenetico.get( i );
		}
		
		return ret; 
	}
	
	
	/**
	 * M�todo para comparar dois objetos do tipo Cromossomo.
	 * A compara��o � feita atrav�z da vari�vel <code>fitness</code>.
	 * @param c Objeto do tipo Cromossomo a ser comparado.
	 * @return Se o <code>fitness</code> for menor que o <code>fitness</code> do objeto passado por
	 * par�metro, retorna -1; se for maior, retorna 1; se for igual, retorna 0.
	 */
	public int compareTo( Cromossomo c ) {
//		if ( this.getFitness() < c.getFitness() )
//			return -1;
//		else if ( this.getFitness() >  c.getFitness() )
//			return 1;
//		else 
//			return 0;
		return this.getFitness() - c.getFitness();
	}

	
}
