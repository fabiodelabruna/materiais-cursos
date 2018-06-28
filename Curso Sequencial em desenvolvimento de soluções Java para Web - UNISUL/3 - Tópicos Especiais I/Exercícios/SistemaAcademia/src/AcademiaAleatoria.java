import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

public class AcademiaAleatoria implements PersistenciaAcademia {

	//attributes
	private File file;
	private RandomAccessFile randomFile;

	//construtor
	public AcademiaAleatoria(String arquivo) {
		try {

			this.file = new File(arquivo);
			this.randomFile = new RandomAccessFile(this.file, "rw");

			if (this.randomFile.length() == 0) { // preenche o arquivo com valores default dos atletas,
				for (int i = 1; i <= 10; i++) { // executado apenas quando o arquivo estiver vazio.
					Atleta atleta = new Atleta();
					writeRecord(this.randomFile, atleta, i);
				}
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("Arquivo n�o encontrado");
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Erro na grava��o");
			System.out.println(ioe);
		} finally {
			closeFile(this.randomFile);
		}
	}

	//M�TODOS PRINCIPAIS

	@Override
	public boolean alterarAtleta(Atleta atleta) {
		boolean alterado = false;
		try {

			this.randomFile = new RandomAccessFile(this.file, "rw");
			Atleta atleta1 = readRecord(this.randomFile, atleta.getCode());
			if (atleta1.getCode() != 0) {
				writeRecord(this.randomFile, atleta1, atleta.getCode());
				alterado = true;
			}

		} catch (EOFException eofe) {
			System.out.println("Fim do arquivo");
			System.out.println(eofe);
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			closeFile(this.randomFile);
		}
		return alterado;
	}

	@Override
	public Atleta consultarAtleta(int codigo) {
		Atleta atleta = null;
		try {

			this.randomFile = new RandomAccessFile(this.file, "rw");
			Atleta atletaTemp = readRecord(this.randomFile, codigo);
			if (atleta.getCode() == codigo) {
				atleta = atletaTemp;
			}

		} catch (EOFException eofe) {
			System.out.println("Fim do arquivo");
			System.out.println(eofe);
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			System.out.println(ioe);
		} finally {
			closeFile(this.randomFile);
		}

		return atleta;
	}

	@Override
	public boolean inserirAtleta(Atleta atleta) {
		boolean inserted = false;

		try {

			this.randomFile = new RandomAccessFile(this.file, "rw");
			writeRecord(this.randomFile, atleta, atleta.getCode());
			inserted = true;

		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			closeFile(this.randomFile);
		}

		return inserted;
	}

	@Override
	public Atleta[] listarAtletas() {
		Vector<Atleta> list = new Vector<Atleta>();
		try {

			this.randomFile = new RandomAccessFile(this.file, "rw");
			for (int i = 1; i <= this.randomFile.length() / Atleta.TAM_REGISTRO; i++) {
				Atleta atleta = readRecord(this.randomFile, i);
				if (atleta.getCode() != 0) {
					list.add(atleta);
				}
			}

		} catch (EOFException eofe) {
			System.out.println("Fim do arquivo");
			System.out.println(eofe);
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			ioe.printStackTrace();
		} finally {
			closeFile(this.randomFile);
		}

		return list.toArray(new Atleta[0]);
	}

	@Override
	public boolean removerAtleta(int codigo) {
		boolean removido = false;
		try {

			this.randomFile = new RandomAccessFile(this.file, "rw");
			Atleta atleta = readRecord(this.randomFile, codigo);
			if (atleta != null) {
				writeRecord(this.randomFile, new Atleta(), codigo);
				removido = true;
			}

		} catch (EOFException eofe) {
			System.out.println("Fim do arquivo");
			System.out.println(eofe);
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			System.out.println(ioe);
		} finally {
			closeFile(this.randomFile);
		}
		return removido;
	}

	/**
	 * M�todo para escrever um para o arquivo.
	 * 
	 * @param file - Arquivo onde ser� gravado dados.
	 * @param atleta - Atleta contendo os dados.
	 * @param position - Posi��o.
	 * 
	 * @throws IOException - Exce��o de entrada e sa�da de dados que pode ser disparada.
	 */
	private void writeRecord(RandomAccessFile file, Atleta atleta, int position)
			throws IOException {
		file.seek((position - 1) * Atleta.TAM_REGISTRO); //posiciona o ponteiro no local certo.
		file.writeInt(atleta.getCode()); //escreve o c�digo.
		writeString(file, atleta.getNome(), Atleta.TAM_NOME); //escreve o nome.
		file.writeDouble(atleta.getAltura()); //escreve a altura.
	}

	/**
	 * M�todo para ler um registro do arquivo.
	 * 
	 * @param file - Arquivo de onde ser� lido os registros.
	 * @param position - Posi��o.
	 * 
	 * @return Atleta com todos os dados.
	 * 
	 * @throws IOException - Exce��o de entrada e sa�da de dados que pode ser disparada.
	 */
	private Atleta readRecord(RandomAccessFile rFile, int position)
			throws IOException {
		rFile.seek((position - 1) * Atleta.TAM_REGISTRO); //posiciona o ponteiro no local certo.
		int codigo = rFile.readInt(); //l� o d�digo.
		String nome = readString(rFile, Atleta.TAM_NOME); //l� o nome.
		double altura = rFile.readDouble(); //l� a altura.
		Atleta atleta = new Atleta(codigo, nome, altura);
		return atleta;
	}

	/**
	 * M�todo que l� uma String de tamanho espec�fico de um arquivo.
	 * 
	 * @param file - Arquivo a ser lido.
	 * @param length - Tamanho da String
	 * 
	 * @return String contendo os dados lidos.
	 * 
	 * @throws IOException - Exce��o de entrada e sa�da de dados que pode ser disparada.
	 */
	private String readString(RandomAccessFile file, int length)
			throws IOException {
		char chars[] = new char[length];
		for (int i = 0; i < length; i++) {
			chars[i] = file.readChar();
		}
		String temp = new String(chars);
		return temp.trim(); //elimina os espa�os em branco depois dos demais caracteres
	}

	/**
	 * M�todo que escreve uma String de tamanho espec�fico para um arquivo.
	 * 
	 * @param file - Arquivo que receber� os dados de uma determinada String.
	 * @param string - String a ser lida e posteriormente gravada para o arquivo.
	 * @param length - Tamanho da String.
	 * 
	 * @throws IOException - Exce��o de entrada e sa�da de dados que pode ser disparada.
	 */
	private void writeString(RandomAccessFile file, String string, int length)
			throws IOException {
		StringBuffer buffer = null;
		if (string != null) {
			buffer = new StringBuffer(string);
		} else {
			buffer = new StringBuffer(length);
		}
		buffer.setLength(length);
		file.writeChars(buffer.toString());
	}

	/**
	 * M�todo para fechar um arquivo de acesso aleat�rio.
	 * 
	 * @param file - Arquivo a ser fechado.
	 */
	private void closeFile(RandomAccessFile file) {
		try {
			if (file != null) {
				file.close();
			}
		} catch (IOException ioe) {
			System.out.println("Erro no fechamento do arquivo");
			System.out.println(ioe);
		}
	}
}
