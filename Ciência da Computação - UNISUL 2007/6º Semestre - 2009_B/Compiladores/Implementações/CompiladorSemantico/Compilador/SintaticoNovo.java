import java.util.Stack;
import java.util.Vector;
import javax.swing.*;


class SintaticoNovo implements Constants{
	
	private int x,a,ind_token;
	private Vector TOKENS;
	private Stack PILHA_EXPANSAO;
	private Vector LISTA_TOKENS;
	private SemanticoNovo semantico = new SemanticoNovo();
	private String ultimo_token;
	private String erro_semantico;
	
	
	//Recebe como par�metro a lista de tokens
	public SintaticoNovo(Vector listaTokens){
		ind_token = -1;
		TOKENS = listaTokens;
		
		inicializaPilhaExpansao();
		a = proximoToken();
	}
	
	private String getValorTokenAtual()
	{
		Tabela aux = (Tabela)(TOKENS.elementAt(ind_token-1));
		return aux.valor;
	}


	//Retorna o pr�ximo token "Inteiro"
	private int proximoToken(){
		ind_token++;
		Tabela aux;
		
		if (ind_token == TOKENS.size())
		 return (-1);
		 
		aux = (Tabela)(TOKENS.elementAt(ind_token));
		
		return aux.codigo;
	}
	
	private void inicializaPilhaExpansao(){
		PILHA_EXPANSAO = new Stack();
		
		PILHA_EXPANSAO.clear();
		PILHA_EXPANSAO.push(new Integer(DOLLAR)); //Coloca "$" na pilha
		PILHA_EXPANSAO.push(new Integer(START_SYMBOL));	//Coloca o s�mbolo inicial na pilha
	}
		
	private static final boolean eTerminal(int x)
    {
        return x < FIRST_NON_TERMINAL;
    }

    private static final boolean eNaoTerminal(int x)
    {
        return x >= FIRST_NON_TERMINAL && x < FIRST_SEMANTIC_ACTION;
    }
    
	private String principal(){
		do{
			
			x = Integer.parseInt(""+PILHA_EXPANSAO.peek());
			
			//x � TERMINAL ou "$"
			if (eTerminal(x)){
		
				//Se uma determinda produ��o levou a vazio, ou seja, "0", ent�o retira-se o terminal "0" (vazio) do topo da pilha
				if ( (x == 0) || (x == DOLLAR) ){
					PILHA_EXPANSAO.pop(); //retira "x" da pilha	
				}
				else{
					if (x == a){

						PILHA_EXPANSAO.pop(); //retira "x" da pilha
						a = proximoToken(); //pega a pr�xima entrada
						ultimo_token = getValorTokenAtual();
					}
					else{
						return "Erro sint�tico:\n" + "Era esperado: \"" + getNomeToken(x) + "\"";
					}
				}
			}
			//x � N�O-TERMINAL
			else if(eNaoTerminal(x))
			{	
				
				if (a<0){
					return "Erro sint�tico:\n" + "N�o foi poss�vel expandir regra n� " + x + "\nDescri��o: " + PARSER_ERROR[x];
				}
				
				int r = PARSER_TABLE[x-FIRST_NON_TERMINAL][a-2];
				
				//Existe regra na matriz de parsing?
				if (r >= 0){
					PILHA_EXPANSAO.pop(); //retira "x" da pilha
					pushProducao(r);  //Coloca a produ��o na pilha de expans�o
				}
				else{
					String erro_ret = "Erro sint�tico:\nEra esperado: ";
					int regra_erro;
					
				for (int i=0;i<50;i++)
					{
						regra_erro = PARSER_TABLE[x-FIRST_NON_TERMINAL][i];
						if (regra_erro>0){
							if (erro_ret.equals("Erro sint�tico:\nEra esperado: "))							
								erro_ret+=getNomeToken(i+2);
							else
								erro_ret+=", " + getNomeToken(i+2);
						}
							
					}
					return erro_ret;
				}
			}			
			else//x � a��o semantica
			{
				erro_semantico = semantico.executaAcao(x,ultimo_token);
				PILHA_EXPANSAO.pop(); //retira "x" da pilha
				if (!erro_semantico.equals(""))
					return ("Erro sem�ntico:\n" + erro_semantico);
			}

		}while(x != DOLLAR);
		
		return "SUCESSO!";
	}
	
