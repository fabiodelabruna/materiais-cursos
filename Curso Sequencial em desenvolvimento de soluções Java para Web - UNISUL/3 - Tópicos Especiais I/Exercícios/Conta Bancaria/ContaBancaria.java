public class ContaBancaria {

	// vari�veis de inst�ncia (atributos)
	private long numero;
	private String nome;
	private double saldo;

	// vari�veis de classe
	private static double taxaJuros = 2.0;

	// construtor padr�o
	public ContaBancaria() {
		this(0, "", 0.0);
	}
	
	// construtor no qual se passam dois par�metros
	public ContaBancaria(long numero, String nome) {
		this(numero, nome, 0.0);
	}	
	
	// construtor no qual se passam tr�s par�metros
	public ContaBancaria(long numero, String nome, double saldo) {
		this.numero = numero;
		this.nome = nome;
		this.saldo = saldo;
	}

	// m�todo para realizar um dep�sito
	public double depositar(double valor) {
		if (valor < 0) // valor do dep�sito � negativo
			throw new ExcecaoValorInvalido("Erro: Valor do dep�sito � inv�lido!");
		saldo = saldo + valor;
		return saldo;
	}

	// m�todo para realizar uma retirada
	public double retirar(double valor) {
		if (valor < 0) // valor do saque � negativo
			throw new ExcecaoValorInvalido("Erro: Valor do saque � inv�lido!");
		if (valor > saldo) // valor do saque � maior do que o saldo
			throw new ExcecaoValorInvalido("Erro: saldo insuficiente!");
		saldo = saldo - valor;
		return saldo;
	}
	
	// m�todo para realizar uma transfer�ncia
	public double transferir(ContaBancaria contaDestino, double valor) throws ExcecaoSaldoInsuficiente{
		if (valor < 0) // valor da tranfer�ncia � negativo
			throw new ExcecaoValorInvalido("Erro: Valor da transfer�ncia � inv�lido!");
		if (valor > saldo) // valor da transfer�ncia � maior do que o saldo
			throw new ExcecaoSaldoInsuficiente("Erro: saldo insuficiente!");
		saldo = saldo - valor;
		return saldo;
	}	
	
	//m�todo sobrecarregado para adicionar os juros
	public double adicionaJuros(double taxa) {
		saldo = saldo + (saldo * (taxa/100) );
		return saldo;
	}
	
	//m�todo sobrecarregado para adicionar os juros
	public double adicionaJuros() {
		saldo = saldo + (saldo * (taxaJuros/100) );
		return saldo;
	}	

	//m�todo para obter o n�mero da conta
	public long getNumero() {
		return this.numero;
	}

	//m�todo para atualizar o n�mero da conta
	public void setNumero(long numero) {
		this.numero = numero;
	}
	
	//m�todo para obter o nome do cliente
	public String getNome() {
		return this.nome;
	}

	//m�todo para atualizar o nome do cliente
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	//m�todo para obter o saldo da conta
	public double getSaldo() {
		return this.saldo;
	}

	// m�todo est�tico para atualizar a taxa de juros
	public static void setTaxaJuros(double taxa) {
		taxaJuros = taxa;
	}
	
	// m�todo est�tico para retornar a taxa de juros
	public static double getTaxaJutos() {
		return taxaJuros;
	}
	
	//m�todo para criar uma representa��o literal da conta
	public String toString() {
		return ("Conta Bancaria: " + numero + "\t" + nome + "\t" + saldo);
	}

	//m�todo para verificar se duas contas banc�rias s�o iguais
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof ContaBancaria) {
			ContaBancaria conta = (ContaBancaria) obj;
			return this.numero == conta.numero
				&& this.nome.equals(conta.nome)
				&& this.saldo == conta.saldo;
		}
		return false;
	}
}