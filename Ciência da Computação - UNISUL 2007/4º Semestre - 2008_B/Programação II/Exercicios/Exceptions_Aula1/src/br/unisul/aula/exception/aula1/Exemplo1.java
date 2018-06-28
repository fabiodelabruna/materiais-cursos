package br.unisul.aula.exception.aula1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Exemplo1 {

	public static void main(String[] args) {
		
		String aux = "";
		int value = 0;
		
		while (true) {
			//aux = JOptionPane.showInputDialog(null, "Digite um n�mero: ", "N�mero:", JOptionPane.PLAIN_MESSAGE);
			
			JTextArea ta = new JTextArea(10,10);
			aux = JOptionPane.showInputDialog(null, ta, "Teste");
			try {
				
				//Entrada ddMMyyy
				//Sa�da dd/MM/yyy
				
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
				Date data = sdf.parse(aux);
				System.out.println(data.toString());
				break;

			} catch (ParseException pe) {
				JOptionPane.showMessageDialog(null, "Digite um formato de data v�lido: dd/mm/yyyy", "Data Inv�lida", JOptionPane.ERROR_MESSAGE);
			} finally {
				JOptionPane.showMessageDialog(null, "Entrou no finally");
			}
		}
		
	}
	
}
