package br.unisul.aula.interfacegrafica.model;

import java.util.List;

import br.unisul.aula.interfacegrafica.dao.DAOException;
import br.unisul.aula.interfacegrafica.dao.FuncionarioDAO;
import br.unisul.aula.interfacegrafica.dto.ErroValidacao;
import br.unisul.aula.interfacegrafica.dto.FuncionarioDTO;
import br.unisul.aula.interfacegrafica.util.CPFValidator;

public class FuncionarioModel extends BaseModel {

	
	/**
	 * Construtor default.
	 */
	public FuncionarioModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void validate(Object obj) throws ErroValidacao {
		FuncionarioDTO func = (FuncionarioDTO) obj;
		
		if (func.getNome() == null || func.getNome().trim().length() == 0)
			throw new ErroValidacao("O campo Nome deve ser preenchido");
		if (!CPFValidator.validaCpf( func.getCpf() ))
			throw new ErroValidacao("Digite um Cpf v�lido");
		if (func.getPis() == null || func.getPis().trim().length() == 0)
			throw new ErroValidacao("O campo PIS deve ser preenchido");
		if (func.getFone() == null || func.getFone().trim().length() == 0)
			throw new ErroValidacao("O campo Telefone deve ser preenchido");
		if (func.getEndereco() == null || func.getEndereco().trim().length() == 0)
			throw new ErroValidacao("O campo Endere�o deve ser preenchido");
		
	}
	
	
	/**
	 * M�todo para inserir o cadastro de um funcion�rio no Banco.
	 * @param funcionario Funcionario a ser inserido.
	 * @throws ErroValidacao 
	 */
	public void insert (FuncionarioDTO funcionario) throws ErroValidacao, DAOException {
		validate(funcionario);
		FuncionarioDAO.insert(funcionario);		
	}
	
	/**
	 * M�todo para atualizar o cadastro de um funcion�rio.
	 * @param funcionario Funcionario a ter o cadastro atualizado.
	 */
	public void update (FuncionarioDTO funcionario) throws ErroValidacao, DAOException {
		validate(funcionario);
		FuncionarioDAO.update(funcionario);
	}
	
	
	/**
	 * M�todo para apagar o cadastro de um funcionario do Banco.
	 * @param funcionario Funcionario a ser apagado.
	 */
	public void delete (FuncionarioDTO funcionario) throws ErroValidacao, DAOException {
		validate(funcionario);
		FuncionarioDAO.delete(funcionario);
	}
	
	
	/**
	 * M�todo para efetuar uma busca de cadastro de funcionarios
	 * atrav�z de um determinado nome.
	 * @param nome Nome do Funcion�rio a ser buscado.
	 * @return Retorna uma lista de Funcion�rios com o nome especificado.
	 * Caso nenhum funcion�rio foi encontrado, � retornado null.
	 */
	public List<FuncionarioDTO> find (String nome) throws DAOException {
		return FuncionarioDAO.find(nome);
	}

}
