package messenger.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import static messenger.MessengerConstants.*;

/**
 * Classe que representa um servidor.
 * Este servidor permite que um cliente se conecte e possa enviar e receber mesnsagens.
 * @author Fabio Dela Bruna
 */
public class Servidor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField enterField; // insere a menssagem do usu�rio.
	private JTextArea displayArea; // exibe informa��es para o usu�rio.
	private ObjectOutputStream output; // gera fluxo de sa�da para o cliente.
	private ObjectInputStream input; // gera o fluxo de entrada a partir do cliente.
	private ServerSocket server; // socket de servidor.
	private Socket connection; // conex�o com o cliente.
	private int counter = 1; // contador do numero de conex�es.
	
	
	/**
	 * Construtor da classe Servidor, que configura a GUI.
	 */
	public Servidor() {
		super( "CCP-Messenger (server)" );
		
		enterField = new JTextField();
		enterField.setEditable( false );
		enterField.addActionListener(
				new ActionListener() {
					
					public void actionPerformed( ActionEvent e ) {
						sendData( e.getActionCommand() );
						enterField.setText( "" );
					}
					
				}
		);
		
		add( enterField, BorderLayout.SOUTH );
		
		
		displayArea = new JTextArea();
		displayArea.setEditable( false );
		add( new JScrollPane( displayArea ), BorderLayout.CENTER );
		
		
		setSize( 600, 300 );
		setVisible( true );
		
	}
	
	
	
	/**
	 * Este m�todo configura e executa o servidor.
	 */
	public void runServer() {
		
		try {
			
			server = new ServerSocket( SERVER_PORT, NUMBER_OF_ROWS ); // cria SocketServer.
			
			while ( true ) {
				
				try {
					
					waitForConnection(); // espera uma conex�o.
					getStreams(); // obt�m fluxos de entrada e sa�da.
					processConnection(); // precessa a conex�o.
					
				} catch ( EOFException eofe ) {
					displayMessage( "\nA conex�o do servidor terminou!\n" );
				} finally {
					closeConnection(); // fecha a conex�o.
					counter++;
				}
			}
			
		} catch ( IOException ioe ) {
			ioe.printStackTrace();
		}
	}
	
	
	/**
	 * Este m�todo espera que a conex�o chegue e ent�o exibe informa��es sobre a conex�o.
	 * @throws IOException
	 */
	private void waitForConnection() throws IOException {
		
		displayMessage( "\nAguardando por uma conex�o!\n" );
		connection = server.accept(); // permite que o servidor aceite a conex�o.
		displayMessage( "Conex�o " + counter + " recebida de " + 
				connection.getInetAddress().getHostName() );
		
	}
	
	
	/**
	 * Este m�todo obt�m fluxos para enviar e receber dados.
	 * @throws IOException
	 */
	private void getStreams() throws IOException {
		
		// configura o fluxo de sa�da para objetos.
		output = new ObjectOutputStream( connection.getOutputStream() );
		output.flush(); // esvazia buffer de sa�da para enviar as informa��es de cabe�alho.
		
		// configura o fluxo de entrada para objetos.
		input = new ObjectInputStream( connection.getInputStream() );
		
		displayMessage( "\nFluxos de entrada/sa�da inicializados e configurados.\n" );
		
	}
	
	
	/**
	 * Este m�todo processa a conex�o com o cliente.
	 * @throws IOException
	 */
	private void processConnection() throws IOException {
		
		String message = "Conex�o efetuada com sucesso!\n";
		sendData( message ); // envia uma mensagem de conex�o bem sucedida.
		
		// ativa enterField de modo que o usu�rio do servidor possa enviar mensagens.
		setTextFieldEditable( true );
		
		do { // processa as mensagens enviadas pelo cliente.
			
			try { // l� e exibe a mensagem.
				
				message = ( String ) input.readObject(); // l� uma nova mensagem.
				displayMessage( "\n" + message ); // exibe a mensagem.
				
			} catch ( ClassNotFoundException cnfe ) {
				displayMessage( "\nA mensagem n�o pode ser recebida" );
			} catch ( SocketException se ) {
				break;
			}
			
			
		} while ( !message.equals( "CLIENTE" + STRING_SEPARATOR + "TERMINATE" ) );
		
	}
	
	
	/**
	 * Este m�todo fecha os fluxos e o socket.
	 */
	private void closeConnection() {

		displayMessage( "\nConex�o encerrada!\n" );
		setTextFieldEditable( false );
		
		try {
			
			output.close(); // fecha o fluxo de sa�da.
			input.close(); // fecha o fluxo de entrada.
			connection.close(); // fecha o socket.
			
		} catch ( IOException ioe ) {
			ioe.printStackTrace();
		}
	}
	
	
	/**
	 * Este m�todo envia a menss�gem ao cliente.
	 * @param message String com a mensagem a ser enviada.
	 */
	private void sendData ( String message ) {
		
		try { // envia o objeto ao cliente.
			
			output.writeObject( "SERVER" + STRING_SEPARATOR + message );
			output.flush(); // esvazia a sa�da para o cliente.
			displayMessage( "\nSERVER" + STRING_SEPARATOR + message );
			
		} catch ( IOException ioe ) {
			displayArea.append( "\nErro ao processar a menssagem" );
		}
	}

	
	/**
	 * Este m�todo manipula o displayArea na thread de despacho de eventos.
	 * @param message Menssagem a ser exibida.
	 */
	private void displayMessage( final String messageToDisplay ) {

		SwingUtilities.invokeLater(
				new Runnable() {

					public void run() { // atualiza o displayArea
						displayArea.append( messageToDisplay ); // acrescenta a mensagem.
					}
					
				}
		);

	}
	

	/**
	 * Este m�todo o enterField na thread de despacho de eventos.
	 * @param editable Vari�vel booleana.
	 */
	private void setTextFieldEditable( final boolean editable ) {

		SwingUtilities.invokeLater( 
				new Runnable() {

					public void run() {
						enterField.setEditable( editable );	// configura a editabilidade do enterField.
					}

				}
		);

	}
	
	
}
