package br.unisul.view;
import java.io.File;

/**
 * Classe para Filtrar apenas arquivos com a extens�o .lms
 * @author Fabio Dela Bruna, M�rcio Oz�rio Teixeira
 */
public class FileFilter extends javax.swing.filechooser.FileFilter {

	
	public boolean accept( File f ) {
		//se for diret�rio
		if ( f.isDirectory() ) 
			return true;

		//Retorna a extens�o do arquivo
		String extension = getExtension( f );

		//verifica se a extens�o � igual a lms
		if ( extension.equals( "lms" ) )
			return true; 

		return false;
	}

	/**
	 * Retorna a descri��o do arquivo.
	 */
	public String getDescription() {
		return "*.lms";
	}

	/**
	 * Retorna a extens�o do arquivo.
	 */
	private String getExtension( File f ) {
		String s = f.getName();
		int i = s.lastIndexOf( '.' );
		if ( i > 0 &&  i < s.length() - 1 ) 
			return s.substring( i + 1 ).toLowerCase();
		return "";
	}
}