package br.unisul.aula.interfacegrafica.model;

import java.util.List;

import br.unisul.aula.interfacegrafica.dao.DAOException;
import br.unisul.aula.interfacegrafica.dao.QuartoDAO;
import br.unisul.aula.interfacegrafica.dto.ErroValidacao;
import br.unisul.aula.interfacegrafica.dto.QuartoDTO;

public class QuartoModel extends BaseModel {

	
	public QuartoModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void validate(Object obj) throws ErroValidacao {
		QuartoDTO quartoDTO = (QuartoDTO) obj;
		
		if (quartoDTO.getNumero() <= 0)
			throw new ErroValidacao("O n�mero do quarto deve ser maior que 0 (zero)");
		if (quartoDTO.getDescricao() == null || quartoDTO.getDescricao().trim().length() == 0)
			throw new ErroValidacao("O campo Descri��o deve ser preechido");
		if (quartoDTO.getValorDiaria() <= 0) {
			throw new ErroValidacao("O valor da di�ria deve ser maior que 0 (zero)");
		}
	}
	
	
	/**
	 * M�todo para inserir o cadastro de um quarto no Banco.
	 * @param quarto Quarto a ser inserido.
	 * @throws ErroValidacao 
	 */
	public void insert (QuartoDTO quarto) throws ErroValidacao, DAOException {
		validate(quarto);
		QuartoDAO.insert(quarto);
	}
	
	
	/**
	 * M�todo para atualizar o cadastro de um quarto.
	 * @param quarto Quarto a ter o cadastro atualizado.
	 */
	public void update (QuartoDTO quarto) throws ErroValidacao, DAOException {
		validate(quarto);
		QuartoDAO.insert(quarto);
	}
	
	
	/**
	 * M�todo para apagar o cadastro de um quarto do Banco.
	 * @param quarto Quarto a ser apagado.
	 */
	public void delete (QuartoDTO quarto) throws ErroValidacao, DAOException {
		QuartoDAO.delete(quarto);
	}
	
	
	/**
	 * M�todo para efetuar uma busca de cadastro de quartos
	 * atrav�z de um determinado n�mero.
	 * @return Retorna uma lista de Quato.
	 * Caso nenhum quarto for encontrado, � retornado null.
	 */
	public List<QuartoDTO> find () throws DAOException {
		return QuartoDAO.find();
	}

}
