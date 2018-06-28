package perceptron;
import javax.swing.JFrame;
import javax.swing.UIManager;

import perceptron.gui.MainWindow;

/**
 * Classe principal. Cont�m o m�todo main utilizado para inicializar a aplica��o.
 * @author Almir Hoepers, Fabio Dela Bruna, M�rcio Oz�rio Teixeira
 */
public class StartApplication {

	
	public static void main( String[] args ) {
		
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Exception e) {
			System.err.println( e.getMessage() );
		}
		
		MainWindow app = new MainWindow();
		app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		app.setVisible( true );
		
	}
	
	
}
