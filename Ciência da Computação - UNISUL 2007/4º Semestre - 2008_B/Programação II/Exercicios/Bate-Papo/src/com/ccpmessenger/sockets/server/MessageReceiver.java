package com.ccpmessenger.sockets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.StringTokenizer;

import com.ccpmessenger.MessageListener;
import static com.ccpmessenger.sockets.SocketMessengerConstants.*;


/**
 * MessageReceiver � um execut�vel que recebe mensagens a partir de um
 * cliente particuar e envia mensagens para um MessageListener.
 * @author Fabio Dela Bruna
 *
 */
public class MessageReceiver implements Runnable {

	private BufferedReader input; // fluxo de entrada.
	private MessageListener messageListener; // receptor da mensagem.
	private boolean keepListening = true; // quando false, termina o execut�vel.
	
	
	/**
	 * Construtor de MessageReceiver.
	 */
	public MessageReceiver( MessageListener listener, Socket clientSocket ) {
		// configura o receptor ao qual novas mensagens devem ser enviadas.
		messageListener = listener;
		
		try {
			// configura o tempo-limite para leitura do cliente.
			clientSocket.setSoTimeout( 5000 ); // cinco segundos.
			
			// cria BufferedReader para ler mensagens entrantes.
			input = new BufferedReader( new InputStreamReader(
					clientSocket.getInputStream() ) );
			
		} catch ( IOException ioe ) {
			ioe.printStackTrace();
		}
		
	}
	
	
	/**
	 * Este m�todo recebe novas mensagens e as envia para o MessageListener.
	 */
	public void run() {
		
		String message; // String para mensagens entrantes.
		
		// recebe mensagens at� ser parado.
		while ( keepListening ) {
			
			try {
				
				message = input.readLine(); // l� a mensagem do cliente.
				
			} catch ( SocketTimeoutException stoe ) {
				continue; // continua para a pr�xima itera��o para se manter receptor.
				
			} catch ( IOException ioe ) {
				ioe.printStackTrace();
				break;
			}
			
			// assegura que a mensagem n�o seja nula.
			if ( message != null ) {
				
				// divide a mensagem em tokens para recuperar o nome de usu�rio e o corpo da mensagem.
				StringTokenizer tokenizer = new StringTokenizer(
						message, MESSAGE_SEPARATOR );
				
				// ignora as mensagens que n�o cont�m um nome de usu�rio e um corpo de mensagem.
				if ( tokenizer.countTokens() == 2 ) {
					
					// envia a menssagem para MessageListener.
					messageListener.messageReceived(
							tokenizer.nextToken(), // nome de usu�rio.
							tokenizer.nextToken() ); // corpo da mensagem.
					
				} else {

					// se recebeu mensagem de desconex�o, para de receber mensagens.
					if ( message.equalsIgnoreCase(
							MESSAGE_SEPARATOR + DISCONNECT_STRING ) )
						stopListening();

				}

			}
			
		}
		
		try {
			
			input.close(); // fecha o BefferedReader (tamb�m fecha o socket).
			
		} catch ( IOException ioe ) {
			ioe.printStackTrace();
		}
		
	}
	

	/**
	 * Este m�todo faz com que para de receber as mensagens entrantes.
	 */
	public void stopListening() {
		keepListening = false;
	}
	

}
