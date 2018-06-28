package compiladorLMS.analise_sintatica;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import compiladorLMS.analise_lexica.AnalisadorLexico;
import compiladorLMS.analise_lexica.TabelaToken;
import compiladorLMS.analise_lexica.Token;


/**
 * Classe respons�vel por realizar a an�lise sint�tica do c�digo-fonte.
 * @author Fabio Dela Bruna, M�rcio Oz�rio Teixeira.
 */
public class AnalisadorSintatico {

	/**
	 * Armazena a tabela de tokens.
	 */
	private ArrayList<Token> tabelaTokens = new TabelaToken().getTabelaTokens();
	
	
	/**
	 * Armazena a tabela de parse.
	 */
	private ArrayList<Regra> tabelaDeParse;
	
	/**
	 * Pilha de entrada. Fornecida pela an�lise l�xica.
	 */
	private Stack<Integer> pilhaDeEntrada;
	
	/**
	 * Pilha de expans�o.
	 */
	private Stack<Integer> pilhaDeExpansao;
	
	/**
	 * Constante que define o c�digo do simbolo inicial da gram�tica.
	 */
	private final int SIMBOLO_INICIAL = 52;
	
	/**
	 * Constante que define o c�digo do simbolo <code>cadeia vazia</code>.
	 */
	private final int CADEIA_VAZIA = 0;
	
	/**
	 * Armazena o simbolo do topo da pilha de expans�o. 
	 */
	private int X;
	
	/**
	 * Armazena o token do topo da pilha de entrada.
	 */
	private int a;
	
	/**
	 * Armazena true se o fonte tiver erros 
	 * ou false, caso n�o contenha erros.
	 */
	private boolean temErro;
	
	/**
	 * Armazena a descri��o do erro, caso tenha encontrado,
	 * caso contr�rio fica vazia;
	 */
	private String descricaoErro = null;
	
	/**
	 * Armazena os simbolos reconhecidos pela analise l�xica.
	 */
	private ArrayList<Token> simbolosReconhecidos;
	
	/**
	 * Construtor da classe.
	 */
	public AnalisadorSintatico( String prog ) {
		
		pilhaDeEntrada = new Stack<Integer>(); // inicializa a pilha de entrada
		
		simbolosReconhecidos = new AnalisadorLexico().efetuarAnalise( prog );
		
		// preenche a pilha de entrada com os dados da an�lise l�xica.
		for ( int i = 0; i < simbolosReconhecidos.size(); i++ ) {
			pilhaDeEntrada.push( simbolosReconhecidos.get(i).getCodigo() );
		}
		
		// inicializa a pilha de expans�o.
		pilhaDeExpansao = new Stack<Integer>();
		pilhaDeExpansao.push( SIMBOLO_INICIAL ); // p�e o simbolo inicial no topo da pilha de expans�o.
		
		// pega a tabela de parse.
		tabelaDeParse = new DefinicoesSintaticas().getTabelaDeParse();
		
		iniciaAnalise(); // chama o m�todo para inicializar a an�lise.

	}
	
