import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;


public class AcademiaAleatorio implements PersistenciaColecao {

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
					Moeda moeda = new Moeda();
					writeRecord(this.arquivoAleatorio, moeda, i);
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
	public boolean inserirMoeda(Moeda moeda) {
		boolean inserido = false;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			writeRecord(arquivoAleatorio, moeda, moeda.getCodigo());
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
	public Moeda consultarMoeda(int codigo) {
		Moeda moeda = null;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			Moeda tempMoeda = readRecord(arquivoAleatorio, codigo);
			if (tempMoeda.getCodigo() != 0) {
				moeda = tempMoeda;
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			System.out.println(ioe);
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		return moeda;
	}

	// m�todo para excluir um contato
	public boolean removerMoeda(int codigo) {
		boolean excluido = false;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			Moeda moeda = readRecord(arquivoAleatorio, codigo);
			if (moeda.getCodigo() != 0) {
				writeRecord(arquivoAleatorio, new Moeda(), codigo);
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
	public boolean alterarMoeda(Moeda moeda) {
		boolean alterado = false;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			Moeda tempMoeda = readRecord(arquivoAleatorio, moeda.getCodigo());
			if (tempMoeda.getCodigo() != 0) {
				writeRecord(arquivoAleatorio, moeda, moeda.getCodigo());
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
	public Moeda[] listarMoedas() {
		Vector<Moeda> lista = new Vector<Moeda>();
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			for (int i = 1; i <= 10; i++) {
				Moeda moeda = readRecord(arquivoAleatorio, i);
				if (moeda.getCodigo() != 0) {
					lista.add(moeda);
				}
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			ioe.printStackTrace();
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		System.out.println("Tamanho fina: " + arquivo.length());
		return vectorToArray(lista);
	}

	// m�todo que transforma um vector em um array de atletas
	private Moeda[] vectorToArray(Vector<Moeda> lista) {
		Moeda[] moedas = new Moeda[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			moedas[i] = lista.get(i);
		}
		return moedas;
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
	private void writeRecord(RandomAccessFile arquivo, Moeda moeda, int posicao) throws IOException {
		arquivo.seek((posicao - 1) * Moeda.TAM_REGISTRO); // posiciona o ponteiro no local certo
		arquivo.writeInt(moeda.getCodigo()); // escreve o c�digo
		writeString(arquivo, moeda.getNome(), Moeda.TAM_STRING); // escreve o nome
		writeString(arquivo, moeda.getNacionalidade(), Moeda.TAM_STRING);
		arquivo.writeInt(moeda.getAnoCunhagem());
		arquivo.writeDouble(moeda.getValor());
		arquivo.writeInt(moeda.getQuantidade());
	}

	// m�todo para ler um registro do arquivo
	private Moeda readRecord(RandomAccessFile arquivo, int posicao) {
		Moeda moeda = null;
		try{
		arquivo.seek((posicao - 1) * Moeda.TAM_REGISTRO); //posiciona o ponteiro no locar certo
		int codigo = arquivo.readInt(); // l� o c�digo
		String nome = readString(arquivo, Moeda.TAM_STRING); // l� o nome
		String nacionalidade = readString(arquivo, Moeda.TAM_STRING);
		int anoCunhagem = arquivo.readInt();
		double valor = arquivo.readDouble();
		int quantidade = arquivo.readInt();
		moeda = new Moeda(codigo, nome, nacionalidade, anoCunhagem, valor, quantidade);
		}catch(EOFException eofe) {
			eofe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return moeda;
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