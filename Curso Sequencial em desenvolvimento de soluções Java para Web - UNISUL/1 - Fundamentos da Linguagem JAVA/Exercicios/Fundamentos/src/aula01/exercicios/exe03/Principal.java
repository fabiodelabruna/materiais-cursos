package aula01.exercicios.exe03;

import javax.swing.JOptionPane;


/**
 * Classe que calcula o produto de tr�s inteiros
 * @author Fabio Dela Bruna
 */
public class Principal {

	public static void main(String[] args) {
		
		int x = 0, y = 0, z = 0;
		String xVal = "", yVal = "", zVal = "";	
		
		xVal = JOptionPane.showInputDialog("1� valor:");
		yVal = JOptionPane.showInputDialog("2� valor:");
		zVal = JOptionPane.showInputDialog("3� valor:");
		
		x = Integer.parseInt(xVal);
		y = Integer.parseInt(yVal);
		z = Integer.parseInt(zVal);
		
		int resultado = x * y * z;
		
		JOptionPane.showMessageDialog(null, "Produto �: " + resultado);
		
		System.exit(0);
	}
}
