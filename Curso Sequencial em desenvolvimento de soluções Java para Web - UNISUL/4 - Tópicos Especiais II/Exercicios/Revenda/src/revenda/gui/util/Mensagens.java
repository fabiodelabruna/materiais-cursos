package revenda.gui.util;

import javax.swing.JOptionPane;

/**
 * Classe que cont�m as mens�gens de erro a
 * serem exibidas, caso seja necess�rio.
 * 
 * @author Fabio Dela Bruna
 *
 */
public class Mensagens {

	
	/**
	 * M�todo que recebe a causa do erro por par�metro.
	 * @param msg Mensagem de erro.
	 */
	public static void showError (String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro",
				JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * M�todo que recebe a exce��o por par�metros
	 * e exibe a causa usando o m�todo getMessage().
	 * @param e Exce��o ocorrida.
	 */
	public static void showError (Exception e) {
		showError(e.getMessage());
	}
	
	
	/**
	 * M�todo que exibe uma menssagem na tela.
	 * � poss�vel passa por par�metro o titulo e a menss�gem.
	 * @param title T�tulo da JOptionPane.
	 * @param msg Menss�gem a ser exibida.
	 */
	public static void showMessage(String title, String msg) {
		JOptionPane.showMessageDialog(null, msg, title,
				JOptionPane.PLAIN_MESSAGE);
	}
}
