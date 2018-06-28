package br.unisul.aula.interfacegrafica.model;

import br.unisul.aula.interfacegrafica.dto.ErroValidacao;


/**
 * Classe comum a todos os objetos de neg�cio.
 * @author Fabio Dela Bruna.
 * @since 14/10/2008.
 */
public abstract class BaseModel {
	
	/**
	 * M�todo para validar os atributos de um determinado cadastro.
	 * @param obj - Objeto a ter seus atributos validados.
	 */
	public abstract void validate(Object obj) throws ErroValidacao;

}