	private void pushProducao(int indiceProducao)
	{
		int[] producao = PRODUCTIONS[indiceProducao]; //Armazena a produ��o da regra encontrada na matriz de parsing
		
		//empilha a produ��o em ordem reversa
		for (int i=producao.length-1; i>=0; i--)
		{
			PILHA_EXPANSAO.push(new Integer(producao[i]));
		}
	}
	
	public Vector executar(){
		Vector ret_sint = new Vector();
		inicializaListaTokens();
		
		ret_sint.addElement(principal()); //1� elemento � a msg de erro ou "SUCESSO!" [STRING]
		ret_sint.addElement(semantico.getInstrucoes()); //2� elemento � a �rea de instru��es [DADOSINSTRUCAO]
		ret_sint.addElement(semantico.getLiterais()); //3� elemento � a �rea de literais [VECTOR]
		
		return ret_sint;
	}
	
	private String getNomeToken(int codigo){
		return (String)LISTA_TOKENS.elementAt(codigo);
	}
	
	private void inicializaListaTokens(){
		LISTA_TOKENS = new Vector();
		
		LISTA_TOKENS.addElement("");
		
		LISTA_TOKENS.addElement("$");
		LISTA_TOKENS.addElement("PROGRAMA");
		LISTA_TOKENS.addElement("ROTULO");
		LISTA_TOKENS.addElement("CONSTANTE");
		LISTA_TOKENS.addElement("VARIAVEL");
		LISTA_TOKENS.addElement("PROCEDIMENTO");
		LISTA_TOKENS.addElement("INICIO");
		LISTA_TOKENS.addElement("FIM");
		LISTA_TOKENS.addElement("INTEIRO");
		LISTA_TOKENS.addElement("VETOR");
		LISTA_TOKENS.addElement("DE");
		LISTA_TOKENS.addElement("CHAMADA");
		LISTA_TOKENS.addElement("VAIPARA");
		LISTA_TOKENS.addElement("SE");
		LISTA_TOKENS.addElement("ENTAO");
		LISTA_TOKENS.addElement("SENAO");
		LISTA_TOKENS.addElement("ENQUANTO");
		LISTA_TOKENS.addElement("FACA");
		LISTA_TOKENS.addElement("REPITA");
		LISTA_TOKENS.addElement("ATEQUE");
		LISTA_TOKENS.addElement("LERLINHA");
		LISTA_TOKENS.addElement("ESCREVELINHA");
		LISTA_TOKENS.addElement("OU");
		LISTA_TOKENS.addElement("E");
		LISTA_TOKENS.addElement("NAO");
		LISTA_TOKENS.addElement("IDENTIFICADOR");
		LISTA_TOKENS.addElement("INTEIRO");
		LISTA_TOKENS.addElement("PARA");
		LISTA_TOKENS.addElement("ATE");
		LISTA_TOKENS.addElement("CASO");
		LISTA_TOKENS.addElement("+");
		LISTA_TOKENS.addElement("-");
		LISTA_TOKENS.addElement("*");
		LISTA_TOKENS.addElement("/");
		LISTA_TOKENS.addElement("[");
		LISTA_TOKENS.addElement("]");
		LISTA_TOKENS.addElement("(");
		LISTA_TOKENS.addElement(")");
		LISTA_TOKENS.addElement(":=");
		LISTA_TOKENS.addElement(":");
		LISTA_TOKENS.addElement("=");
		LISTA_TOKENS.addElement(">");
		LISTA_TOKENS.addElement(">=");
		LISTA_TOKENS.addElement("<");
		LISTA_TOKENS.addElement("<=");
		LISTA_TOKENS.addElement("<>");
		LISTA_TOKENS.addElement(",");
		LISTA_TOKENS.addElement(";");
		LISTA_TOKENS.addElement("LITERAL");
		LISTA_TOKENS.addElement(".");
		LISTA_TOKENS.addElement("..");
	}
}