	/**
	 * M�todo que efetua a an�lise sint�tica do programa-fonte.
	 * Caso a an�lise encontrar um erro, � retorna do false (para 
	 * indicar falha na an�lise), caso contr�rio, retorna true
	 * (para indicar sucesso na an�lise).
	 */
	public void iniciaAnalise() {
		
		int count = 0;
		
		do{ // faz o la�o at� que umas das pilhas esteja vazia.
		
			X = pilhaDeExpansao.elementAt( 0 ); // X recebe o elemento do topo da pilha de expans�o.
			a = pilhaDeEntrada.elementAt( 0 ); // a recebe o elemento do topo da pilha de entrada.		
			
			
			if(pilhaDeEntrada.get(0) != 1 && count == 0){ //se n�o estiver s�mbolo inicial Program no inicio da cadeia ocorre um erro.
				descricaoErro = "Sem regra para " + tabelaTokens.get(a-1).getTipo().toLowerCase() + " '" + buscaSimbolo(a) + "' na posi��o inicial.";
				temErro = true;
				break; 
				
			} else {
				
				count++;
				
			if ( X > 0 && X <= 51 ) { // verifica se X � um terminal
				if ( X == a ) {
					pilhaDeExpansao.remove( 0 ); // remove X da pilha.
					pilhaDeEntrada.remove( 0 ); // remove a da pilha.
				} else { //erro o terminal encontrado na pilha de entrada n�o � igual ao teminal esperado na pilha de expans�o.
					if(tabelaTokens.get(a-1).getTipo().equals(tabelaTokens.get(a-1).getToken()) && tabelaTokens.get(X-1).getTipo().equals(tabelaTokens.get(X-1).getToken())){
						descricaoErro = "N�o esperava o " + tabelaTokens.get(a-1).getTipo() + " ' " + buscaSimbolo(a) + " '," +
										" era esperado um " + tabelaTokens.get(X-1).getTipo() +".";
					} else {
						if(tabelaTokens.get(a-1).getTipo().equals(tabelaTokens.get(a-1).getToken())){
							descricaoErro = "N�o esperava o " + tabelaTokens.get(a-1).getTipo() + " ' " + buscaSimbolo(a) + " '," +
						    				" era esperado um " + tabelaTokens.get(X-1).getTipo() + " '" + tabelaTokens.get(X-1).getToken() + "'.";
						} else {
							if(tabelaTokens.get(X-1).getTipo().equals(tabelaTokens.get(X-1).getToken())){
								descricaoErro = "N�o esperava o " + tabelaTokens.get(a-1).getTipo() + " ' " + buscaSimbolo(a) + " '," +
							    " era esperado um " + tabelaTokens.get(X-1).getTipo() +".";
							} else {
									descricaoErro = "N�o esperava o " + tabelaTokens.get(a-1).getTipo() + " ' " + tabelaTokens.get(a-1).getToken() + " '," +
													" era esperado um " + tabelaTokens.get(X-1).getTipo() + " '" + tabelaTokens.get(X-1).getToken() + "'.";
								}
							}
						}
					temErro = true;
					break; // sai fora do la�o pois encontrou um erro.
				}
				
			} else { // � n�o-terminal
				Regra regraReturn = getRegraFromTabelaDeParse( X, a ); // pega a regra na tabela de parse para X e a
				
				if ( regraReturn != null  ) { // se for diferente de null, ent�o encontrou uma regra.
					ArrayList<Integer> derivacoes = getDerivacoes( regraReturn.getRegra() ); //retorna as deriva��es para a regra.
						
					if ( derivacoes.get( 0 ) == CADEIA_VAZIA ){ // verifica se � cadeia vazia
						pilhaDeExpansao.remove( 0 ); // remove X da pilha.
					} else { // se n�o � cadeia vazia
						derivaNaoTerminal( derivacoes, X ); // deriva nao-terminal X.
					}
						
				} else { // erro, pois n�o existe regra para X e a.
					descricaoErro = "N�o esperava fim da cadeia ap�s " + tabelaTokens.get(a-1).getTipo() + " '" + buscaSimbolo(a) + "'.";
					temErro = true;
					break; // sai fora do la�o pois encontrou um erro.
				}
			}
			
			//Verifica se a pilha de entrada ficou vazia
			if(pilhaDeEntrada.isEmpty() && !pilhaDeExpansao.isEmpty()){
				descricaoErro = "Sem regra para fim da cadeia para " + tabelaTokens.get(X-1).getTipo().toLowerCase() + " '" + buscaSimbolo(X) + "'.";
				temErro = true;
				break;
			}
		  }
			
		} while ( !pilhaDeExpansao.isEmpty() || !pilhaDeEntrada.isEmpty() ); 

		if ( !temErro ) { // se an�lise for sucesso
			JOptionPane.showMessageDialog( null, new JLabel( "An�lise sint�tica efetuada com sucesso!", SwingConstants.CENTER ), 
					"Sucesso", JOptionPane.PLAIN_MESSAGE , new ImageIcon("icons/ok.png"));
		} else { // se for falha
			JOptionPane.showMessageDialog( null, "ERRO SINT�TICO!\n" + descricaoErro,
					"Erro", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons/error.png") );
		}
	
	}
	

	/**
	 * Retorna a regra correspondente de acordo com os dados informados. 
	 */
	private Regra getRegraFromTabelaDeParse( Integer X, Integer a ) {
		Regra temp = null;
		for ( int i = 0; i < tabelaDeParse.size(); i++ ) {
			if ( tabelaDeParse.get( i ).getNaoTerminal() == X &&
					tabelaDeParse.get( i ).getTerminal() == a ) {
				temp = tabelaDeParse.get( i );
			}
		}
		return temp;
	}
	
	
	
	/**
	 * Retorna as deriva��es para a regra especificada.
	 */
	private ArrayList<Integer> getDerivacoes( Integer r ) {
		for ( int i = 0; i < tabelaDeParse.size(); i++ ) {
			if ( tabelaDeParse.get( i ).getRegra() == r )
				return tabelaDeParse.get( i ).getDerivacoes();
		}
		return null;
	}
	
	
	/**
	 * Substitui o n�o-terminal em quest�o por suas deriva��es.
	 */
	private void derivaNaoTerminal( ArrayList<Integer> deriv, Integer X ) {
		pilhaDeExpansao.remove( X ); // remove X da pilha
		//armazena as deriva��es de X na pilha
		for ( int i = 0; i < deriv.size(); i++ ) {
			pilhaDeExpansao.add( i, deriv.get( i ) );
		}		
	}
	
	/**
	 * M�todo que busca na tabela de simbolos reconhecidos o token a partir do c�digo repassado
	 * @param codigo, do token a ser pesquisado
	 * @return temp, a string contendo o token em si
	 */
	private String buscaSimbolo(int codigo){
		String temp = null; 
		for (int i = 0; i < simbolosReconhecidos.size(); i++) {
			if(simbolosReconhecidos.get(i).getCodigo() == codigo)
				temp = simbolosReconhecidos.get(i).getToken();
		}
		return temp;
	}
	
}
