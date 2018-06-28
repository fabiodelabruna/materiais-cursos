package br.unisul.aula.exemplorevenda.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.unisul.aula.exemplorevenda.entidade.Cidade;

public class CidadeModel implements ICidadeModel {

	// atributos
	
	private ArrayList<Cidade> cidades;
	
	
	// construtores
	
	/**
	 * Construtor default.
	 */
	public CidadeModel(){
		super();
	}
	
	/**
	 * Construtor com par�metros.
	 * @param cidades - ArrayList de Cidades.
	 */
	public CidadeModel(ArrayList<Cidade> cidades){
		super();
		this.cidades = cidades;
	}
	
	
	// m�todos
	
	@Override
	public Cidade buscaCidade(String nome) {
		if(nome != null && nome.length() > 1){
			for (Cidade c :this.cidades) {
				if(c.getNome().equals(nome)){
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public void cadastraCidade(Cidade c) {
		if(validaCidade(c)){
			this.cidades.add(c);
		}else{
			JOptionPane.showMessageDialog(null, "Verifique os dados da Cidade",
					"Erro de cadastro", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public ArrayList<Cidade> mostraCidades() {
		return this.cidades;
	}

	@Override
	public void removeCidade(Cidade c) {
		this.cidades.remove(c);		
	}

	/**
	 * M�todo para verificar se os dados da cidade est�o corretos.
	 * @param c - cidade.
	 * @return true ou false.
	 */
	private boolean validaCidade(Cidade c){
		if(c == null)
			return false;
		if(c.getNome() == null || c.getNome().length() <= 1)
			return false;
		if(c.getEstado() == null || c.getEstado().length() <= 1)
			return false;
		
		return true;
	}
	
}
