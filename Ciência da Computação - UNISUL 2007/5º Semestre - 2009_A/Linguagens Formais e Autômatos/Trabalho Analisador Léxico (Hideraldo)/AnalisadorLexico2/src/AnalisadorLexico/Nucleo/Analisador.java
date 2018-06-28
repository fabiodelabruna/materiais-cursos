package AnalisadorLexico.Nucleo;
import java.util.ArrayList;



/**
 * Classe que verifica se a cadeia inserida pelo usu�rio � v�lida.
 * @author 	Hideraldo Lu�s Simon J�nior
 * 			Marco Aur�lio Lima Fernandes
 * 			Rodrigo Paschoal Jeremias
 * @since setembro de 2008
 *
 */
public class Analisador {

	private TabelaTokens tab;
	private ArrayList<Token> lista;
	private boolean ERRO;
	private String descricaoERRO;
	
	/**
	 * Construtor da classe
	 */
	public Analisador(){
		this.tab= new TabelaTokens();
		this.lista= new ArrayList<Token>();
		this.ERRO= false;
		this.descricaoERRO= "";
	}
	
	/**
	 * Retorna todos os elementos verificados pelo Analisador (Uma lista de Token)
	 * @param  cadeia String - cadeia a ser verificada
	 * @return ArrayList<Token> 
	 */
	public ArrayList<Token> analisar(String cadeia) {		
		int i=0;
		while (i<cadeia.length()) {
			if (defineCaracter(cadeia.charAt(i))==1){
				i= reconheceLetras(cadeia, i);
				try {
					defineCaracter(cadeia.charAt(i));
				}catch (Exception e) {
					break;
				}
			}
			if (defineCaracter(cadeia.charAt(i))==2){
				i= reconheceNumeros(cadeia, i);
			}
			if (defineCaracter(cadeia.charAt(i))==3){
				i= reconheceSimbolos(cadeia, i);
			}
			if (defineCaracter(cadeia.charAt(i))==0){
				if(cadeia.charAt(i)=='"'){
					i= reconheceLiterais(cadeia, i);
				}else{
					if ((cadeia.charAt(i)!='\n')&&(cadeia.charAt(i)!=' ')){
						this.ERRO= true;
						this.descricaoERRO= "O caracter "+cadeia.charAt(i)+" na posi��o "
							+(i+1)+" n�o � reconhecido pelo Analisador.";
					}
				}
			}
			i+= 1;
		}
		return this.lista;
	}
	
	/**
	 * Define qual o tipo do caracter passado por par�metro.
	 * 1-Letra, 2-N�mero, 3-S�mbolo V�lido, 0-N�o Identificado 
	 * @param c char - caracter a ser verificado
	 * @return int
	 */
	private int defineCaracter(char c){
		String letras= "abcdefghijklmnopqrstuvxyzwABCDEFGHIJKLMNOPQRSTUVXYZW";
		String numeros= "0123456789";
		String simbolos= "+-*/[]():=><,;.$";
		
		for (int i=0; i<letras.length();i++){
			if (letras.charAt(i)==c){
				return 1;
			}
		}
		for (int i=0; i<numeros.length();i++){
			if (numeros.charAt(i)==c){
				return 2;
			}
		}
		for (int i=0; i<simbolos.length();i++){
			if (simbolos.charAt(i)==c){
				return 3;
			}
		}
		return 0;
	}	
	
	/**
	 * Verifica o tamanho da Literal e insere na lista de Tokens.
	 * Retorna a posi��o do fim da Literal.
	 * Grava Erro caso a Literal n�o tenha fim.
	 * @param cadeia String - cadeia a ser verificada
	 * @param i int - posi��o em que come�a a literal
	 * @return int
	 */
	private int reconheceLiterais(String cadeia, int i){
		boolean literal= false;
		int fimLit= 0;
		for (int j=i+1; j<cadeia.length(); j++){
			if (cadeia.charAt(j)=='"'){
				literal= true;
				fimLit= j;
				break;
			}
		}
		if (literal){
			String novaCadeia= cadeia.substring(i, fimLit+1);
			if (novaCadeia.length()>255){
				this.ERRO= true;
				this.descricaoERRO= "Literal iniciada na posi��o "+(i+1)+" com mais 255 caracteres.";
			}
			lista.add(new Token(48, novaCadeia, "Literal"));
			return fimLit;
		}else{
			this.ERRO= true;
			this.descricaoERRO= "N�o encontrado o fim da Literal iniciada na posi��o "+(i+1)+".";
			return i;
		}
	}


