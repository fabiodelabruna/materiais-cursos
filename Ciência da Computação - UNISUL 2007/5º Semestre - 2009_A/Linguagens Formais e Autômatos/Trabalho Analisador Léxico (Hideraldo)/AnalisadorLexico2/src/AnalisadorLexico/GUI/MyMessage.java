package AnalisadorLexico.GUI;


import javax.swing.JOptionPane;

/**
 * Classe de mensagens personalizadas.
 * @author Hideraldo Lu�s Simon J�nior
 * @since jun/2008
 * @version 1.1
 *
 */

public class MyMessage {

	//Mensagens de Input
	/** Retorna um n�mero INTEIRO digitado pelo usu�rio 
	 * @param String titulo - T�tulo da janela
	 * @param String texto - Texto da janela
	 * @return int
	 * 
	 * */
	public static int getInteiro(String titulo, String texto){
		return Integer.parseInt(JOptionPane.showInputDialog(null, texto, titulo, JOptionPane.QUESTION_MESSAGE));
	}
	
	/** Retorna um n�mero REAL digitado pelo usu�rio 
	 * @param String titulo - T�tulo da janela
	 * @param String texto - Texto da janela
	 * @return double
	 * 
	 * */
	public static double getReal(String titulo, String texto){
		return Double.parseDouble(JOptionPane.showInputDialog(null, texto, titulo, JOptionPane.QUESTION_MESSAGE));
	}
	
	/** Retorna um n�mero LITERAL digitado pelo usu�rio  
	 * @param String titulo - T�tulo da janela
	 * @param String texto - Texto da janela
	 * @return String
	 * 
	 * */
	public static String getLiteral(String titulo, String texto){
		return JOptionPane.showInputDialog(null, texto, titulo, JOptionPane.QUESTION_MESSAGE);
	}

	/** Retorna um n�mero CARACTER digitado pelo usu�rio  
	 * @param String titulo - T�tulo da janela
	 * @param String texto - Texto da janela
	 * @return char
	 * 
	 * */
	public static char getCaracter(String titulo, String texto){
		return JOptionPane.showInputDialog(null, texto, titulo, JOptionPane.QUESTION_MESSAGE).charAt(0);
	}
	
	//Mensagens de Output
	/** Mensagem de ERRO  
	 * @param String titulo - T�tulo da janela
	 * @param String texto - Texto da janela
	 * 
	 * */
	public static void showErro(String titulo, String texto){
		JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	/** Mensagem de INFORMA��O
	 * @param String titulo - T�tulo da janela
	 * @param String texto - Texto da janela
	 * 
	 * */
	public static void showInfo(String titulo, String texto){
		JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/** Mensagem de ATEN��O 
	 * @param String titulo - T�tulo da janela
	 * @param String texto - Texto da janela
	 * 
	 * */
	public static void showAtencao(String titulo, String texto){
		JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.WARNING_MESSAGE);
	}
	
	/** Mensagem de SEM �CONE 
	 * @param String titulo - T�tulo da janela
	 * @param String texto - Texto da janela
	 * 
	 * */
	public static void showSem(String titulo, String texto){
		JOptionPane.showMessageDialog(null, texto, titulo, JOptionPane.PLAIN_MESSAGE);
	}
	
}
