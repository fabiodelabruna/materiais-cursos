package criptografia;

import java.io.*;

import javax.swing.JFileChooser;

public class Principal {


	private static final int CHAVE = 10;


	
	public static void main(String[] args) {
		
		
		criptografar( selecionaArquivo() );
		
		//decriptografar( selecionaArquivo() );
		
		
		
	}
	
	

	/**
	 * M�todo para criptografar arquivos.
	 * @param arquivo - Arquivo a ser criptografado.
	 */
	private static void criptografar (File arquivo) {

		try {
			
			FileInputStream reader = new FileInputStream(arquivo);
			int next = reader.read();
			
			while (next != -1) {
				byte b = (byte) next;
				System.out.println(b);
				b += CHAVE;
				next = reader.read();
				
				gravaByte(b);
			
			}
			
		} catch (IOException ioe) {
			System.out.println(ioe);
		}

	}
	
	
	/**
	 * M�todo para decriptografar arquivos.
	 * @param arquivo - Arquivo a ser decriptografado.
	 */
	private static void decriptografar (File arquivo) {

		try {
			
			FileInputStream reader = new FileInputStream(arquivo);
			int next = reader.read();
			
			while (next != -1) {
				byte b = (byte) next;
				System.out.println(b);
				b -= CHAVE;
				next = reader.read();
				
				gravaByte(b);
			
			}
			
		} catch (IOException ioe) {
			System.out.println(ioe);
		}

	}
	
	
	/**
	 * M�todo para gravar byte por byte criptografado em um arquivo tempor�rio.
	 * @param b - Byte a ser gravado no arquivo tempor�rio.
	 */
	private static void gravaByte (byte b) {
		
		File arquivoCriptografado = new File("temp.txt");
		
    	try {
    		
            FileOutputStream writer = new FileOutputStream(arquivoCriptografado, true);
            writer.write(b);
            writer.flush();
            writer.close();
            
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
	}

	
	/**
	 * M�todo que abre uma janela de sele��o de arquivos.
	 * @return Arquivo selecionado.
	 */
	private static File selecionaArquivo() {
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
		File file = null;
		
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			return file;
		}
		return null;
	}
}
