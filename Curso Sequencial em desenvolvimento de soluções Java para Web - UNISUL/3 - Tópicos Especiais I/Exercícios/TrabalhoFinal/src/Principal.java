import br.unisul.aula.classes.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 * Classe principal do Programa.
 * @author Fabio Dela Bruna
 * @since 18/04/2008
 */
public class Principal {

	static DecimalFormat df = new DecimalFormat("###,###,##0.##");
	
	public static void main(String[] args) {
		
		ArrayList<Acidente> acidentes = new ArrayList<Acidente>();
		byte op = 0;
		
		op = showMenu();
		
		do{
		
			if(op == 0){
				JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema!", "Bye!", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}

			switch(op){

			case 1:
				cadastrarAcidente(acidentes);
				break;


			case 2:
				if(acidentes.isEmpty()){
					JOptionPane.showMessageDialog(null, "Voc� deve cadastrar um acidente primeiramente", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Percentual de acidentes com v�timas fatais: " + df.format(Metodos.percentualAcidentesVitimasFatais(acidentes)) + "%");
				}
				break;

			case 3:
				if(acidentes.isEmpty()){
					JOptionPane.showMessageDialog(null, "Voc� deve cadastrar um acidente primeiramente", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Percentual de acidentes com menores de idade: " + df.format(Metodos.percentualAcidentesMenoresIdade(acidentes)) + "%");
				}
				break;

			case 4:
			
			if(acidentes.isEmpty()){
					JOptionPane.showMessageDialog(null, "Voc� deve cadastrar um acidente primeiramente", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Percentual de pessoas embriagadas: " + df.format(Metodos.percentualEmbriagados(acidentes)) + "%");
				}
				break;

			case 5:
				if(acidentes.isEmpty()){
					JOptionPane.showMessageDialog(null, "Voc� deve cadastrar um acidente primeiramente", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Quantidade de acidentes ocorridos no carnaval: " + Metodos.qtdAcidentesCarnaval(acidentes));
				}
				break;

			case 6:
				if(acidentes.isEmpty()){
					JOptionPane.showMessageDialog(null, "Voc� deve cadastrar um acidente primeiramente", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Quantidade de acidentes envolvendo condutores de outro estado: " + Metodos.qtdAcidentesOutroEstado(acidentes));
				}
				break;

			case 7:
				if(acidentes.isEmpty()){
					JOptionPane.showMessageDialog(null, "Voc� deve cadastrar um acidente primeiramente", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Percentual de mulheres envolvidas em acidentes: " + df.format(Metodos.pencetualMulheresAcidentes(acidentes)) + "%");
				}
				break;

			case 8:
				if(acidentes.isEmpty()){
					JOptionPane.showMessageDialog(null, "Voc� deve cadastrar um acidente primeiramente", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Percentual de homens envolvidos em acidentes: " + df.format(Metodos.pencetualHomensAcidentes(acidentes)) + "%");
				}
				break;
				
			case 9:
				if(acidentes.isEmpty()){
					JOptionPane.showMessageDialog(null, "Voc� deve cadastrar um acidente primeiramente", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Quantidade de acidentes envolvendo motos com v�timas fatais: " + Metodos.qtdAcidenteMotocicletasVitimasFatais(acidentes));
				}
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "Opera��o inv�lida!", "Error", JOptionPane.ERROR_MESSAGE);
			break;	

			}
			op = showMenu();
		}
		while(op != 0);
		
		JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema!", "Bye", JOptionPane.PLAIN_MESSAGE);
	}
	
	
	// Outros m�todos...
	
	
	/**
	 * M�todo para mostrar o menu do sistema.
	 */
	private static byte showMenu() {
		return Byte.parseByte(
				JOptionPane.showInputDialog(".:: MENU DO SISTEMA ::.\n\n".concat(
						"|1|  -  CADASTRAR ACIDENTE\n\n").concat(
						"|2|  -  PERCENTUAL DE ACIDENTES COM V�TIMAS FATAIS\n").concat(
						"|3|  -  PERCENTUAL DE ACIDENTES COM MENORES DE 18 ANOS ENVOLVIDOS\n").concat(
						"|4|  -  PERCENTUAL DE EMBRIAGADOS\n").concat(
						"|5|  -  QUANTIDADE DE ACIDENTES OCORRIDOS NO CARNAVAL\n").concat(
						"|6|  -  QUANTIDADE DE ACIDENTES COM CONDUTORES DE OUTRO ESTADO ENVOLVIDOS\n").concat(
						"|7|  -  PERCENTUAL EM QUE AS V�TIMAS S�O MULHERES\n").concat(
						"|8|  -  PERCENTUAL EM QUE AS V�TIMAS S�O HOMENS\n").concat(
						"|9|  -  QUANTIDADE DE ACIDENTES COM MOTOCICLETAS COM V�TIMAS FATAIS\n\n".concat(
				"|0|  -  SAIR"))));
	}

	// Outros m�todos
	
	/**
	 * M�todo que cadastra um acidente.
	 * @param acidentes - ArrayList de Acidente
	 */
	private static void cadastrarAcidente(ArrayList<Acidente> acidentes){
		Acidente a = new Acidente();
	
			Pessoa p = cadastraPessoa();
			a.setPessoa(p);
			
			a.setData(verificaData());
			
			a.setTipoVeiculo(verificaVeiculo());
			
			a.setVitimasFatais(verificaAcidentesFatais());
			
			acidentes.add(a);
	}
	
	/**
	 * M�todo que cadastra uma pessoa.
	 * @return p.
	 */
	private static Pessoa cadastraPessoa(){
		Pessoa p = new Pessoa();
		
		String nome = JOptionPane.showInputDialog("Nome:").toUpperCase();
		p.setNome(nome);
		
		byte idade = Byte.parseByte(JOptionPane.showInputDialog("Idade:"));
		p.setIdade(idade);
		
		char sexo = verificaSexo();
		p.setSexo(sexo);
		
		String estado = JOptionPane.showInputDialog("Estado onde reside:").toUpperCase();
		p.setEstado(estado);
		
		p.setEmbriagado(verificaEmbriagues());
		
		return p;
	}
	
	/**
	 * M�todo que l� e retorna o tipo do ve�culo.
	 * @return aux.
	 */
	private static byte verificaVeiculo(){
		byte aux = 0;
		do{
			aux = Byte.parseByte(JOptionPane.showInputDialog("Tipo do ve�culo: // 1-Autom�vel, 2-Motocicleta, 3-Bicicleta"));
			if((aux < 1) || (aux > 3)){
				JOptionPane.showMessageDialog(null, "Op��o inv�lida!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}while((aux < 1) || (aux > 3));
		return aux;
	}
	
	/**
	 * M�todo que l� e retorna o grau de embriagu�s.
	 * @return aux.
	 */
	private static boolean verificaEmbriagues(){
		byte aux;
		do{
			aux = Byte.parseByte(JOptionPane.showInputDialog("Grau de embriagu�s: 1-SIM  /  2-N�O"));
			if((aux < 1) || (aux > 2)){
				JOptionPane.showMessageDialog(null, "Op��o inv�lida!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}while((aux < 1) || (aux > 2));
		if(aux == 2)
			return false;
		else
			return true;
	}
	
	/**
	 * M�todo que l� e retorna se houve v�tima fatal.
	 * @return aux.
	 */
	private static boolean verificaAcidentesFatais(){
		byte aux;
		do{
			aux = Byte.parseByte(JOptionPane.showInputDialog("Houve v�tima fatal? 1-SIM  /  2-N�O"));
			if((aux < 1) || (aux > 2)){
				JOptionPane.showMessageDialog(null, "Op��o inv�lida!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}while((aux < 1) || (aux > 2));
		if(aux == 2)
			return false;
		else
			return true;
	}
	
	/**
	 * M�todo que l� e retorna o sexo caso ele seja v�lido.
	 * @return sexo.
	 */
	private static char verificaSexo(){
		char sexo = ' ';
		do{
			sexo = JOptionPane.showInputDialog("Sexo:").toUpperCase().charAt(0);
			if((sexo != 'M') && (sexo != 'F')){
				JOptionPane.showMessageDialog(null, "Sexo inv�lido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}while(sexo != 'M' && sexo != 'F');
		return sexo;
	}
	
	/**
	 * Metodo que l� a data, verifica se � v�lida e retorna
	 * @return data.
	 */
	private static String verificaData(){
		String data = "";
		boolean aux = false;
		do{
			data = JOptionPane.showInputDialog("Data do acidente: dd/mm/aaaa");
			String a = data.substring(2, 3);
			String b = data.substring(5, 6);
			if((a.equals("/")) && (b.equals("/")) && (data.length() == 10)){
				aux = true;
			}else{
				JOptionPane.showMessageDialog(null, "Por favor, digite a data no seguinte formato: dd/mm/aaaa", "Formato de DATA inv�lido!", JOptionPane.ERROR_MESSAGE);
				aux = false;
			}
		}while(aux != true);
		return data;
	}
	
}
