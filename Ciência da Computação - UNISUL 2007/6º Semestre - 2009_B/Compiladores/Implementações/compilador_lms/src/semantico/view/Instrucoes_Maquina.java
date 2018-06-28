package semantico.view;
import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Classe respons�vel por exibir e imprimir as instru��es geradas
 */
@SuppressWarnings("serial")
public class Instrucoes_Maquina extends JDialog {
	
	private Container conteiner;
	private JTable table;
	private DefaultTableModel modTab;
	private JScrollPane sp_table;
	private String instrucoes[];
	private int pt;
	private String aux;
		
	public Instrucoes_Maquina(){
		setTitle( "Instru��es passadas para a M�quina Hipot�tica" );
		
		instrucoes = new String[30];
		instrucoes[1]="RETU";
		instrucoes[2]="CRVL";
		instrucoes[3]="CRCT";
		instrucoes[4]="ARMZ";
		instrucoes[5]="SOMA";
		instrucoes[6]="SUBT";
		instrucoes[7]="MULT";
		instrucoes[8]="DIVI";
		instrucoes[9]="INVR";
		instrucoes[10]="NEGA";
		instrucoes[11]="CONJ";
		instrucoes[12]="DISJ";
		instrucoes[13]="CMME";
		instrucoes[14]="CMMA";
		instrucoes[15]="CMIG";
		instrucoes[16]="CMDF";
		instrucoes[17]="CMEI";
		instrucoes[18]="CMAI";
		instrucoes[19]="DSVS";
		instrucoes[20]="DSVF";
		instrucoes[21]="LEIT";
		instrucoes[22]="IMPR";
		instrucoes[23]="IMPRL";
		instrucoes[24]="AMEM";
		instrucoes[25]="CALL";
		instrucoes[26]="PARA";
		instrucoes[27]="NADA";
		instrucoes[28]="COPI";
		instrucoes[29]="DSVT";
		
		pt=0;
		
		aux="Instrucao - OP1 - OP2";
		
		inicio();
	}
	
	public void inicio(){
		conteiner = getContentPane();
		conteiner.setLayout(null);
		setResizable(false);
		setBounds(301, 0, 225, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() );
		
		modTab = new DefaultTableModel();
		modTab.setColumnCount(3);
		modTab.setRowCount(400);
		modTab.setColumnIdentifiers(new String[]{"Instru��o","Op1","Op2"});
		
		table = new JTable();
		table.setEnabled(true);
		table.setModel(modTab);
		table.setBounds(0, 20, 220, 750);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		
		sp_table = new JScrollPane();
		sp_table.setViewportView(table);
		sp_table.setBounds(10, 10, 198, 700);
		sp_table.setAutoscrolls(true);

		conteiner.add(sp_table);
	}
	
	
	public void insereInstrucao(int indice, int op1, int op2){
		table.setValueAt((pt+1)+". "+instrucoes[indice], pt, 0);
		table.setValueAt(op1, pt, 1);
		table.setValueAt(op2, pt, 2);
		pt++;
	}
	
	public void alteraInstrucao(int indice, int op1, int op2){
		table.setValueAt(op1, indice, 1);
		table.setValueAt(op2, indice, 2);
	}
	
	public void carregaInstrucoes(){
		String indice="";
		Integer op1=0, op2=0;
		for (int i=0; i<pt; i++){
			indice = (String)table.getValueAt(i, 0);
			op1 = (Integer)table.getValueAt(i, 1);
			op2 = (Integer)table.getValueAt(i, 2);
			aux = aux + "\n" + indice + " - " + op1 + " - " + op2;
		}
	}
	
	public void limpar(){
		for (int i=0; i<400; i++){
			table.setValueAt("", i, 0);
			table.setValueAt("", i, 1);
			table.setValueAt("", i, 2);
		}
	}
	


}
