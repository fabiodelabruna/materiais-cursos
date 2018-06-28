/**
 * Classe que representa um polin�mio.
 * @author Fabio Dela Bruna, Marcio Oz�rio.
 * @since 26/03/2008.
 */
public class Polinomio {
    
    private Termo primeiro; //Aponta para o primeiro termo do polin�mio.
    
    
    /**
     * Construtor default.
     */
    public Polinomio() {
        this.primeiro = null;
    }


    /** 
     *  M�todo para inserir um termo no polin�mio.
     *  Se o polin�nio j� tiver um termo com o mesmo expoente,
     *  ent�o os coeficientes dos dois termos devem ser somados.
     */
	public void insereTermo(int c, int e) {
		if(this.primeiro == null){
			Termo temp1 = new Termo(c, e, null);
			this.primeiro = temp1;
		}else{
			if(this.primeiro.getExpoente()<e){
				Termo temp1 = new Termo(c, e, this.primeiro);
				this.primeiro = temp1;
			}else{
				Termo temp1 = new Termo(c, e);
				Termo temp2 = this.primeiro;
				Termo termoAntigo = null;
				while (temp2 != null && temp2.getExpoente()>=e){
					if(temp2.getExpoente() == temp1.getExpoente()){
						temp2.setCoeficiente(c+temp2.getCoeficiente());
						return;
					}
					termoAntigo = temp2;
					temp2 = temp2.getProximo();
				}
				temp1.setProximo(termoAntigo.getProximo());
				termoAntigo.setProximo(temp1);
			}
		}
	}
    
		
	
	/**
	 * M�todo para somar um polin�mio passado como par�metro
	 * ao polin�mio em quest�o.
	 */
	public Polinomio soma(Polinomio outro) {
		Termo temp1 = outro.primeiro;
		Termo temp2 = this.primeiro;
		
		Polinomio soma = new Polinomio();
		
		soma = fazSoma(temp1, temp2);

		return soma;
	}
    
    
	/**
	 * Faz a soma.
	 */
	public Polinomio fazSoma(Termo temp1, Termo temp2){
		Polinomio soma = new Polinomio();

		while (temp2 != null){
			soma.insereTermo(temp2.getCoeficiente(), temp2.getExpoente());
			temp2 = temp2.getProximo();
		}
		while (temp1 != null){
			soma.insereTermo(temp1.getCoeficiente(), temp1.getExpoente());
			temp1 = temp1.getProximo();
		}
		return soma;
	}
	
    
	/**
	 * M�todo para multiplicar um polin�mio passado como par�metro
	 * ao polin�mio em quest�o.
	 */
	public Polinomio multiplica(Polinomio outro) {
		Termo temp = this.primeiro;
		Polinomio m = new Polinomio();
		while (temp != null){
			Termo temp2 = outro.primeiro;
			while (temp2 != null){
				Termo aux = temp.fazMultiplicacao(temp2);
				m.insereTermo(aux.getCoeficiente(), aux.getExpoente());
				temp2 = temp2.getProximo();
			}
			temp = temp.getProximo();
		}
		return m;
	}
	

    
    /**
     * M�todo toString para mostrar o polin�mio.
     */
    public String toString(){
        String resultado = "";
        Termo temp = this.primeiro;
	        while(temp != null){
		        if(temp.getCoeficiente() < 0){
		        	resultado+= " " + temp.toString();
		        	temp = temp.getProximo();
		        }else{
		        	resultado += " + " + temp.toString();
		        	temp = temp.getProximo();
		        }
	        }
	return resultado;
    }
}