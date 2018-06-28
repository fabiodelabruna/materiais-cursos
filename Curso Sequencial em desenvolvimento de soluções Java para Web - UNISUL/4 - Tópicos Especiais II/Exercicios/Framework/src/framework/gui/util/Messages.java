package framework.gui.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Messages {
	
	
	/**
	 * Este m�todo mostra uma mensagem de erro de acordo
	 * com o par�metro que lhe � passado.
	 * @param msg Menssagem a ser mostrada.
	 */
	public static void showError (String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro",
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Este m�todo mostra uma mensagem de erro de
	 * acordo com a exce��o ocorrida.
	 * @param e Exce��o ocorrida.
	 */
	public static void showError (Exception e) {
		showError(e.getMessage());
	}
	
	/**
	 * Este m�todo mostra uma mensagem simples,
	 * onde a mensagem e o titulo s�o passados por par�metro.
	 * @param msg Mensagem a ser exibida.
	 * @param title T�tulo da mensagem.
	 * @param component Componente pai que chamou esse m�todo.
	 */
	public static void showPlainMessage (String msg, String title, Component component) {
		JOptionPane.showMessageDialog(component, msg, title,
				JOptionPane.PLAIN_MESSAGE);
	}

}
