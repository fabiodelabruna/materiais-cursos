package compiladorLMS.gui.lexico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import compiladorLMS.gui.JDialogBase;

/**
 * Classe utilizada para mostrar o desenho do aut�mato.
 * @author Fabio Dela Bruna, M�rcio Oz�rio Teixeira.
 */
public class DesenhoAutomato extends JDialogBase {

	private static final long serialVersionUID = 1L;
	private ImageIcon image;
	private JLabel labelImage;
	private JPanel mainPanel;
	private ScrollPane scroll;
	
	
	
	/**
	 * Construtor da classe.
	 * Chama o m�todo que inicializa os componentes.
	 */
	public DesenhoAutomato( JFrame parent ) {
		initComponents( parent );
	}
	
	
	
	/**
	 * M�todo privado que inicializa os componentes da tela.
	 */
	private void initComponents( JFrame parent ) {
		
		setTitle("Desenho do aut�mato");
		setResizable( false );
		
		image = new ImageIcon("icons/automato.jpg");
		labelImage = new JLabel(image);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(labelImage, BorderLayout.CENTER);
		
		scroll = new ScrollPane();
		scroll.setSize(500, 500);
		scroll.add(mainPanel);
		
		
		getContentPane().add(scroll, BorderLayout.CENTER);
		
		pack();
		
    	setLocation(
            	(int)parent.getX() + (parent.getWidth()/2) - (getWidth()/2), 
            	(int)parent.getY() + (parent.getHeight()/2) - (getHeight()/2)
            );
	}

}
