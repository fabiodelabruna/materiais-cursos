
public class Cliente extends Pessoa {

	// construtor
	
	/**
	 * Construtor com parametros
	 */
	public Cliente(String nome, int idade) {
		super(nome, idade);
	}

	
	// m�todos
	
	@Override
	public boolean verificaDependentes() {
		return true;
	}

	
}
