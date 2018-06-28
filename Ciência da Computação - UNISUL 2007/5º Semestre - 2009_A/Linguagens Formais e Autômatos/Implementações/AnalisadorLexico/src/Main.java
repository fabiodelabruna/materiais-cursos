import javax.swing.UIManager;
import javax.swing.WindowConstants;


/**
 * Classe principal do programa. Cont�m o m�todo Main que inicializa o sistema.
 * @author Fabio Dela Bruna, M�rcio Oz�rio Teixeira
 */
public class Main {
	
	
	/**
	 * M�todo inicializador do sistema. Apenas cria uma 
	 * inst�ncia da Classe ... onde est� a interface.
	 */
	public static void main(String[] args) {
		
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		JanelaPrincipal gui = new JanelaPrincipal();
		gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gui.setVisible(true);

		
	}
	

}
