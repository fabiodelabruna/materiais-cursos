/**
 * Exce��o que ser� disparada quando um determinado valor for
 *   inv�lido para dep�sitos, retiradas e transfer�ncias.
 *   
 * @author Fabio Dela Bruna.
 *
 */
public class ExcecaoValorInvalido extends RuntimeException {

	public ExcecaoValorInvalido(String reason) {
		super(reason);
	}
}
