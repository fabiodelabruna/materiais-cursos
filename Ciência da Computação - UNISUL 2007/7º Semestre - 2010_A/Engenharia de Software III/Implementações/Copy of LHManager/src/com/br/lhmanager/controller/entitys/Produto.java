package com.br.lhmanager.controller.entitys;

import java.util.ArrayList;

/**
 * Classe que representa um produto.
 * 
 * @author Fabio Dela Bruna.
 * @since 04/05/2010.
 */
public class Produto implements ICrudBase {

	// Atributos

	/**
	 * C�digo do produto.
	 */
	private int codigo;

	/**
	 * Descri��o do produto.
	 */
	private String descricao;

	/**
	 * Valor do produto.
	 */
	private double valor;

	// Construtores

	/**
	 * Construtor sem par�metros (padr�o).
	 */
	public Produto() {
		this(0, "", 0.0);
	}

	/**
	 * Construtor com par�metros.
	 * 
	 * @param codigo
	 *            C�digo do produto.
	 * @param descricao
	 *            Descri��o do produto.
	 * @param valor
	 *            Valor do Produto.
	 */
	public Produto(int codigo, String descricao, double valor) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
	}

	// M�todos de persist�ncia.

	@Override
	public boolean atualizar(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletar(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inserir(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Produto> listar(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	// toString
	@Override
	public String toString() {
		return String.format("C�digo: %d\nDescri��o: %s\nValor%.2f\n",
				getCodigo(), getDescricao(), getValor());
	}

	// Getters e Setters

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
