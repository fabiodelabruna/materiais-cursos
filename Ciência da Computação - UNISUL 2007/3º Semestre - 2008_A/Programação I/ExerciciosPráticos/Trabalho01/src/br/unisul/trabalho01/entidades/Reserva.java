
/**
 * Classe que representa reserva para v�os.
 * @author Fabio Dela Bruna, M�rcio Oz�rio.
 * @since 01/04/2008.
 */
public class Reserva {

	
	// Atributos
	
	private Cliente cliente;
	private Voo voo;
	
	
	// Construtores

	/**
	 * Construtor com par�metros (com dados da reserva).
	 * @param cliente - Cliente;
	 * @param voo - V�o;
	 */
	public Reserva(Cliente cliente, Voo voo) {
		super();
		this.cliente = cliente;
		this.voo = voo;
	}

	
	// Getters and Setters
	
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the voo
	 */
	public Voo getVoo() {
		return voo;
	}

	/**
	 * @param voo the voo to set
	 */
	public void setVoo(Voo voo) {
		this.voo = voo;
	}

	
	// equals e toString
	
	/**
	 * M�todo para verificar se determinada reserva j� esta cadastrada.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Reserva) {
			Reserva reserva = (Reserva) obj;
			return (this.getCliente().equals(reserva.getCliente()) && this.voo.equals(reserva.voo));
		}
		return false;
	}
	
	/**
	 * M�todo para mostrar as reservas.
	 */
	public String toString(){
		return "-- RESERVA --\nVoo: " + this.getVoo().getNumero() + "\nPassageiro: " + this.getCliente().getNome() + "\nCPF: " + this.getCliente().getCpf() + "\n\nEndere�o do cliente:\n" + this.getCliente().getEndereco();
	}
}