
public class Funcionario extends Pessoa{

	
	// construtor
	
	/**
	 * Construtor com par�metros
	 * @param nome - nome do funcionario
	 * @param idade - idade do funcionario
	 */
	public Funcionario(String nome, int idade) {
		super(nome, idade);
	
	}

	
	// m�todos
	
	@Override
	public boolean verificaDependentes() {
		return false;
	}
	
	

}
