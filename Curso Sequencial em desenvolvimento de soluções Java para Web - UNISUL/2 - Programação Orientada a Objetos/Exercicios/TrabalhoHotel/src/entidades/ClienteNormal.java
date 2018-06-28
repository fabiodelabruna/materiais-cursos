package entidades;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Classe que represenra um cliente de classifica��o preferencial.
 * @author Fabio Dela Bruna / Carlos Henrique S�
 */
public class ClienteNormal extends Cliente {

	//atributos
	
	private static final double TAXA = 5.00;
	
	
	//construtores
	
	public ClienteNormal() {
		super();
	}

	public ClienteNormal(int codigo, String nome, String sobrenome, String cpf, Endereco endereco, GregorianCalendar dataNascimento, String fone1, String fone2, ArrayList<Estadia> estadias) {
		super(codigo, nome, sobrenome, cpf, endereco, dataNascimento, fone1, fone2, estadias);
	}
	

	//m�todos principais
	
	@Override
	public double calculaValorEstadia() {
		double total = 0;
		for (Estadia e : this.getEstadias()) {
			total += (e.getQuantidadeDias() * e.getQuarto().getValorDiaria()) + (e.getQuantidadeDias() * TAXA);
		}
		return total;
	}
	
	public void cadastraEstadia(Estadia e){
		this.getEstadias().add(e);
	}
	
	
	//toString
	
	public String toString(){
		return String.format("%sValor a ser pago: %s\n", super.toString(), calculaValorEstadia());
	}
	
}
