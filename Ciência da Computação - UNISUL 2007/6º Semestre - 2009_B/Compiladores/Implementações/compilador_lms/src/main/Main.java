package main;

import javax.swing.JFrame;

import br.unisul.view.FramePrincipal;

public class Main {
	
	/**
	 * M�todo principal que inicializa a aplica��o.
	 */
	public static void main(String[] args) {
		FramePrincipal app = new FramePrincipal();
		app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		app.setVisible( true );
	}
}