	/**
	 * Verifica o tamanho do Indentificador ou Palavra Reservada e insere na lista de Tokens.
	 * Retorna a posi��o do fim do Indentificador ou Palavra Reservada.
	 * Grava Erro caso o Indentificador tenha mais de 30 caracteres.
	 * @param cadeia String - cadeia a ser verificada
	 * @param i int - posi��o em que come�a o Indentificador ou Palavra Reservada
	 * @return int
	 */
	private int reconheceLetras(String cadeia, int i){
		int fim= 0;
		for (int j=i+1; j<cadeia.length(); j++){
			int tipo= defineCaracter(cadeia.charAt(j));
			if ((tipo!=1)&&(tipo!=2)){
				fim= j;
				break;
			}
		}
		String novaCadeia;
		if (fim==0){
			novaCadeia= cadeia.substring(i);
			fim= cadeia.length();
		}else{
			novaCadeia= cadeia.substring(i, fim);
		}
		if (novaCadeia.length()>30){
			this.ERRO= true;
			this.descricaoERRO= "Identificador iniciado na posi��o "+(i+1)+" com mais 30 caracteres.";
		}
		Token t=tab.procurarToken(novaCadeia);		
		if (!(t==null)){
			lista.add(t);
		}else{
			lista.add(new Token(25, novaCadeia, "Identificador"));
		}
		return fim;
	}

	/**
	 * Verifica os s�mbolos v�lidos e insere na lista de Tokens.
	 * Verifica tamb�m N�meros Inteiros Negativos e Coment�rios.
	 * Retorna a posi��o do fim dos S�mbolos, N�meros Inteiros Negativos ou Coment�rios.
	 * Grava Erro caso o N�mero Inteiro Negativo seja menor que -32767.
	 * @param cadeia String - cadeia a ser verificada
	 * @param i int - posi��o em que come�a o S�mbolo, N�mero Inteiro Negativo ou Coment�rios
	 * @return int
	 */
	private int reconheceSimbolos(String cadeia, int i){
		switch(cadeia.charAt(i)){
		case '/': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		case '[': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		case ']': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		case '*': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		case ')': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		case '=': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		case ',': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		case ';': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		case '$': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		case '+': {lista.add(tab.procurarToken(cadeia.charAt(i))); break;}
		default: {break;}
		}
		if (cadeia.charAt(i)=='('){
			try {
				if (cadeia.charAt(i+1)=='*'){
					boolean comentario= false;
					int fimComent= 0;
					for (int j=i+1; j<cadeia.length(); j++){
						if (cadeia.charAt(j)=='*'){
							if (cadeia.charAt(j+1)==')'){
								comentario= true;
								fimComent= j+1;
							}
						}
					}
					if (comentario){
						return fimComent;
					}else{
						lista.add(tab.procurarToken(cadeia.charAt(i)));
						return i;
					}
				}else{
					lista.add(tab.procurarToken(cadeia.charAt(i)));
					return i;
				}
			} catch (Exception e) {
				lista.add(tab.procurarToken(cadeia.charAt(i)));
				return i;
			}
		}
		if (cadeia.charAt(i)=='-'){
			if (defineCaracter(cadeia.charAt(i+1))==2){
				int fim= i+1;
				while (defineCaracter(cadeia.charAt(fim))==2){
					fim+=1;
					if(fim>=cadeia.length()){
						break;
					}
				}
				String novaCadeia= cadeia.substring(i, fim);
				lista.add(new Token(25, novaCadeia, "N�mero Inteiro"));
				int numero= Integer.parseInt(novaCadeia);
				if (numero<-32767){
					this.ERRO= true;
					this.descricaoERRO= "N�mero Inteiro iniciado na posi��o "+(i+1)+" menor que -32767.";
				}
				return fim-1;
			}else{
				lista.add(tab.procurarToken(cadeia.charAt(i)));
				return i;
			}
		}
		if (cadeia.charAt(i)==':'){
			if ((cadeia.length()!=(i+1))&&(cadeia.charAt(i+1)=='=')){
				lista.add(tab.procurarToken(""+cadeia.charAt(i)+cadeia.charAt(i+1)));
				return i+1;
			}else{
				lista.add(tab.procurarToken(cadeia.charAt(i)));
				return i;
			}
		}
		if (cadeia.charAt(i)=='>'){
			if ((cadeia.length()!=(i+1))&&(cadeia.charAt(i+1)=='=')){
				lista.add(tab.procurarToken(""+cadeia.charAt(i)+cadeia.charAt(i+1)));
				return i+1;
			}else{
				lista.add(tab.procurarToken(cadeia.charAt(i)));
				return i;
			}
		}
		if (cadeia.charAt(i)=='<'){
			if ((cadeia.length()!=(i+1))&&(cadeia.charAt(i+1)=='=')){
				lista.add(tab.procurarToken(""+cadeia.charAt(i)+cadeia.charAt(i+1)));
				return i+1;
			}else{
				if (cadeia.charAt(i+1)=='>'){
					lista.add(tab.procurarToken(""+cadeia.charAt(i)+cadeia.charAt(i+1)));
					return i+1;
				}else{
					lista.add(tab.procurarToken(cadeia.charAt(i)));
					return i;
				}
			}
		}
		if (cadeia.charAt(i)=='.'){
			if ((cadeia.length()!=(i+1))&&(cadeia.charAt(i+1)=='.')){
				lista.add(tab.procurarToken(""+cadeia.charAt(i)+cadeia.charAt(i+1)));
				return i+1;
			}else{
				lista.add(tab.procurarToken(cadeia.charAt(i)));
				return i;
			}
		}
		return i;
	}
	
