


/**
 * Classe que representa um atendente.
 * @author Fabio Dela Bruna, M�rcio Oz�rio.
 * @since 31/03/2007.
 */
public class Atendente extends Pessoa {

	
	// Atributos
	
	private int carteiraTrabalho;
	private int numeroMatricula;

	
	// Construtores
	
	/**
	 * Construtor com par�metros (com dados do atendente).
	 * @param data_nascimento - Data de Nascimento;
	 * @param nome - Nome;
	 * @param cpf - CPF;
	 * @param rg - RG;
	 * @param endereco - Endere�o;
	 * @param carteiraTrabalho - N� do Cart�o de Cr�dito;
	 */
	public Atendente(String dataNascimento, String nome, String cpf,
			String rg, Endereco endereco, int carteiraTrabalho, int numeroMatricula) {
		super(dataNascimento, nome, cpf, rg, endereco);
		this.carteiraTrabalho = carteiraTrabalho;
		this.numeroMatricula = numeroMatricula;		
	}

	public Reserva geraReserva(Cliente cliente, Voo voo){
		if(voo.getAviao().temVaga()){
			voo.getAviao().reservaAssento();
			Reserva reserva = new Reserva(cliente, voo);
			return reserva;
		}
		return null;
	}
	
	// getters and setter
	
	/**
	 * @return the carteiraTrabalho
	 */
	public int getCarteiraTrabalho() {
		return carteiraTrabalho;
	}

	/**
	 * @param carteiraTrabalho the carteiraTrabalho to set
	 */
	public void setCarteiraTrabalho(int carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}

	/**
	 * @return the numeroMatricula
	 */
	public int getNumeroMatricula() {
		return numeroMatricula;
	}

	/**
	 * @param numeroMatricula the numeroMatricula to set
	 */
	public void setNumeroMatricula(int numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}
	
	
	// toString
	
	/**
	 * M�todo para mostrar os atendentes cadastrados.
	 */
	public String toString(){
		return "Data Nascimento: " + super.getDataNascimento() + "\nNome: " + super.getNome() + "\nCPF: " + super.getCpf() + "\nRG: " + super.getRg() +  "\nEndereco: " + super.getEndereco() + "\nCarteira Trabalho: " + this.carteiraTrabalho + "\nNumero Matricula: " + this.numeroMatricula ;
	}
}