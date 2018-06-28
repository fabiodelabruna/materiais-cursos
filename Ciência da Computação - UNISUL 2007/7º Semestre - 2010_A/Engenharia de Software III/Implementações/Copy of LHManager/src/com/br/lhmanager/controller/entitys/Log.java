package com.br.lhmanager.controller.entitys;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa um log que ser� gerado a partir de um terminal.
 * 
 * @author Fabio Dela Bruna.
 * @since 04/05/2010.
 */
public class Log {

	// Atributos

	/**
	 * C�digo do log.
	 */
	private int codigo;

	/**
	 * Informa��o do log.
	 */
	private String descricao;

	/**
	 * Data de gera��o do log.
	 */
	private Date data;

	/**
	 * Terminal que gerou o log.
	 */
	private Terminal terminal;

	// Construtores

	/**
	 * Construtor sem par�metros (padr�o).
	 */
	public Log() {
		this(0, "", new Date(), new Terminal());
	}

	/**
	 * Construtor com par�metros.
	 * 
	 * @param codigo
	 *            C�digo do log.
	 * @param descricao
	 *            Informa��es do log.
	 * @param data
	 *            Data de gera��o do log.
	 * @param terminal
	 *            Terminal que gerou o log.
	 */
	public Log(int codigo, String descricao, Date data, Terminal terminal) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.data = data;
		this.terminal = terminal;
	}

	// toString
	@Override
	public String toString() {
		Format f = new SimpleDateFormat("dd/MM/yyyy");
		return String.format(
				"C�digo: %d\nDescri��o: %s\nData: %s\nTerminal: %s (%s)\n",
				getCodigo(), getDescricao(), f.format(getData()), getTerminal()
						.getDescricao(), getTerminal().getIp());
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

}
