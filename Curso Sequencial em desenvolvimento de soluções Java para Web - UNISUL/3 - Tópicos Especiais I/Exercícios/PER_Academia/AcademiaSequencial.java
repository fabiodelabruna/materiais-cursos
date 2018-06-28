import java.io.*;
import java.util.*;

public class AcademiaSequencial implements PersistenciaAcademia {

	// atributos
	private File arquivo;
	private BufferedReader entrada;
	private PrintWriter saida;

	// construtor
	public AcademiaSequencial(String arquivo) {
		this.arquivo = new File(arquivo);
	}

	// m�todo para inserir um atleta
	public boolean inserirAtleta(Atleta atleta) {
		boolean inserido = false;
		try {
			saida = new PrintWriter(new FileWriter(this.arquivo, true)); // append - adiciona no final do arquivo
			writeRecord(saida, atleta);
			inserido = true;
		} catch (IOException ioe) {
			System.out.println("Erro de grava��o");
			System.out.println(ioe);
		} finally {
			fecharFluxo(saida);
		}
		return inserido;
	}
	
	// m�todo para consultar um atleta
	public Atleta consultarAtleta(int codigo) {
		Atleta atleta = null;
		try {
			entrada = new BufferedReader(new FileReader(this.arquivo));
			String line;
			while ((line = entrada.readLine()) != null) {
				Atleta atleta1 = readRecord(line);
				if (atleta1.getCodigo() == codigo) {
					atleta = atleta1;
				}
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			System.out.println(ioe);
		} finally {
			fecharFluxo(entrada);
		}
		return atleta;
	}

	// m�todo para remover um atleta
	public boolean removerAtleta(int codigo) {
		boolean excluido = false;
		try {
			entrada = new BufferedReader(new FileReader(this.arquivo));
			File temp = new File("temp.txt");
			saida = new PrintWriter(new FileWriter(temp));
			String line;
			while ((line = entrada.readLine()) != null) {
				Atleta atleta = readRecord(line);
				if (atleta.getCodigo() == codigo) {
					excluido = true;
				} else {
					writeRecord(saida, atleta);
				}
			}
			if (excluido) {
				entrada.close();
				saida.close();
				copiarArquivo(temp, this.arquivo);
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			fecharFluxo(entrada);
			fecharFluxo(saida);
		}
		return excluido;
	}

	// m�todo para alterar um contato
	public boolean alterarAtleta(Atleta atleta) {
		boolean alterado = false;
		try {
			entrada = new BufferedReader(new FileReader(this.arquivo));
			File temp = new File("temp.txt");
			saida = new PrintWriter(new FileWriter(temp));
			String line;
			while ((line = entrada.readLine()) != null) {
				Atleta atleta1 = readRecord(line);
				if (atleta1.getCodigo() == atleta.getCodigo()) {
					writeRecord(saida, atleta);
					alterado = true;
				}
				else {
					writeRecord(saida, atleta1);
				}
			}
			if (alterado) {
				entrada.close();
				saida.close();
				copiarArquivo(temp, this.arquivo);
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			fecharFluxo(entrada);
			fecharFluxo(saida);
		}
		return alterado;
	}

	// m�todo para retornar todos os contatos
	public Atleta[] listarAtletas() {
		Vector<Atleta> lista = new Vector<Atleta>();
		try {
			entrada = new BufferedReader(new FileReader(this.arquivo));
			String line;
			while ((line = entrada.readLine()) != null) {
				Atleta atleta = readRecord(line);
				lista.add(atleta);
			}
		} catch (IOException e) {
			System.out.println("Erro de leitura");
		} finally {
			fecharFluxo(entrada);
		}
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

	// m�todo para copiar um arquivo para o outro
	private void copiarArquivo(File de, File para) {
		try {
			entrada = new BufferedReader(new FileReader(de));
			saida = new PrintWriter(new FileWriter(para));
			String line;
			while ((line = entrada.readLine()) != null) {
				Atleta atleta = readRecord(line);
				writeRecord(saida, atleta);
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			fecharFluxo(entrada);
			fecharFluxo(saida);
		}
	}
	
	// m�todo para fechar um fluxo de entrada
	private void fecharFluxo(PrintWriter saida) {
		if (saida != null) {
			saida.close();
		}
	}

	// m�todo para fechar um fluxo de saida
	private void fecharFluxo(BufferedReader entrada) {
		try {
			if (entrada != null) {
				entrada.close();
			}
		} catch (IOException ioe) {
			System.out.println("Erro no fechamento do arquivo");
			System.out.println(ioe);
		}
	}
	
	// m�todo para escrever um registro para o arquivo
	private void writeRecord(PrintWriter saida, Atleta atleta) {
		saida.println(atleta.getCodigo() + "\t" + atleta.getNome() + "\t" + atleta.getAltura());
		saida.flush();
	}
	
	//m�todo para transformar uma linha do arquivo em um objeto do tipo Atleta
	private Atleta readRecord(String linha) {
		StringTokenizer tokens = new StringTokenizer(linha, "\t\n");
		int codigo = Integer.parseInt(tokens.nextToken());
		String nome = tokens.nextToken();
		double altura = Double.parseDouble(tokens.nextToken());
		Atleta atleta = new Atleta(codigo, nome, altura);
		return atleta;
	}
}