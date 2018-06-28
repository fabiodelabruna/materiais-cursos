package br.unisul.aula.trabalho.model;

import java.util.ArrayList;

import br.unisul.aula.trabalho.entidades.Quarto;

public class QuartoModel implements IQuartoModel {
	
	//atributos
	
	private ArrayList<Quarto> quartos;
	
	
	//construtores

	/**
	 * Construtor default.
	 */
	public QuartoModel() {
		super();
	}
	
	/**
	 * Construtor com par�metros.
	 * @param quartos - ArrayList de Quartos.
	 */
	public QuartoModel(ArrayList<Quarto> quartos) {
		super();
		this.quartos = quartos;
	}
	
	
	//m�todos principais

	@Override
	public void cadastraQuarto(Quarto q) {
		this.quartos.add(q);
	}

	@Override
	public ArrayList<Quarto> mostraQuartos() {
		return this.quartos;
	}

	@Override
	public void removeQuarto(Quarto q) {
		this.quartos.remove(q);
		
	}
	

}
