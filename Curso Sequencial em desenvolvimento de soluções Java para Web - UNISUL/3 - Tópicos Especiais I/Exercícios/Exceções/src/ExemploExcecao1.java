
public class ExemploExcecao1 {

	public static void main(String[] args) {
		String nome = new String ("Programa��o para Computa��o II");
		System.out.println(nome.charAt(10));
		nome = null;
		System.out.println(nome.charAt(10));
	}
}
