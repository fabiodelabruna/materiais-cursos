package com.br.lhmanager.controller.entitys;

/**
 * Classe que representa um atendimento na LanHouse.
 * 
 * @author Fabio Dela Bruna.
 * @since 04/05/2010.
 */
public class Atendimento {

	// Atributos

	/**
	 * Representa um usu�rio.
	 */
	private Usuario usuario;

	/**
	 * Representa um cliente.
	 */
	private Cliente cliente;

	// Construtores

	/**
	 * Construtor sem par�metros (padr�o).
	 */
	public Atendimento() {
		this(new Usuario(), new Cliente());
	}

	/**
	 * Construtor com par�metros.
	 * 
	 * @param usuario
	 *            Objeto do tipo Usu�rioEntity.
	 * @param cliente
	 *            Objeto do tipo ClienteEntity
	 */
	public Atendimento(Usuario usuario, Cliente cliente) {
		this.usuario = usuario;
		this.cliente = cliente;
	}
	
	// M�todos de controle da classe

	/**
	 * Calcula o total a ser pago pelo cliente.
	 */
	public double calcularTotalSerPago() {
		return 0;
	}

	/**
	 * Efetua a compra de cr�ditos ao cliente.
	 */
	public void comprarCredito() {

	}

	// Getters e Setters

	@Override
	public String toString() {
		return String.format("Usu�rio: %s\nCliente: %s\n", getUsuario()
				.getNome(), getCliente().getNome());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
