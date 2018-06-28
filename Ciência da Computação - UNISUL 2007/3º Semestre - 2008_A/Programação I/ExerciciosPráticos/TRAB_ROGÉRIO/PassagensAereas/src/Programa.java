package PassagensAereas.src;

import javax.swing.*;



public class Programa {
	
	private static int formulario;
	private static ArrayList clientes = new ArrayList();
	private static ArrayList atendentes= new ArrayList();
	private static ArrayList avioes= new ArrayList();
	private static ArrayList companhias= new ArrayList();
	private static ArrayList aeroportos= new ArrayList();
	private static Voo[] voos = new  Voo[999];
	private static int indice_voos = 0;

	public static void main(String[] args) {
			
			
			do{
				menuPrincipal();
				
				
				
				//********************************MENU CADASTROS*********************
				if(formulario==0){
					
					menuCadastros();
					
					if(formulario==0){
						menuCadastroDeClientes();
					}
					
					if(formulario==1){
						menuCadastroDeAtendentes();
					}
					
					if(formulario==2){
						menuCadastroDeAeroportos();
					}
					
					if(formulario==3){
						menuCadastroDeAvioes();
					}
					
					if(formulario==4){
						menuCadastroDeCompanhias();
					}
					
					if(formulario==5){
						novoVoo();
					}
					
					
					
					
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
			}while(formulario!=99);
		
			JOptionPane.showMessageDialog(null, "Bye!! ;D");
		
		
		
		
		
		
		
		}





	private static void menuPrincipal(){
		formulario = Integer.parseInt(JOptionPane.showInputDialog("0-Cadastros \n1-Vender Passagem\n"));
	}
	
	private static void menuCadastros(){
		formulario = Integer.parseInt(JOptionPane.showInputDialog("0-Cadastro de Clientes \n1-Cadastro de Atendentes\n2-Cadastro de Aeroportos\n3-Cadastro de Avioes\n4-Cadastro de Companhias A�reas\n5-Cadastro de Voos\n6-voltar"));		
	}
	
	
	
	
	//******************************CADASTROS***************************
	private static void menuCadastroDeClientes(){
		//(String nome, String dataDeNascimento, String endereco,String fone,int CPF,int RG, String numeroMAtricula)
		Clientes novo = new Clientes(JOptionPane.showInputDialog("Digite o nome"),JOptionPane.showInputDialog("Digite a data de nascimento"),JOptionPane.showInputDialog("Digite o endere�o"),JOptionPane.showInputDialog("Digite o fone"),Integer.valueOf(JOptionPane.showInputDialog("Digite o CPF")),Integer.valueOf(JOptionPane.showInputDialog("Digite o RG")),JOptionPane.showInputDialog("Digite o n� de matr�cula"));
		clientes.insertAt(novo,clientes.size()+1);
	}
	
	private static void menuCadastroDeAtendentes(){
		String senha = new String(JOptionPane.showInputDialog("Digite a senha do Administrador"));
		if (senha=="rurgs"){		
		Atendentes novo = new Atendentes(JOptionPane.showInputDialog("Digite o nome"),JOptionPane.showInputDialog("Digite a data de nascimento"),JOptionPane.showInputDialog("Digite o endere�o"),JOptionPane.showInputDialog("Digite o fone"),Integer.valueOf(JOptionPane.showInputDialog("Digite o CPF")),Integer.valueOf(JOptionPane.showInputDialog("Digite o RG")),JOptionPane.showInputDialog("Digite o n� de matr�cula"),JOptionPane.showInputDialog("Digite a Carteira de Trabalho"),JOptionPane.showInputDialog("Digite a data de Emi��o da Carteira"));
		atendentes.insertLast(novo);
		}else{JOptionPane.showMessageDialog(null, "Senha Incorreta!!!");}
				
	}
	
	
	private static void menuCadastroDeAvioes(){
		//String modelo, String numeroDeSerie, int numeroDePassageiros,int anoDeFabricacao, int mesDeFabricacao, int diaDaFabricacao)
		Avioes novo = new Avioes(JOptionPane.showInputDialog("Digite o modelo"),JOptionPane.showInputDialog("Digite o n�mero de s�rie"),Integer.valueOf(JOptionPane.showInputDialog("Digite o n�mero de Passageiros")),Integer.valueOf(JOptionPane.showInputDialog("Digite o ano em que foi fabricado")),Integer.valueOf(JOptionPane.showInputDialog("Digite o m�s de fabrica��o")),Integer.valueOf(JOptionPane.showInputDialog("Digite o dia da fabrica��o")));
		avioes.insertLast(novo);
	}
	
	private static void menuCadastroDeCompanhias(){
		CompanhiasA novo = new CompanhiasA(JOptionPane.showInputDialog("Digite o nome da Companhia"),JOptionPane.showInputDialog("Digite a sigla"),Integer.valueOf(JOptionPane.showInputDialog("Digite o n�mero de Avi�es que possui")));
		companhias.insertLast(novo);		
	}
	
	private static void menuCadastroDeAeroportos(){
		Aeroportos novo = new Aeroportos(JOptionPane.showInputDialog("Digite o nome"),JOptionPane.showInputDialog("Digite o Local"));
		aeroportos.insertLast(novo);
	}
	
	private static void novoVoo(){
		//int numero, Literal companhia, double saida,double chegada, double saida2, double chegada2,	Aeroportos aeroSaida, Aeroportos aeroChegada, Clientes[] clientes)
		
				
		 Voo novo = new Voo(Integer.valueOf(JOptionPane.showInputDialog("Digite o n�mero do voo")), JOptionPane.showInputDialog("Digite a sigla da companhia"),
				 Double.valueOf(JOptionPane.showInputDialog("digite a hora da partida")),
				 Double.valueOf(JOptionPane.showInputDialog("digite a hora da chegada")),
				 Double.valueOf(JOptionPane.showInputDialog("digite a hora da nova partida")),
				 Double.valueOf(JOptionPane.showInputDialog("digite a hora da nova chegada"))
				 ,JOptionPane.showInputDialog("Digite o nome do Aeroporto de sa�da"),
				 JOptionPane.showInputDialog("Digite o nome do Aeroporto de chegada"));
		 
		 voos[indice_voos] = novo;
		 indice_voos++;
		 
		 
	}
	
	
	//***************************************  VENDER PASSAGEM  ***********************************
	
	private static void venderPassagem(){
		String pessoa = new String(JOptionPane.showInputDialog("Digite o nome da pessoa"));
		int pessoaN = -1;
				
		for(int i = 0; i<=clientes.size();i++){
			if(clientes.elementAt(i).toString().equalsIgnoreCase(pessoa)){
				pessoaN = i;
			}
		}
		
		
		
		if(pessoaN==-1){
			JOptionPane.showMessageDialog(null, "PESSOA N�O ENCONTRADA");
		}else{
			
			

			int voo = Integer.parseInt(JOptionPane.showInputDialog("Digite o n�mero do voo"));
			int vooN = -1;
			for (int i = 0;i<=voos.length;i++){
				if(voos[i].getNumero()==voo){
					vooN = i;
				}
			}

			if (vooN==-1){
				JOptionPane.showMessageDialog(null, "VOO N�O ENCONTRADO");
			}else{
				
				voos[vooN].adicionarCliente(clientes.elementAt(pessoaN));
								
			}
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
			
		
		
		
	}
	
	




}
