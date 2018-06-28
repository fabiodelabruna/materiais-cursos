import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class AcademiaSequencial implements PersistenciaAcademia {

	//attributes
	private File file;
	private BufferedReader input;
	private PrintWriter output;

	//constructor
	public AcademiaSequencial(String file) {
		this.file = new File(file);
	}


	//methods
	@Override
	public boolean alterarAtleta(Atleta atleta) {
		boolean changed = false;
		try {
			this.input = new BufferedReader(new FileReader(this.file));
			File temp = new File("temp.txt");
			this.output = new PrintWriter(new FileWriter(temp));
			String line = "";
			while ((line = this.input.readLine()) != null) {
				Atleta tempAtleta = readRecord(line);
				if (atleta.getCode() == tempAtleta.getCode()) {
					writeRecord(this.output, atleta);
					changed = true;
				} else {
					writeRecord(this.output, tempAtleta);
				}
			}
			if (changed) {
				closeFlow(this.input);
				closeFlow(this.output);
				copyFile(temp, this.file);
				temp.delete();
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			closeFlow(this.input);
			closeFlow(this.output);
		}
		return changed;
	}

	@Override
	public Atleta consultarAtleta(int code) {
		try {
			this.input = new BufferedReader(new FileReader(this.file));
			String line = "";
			while ((line = this.input.readLine()) != null) {
				Atleta atleta = readRecord(line);
				if (atleta.getCode() == code) {
					closeFlow(this.input);
					return atleta;
				}
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			System.out.println(ioe);
		} finally {
			closeFlow(input);
		}
		return null;
	}

	@Override
	public boolean inserirAtleta(Atleta atleta) {
		boolean inserted = false;
		try {
			if (consultarAtleta(atleta.getCode()) == null) {
				this.output = new PrintWriter(new FileWriter(this.file, true)); //append - add in the final file
				writeRecord(this.output, atleta);
				inserted = true;
			}
		} catch (IOException ioe) {
			System.out.println("Erro de grava��o");
			System.out.println(ioe);
		} finally {
			closeFlow(this.output);
		}
		return inserted;
	}

	@Override
	public Atleta[] listarAtletas() {
		Vector<Atleta> list = new Vector<Atleta>();
		try {
			this.input = new BufferedReader(new FileReader(this.file));
			String line = "";
			while ((line = this.input.readLine()) != null) {
				Atleta atleta = readRecord(line);
				list.add(atleta);
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura");
			System.out.println(ioe);
		} finally {
			closeFlow(this.input);
		}
		return list.toArray(new Atleta[0]);
	}

	@Override
	public boolean removerAtleta(int code) {
		boolean removed = false;
		try {
			this.input = new BufferedReader(new FileReader(this.file));
			File temp = new File("temp.txt");
			this.output = new PrintWriter(new FileWriter(temp));
			String line = "";
			while ((line = this.input.readLine()) != null) {
				Atleta atleta = readRecord(line);
				if (atleta.getCode() == code) {
					
					removed = true;
				} else {
					writeRecord(output, atleta);
				}
			}
			if (removed) {
				closeFlow(this.input);
				closeFlow(this.output);
				copyFile(temp, this.file);
				temp.delete();
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			closeFlow(this.input);
			closeFlow(this.output);
		}
		return removed;
	}


	/**
	 * M�todo usado para fazer c�pia de arquivos como um todo.
	 * @param fileOrigin - arquivo de origem.
	 * @param fileDestination - arquivo de destino.
	 */
	private void copyFile(File fileOrigin, File fileDestination) {
		try {
			this.input = new BufferedReader(new FileReader(fileOrigin));
			this.output = new PrintWriter(new FileWriter(fileDestination));
			String line = "";
			while ((line = this.input.readLine()) != null) {
				Atleta atleta = readRecord(line);
				writeRecord(this.output, atleta);
			}
		} catch (IOException ioe) {
			System.out.println("Erro de leitura ou grava��o");
			System.out.println(ioe);
		} finally {
			closeFlow(this.input);
			closeFlow(this.output);
		}

	}


	/**
	 * M�todo para fechar um fluxo de entrada.
	 * 
	 * @param output - Objeto do tipo PrintWriter,
	 * usado para fazer a grava��o dos dados em um
	 */
	private void closeFlow(PrintWriter output) {
		if (output != null) {
			output.close();
		}
	}

	/**
	 * M�todo para fechar um fluxo de sa�da.
	 * 
	 * @param input - Objeto do tipo BufferedWriter,
	 * usado para fazer a leitura dos dados em uma linha do arquivo.
	 */
	private void closeFlow(BufferedReader input) {
		try {
			if (input != null) {
				input.close();
			}
		}catch (IOException ioe) {
			System.out.println("Erro no fechamento do arquivo");
			System.out.println(ioe);
		}
	}

	/**
	 * M�todo para escrever um registro para o arquivo.
	 * 
	 * @param output - Objeto do tipo PrintWriter,
	 * usado para fazer a grava��o dos dados em uma linha do arquivo.
	 * @param atleta - Atleta contendo os dados a
	 * serem gravados no arquivo de texto.
	 */
	private void writeRecord(PrintWriter output, Atleta atleta) {
		output.println(atleta.getCode() + "\t" + atleta.getNome() + "\t" + atleta.getAltura());
		output.flush();
	}

	/**
	 * M�todo para transformar uma linha do arquivo em um objeto do tipo Atleta
	 * 
	 * @param line - String contendo os registro do atleta.
	 * 
	 * @return atleta - Objeto do tipo Atleta.
	 */
	private Atleta readRecord(String line) {
		StringTokenizer tokens = new StringTokenizer(line, "\t\n");
		int code = Integer.parseInt(tokens.nextToken());
		String nome = tokens.nextToken();
		double altura = Double.parseDouble(tokens.nextToken());
		Atleta atleta = new Atleta(code, nome, altura);
		return atleta;
	}
	
}
