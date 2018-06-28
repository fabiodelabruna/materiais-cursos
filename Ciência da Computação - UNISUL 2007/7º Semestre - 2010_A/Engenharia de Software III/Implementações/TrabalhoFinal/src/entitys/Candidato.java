package entitys;

/**
 * Classe Candidato estendendo pessoa, ela recebe um objeto do tipo Curr�culo e algumas propriedades exclusivas do candidato.
 * @author Allan
 *
 */
public class Candidato extends Pessoa{
	
	/**
	 * Obejto do tipo Curriculo que a candidato possui.
	 */
	private Curriculo curriculo;
	

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	
	}

	/**
	 * toString da classe candidato.
	 */
	public String toString(){
		return "";
	}
	
	
	/**
	 * Construtor com par�metros.
	 * @param codigo Codigo do candidato.
	 * @param curriculo Curriculo do candidato.
	 */
	public Candidato(int codigo, Curriculo curriculo){
		super(codigo);
		this.curriculo = curriculo;
	}
	
	/**
	 * Construtor padr�o.
	 */
	public Candidato() {

	}
	
}
