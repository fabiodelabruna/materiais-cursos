	
	/**
	 * Classe interna para simular as opera��es de um caixa eletr�nico.
	 */
	public class Banco {
		
		/**
		 * Vari�vel que guarda o valor do saldo.
		 */
		private double saldo;
		
		
		
		/**
		 * Construtor default.
		 */
		public Banco() {
			this.saldo = 0;
		}
		
		
		
		/**
		 * M�todo que retorna o saldo dispon�vel.
		 */
		public double verificaSaldo() {
			return this.saldo;
		}
		
		/**
		 * M�todo para efetuar um saque.
		 */
		public boolean saque(double valor) {
			
			boolean efetuado = false;
			
			if (valor <= 0) {

			} else {
				
				if (this.saldo >= valor) {
					this.saldo -= valor;
					efetuado = true;
				} else {
				}
			}
				
			return efetuado;
		}
		
		/**
		 * M�todo para efetuar um dep�sito.
		 */
		public boolean deposito(double valor) {
			
			boolean efetuado = false;
			
			if (valor > 0) {
				this.saldo += valor;
				efetuado = true;				
			}
			
			return efetuado;
		}
		
}
	