package com.br.lhmanager.controller.entitys;

/**
 * Classe que representa uma entidade Cr�dito.
 * 
 * @author Fabio Dela Bruna.
 * @since 04/05/2010.
 */
public class Credito {

	// Atributos

	/**
	 * C�digo do cr�dito.
	 */
	private int codigo;

	/**
	 * Valor do cr�dito.
	 */
	private double valor;

	/**
	 * Horas de utiliza��o equivalentes �quele cr�dito.
	 */
	private double horas;

	/**
	 * Uma promo��o feita sobre os cr�ditos.
	 */
	private Promocao promocao;

	// Construtores

	/**
	 * Construtor sem par�metros (padr�o).
	 */
	public Credito() {
		this(0, 0.0, 0.0, new Promocao());
	}

	/**
	 * Construtor com par�metros.
	 * 
	 * @param codigo
	 *            C�digo do cr�dito.
	 * @param valor
	 *            Valor do cr�dito.
	 * @param horas
	 *            Horas de utiliza��o equivalentes �quele cr�dito.
	 * @param promocao
	 *            Uma promo��o feita sobre os cr�ditos.
	 */
	public Credito(int codigo, double valor, double horas,
			Promocao promocao) {
		this.codigo = codigo;
		this.valor = valor;
		this.horas = horas;
		this.promocao = promocao;
	}

	// M�todos de persist�ncia

	public boolean incluirCredito() {
		return false;
	}

	public boolean atualizarCredito() {
		return false;
	}

	// toString
	@Override
	public String toString() {
		return String.format(
				"C�digo: %d\nValor: %.2f\nHoras: %.2f\nPromo��o: %s\n",
				getCodigo(), getValor(), getHoras(), getPromocao()
						.getDescricao());
	}

	// Getters e Setters

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getHoras() {
		return horas;
	}

	public void setHoras(double horas) {
		this.horas = horas;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

}