	/**
	 * Verifica os N�meros Inteiros Positivos.
	 * Retorna a posi��o do fim dos N�meros Inteiros Positivos.
	 * Grava Erro caso o N�mero Inteiro Positivo seja maior que 32767.
	 * @param cadeia String - cadeia a ser verificada
	 * @param i int - posi��o em que come�a o N�mero Inteiro Positivo.
	 * @return int
	 */
	private int reconheceNumeros(String cadeia, int i){
		int fim= 0;
		for (int j=i+1; j<cadeia.length(); j++){
			if (defineCaracter(cadeia.charAt(j))!=2){
				fim= j;
				break;
			}
		}
		String novaCadeia;
		if (fim==0){
			novaCadeia= cadeia.substring(i);
			fim= cadeia.length();
		}else{
			novaCadeia= cadeia.substring(i, fim);
		}
		lista.add(new Token(26, novaCadeia, "N�mero Inteiro"));
		int numero= Integer.parseInt(novaCadeia);
		if (numero>32767){
			this.ERRO= true;
			this.descricaoERRO= "N�mero Inteiro iniciado na posi��o "+(i+1)+" maior que 32767.";
		}
		return fim-1;
	}
	
	/**
	 * Retorna o valor do atributo ERRO 
	 * @return boolean
	 */
	public boolean getERRO(){
		return this.ERRO;
		
	}
	
	/**
	 * Retorna o valor do atributo DescricaoERRO
	 * @return String
	 */
	public String getDescricaoERRO(){
		return this.descricaoERRO;
	}

	/**
	 * Mostra a lista resultante da an�lise
	 * @return String
	 */
	public String mostraLista(){
		String output= "# Lista #\n";
		for (int i=0; i<lista.size(); i++){
			output+= lista.get(i).getCodigo()+" - ";
			output+= lista.get(i).getNome()+" - ";
			output+= lista.get(i).getDescricao()+"\n";
		}
		return output;
	}
}
