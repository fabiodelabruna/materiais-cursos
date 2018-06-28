package AnalisadorLexico.GUI;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Classe de interface com o usu�rio que mostra o resultado da An�lise.
 * @author 	Hideraldo Lu�s Simon J�nior
 * 			Marco Aur�lio Lima Fernandes
 * 			Rodrigo Paschoal Jeremias
 * @since setembro de 2008
 *
 */
public class GuiTabelaResultado extends JFrame{

	public static JPanel painel;
	public static JTable tabela;
	
	public GuiTabelaResultado(String titulo){
		super(titulo);
		
		painel= new JPanel();
		GridBagLayout gbl= new GridBagLayout();
		painel.setLayout(gbl);
		
		String[] dadosColuna= {"C�digo","Token","Descri��o"};
		DefaultTableModel dfm= new DefaultTableModel(null, dadosColuna);
		tabela= new JTable(dfm);
		JScrollPane scroll= new JScrollPane(tabela);
		painel.add(scroll);
		
		setContentPane(painel);
		setSize(800, 600);
		setVisible(true);
	}
}
