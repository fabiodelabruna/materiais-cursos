import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;


public class Principal {

	public static void main(String[] args) {
		
		File arquivo = new File("executando.dat");
		
		if (arquivo.exists()) {
			
			menssagemProgramaExecutando();
			System.exit(0);
			
		} else {
			
			try {
				
				arquivo.createNewFile();
				
			} catch (IOException e) {
				menssagemArquivoNaoCriado();
			}
			
			int op = 0;
			
			Banco b = new Banco();
			
			do {
				
				op = menu();
				
				switch (op) {
				
					case 0 : {
						
						arquivo.delete();
						
						JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema!",
								"Saindo...", JOptionPane.PLAIN_MESSAGE);
						
						System.exit(0);
						
						break;
					}
				
					case 1 : {
						
						double saldo = b.verificaSaldo();
						
						JOptionPane.showMessageDialog(null, "Atualmente seu saldo �:  R$ " + saldo,
								"Consulta de saldo...", JOptionPane.PLAIN_MESSAGE);
						
						break;
					}
					
					case 2 : {
						
						double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor..."));
						
						if (b.deposito(valor)) {
							JOptionPane.showMessageDialog(null, "Dep�sito efetuado com sucesso!",
									"Dep�sito...", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "N�o foi poss�vel efetuar o seu dep�sito!",
									"Dep�sito...", JOptionPane.PLAIN_MESSAGE);
						}
						
						break;
					}
					
					case 3 : {

						double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor..."));
						
						if (b.saque(valor)) {
							JOptionPane.showMessageDialog(null, "Saque efetuado com sucesso!",
									"Saque...", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "N�o foi poss�vel efetuar o seu saque!",
									"Saque...", JOptionPane.PLAIN_MESSAGE);
						}
						
						break;
					}
						
					default : {
						JOptionPane.showMessageDialog(null, "Op��o inv�lida!!!",
								"Erro...", JOptionPane.ERROR_MESSAGE);
						
						break;
					}
				
				}
				
			} while (op != 0);
			
		}
		
	}
	
	
	/**
	 * M�todo que mostra uma menssagem de erro quando,
	 * tentarmos executar duas vezes o mesmo programa.
	 */
	private static void menssagemProgramaExecutando() {
		JOptionPane.showMessageDialog(null, "Este programa j� esta sendo executado por outra pessoa..." +
				"Por favor, tente mais tarde!", "Erro!!!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * M�todo que mostra uma menssagem de erro quando,
	 * por qualquer motivo, n�o seja poss�vel criar
	 * o arquivo usado para verificar se o programa
	 * j� est� sendo executado.
	 */
	private static void menssagemArquivoNaoCriado() {
		JOptionPane.showMessageDialog(null, "N�o foi poss�vel criar o arquivo...",
				"Erro!!!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * M�todo que exibe o Menu do programa.
	 */
	private static int menu() {
		return Integer.parseInt(JOptionPane.showInputDialog(". . : MENU : . ." +
				"\n| 1 |  -  VERIFICAR SALDO" +
				"\n| 2 |  -  EFETUAR DEP�SITO" +
				"\n| 3 |  -  EFETUAR SAQUE\n" +
				"\n| 0 |  -  SAIR"));
	}
	
}

