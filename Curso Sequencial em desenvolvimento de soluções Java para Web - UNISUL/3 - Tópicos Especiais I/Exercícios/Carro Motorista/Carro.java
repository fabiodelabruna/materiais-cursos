public class Carro {

	private String placa;
	private int velocidade;
	private boolean ligado;

	public Carro(String placa) {
		this.placa = placa;
		this.velocidade = 0;
		this.ligado = false;
	}

	public void ligar() throws ExcecaoLigado {
		if (ligado)
			throw new ExcecaoLigado("O carro j� est� ligado!");	
		ligado = true;
	}

	public void desligar() throws ExcecaoLigado {
		if (!ligado)
			throw new ExcecaoLigado("O carro j� est� desligado!");
		ligado = false;
	}

	public int acelerar(int valor) throws ExcecaoLigado, ExcecaoVelocidade {
		if (!ligado)
			throw new ExcecaoLigado("O carro n�o est� ligado!");
		if ((velocidade + valor) > 100)
			throw new ExcecaoVelocidade("O valor ultrapassa a velocidade m�xima!");
		velocidade += valor;
		return this.velocidade;
	}

	public int frear(int valor) throws ExcecaoLigado, ExcecaoVelocidade {
		if (!ligado)
			throw new ExcecaoLigado("O carro n�o est� ligado!");
		if ((velocidade - valor) < 0)
			throw new ExcecaoVelocidade("O valor far� com que a velocidade fique negativa");
		velocidade -=valor;
		return this.velocidade;
	}

	public String toString() {
		return placa + ": " + (ligado ? "ligado" : "desligado")  + "(" + velocidade + " km/h)";
	}
}