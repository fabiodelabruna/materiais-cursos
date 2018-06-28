package br.unisul.aula.exercicio.negocio;

import javax.swing.JOptionPane;

import br.unisul.aula.exercicio.entidade.Pessoa;


/**
 * Gerencia de uma conta Bancaria
 * 
 * @author Fabio Dela Bruna
 * @since 04/03/2008
 */
public class ContaBancaria {
	
	
	/**
	 * Realiza o dep�sito em uma conta
	 * @param valor Valor a ser depositado
	 * @param pessoa
	 */
	public void deposito (double valor, Pessoa pessoa){
		double saldo = pessoa.getConta().getSaldo();
		saldo += valor;
		
		pessoa.getConta().setSaldo(saldo);
		
		String hist = pessoa.getConta().getHistorico();
		hist += "Dep�sito no valor de R$: " + valor + "\n";
		
		pessoa.getConta().setHistorico(hist);
	}
	
	
	/**
	 * Remove dinheiro da conta
	 * @param valor Valor a ser removido
	 * @param pessoa
	 */
	public void saque(double valor, Pessoa pessoa){
		
		if(verificaSaldo(pessoa)){
			double saldo = pessoa.getConta().getSaldo();
			saldo -= valor;
			pessoa.getConta().setSaldo(saldo);
			
			String hist = pessoa.getConta().getHistorico();
			hist += "Saque no valor de R$: " + valor + "\n";
			
			pessoa.getConta().setHistorico(hist);
			
		}else{
			JOptionPane.showMessageDialog(null, "Voc� n�o possui " + valor + " em sua conta banc�ria");
		}
	}

	/**
	 * Verifica o saldo de uma pessoa
	 * @param pessoa
	 * @return
	 */
	private boolean verificaSaldo(Pessoa pessoa){
		
		if (pessoa.getConta().getSaldo() <= -1000){
			return false;
		}
		return true;	
	}
	
	/**
	 * Consulta o saldo da conta de uma pessoa
	 * 
	 * @param pessoa
	 * @return
	 */
	public double consultaSaldo(Pessoa pessoa){
		return pessoa.getConta().getSaldo();
		
	}
}