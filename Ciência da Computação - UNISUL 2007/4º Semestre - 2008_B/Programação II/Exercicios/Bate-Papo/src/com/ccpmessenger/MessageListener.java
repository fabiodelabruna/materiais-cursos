package com.ccpmessenger;

/**
 * MessageListener � uma interface para classes que
 * desejam receber novas mensagens de bate-papo.
 * @author Fabio Dela Bruna
 *
 */
public interface MessageListener {

	
	/**
	 * Este m�todo recebe uma nova mensagem de bate-papo.
	 * @param from Remetente da mensagem.
	 * @param message Mensagem recebida.
	 */
	public void messageReceived( String from, String message );
	
	
}
