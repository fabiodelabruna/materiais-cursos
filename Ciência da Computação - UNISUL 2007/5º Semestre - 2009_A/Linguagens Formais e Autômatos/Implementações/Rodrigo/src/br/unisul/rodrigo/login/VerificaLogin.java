package br.unisul.rodrigo.login;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe respons�vel por retornar informa��es
 * sobre o arquivo de propriedades.
 * @author Fabio Dela Bruna.
 * @since 24/06/2009.
 */
public class VerificaLogin {

	
	/**
	 * Construtor default.
	 */
	public VerificaLogin() {
		
	}
	
	
	/**
	 * Carrega as informa��es de usu�rio do arquivo de configura��es.
	 */
	public String getUserProperties() throws IOException {
		Properties prop = new Properties();
		try {
			prop.load( VerificaLogin.class.getResourceAsStream("/conf/conf.properties") );
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException ("N�o foi poss�vel carregar o arquivo de propriedades");
			
		}
		return prop.getProperty("user");
	}
	
	
	/**
	 * Carrega as informa��es de senha do arquivo de configura��es. 
	 */
	public String getPasswordProperties() throws IOException {
		Properties prop = new Properties();
		try {
			prop.load( VerificaLogin.class.getResourceAsStream("/conf/conf.properties") );
		} catch (IOException e) {
			throw new IOException ("N�o foi poss�vel carregar o arquivo de propriedades");
		}
		return prop.getProperty("password");
	}
	
	public static void main(String[] args) {
		VerificaLogin vl = new VerificaLogin();
		try {
			System.out.println(vl.getUserProperties());
			System.err.println(vl.getPasswordProperties());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
