package aula02.array.exercicios.exe06;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Principal {

	static DecimalFormat df = new DecimalFormat("###,###,##0.00");

	public static void main(String[] args) {

		byte size = 0;
		byte n = 0;
		byte qtdHomen = 0;
		byte qtdMulheres = 0;

		do{
			n = Byte.parseByte(JOptionPane.showInputDialog("Quandidade de pessoas:"));
			if (n <= 0){
				JOptionPane.showMessageDialog(null, "Voc� precisa definir uma quantidade de pessoas acima de 0(zero)", "quantidade inv�lida", JOptionPane.ERROR_MESSAGE);
			}
		}while(n <= 0);

		Pessoa[] pessoas = new Pessoa[n];
		byte op = (byte) showMenu();

		do{

			if(op == 0){
				JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema!", "Bye!", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}

			switch(op){

			case 1:

				for (int i = 0; i < pessoas.length; i++) {
					Pessoa p = new Pessoa();

					String nome = JOptionPane.showInputDialog((i+1) + "� Pessoa:\n\nNome:").toUpperCase();
					p.setNome(nome);

					byte idade = Byte.parseByte(JOptionPane.showInputDialog((i+1) + "� Pessoa:\n\nIdade:"));
					p.setIdade(idade);

					char sexo = ' ';
					do{
						sexo = JOptionPane.showInputDialog((i+1) + "� Pessoa:\n\nSexo:").toUpperCase().charAt(0);
						if (sexo == 'M'){
							qtdHomen++;
							p.setSexo(sexo);
						}else{
							if(sexo == 'F'){
								qtdMulheres++;
								p.setSexo(sexo);
							}else{
								JOptionPane.showMessageDialog(null, "Sexo inv�lido", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}while(sexo != 'M' && sexo != 'F');	

					pessoas[i] = p;
					size++;
				}
				break;


			case 2:
				if(isEmpty(size)){
					JOptionPane.showMessageDialog(null, "Primeiro voc� deve deve cadastrar as pessoas", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "M�dia Geral: " + df.format(Calculos.mediaGeralIdades(pessoas)) + " anos");
				}
				break;

			case 3:
				if(isEmpty(size)){
					JOptionPane.showMessageDialog(null, "Primeiro voc� deve deve cadastrar as pessoas", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					if(qtdHomen <= 0){
						JOptionPane.showMessageDialog(null, "Voc� n�o pode calcular, pois n�o h� homens cadastrados", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					}
					JOptionPane.showMessageDialog(null, "M�dia de Idade dos Homens: " + df.format(Calculos.mediaIdadeHomens(pessoas)) + " anos");
				}
				break;

			case 4:
				if(isEmpty(size)){
					JOptionPane.showMessageDialog(null, "Primeiro voc� deve deve cadastrar as pessoas", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					if(qtdMulheres <= 0){
						JOptionPane.showMessageDialog(null, "Voc� n�o pode calcular, pois n�o h� mulheres cadastradas", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					}
					JOptionPane.showMessageDialog(null, "M�dia de Idade das Mulheres " + df.format(Calculos.mediaIdadeMulheres(pessoas)) + " anos");
				}
				break;

			case 5:
				if(isEmpty(size)){
					JOptionPane.showMessageDialog(null, "Primeiro voc� deve deve cadastrar as pessoas", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Percentual de Homens: " + df.format(Calculos.percentualHomem(pessoas)) + "%");
				}
				break;

			case 6:
				if(isEmpty(size)){
					JOptionPane.showMessageDialog(null, "Primeiro voc� deve deve cadastrar as pessoas", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Percentual de Mulheres: " + df.format(Calculos.percentualMulher(pessoas)) + "%");
				}
				break;

			case 7:
				if(isEmpty(size)){
					JOptionPane.showMessageDialog(null, "Primeiro voc� deve deve cadastrar as pessoas", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Homem mais velho: " + Calculos.homenMaisVelho(pessoas));
				}
				break;

			case 8:
				if(isEmpty(size)){
					JOptionPane.showMessageDialog(null, "Primeiro voc� deve deve cadastrar as pessoas", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Mulher mais velha: " + Calculos.mulherMaisNova(pessoas));
				}
				break;

			default:
				JOptionPane.showMessageDialog(null, "Opera��o inv�lida!", "Error", JOptionPane.ERROR_MESSAGE);
			break;

			}
			op = (byte) showMenu();
		}
		while(op != 0);

		JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema!", "Bye!", JOptionPane.PLAIN_MESSAGE);
	
		System.exit(0);
	}
	


	/**
	 * M�todo para mostrar o menu do sistema.
	 */
	private static int showMenu() {
		return Integer.parseInt(
				JOptionPane.showInputDialog(".:: MENU DO SISTEMA ::.\n\n" +
						"|1|  -  CADASTRAR PESSOAS\n\n" +
						"|2|  -  M�DIA GERAL DE IDADE\n" +
						"|3|  -  M�DIA DE IDADE DOS HOMENS\n" +
						"|4|  -  M�DIA DE IDADE DAS MULHERES\n" +
						"|5|  -  PERCENTUAL DE HOMENS\n" +
						"|6|  -  PERCENTUAL DE MULHERES\n" +
						"|7|  -  HOMEM MAIS VELHO\n" +
						"|8|  -  MULHER MAIS NOVA\n\n" +
				"|0|  -  SAIR"));
	}


	/**
	 * M�todo que retorna se o vetor de Pessoas est� vazio ou n�o.
	 */
	private static boolean isEmpty(byte size){
		return size == 0;
	}

}