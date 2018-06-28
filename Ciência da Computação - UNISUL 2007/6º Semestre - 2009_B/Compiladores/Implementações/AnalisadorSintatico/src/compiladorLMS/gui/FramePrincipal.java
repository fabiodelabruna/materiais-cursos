package compiladorLMS.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import compiladorLMS.analise_lexica.AnalisadorLexico;
import compiladorLMS.analise_lexica.Token;
import compiladorLMS.analise_sintatica.AnalisadorSintatico;
import compiladorLMS.gui.lexico.AnaliseLexicaGUI;
import compiladorLMS.gui.lexico.DesenhoAutomato;
import compiladorLMS.gui.lexico.TabelaTerminaisGUI;



/**
 * Classe que exibe a tabela de tokens da linguagem LMS.
 * @author Fabio Dela Bruna, M�rcio Oz�rio Teixeira.
 */
public class FramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar; // barra de menus
	private JMenu menuArquivo; // menu arquivo
	private JMenu menuAnalise; // menu an�lise
	private JMenu menuAjuda; // menu ajuda
	private JToolBar toolBar; // barra de ferramentas
	private JTextArea textArea; // �rea para digitar o c�digo fonte
	private File arquivo; // arquivo de texto para armazenar o fonte
	private JFileChooser fileChooser; // di�logo de navega��o
	private final String DIRETORIO_TESTES = "testes";
	
	
	
	
	/**
	 * Construtor da classe. Monta a GUI.
	 */
	public FramePrincipal() {
		super( "LMS Analisador L�xico e Sint�tico - Ambiente para an�lise l�xica e sint�lica da linguagem LMS" );
		
		
		ImageIcon iconImage = new ImageIcon( "icons/icon1.gif" );
		super.setIconImage( iconImage.getImage() );
		
		setResizable( false );
		
		// seta tamanho da janela.
		Dimension dimensionFrame = Toolkit.getDefaultToolkit().getScreenSize();
		setSize( dimensionFrame );
		
		menuBar = new JMenuBar(); // inicializa menuBar
		
		// cria menuArquivo e seus componentes
		menuArquivo = new JMenu( "Arquivo" );
		menuArquivo.setMnemonic( 'A' );
		
		
		
		
		// cria item de menu: novo
		JMenuItem menuItemNovo = new JMenuItem( "Novo" );
		menuItemNovo.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_N, ActionEvent.CTRL_MASK ) );
		menuItemNovo.setMnemonic( 'N' );
		menuItemNovo.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						novo();
						
					}
				}
		);
		
		// cria item de menu: abrir
		JMenuItem menuItemAbrir = new JMenuItem( "Abrir" );
		menuItemAbrir.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_A, ActionEvent.CTRL_MASK ) );
		menuItemAbrir.setMnemonic( 'A' );
		menuItemAbrir.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						abrir();
						
					}
				}
		);
		
		// cria item de menu: salvar
		JMenuItem menuItemSalvar = new JMenuItem( "Salvar" );
		menuItemSalvar.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_B, ActionEvent.CTRL_MASK ) );
		menuItemSalvar.setMnemonic( 'S' );
		menuItemSalvar.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						salvar();
						
					}
				}
		);
		
		// cria item de menu: salvar como
		JMenuItem menuItemSalvarComo = new JMenuItem( "Salvar como" );
		menuItemSalvarComo.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK ) );
		menuItemSalvarComo.setMnemonic( 'v' );
		menuItemSalvarComo.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						salvarComo();
					
					}
				}
		);
		
		// cria item de menu: fechar
		JMenuItem menuItemSair = new JMenuItem( "Sair" );
		menuItemSair.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_F4, ActionEvent.ALT_MASK ) );
		menuItemSalvar.setMnemonic( 'r' );
		menuItemSair.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						closeAplication();
							
					}
				}
		);
		
		
		// cria menuAnalise e seus componentes
		menuAnalise = new JMenu( "An�lise" );
		menuAnalise.setMnemonic( 'n' );
		
		// cria menuLexica e seus componentes
		JMenu menuLexica = new JMenu( "L�xica" );
		menuLexica.setMnemonic( 'L' );

		// cria item de menu: inicia an�lise l�xica
		JMenuItem menuItemIniciaAnLexica = new JMenuItem( "Iniciar an�lise" );
		menuItemIniciaAnLexica.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_L, ActionEvent.CTRL_MASK ) );
		menuItemIniciaAnLexica.setMnemonic( 'I' );
		menuItemIniciaAnLexica.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						efetuaAnaliseLexica();
						
					}
				}
		);
		
		// cria item de menu: tabela de terminais
		JMenuItem menuItemTabelaTerminais = new JMenuItem( "Tabela de terminais" );
		menuItemTabelaTerminais.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_T, ActionEvent.CTRL_MASK ) );
		menuItemTabelaTerminais.setMnemonic( 'T' );
		menuItemTabelaTerminais.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						abreTabelaDeTerminais();
						
					}
				}
		);
		
		// cria item de menu: desenho do automato
		JMenuItem menuItemDesenhoAutomato = new JMenuItem( "Desenho do aut�mato" );
		menuItemDesenhoAutomato.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_D, ActionEvent.CTRL_MASK ) );
		menuItemDesenhoAutomato.setMnemonic( 'D' );
		menuItemDesenhoAutomato.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						abreJanelaDesenhoAutomato();
						
					}
				}
		);
		
		menuLexica.add( menuItemDesenhoAutomato );
		menuLexica.add( menuItemTabelaTerminais );
		menuLexica.add( menuItemIniciaAnLexica );

		
		// cria menuSint�tica
		JMenu menuSintatica = new JMenu( "Sint�tica" );
		menuSintatica.setMnemonic( 'S' );
		
		// cria item de menu: inicia an�lise sint�tica
		JMenuItem menuItemIniciaAnSintatica = new JMenuItem( "Iniciar an�lise" );
		menuItemIniciaAnSintatica.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.CTRL_MASK ) );
		menuItemIniciaAnSintatica.setMnemonic( 'I' );
		menuItemIniciaAnSintatica.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						efetuaAnaliseSintatica();
						
					}
				}
		);
	
		menuSintatica.add( menuItemIniciaAnSintatica );
		
		
		// cria menuAjuda e seus componentes
		menuAjuda = new JMenu( "Ajuda" );
		menuAjuda.setMnemonic( 'j' );

		
		// cria item de menu: sobre
		JMenuItem menuItemSobre = new JMenuItem( "Sobre" );
		menuItemSobre.setIcon( new ImageIcon( "icons/tip.gif" ) );
		menuItemSobre.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_F1, ActionEvent.CTRL_MASK ) );
		menuItemSobre.setMnemonic( 'b' );
		menuItemSobre.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						abreJanelaSobre();
							
					}
				}
		);
		
		
		// adiciona itens de menu ao menuArquivo
		menuArquivo.add( menuItemNovo );
		menuArquivo.add( menuItemAbrir );
		menuArquivo.add( menuItemSalvar );
		menuArquivo.add( menuItemSalvarComo );
		menuArquivo.addSeparator(); // adiciona separador
		menuArquivo.add( menuItemSair );
		
		// adiciona itens de menu ao menuAnalise
		menuAnalise.add( menuLexica );
		menuAnalise.add( menuSintatica );

		// adiciona itens de menu ao menuAjuda
		menuAjuda.add( menuItemSobre );
		
		
		// adiciona menus � barra de menus
		menuBar.add( menuArquivo );
		menuBar.add( menuAnalise );
		menuBar.add( menuAjuda );
		
		
		// cria toolBar e seus componentes
		toolBar = new JToolBar();
		
		// cria bot�o: novo
		JButton buttonNovo = new JButton();
		buttonNovo.setIcon( new ImageIcon( "icons/new.gif" ) );
		buttonNovo.setToolTipText( "Novo documento em branco." );
		buttonNovo.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						novo();
						
					};
				}
		);
		
		// cria bot�o: abrir
		JButton buttonAbrir = new JButton();
		buttonAbrir.setIcon( new ImageIcon( "icons/open.gif" ) );
		buttonAbrir.setToolTipText( "Abrir documento." );
		buttonAbrir.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						abrir();
						
					}
				}
		);
		
		// cria bot�o: salvar
		JButton buttonSalvar = new JButton();
		buttonSalvar.setIcon( new ImageIcon( "icons/save.gif" ) );
		buttonSalvar.setToolTipText( "Salvar documento." );
		buttonSalvar.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						salvar();
						
					}
				}
		);
		
		// cria item de menu: salvar como
		JButton buttonSalvarComo = new JButton();
		buttonSalvarComo.setIcon( new ImageIcon( "icons/saveas.gif" ) );
		buttonSalvarComo.setToolTipText( "Salvar documento como..." );
		buttonSalvarComo.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						salvarComo();
					
					}
				}
		);

		// cria bot�o: executar an�lise l�xica
		JButton buttonLexic = new JButton();
		buttonLexic.setIcon( new ImageIcon( "icons/lexic.gif" ) );
		buttonLexic.setToolTipText( "Iniciar an�lise l�xica." );
		buttonLexic.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						efetuaAnaliseLexica();
						
					}
				}
		);
		
		// cria bot�o: executar an�lise sint�tica
		JButton buttonSintatic = new JButton();
		buttonSintatic.setIcon( new ImageIcon( "icons/sintatic.gif" ) );
		buttonSintatic.setToolTipText( "Iniciar an�lise sint�tica." );
		buttonSintatic.addActionListener( 
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						efetuaAnaliseSintatica();
						
					}
				}
		);
		
		
		// adiciona componentes � toolBar
		toolBar.add( buttonNovo );
		toolBar.add( buttonAbrir );
		toolBar.add( buttonSalvar );
		toolBar.add( buttonSalvarComo );
		toolBar.addSeparator();
		toolBar.add( buttonLexic );
		toolBar.add( buttonSintatic );

		
		// cria textArea e o adiciona em um jScrollPane
		textArea = new JTextArea();
		textArea.setTabSize( 3 );
		textArea.setFont( new Font( "Monospaced", Font.BOLD, 18 ) );
		textArea.setBorder( BorderFactory.createEmptyBorder( 5, 10, 0, 0 ) );
		JScrollPane scroll = new JScrollPane( textArea );
		
		// adiciona a menubar
		setJMenuBar( menuBar );

		// cria painel para armazenar textArea e panel de imagem
		JPanel panelImage = new JPanel( new BorderLayout() );
		panelImage.setBackground( Color.WHITE );
		panelImage.setBorder( BorderFactory.createLineBorder( Color.GRAY ) );
		
		ImageIcon lmsIcon = new ImageIcon( "icons/lms.gif" );
		
		
		GridLayout layout = new GridLayout();
		layout.setRows( 12 );
		
		JPanel panelSobre = new JPanel( layout );
		panelSobre.setBackground( Color.WHITE );
		
		Color cor = new Color( 96, 197, 236 );
		Font font = new Font( "Trebuched MS", Font.BOLD, 14 );
		
		JLabel unisul1 = new JLabel( "UNIVERSIDADE DO SUL DE SANTA CATARINA", SwingConstants.CENTER );
		unisul1.setFont( font );
		unisul1.setForeground( cor );
		
		JLabel unisul2 = new JLabel( "UNISUL", SwingConstants.CENTER );
		unisul2.setFont( font );
		unisul2.setForeground( cor );
		
		JLabel labelVazioParaGambiarra1 = new JLabel( "" );

		JLabel labelDescricao = new JLabel( "Trabalho da disciplina de Compiladores", SwingConstants.CENTER );
		labelDescricao.setFont( font );
		labelDescricao.setForeground( cor );
		
		JLabel labelVazioParaGambiarra2 = new JLabel( "" );
		
		JLabel labelOrientadorDes = new JLabel( "Orientado pelo professor:", SwingConstants.CENTER );
		labelOrientadorDes.setFont( font );
		labelOrientadorDes.setForeground( cor );
		
		JLabel labelOrientador = new JLabel( "Dr. Luiz Alfredo Soares Garcindo", SwingConstants.CENTER );
		labelOrientador.setFont( font );
		labelOrientador.setForeground( cor );
		
		JLabel labelVazioParaGambiarra3 = new JLabel( "" );
		
		JLabel labelDesenvolv = new JLabel( "Desenvolvido por:", SwingConstants.CENTER );
		labelDesenvolv.setFont( font );
		labelDesenvolv.setForeground( cor );
		
		JLabel labelDevelopers = new JLabel( "Fabio Dela Bruna / M�rcio Oz�rio Teixeira", SwingConstants.CENTER );
		labelDevelopers.setFont( font );
		labelDevelopers.setForeground( cor );
		
		panelSobre.add( unisul1 );
		panelSobre.add( unisul2 );
		panelSobre.add( labelVazioParaGambiarra1 );
		panelSobre.add( labelDescricao );
		panelSobre.add( labelVazioParaGambiarra2 );
		panelSobre.add( labelOrientadorDes );
		panelSobre.add( labelOrientador );
		panelSobre.add( labelVazioParaGambiarra3 );
		panelSobre.add( labelDesenvolv );
		panelSobre.add( labelDevelopers );
		
		
		panelImage.add( new JLabel( lmsIcon ), BorderLayout.NORTH );
		panelImage.add( panelSobre, BorderLayout.SOUTH );
		
		getContentPane().add( toolBar, BorderLayout.NORTH );
		getContentPane().add( panelImage, BorderLayout.EAST );
		getContentPane().add( scroll, BorderLayout.CENTER );
		
	}
	
	
	// m�todos para tratar eventos da aplica��o
	
	/**
	 * Abre a janela sobre da aplica��o
	 */
	private void abreJanelaSobre() {
		Sobre sobre = new Sobre( this );
		sobre.setVisible( true );
	}
	
	/**
	 * Fecha a janela principal.
	 */
	private void closeAplication() {
		dispose();
	}
	
	/**
	 * Abre a janela que exibe a tabela de tokens da linguagens
	 */
	private void abreTabelaDeTerminais() {
		TabelaTerminaisGUI tabela = new TabelaTerminaisGUI( this );
		tabela.setVisible( true );
	}
	
	/**
	 * Abre a janela que exibe o desenho do aut�mato.
	 */
	private void abreJanelaDesenhoAutomato() {
		DesenhoAutomato auto = new DesenhoAutomato( this );
		auto.setVisible( true );
	}
	
	/**
	 * Efetua a an�lise l�xica de acordo com o fonte
	 * digitado no campo de texto e mostra a janela
	 * contendo a tabela dos s�mbolos reconhecidos.
	 */
	private void efetuaAnaliseLexica() {
		if ( textArea.getText().trim().isEmpty() ) {
			mensagemErroVazio( "efetuar an�lise l�xica" );
		} else {
			// efetua a an�lise l�xica e armazena os tokens reconhecidos.
			ArrayList<Token> tokens = new AnalisadorLexico().efetuarAnalise( textArea.getText() );
			AnaliseLexicaGUI lex = new AnaliseLexicaGUI( this, tokens );
			lex.setVisible( true );
		}
	}
	
	/**
	 * Efetua a an�lise sint�tica de acordo com o fonte
	 * digitado no campo de texto e mostra uma mensagem 
	 * de acordo com o estado da an�lise (sucesso/falha).
	 */
	private void efetuaAnaliseSintatica() {
		if ( textArea.getText().trim().isEmpty() ) {
			mensagemErroVazio( "efetuar an�lise sint�tica" );
		} else {
			new AnalisadorSintatico( textArea.getText() );
		}
	}
	
	/**
	 * Abre um di�logo para que o usu�rio possa escolher
	 * o local e o nome do arquivo a ser salvo.
	 */
	private void salvarComo() {
		
		fileChooser = new JFileChooser( DIRETORIO_TESTES );
		fileChooser.setFileFilter( new compiladorLMS.io.FileFilter() );
		fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
		
		int result = fileChooser.showSaveDialog( this );
		String nome = null;
		
		// se o usu�rio clicar no bot�o cancel
		if ( result == JFileChooser.CANCEL_OPTION ) {
			return;
		} else {
			arquivo = fileChooser.getSelectedFile();
			nome = arquivo.getPath();
			
			if ( !nome.toLowerCase().endsWith( ".lms" ) )
				nome = nome + ".lms";
		}
		
		gravarArquivo( nome );
		
	}
	
	
	/**
	 * Salva o programa-fonte em um arquivo de 
	 * texto simples e com extens�o <code>.lms</code>
	 */
	private void gravarArquivo( String nome ) {
		if ( ( arquivo = new File( nome ) ).exists() ) {

			Object[] options = { "Sim", "N�o" };

			int decisao = JOptionPane.showOptionDialog( this , "Deseja sobrescrever o arquivo existente?", "Salvar!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("icons/warn.png"), options, options[0] );

			if ( decisao == 0 ) {
				gravaNoArquivo();
			} else {
				salvarComo();
			}

		} else {
			gravaNoArquivo();
		}
	}
	
	
	/**
	 * Se o arquivo j� existir apenas grava no arquivo.
	 */
	private void salvar() {
		if ( arquivo != null ) {
			gravaNoArquivo();
		} else {
			salvarComo();
		}
	}
	
	
	/**
	 * Grava dados no arquivo.
	 */
	private void gravaNoArquivo() {
		try {
			FileWriter writer = new FileWriter( arquivo, false );
			writer.write( textArea.getText() );
			writer.flush();
			writer.close();
			
			JOptionPane.showMessageDialog( null, new JLabel( "Arquivo salvo com sucesso!", SwingConstants.CENTER ),
					"Sucesso!", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icons/ok.png") );
			
			textArea.requestFocus();
			
		} catch ( IOException ioe ) {
			JOptionPane.showMessageDialog( this, "Erro ao gravar arquivo!",
					"Erro", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons/error.png")  );
		}
	}
	
	
	/**
	 * Abre um di�logo para que o usu�rio
	 * possa escolher o arquivo � abrir.
	 */
	private void abrir() {

		fileChooser = new JFileChooser( DIRETORIO_TESTES );
		fileChooser.setFileFilter( new compiladorLMS.io.FileFilter() );
		fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
		
		int result = fileChooser.showOpenDialog( this );
		
		// se o usu�rio clicar no bot�o cancel
		if ( result == JFileChooser.CANCEL_OPTION ) {
			return;
		} else {
			arquivo = fileChooser.getSelectedFile();
		}
		
		cleanTextArea();
		abrirArquivo();
		textArea.requestFocus();
		
	}
	
	/**
	 * Abre um arquivo-fonte de texto simples
	 * e com extens�o <code>.lms</code>
	 */
	private void abrirArquivo() {

		try {
			
			BufferedReader reader = new BufferedReader( new FileReader( arquivo ) );
			String str = null;
			
			// enquanto ainda houver linhas no arquivo.
			while ( ( str = reader.readLine() ) != null ) {
				textArea.append( str + "\n" );
			}
			
		} catch ( IOException ioe ) {
			JOptionPane.showMessageDialog( this , "Erro ao abrir arquivo:\n" + ioe.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons/erro/r.png")  );
		}
		
	}
	
	/**
	 * Limpa o campo de texto para que o usu�rio
	 * possa trabalho com um novo c�digo-fonte.
	 * Antes de limpar o campo de texto, � solicitado
	 * ao usu�rio se ele deseja salvar ou n�o o fonte.
	 */
	private void novo() {
		
		Object[] options = { "Sim", "N�o" };

		int decisao = JOptionPane.showOptionDialog( this ,
				new JLabel( "Deseja salvar o arquivo atual?", SwingConstants.CENTER ),
				"Salvar?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				new ImageIcon("icons/saved.png"), options, options[0]
		);

		if ( decisao == 0 ) {
			salvar(); // chama o m�todo para salvar o arquivo
			cleanTextArea();
		} else {
			cleanTextArea();
			textArea.requestFocus();
		}
		
	}
	
	/**
	 * Limpa o campo de texto.
	 */
	private void cleanTextArea() {
		textArea.setText( "" );
	}
	
	/**
	 * M�todo que mostra mensagens de erro quando
	 * o usu�rio n�o digitar um programa fonte
	 * e tentar efetuar alguma a��o dependente disso.
	 */
	private void mensagemErroVazio( String erro ) {
		JOptionPane.showMessageDialog( null , "N�o � poss�vel " + erro + ".\nDigite um programa-fonte.",
					"Erro", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons/error.png") );
	}
	
	
	/**
	 * Seta o focus para o textArea.
	 */
	public void setFocusTextArea() {
		textArea.requestFocus();
	}
	
	
}
