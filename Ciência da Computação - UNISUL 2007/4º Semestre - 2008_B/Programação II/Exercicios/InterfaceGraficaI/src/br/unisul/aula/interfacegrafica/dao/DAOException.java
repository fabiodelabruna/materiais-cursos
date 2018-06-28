package br.unisul.aula.interfacegrafica.dao;


public class DAOException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private Throwable cause;

	/**
	 * Construtor default.
	 */
	public DAOException() {
		
	}
	
	
	/**
	 * Construtor que recebe a causa da exce��o por par�metro.
	 * @param reason Causa da exce��o.
	 */
	public DAOException (String reason) {
		super(reason);
	}

	
	
	
	public DAOException(Throwable c) {
		cause = c;
	}


	public void setCause(Throwable cause) {
		this.cause = cause;
	}


	public Throwable get_cause() {
		return cause;
	}


}