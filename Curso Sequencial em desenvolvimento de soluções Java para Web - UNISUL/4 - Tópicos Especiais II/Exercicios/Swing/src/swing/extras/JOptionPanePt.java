package swing.extras;

import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class JOptionPanePt {

	public static void main(String[] args) {
		//Exemplo 1
        //Para v�rios pa�ses basta a linha abaixo, mas para pt_BR n�o tem
		Locale.setDefault(Locale.GERMAN);
		JOptionPane.showConfirmDialog(null, "Ol�. Bot�es e t�tulo em Alem�o!!!");

		//Exemplo 2
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "N�o");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		//--
		JOptionPane.showConfirmDialog(null, "Ol�. Bot�es em portugu�s!!!");
		//--
		JOptionPane.showConfirmDialog(null, "Ol�. Bot�es e t�tulo em portugu�s!!!", "Selecione uma op��o", JOptionPane.YES_NO_CANCEL_OPTION);
		
		
		//Exemplo 3
		String [] botoes = {"Sim", "N�o", "Cancelar"};   
		//--  
        JOptionPane.showOptionDialog(null, "Ol�. Bot�es em portugu�s com OptionDialog.", "Bot�es em Portugu�s",    
                JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE,    
                null, botoes, null);   

	}
}
