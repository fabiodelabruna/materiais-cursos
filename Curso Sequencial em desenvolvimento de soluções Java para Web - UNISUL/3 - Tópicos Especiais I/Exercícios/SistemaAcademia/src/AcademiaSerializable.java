import java.io.*;


public class AcademiaSerializable implements PersistenciaAcademia {
	
	private File file; //arquivo
	private ObjectInputStream input; //fluxo de entrada
	private ObjectOutputStream output; //fluxo de sa�da
	
	
	public AcademiaSerializable(String arquivo) {
		this.file = new File(arquivo);
	}

	@Override
	public boolean alterarAtleta(Atleta atleta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Atleta consultarAtleta(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inserirAtleta(Atleta atleta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Atleta[] listarAtletas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removerAtleta(int codigo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/**
	 * M�todo para fechar um arquivo de acesso aleat�rio.
	 * 
	 * @param file - Arquivo a ser fechado.
	 */
	private void closeFile(ObjectInputStream input) {
		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException ioe) {
			System.out.println("Erro no fechamento do arquivo");
			System.out.println(ioe);
		}
	}
	
	
	/**
	 * M�todo para fechar um arquivo de acesso aleat�rio.
	 * 
	 * @param file - Arquivo a ser fechado.
	 */
	private void closeFile(ObjectOutputStream output) {
		try {
			if (output != null) {
				output.close();
			}
		} catch (IOException ioe) {
			System.out.println("Erro no fechamento do arquivo");
			System.out.println(ioe);
		}
	}
	
	/**
	 * M�todo usado para fazer c�pia de arquivos como um todo.
	 * @param fileOrigin - arquivo de origem.
	 * @param fileDestination - arquivo de destino.
	 */
	private void copiarArquivo(File de, File para) {
		try {
			input = new ObjectInputStream(new FileInputStream(de));
			output = new ObjectOutputStream(new FileOutputStream(para));

			Atleta atleta = null;

			while (true) {
				atleta = (Atleta) input.readObject();
				output.writeObject(atleta);
				output.flush();
			}
		} catch (EOFException eofe) {
			; // n�o faz nada. � apenas o mecanismo para sair 
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Classe desconhecida.");
			System.out.println(cnfe);
		} finally {
			closeFile(input);
			closeFile(output);
		}
	}
}
