import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

public class AcademiaAleatorio implements PersistenciaAcademia {

	// atributos
	private File arquivo;
	private RandomAccessFile arquivoAleatorio;

	// construtor
	public AcademiaAleatorio(String arquivo) {
		try {
			this.arquivo = new File(arquivo);
			this.arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			if (arquivoAleatorio.length() == 0) { // preenche o arquivo com valores default dos atletas
				for (int i = 1; i <= 10; i++) {   // executado apenas quando o arquivo estiver vazio (10 registros)
					Atleta atleta = new Atleta();
					writeRecord(this.arquivoAleatorio, atleta, i);
				}
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("Arquivo n�o encontrado.");
			System.out.println(fnfe);
		} catch (IOException ioe) {
			System.out.println("Erro na grava��o");
			System.out.println(ioe);
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
	}

	// m�todo para inserir um atleta
	public boolean inserirAtleta(Atleta atleta) {
		boolean inserido = false;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			writeRecord(arquivoAleatorio, atleta, atleta.getCodigo());
				// caso j� tenha um registro com o mesmo c�digo, sobrescreve
			inserido = true;
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		return inserido;
	}

	// m�todo para consultar um atleta
	public Atleta consultarAtleta(int codigo) {
		Atleta atleta = null;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			Atleta atleta1 = readRecord(arquivoAleatorio, codigo);
			if (atleta1.getCodigo() != 0) {
				atleta = atleta1;
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			System.out.println(ioe);
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		return atleta;
	}

	// m�todo para excluir um contato
	public boolean removerAtleta(int codigo) {
		boolean excluido = false;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			Atleta atleta = readRecord(arquivoAleatorio, codigo);
			if (atleta.getCodigo() != 0) {
				writeRecord(arquivoAleatorio, new Atleta(), codigo);
				excluido = true;
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		return excluido;
	}

	// m�todo para alterar um contato
	public boolean alterarAtleta(Atleta atleta) {
		boolean alterado = false;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			Atleta atleta1 = readRecord(arquivoAleatorio, atleta.getCodigo());
			if (atleta1.getCodigo() != 0) {
				writeRecord(arquivoAleatorio, atleta, atleta.getCodigo());
				alterado = true;
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		return alterado;
	}

	// m�todo para retornar todos atletas
	public Atleta[] listarAtletas() {
		Vector<Atleta> lista = new Vector<Atleta>();
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			for (int i = 1; i <= 10; i++) {
				Atleta atleta = readRecord(arquivoAleatorio, i);
				if (atleta.getCodigo() != 0) {
					lista.add(atleta);
				}
			}
		} catch (IOException e) {
			System.out.println("Erro de leitura");
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		System.out.println("Tamanho fina: " + arquivo.length());
		return vectorToArray(lista);
	}

	// m�todo que transforma um vector em um array de atletas
	private Atleta[] vectorToArray(Vector<Atleta> lista) {
		Atleta[] atletas = new Atleta[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			atletas[i] = lista.get(i);
		}
		return atletas;
	}

	// m�todo que l� uma string de tamanho espec�fico de um arquivo
	private String readString(RandomAccessFile arquivo, int tamanho) throws IOException {
		char string[] = new char[tamanho];
		for (int i = 0; i < tamanho; i++) {
			string[i] = arquivo.readChar();
		}
		String temp = new String(string);
		return temp.trim(); // elimina os espa�os em branco depois dos demais caracteres
	}

	// m�todo que escreve uma string de tamanho espec�fico para um arquivo
	private void writeString(RandomAccessFile arquivo, String string, int tamanho) throws IOException {
		StringBuffer buffer = null;
		if (string != null) {
			buffer = new StringBuffer(string);
		}
		else {
			buffer = new StringBuffer(tamanho);
		}
		buffer.setLength(tamanho);
		arquivo.writeChars(buffer.toString());
	}

	// m�todo para escrever um registro para o arquivo
	private void writeRecord(RandomAccessFile arquivo, Atleta atleta, int posicao) throws IOException {
		arquivo.seek((posicao - 1) * Atleta.TAM_REGISTRO); // posiciona o ponteiro no local certo
		arquivo.writeInt(atleta.getCodigo()); // escreve o c�digo
		writeString(arquivo, atleta.getNome(), Atleta.TAM_NOME); // escreve o nome
		arquivo.writeDouble(atleta.getAltura()); // escreve a altura
	}

	// m�todo para ler um registro do arquivo
	private Atleta readRecord(RandomAccessFile arquivo, int posicao) throws IOException {
		arquivo.seek((posicao - 1) * Atleta.TAM_REGISTRO); //posiciona o ponteiro no locar certo
		int codigo = arquivo.readInt(); // l� o c�digo
		String nome = readString(arquivo, Atleta.TAM_NOME); // l� o nome
		double altura = arquivo.readDouble(); // l� a altura
		Atleta atleta = new Atleta(codigo, nome, altura);
		return atleta;
	}

	// m�todo para fechar um arquivo de acesso aleat�rio
	private void fecharArquivo(RandomAccessFile arquivo) {
		try {
			if (arquivo != null) {
				arquivo.close();
			}
		} catch (IOException ioe) {
			System.out.println("Erro no fechamento do arquivo.");
			System.out.println(ioe);
		}
	}	
}