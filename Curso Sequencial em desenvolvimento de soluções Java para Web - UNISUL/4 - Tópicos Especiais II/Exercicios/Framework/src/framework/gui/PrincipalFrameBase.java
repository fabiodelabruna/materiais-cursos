package framework.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import framework.gui.components.DesktopPaneBase;
import framework.gui.components.FrameBase;
import framework.gui.components.MenuBarBase;
import framework.gui.components.MenuBase;

public abstract class PrincipalFrameBase extends FrameBase implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private MenuBarBase menuBar;
	private MenuBase menuArquivo;
	private MenuBase menuCadastro;
	private MenuBase menuRelatorio;
	private MenuBase menuSeguranca;
	private DesktopPaneBase desktopPane;
	
	
	/**
	 * Construtor default.
	 * @param dsTitulo T�tulo da Janela.
	 */
	public PrincipalFrameBase (String dsTitulo) {
		super(dsTitulo);
		setSize(300, 400);
		setLayout(new BorderLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	/**
	 * M�todo para montar o menu de Cadastros.
	 */
	protected abstract void montaMenuCadastro(MenuBase menuCadastro);
	
	/**
	 * M�todo para montar o menu de Relat�rios.
	 */
	protected abstract void montaMenuRelatorio(MenuBase menuRelatorio);

	/**
	 * M�todo para montar o menu Arquivo.
	 */
	protected abstract void montaMenuArquivo(MenuBase menuArquivo);
	
	/**
	 * M�todo para montar o menu Seguran�a.
	 */
	protected abstract void montaMenuSeguranca(MenuBase menuSeguranca);
	
	/**
	 * M�todo para definir a a��o de cada click nos itens de menus.
	 */
	protected abstract ListFrameBase menuItemClick(Object source);
	
	
	
	/**
	 * Monta a janela da aplica��o;
	 */
	protected void montaJanela() {
		menuBar = new MenuBarBase();
		setJMenuBar(menuBar);

		menuArquivo = new MenuBase("Arquivo");
		menuArquivo.setMnemonic(KeyEvent.VK_A);
		
		menuCadastro = new MenuBase("Cadastro");
		menuCadastro.setMnemonic(KeyEvent.VK_C);
		
		menuRelatorio = new MenuBase("Relat�rio");
		menuCadastro.setMnemonic(KeyEvent.VK_R);
		
		menuSeguranca = new MenuBase("Seguran�a");
		menuSeguranca.setMnemonic(KeyEvent.VK_S);
		
		menuBar.add(menuArquivo);
		menuBar.add(menuCadastro);
		menuBar.add(menuRelatorio);
		menuBar.add(menuSeguranca);
		
		montaMenuArquivo(menuArquivo);
		montaMenuCadastro(menuCadastro);
		montaMenuRelatorio(menuRelatorio);
		
		desktopPane = new DesktopPaneBase();
		
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
	}
	
	

	/**
	 * Chama o m�todo que monta a janela e depois mostra ela na tela.
	 */
	public void showPrincipalFrame() {
		montaJanela();
		setVisible(true);
	}
	
	
	/**
	 * M�todo que define as a��es aos componentes desta janela.
	 */
	public void actionPerformed(ActionEvent e) {
		ListFrameBase frameBase = menuItemClick(e.getSource());
		if (frameBase != null) {
			desktopPane.add(frameBase);
		}
		
	}

}
