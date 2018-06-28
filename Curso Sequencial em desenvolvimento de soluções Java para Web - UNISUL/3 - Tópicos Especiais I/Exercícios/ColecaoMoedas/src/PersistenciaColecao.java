
public interface PersistenciaColecao {
	
	/**
	 * M�todo para inserir uma moeda.
	 * @param moeda - objeto do tipo Moeda.
	 * @return true se a moeda for inserida ou false se n�o for inserida.
	 */
	public boolean inserirMoeda(Moeda moeda);
	
	/**
	 * M�todo para consultar uma moeda atrav�z do c�digo.
	 * @param codigo - c�digo da moeda.
	 * @return moeda encontrada ou null se n�o encontrar nenhuma moeda com o c�digo espec�fico.
	 */
	public Moeda consultarMoeda(int codigo);
	
	/**
	 * M�todo para remover uma moeda atrav�z do c�digo.
	 * @param codigo - c�digo da moeda.
	 * @return true se a moeda for exclu�da ou false se n�o for exclu�da.
	 */
	public boolean removerMoeda(int codigo);
	
	/**
	 * M�todo para alterar os dados de uma determinada moeda.
	 * @param moeda - moeda a ser alterada.
	 * @return true se a moeda for alterada ou false se n�o for alterada.
	 */
	public boolean alterarMoeda(Moeda moeda);
	
	/**
	 * M�todo para listar todas as moedas cadastradas.
	 * @return array de moedas.
	 */
	public Moeda[] listarMoedas();
}