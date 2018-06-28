package com.br.lhmanager.controller.entitys;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.CDATASection;

/**
 * Classe que representa uma despesa que ser� "lan�ada" no sistema.
 * 
 * @author Fabio Dela Bruna.
 * @since 04/05/2010.
 */
public class Despesa implements ICrudBase {

	// Atributos

	/**
	 * C�digo da despesa.
	 */
	private int codigo;

	/**
	 * Descri��o da despesa.
	 */
	private String descricao;

	/**
	 * Valor total da despesa.
	 */
	private double valor;

	/**
	 * Data de pagamento da despesa.
	 */
	private String dataPagameto;

	/**
	 * Tipo da despesa.
	 */
	private TipoDespesa tipoDespesa;

	// Construtores

	/**
	 * Construtor sem par�metros (padr�o).
	 */
	public Despesa() {
		this(0, "", 0.0, "", new TipoDespesa());
	}

	/**
	 * Construtor com par�metros.
	 * 
	 * @param codigo
	 *            C�digo da despesa.
	 * @param descricao
	 *            Descri��o da despesa.
	 * @param valor
	 *            Valor da despesa.
	 * @param dataPagameto
	 *            Data de pagamento da despesa.
	 * @param tipoDespesa
	 *            Tipo da despesa.
	 */
	public Despesa(int codigo, String descricao, double valor,
			String dataPagameto, TipoDespesa tipoDespesa) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.dataPagameto = dataPagameto;
		this.tipoDespesa = tipoDespesa;
	}

	// M�todos de controle da classe

	public double calcularTotalDespesa() {
		return 0;
	}

	// M�todos de persist�ncia

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
	public ArrayList<Despesa> listar(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// toString
	@Override
	public String toString() {
		Format f = new SimpleDateFormat("dd/MM/yyyy");
		return String
				.format(
						"C�digo: %d\nDescri��o: %s\nTipo: %s\nData Pagamento: %s\nValor: %.2f",
						getCodigo(), getDescricao(), getTipoDespesa()
								.getDescricao(), f.format(getDataPagameto()),
						getValor());
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

	public String getDataPagameto() {
		return dataPagameto.substring(8)+"/"+dataPagameto.substring(5, 7)+"/"+dataPagameto.substring(0, 4);
	}
	
	public String getDataPagametoMysql() {
		return dataPagameto;
	}

	public void setDataPagameto(String dataPagameto) {
		this.dataPagameto = dataPagameto;
	}
	

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

}
