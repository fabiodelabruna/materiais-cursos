import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

public class MoedaAleatorio implements PersistenciaMoeda{

	// atributos
	private File arquivo;
	private RandomAccessFile arquivoAleatorio;

	// construtor
	public MoedaAleatorio(String arquivo) {
		try {
			this.arquivo = new File(arquivo);
			this.arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			if (arquivoAleatorio.length() == 0) {
				for (int i = 1; i <= 10; i++) {
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

	//------------------------------------
	//Metodos Principais
	//-----------------------------------
	
	// m�todo para inserir uma moeda
	public boolean inserirMoeda(Moeda moeda) {
		boolean inserido = false;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			writeRecord(arquivoAleatorio, moeda, moeda.getCodigo());
			inserido = true;
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		return inserido;
	}

	// m�todo para consultar uma moeda
	public Moeda consultarMoeda(int codigo) {
		Moeda moeda = null;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			Moeda moeda1 = readRecord(arquivoAleatorio, codigo);
			if (moeda1.getCodigo() != 0) {
				moeda = moeda1;
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			System.out.println(ioe);
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		return moeda;
	}

	// m�todo para excluir uma moeda
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

	// m�todo para alterar uma moeda
	public boolean alterarMoeda(Moeda moeda) {
		boolean alterado = false;
		try {
			arquivoAleatorio = new RandomAccessFile(this.arquivo, "rw");
			Moeda moeda1 = readRecord(arquivoAleatorio, moeda.getAnoCunhagem());
			if (moeda1.getCodigo() != 0) {
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

	// m�todo para listar as moedas
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
		} catch (IOException e) {
			System.out.println("Erro de leitura");
		} finally {
			fecharArquivo(arquivoAleatorio);
		}
		System.out.println("Tamanho fina: " + arquivo.length());
		return vectorToArray(lista);
	}

	//------------------------------------------
	//Metodos de apoio
	//------------------------------------------
	
	// m�todo que transforma um vector em um array de moedas
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
		return temp.trim();
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

	// m�todo para escrever um registro no arquivo
	private void writeRecord(RandomAccessFile arquivo, Moeda moeda, int posicao) throws IOException {
		arquivo.seek((posicao - 1) * Moeda.TAM_REGISTRO); 
		arquivo.writeInt(moeda.getCodigo());
		writeString(arquivo, moeda.getNome(), Moeda.TAM_STRING); 
		arquivo.writeInt(moeda.getAnoCunhagem());
		writeString(arquivo, moeda.getNacionalidade(), Moeda.TAM_STRING); 
		arquivo.writeInt(moeda.getValor());
		arquivo.writeDouble(moeda.getQuantidade());
	}

	// m�todo para ler um registro do arquivo
	private Moeda readRecord(RandomAccessFile arquivo, int posicao) throws IOException {
		arquivo.seek((posicao - 1) * Moeda.TAM_REGISTRO);
		int codigo = arquivo.readInt();
		String nome = readString(arquivo, Moeda.TAM_STRING);
		int anoCunhagem = arquivo.readInt();
		String nacionalidade = readString(arquivo, Moeda.TAM_STRING);
		int valor = arquivo.readInt();
		int quantidade = arquivo.readInt();
		Moeda moeda = new Moeda(codigo, nome, anoCunhagem, nacionalidade, valor, quantidade);
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
