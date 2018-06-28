package br.unisul.grafos.main;

import javax.swing.JFrame;
import javax.swing.UIManager;

import br.unisul.grafos.gui.JanelaPrincipal;

/**
 * Classe principal, utilizada para inicializar o sistema.
 * @author Fabio Dela Bruna, Marcio Oz�rio Teixeira.
 */
public class Principal {

	/**
	 * M�todo principal.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// aplica a skin do sistema operacional utilizado pelo usu�rio.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			
		}
		
		JanelaPrincipal app = new JanelaPrincipal();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.monstarJanela();
	}
}
