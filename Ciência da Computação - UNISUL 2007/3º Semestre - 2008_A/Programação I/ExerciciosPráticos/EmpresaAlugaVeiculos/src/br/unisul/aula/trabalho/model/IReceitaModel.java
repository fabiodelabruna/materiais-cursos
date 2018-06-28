package br.unisul.aula.trabalho.model;


/**
 * Interface que define os m�todos de neg�cio da entidade Funcionario.
 * @author Fabio Dela Bruna / Marcio Oz�rio Teixeira.
 * @since 18/06/2008.
 */
public interface IReceitaModel {

	/**
	 * M�todo que retorna o total bruto do dinheiro ganho at� o momento.
	 * @param alugueis - ArrayList de Alugueis.
	 * @return Total Bruto.
	 */
	public abstract double getTotalBruto();
	
	/**
	 * M�todo que retorna o total de dispesas.
	 * @param funcionarios - ArrayList de Funcionarios.
	 * @return Total de Dispesas.
	 */
	public abstract double getTotalDispesa();

	/**
	 * M�todo que retorna o total l�quido (lucro ou preju�zo).
	 * @param alugueis - ArrayList de Alugueis.
	 * @param funcionarios - ArrayList de funcionarios.
	 * @return Total L�quido.
	 */
	public double getTotalLiquido();
}
