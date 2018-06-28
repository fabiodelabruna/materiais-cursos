package br.unisul.aula.prog;

import java.util.Date;

/**
 * Classe exemplo de como utilizar atributos e m�todos do tipo static.
 * 
 * @author Fabio Dela Bruna
 * @since 11/03/2008
 */
public class Exemplo {
	
	public static String qualquerCoisa;
	public String outraCoisa;

	
	/**
	 * Exemplifica��o de um m�todo static.
	 */
	public static void imprimeNome(){
		System.out.println("Fabio");
	}
	
	
	/**
	 * Imprime a Data do sistema.
	 */
	public void printDate(){
		System.out.println(new Date());
	}
}
