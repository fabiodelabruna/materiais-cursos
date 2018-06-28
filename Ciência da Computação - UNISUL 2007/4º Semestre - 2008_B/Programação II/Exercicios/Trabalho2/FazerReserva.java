import java.util.ArrayList;

import javax.swing.JOptionPane;


public class FazerReserva{

	Reserva reserva = new Reserva();

	//m�todo que chama os m�todos principais
	public void fazerReserva(ArrayList<Reserva> listReserva, ArrayList<Cliente> listCliente, ArrayList<Quarto> listQuarto) {
		int opcao = menus();
		while (opcao != 5) {
			switch (opcao) {
			case 1:
				reserva = new Reserva();
				Cliente cliente = confereNome(listCliente);
				Quarto quarto = confereNuQuarto(listQuarto);
				if(cliente != null && quarto != null) {
					incluirReserva(listReserva, cliente, quarto, reserva);
				} else {
					JOptionPane.showMessageDialog(null, "N�o foi possivel efetuar a Reserva" + "\n" + 
					"Confira se o quarto existe ou se o cliente est� cadastrado.");
				}
				break;
			case 2:
				String nomeExclusao = JOptionPane.showInputDialog("Nome do Cliente da reserva a ser excluido");
				int nuQuartoExclusao = Integer.parseInt(JOptionPane.showInputDialog("Numero do quarto da reserva a ser excluida"));
				JOptionPane.showMessageDialog(null, excluirReserva(listReserva, nomeExclusao, nuQuartoExclusao));
				break;
			case 3:
				JOptionPane.showMessageDialog(null, listar(listReserva));
				break;
			case 4:
				JOptionPane.showMessageDialog(null, verificarOcupado(listQuarto, Integer.parseInt(JOptionPane.showInputDialog("Numero do Quarto que se deseja consultar"))));
			default:
				break;
			}
			opcao = menus();
		}

	}

	//-------------------------------------------
	//METODOS PRINCIPAIS
	//------------------------------------------
	public void incluirReserva(ArrayList<Reserva> lisReserva, Cliente cliente, Quarto quarto, Reserva reserva){
		if(quarto.isVerificaReservado() == false) {
			quarto.fazerReserva();
			reserva.setCliente(cliente);
			reserva.setQuarto(quarto);
			lisReserva.add(reserva);
			JOptionPane.showMessageDialog(null, "Quarto Reservado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "O Quarto esta Reservado ou ja est� em Estadia");
		}
	}

	public String excluirReserva(ArrayList<Reserva> listReserva, String nomeExclusao, int nuQuartoExclusao){
		String resultado = "Reserva n�o Existente";
		if(listReserva.size() == 0) {
			resultado = "N�o h� reservas Cadastradas";
		} else {
			for (int i=0; i<listReserva.size(); i++) {
				if(listReserva.get(i).getCliente().getNome().equals(nomeExclusao) &&
						listReserva.get(i).getQuarto().getNumQuarto() == nuQuartoExclusao) {
					listReserva.get(i).getQuarto().setVerificaReservado(false);
					listReserva.remove(listReserva.get(i));
					resultado = "Reserva excluida com sucesso";
					break;
				}
			}
		}
		return resultado;
	}

	public String verificarOcupado(ArrayList<Quarto> listQuarto, int nuQuartoVerificacao){
		String resultado = "Quarto n�o existente";
		for(int i=0; i<listQuarto.size(); i++) {
			if(listQuarto.get(i).getNumQuarto() == nuQuartoVerificacao) {
				if(listQuarto.get(i).isVerificaReservado() == false && listQuarto.get(i).isVerificaEstadia() == false) {
					resultado = "Quarto Desocupado";
				} else {
					resultado = "Quarto reservado ou em Estadia";
				}
				break;
			}
		}
		return resultado;
	}

	public String listar(ArrayList<Reserva> listReserva) {
		String lista = "Lista: " + "\n";
		for (int i=0; i<listReserva.size(); i++) {
			lista +="Cliente: " + listReserva.get(i).getCliente().getNome() + 
			" - Numero Quarto: " + listReserva.get(i).getQuarto().getNumQuarto() + "\n";
		}
		return lista;
	}


	//-----------------------------------------------
	//Func�es
	//-----------------------------------------------

	public int menus() {
		int opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite: " + "\n" +
				"1 - Para Fazer uma reserva: " + "\n" +
				"2 - Para Excluir uma reserva: " + "\n" +
				"3 - Para Listar: " + "\n" +
				"4 - Para Verificar: " + "\n" +
		"5 - Para sair"));
		return opcao;
	}

	public Cliente confereNome(ArrayList<Cliente> listCliente) {
		String nome = JOptionPane.showInputDialog("Nome do Cliente da Reserva");
		Cliente confere = null;
		for(int i=0; i<listCliente.size(); i++) {
			if(listCliente.get(i).getNome().equals(nome)) {
				confere = listCliente.get(i);
			}
		}
		return confere;
	}

	public Quarto confereNuQuarto(ArrayList<Quarto> listQuarto) {
		int nuQuarto = Integer.parseInt(JOptionPane.showInputDialog("Numero do quarto da Reserva"));
		Quarto confere = null;
		for(int i=0; i<listQuarto.size(); i++) {
			if(listQuarto.get(i).getNumQuarto() == nuQuarto) {
				confere = listQuarto.get(i);
			}
		}
		return confere;
	}
}
