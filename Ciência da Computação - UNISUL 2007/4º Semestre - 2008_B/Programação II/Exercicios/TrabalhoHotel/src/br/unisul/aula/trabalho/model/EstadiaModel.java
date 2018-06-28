package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import br.unisul.aula.trabalho.dto.Cliente;
import br.unisul.aula.trabalho.dto.Estadia;

public class EstadiaModel implements IEstadiaModel {

	private ArrayList<Estadia> estadias;

	/**
	 * Construtor default.
	 */
	public EstadiaModel() {
		super();
	}

	/**
	 * Construtor com par�metros.
	 * 
	 * @param estadias - ArrayList de Estadias
	 */
	public EstadiaModel(ArrayList<Estadia> estadias) {
		super();
		this.estadias = estadias;
	}

	@Override
	public ArrayList<Estadia> buscaEstadia(Cliente c) {
		if (c.getEstadias() != null)
			return c.getEstadias();

		return null;
	}

	@Override
	public void cadastraEstadia(Estadia e) throws IllegalArgumentException {
		validaEstadia(e);
		this.estadias.add(e);
	}

	@Override
	public ArrayList<Estadia> mostraEstadias() {
		return this.estadias;
	}

	/**
	 * M�todo que verifica se todos os atributos obrigat�rios de uma estadia s�o v�lidos.
	 * @param e - Estadia.
	 * @throws IllegalArgumentException
	 */
	private void validaEstadia(Estadia e) throws IllegalArgumentException {
		if (e.getQtdDias() <= 0)
			throw new IllegalArgumentException("Quantidade de dias inv�lida");
		if (e.getQuarto() == null)
			throw new IllegalArgumentException("Quarto inv�lido!");

	}

}
