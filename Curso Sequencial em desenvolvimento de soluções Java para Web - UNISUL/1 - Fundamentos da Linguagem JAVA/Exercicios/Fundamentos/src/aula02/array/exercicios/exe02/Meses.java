package aula02.array.exercicios.exe02;

import javax.swing.JOptionPane;

public class Meses {

	public static void main(String[] args) {
		
		byte mes = 0;
	
		int op = Integer.parseInt(JOptionPane.showInputDialog("O que desejas fazer?" + 
				"\n|1|  -  Consultar M�s" + 
				"\n|0|  -  SAIR"));
		
		do{
			
			if(op == 1)
				mes = Byte.parseByte(JOptionPane.showInputDialog("N�mero correspondente:"));

			switch (mes) {
			
			case 1:{
				JOptionPane.showMessageDialog(null, "M�s: Janeiro");
				break;
			}
				
			case 2:{
				JOptionPane.showMessageDialog(null, "M�s: Fevereiro");
				break;
			}
				
			case 3:{
				JOptionPane.showMessageDialog(null, "M�s: Mar�o");
				break;
			}
				
			case 4:{
				JOptionPane.showMessageDialog(null, "M�s: Abril");
				break;
			}
				
			case 5:{
				JOptionPane.showMessageDialog(null, "M�s: Maio");
				break;
			}
				
			case 6:{
				JOptionPane.showMessageDialog(null, "M�s: Junho");
				break;
			}	
				
			case 7:{
				JOptionPane.showMessageDialog(null, "M�s: Julho");
				break;
			}	
				
			case 8:{
				JOptionPane.showMessageDialog(null, "M�s: Agosto");
				break;
			}	
				
			case 9:{
				JOptionPane.showMessageDialog(null, "M�s: Setembro");
				break;
			}	
				
			case 10:{
				JOptionPane.showMessageDialog(null, "M�s: Outubro");
				break;
			}	
				
			case 11:{
				JOptionPane.showMessageDialog(null, "M�s: Novembro");
				break;
			}	
				
			case 12:{
				JOptionPane.showMessageDialog(null, "M�s: Dezembro");
				break;
			}
				
			default:
				JOptionPane.showMessageDialog(null, "M�s Inv�lido!");
				break;
			}
			
			op = Integer.parseInt(JOptionPane.showInputDialog("O que desejas fazer?" + 
					"\n|1|  -  Consultar M�s" + 
					"\n|0|  -  SAIR"));
			}
		
		while(op != 0);
		}
}
