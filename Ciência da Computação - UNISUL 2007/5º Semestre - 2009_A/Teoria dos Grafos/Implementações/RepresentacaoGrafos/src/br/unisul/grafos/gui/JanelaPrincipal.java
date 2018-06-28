package br.unisul.grafos.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import br.unisul.grafos.classes.ComplementoGrafo;
import br.unisul.grafos.classes.Grafo;
import br.unisul.grafos.classes.ListaAdjacencia;
import br.unisul.grafos.classes.ListaArestas;
import br.unisul.grafos.classes.MatrizAdjacencia;
import br.unisul.grafos.classes.MatrizIncidencia;
import br.unisul.grafos.classes.Vertice;
import br.unisul.grafos.exceptions.ArestaRepetidaException;
import br.unisul.grafos.exceptions.VerticeRepetidoException;

/**
 * Classe Java que exibe a janela principal
 * do programa com todos os seu componentes.
 * @author Fabio Dela Bruna, Marcio Oz�rio Teixeira.
 */
public class JanelaPrincipal extends JFrame implements ActionListener {
	
	
	// Atributos
	
	private static final long serialVersionUID = 1L;
	
	private JLabel labelVerticeDes;
	private JLabel labelArestaDes;
	private JLabel labelArestaPeso;
	private JLabel labelSeta;
	private JLabel labelListaAdjac;
	private JLabel labelListaArestas;
	private JLabel labelMatrizAdjac;
	private JLabel labelMatrizIncid;
	private JLabel labelNormal;
	private JLabel labelDirigido;
	private JLabel labelValorado;
	private JLabel labelDirigidoValorado;
	
	private JPanel panelTpRepres;
	private JPanel panelTpGrafo;
	private JPanel panelRepresGerada;
	private JPanel panelSuperior;
	private JPanel panelInserirVertices;
	private JPanel panelListas;
	private JPanel panelInserirArestas;
	private JPanel panelCentral;
	private JPanel panelInferior;
	
	private JRadioButton radioButtonListaAdjac;
	private JRadioButton radioButtonListaArestas;
	private JRadioButton radioButtonMatrizAdjac;
	private JRadioButton radioButtonMatrizIncid;
	private JRadioButton radioButtonNormal;
	private JRadioButton radioButtonDirigido;
	private JRadioButton radioButtonValorado;
	private JRadioButton radioButtonDirigidoValorado;
	
	private ButtonGroup buttonGroupTpRepres;
	private ButtonGroup buttonGroupTpGrafo;
	
	private JTextField textFieldVerticeDes;
	private JTextField textFieldArestaDes;
	private JTextField textFieldArestaPeso;
	
	private JButton buttonAddVertice;
	private JButton buttonAddAresta;
	private JButton buttonGerar;
	private JButton buttonComplemento;
	
	private JTextArea textAreaRepres;
	private JTextArea textAreaCompl;
	
	private JList listOrigem;
	private JList listDestino;
	
	private Font fonte;
	
	private Grafo grafo;
	private ListaAdjacencia listaAdj;
	private ListaArestas listaAre;
	private MatrizAdjacencia matrizAdj;
	private MatrizIncidencia matrizInc;
	private ComplementoGrafo complemento;

	
	// Construtor

	/**
	 * Contrutor default.
	 */
	public JanelaPrincipal() {
		setTitle("GRAFO Representation 1.0 BETA");
		setSize(500, 600);
		setLayout(new  BorderLayout());
		setResizable(false);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = getSize();
		
		setLocation((screenSize.width - size.width)/2, 
				(screenSize.height - size.height)/2);

		this.grafo = new Grafo();
	}
	
	/**
	 * Este m�todo chama montarJanela()
	 * e depois mostra a janela na tela.
	 */
	public void monstarJanela() {
		montarJanela();
		setVisible(true);
	}
	
	
	
	// M�todos privados.
	
	/**
	 * Este m�todo monta a estrutra visual da janela.
	 */
	private void montarJanela() {
		
		/* * * * * * * * * * * * * * * * * * * * * * * * *
		 *  Cria��o dos componentes do Painel Superior.
		 *  No Painel Superior encontra-se os elementos
		 *  respons�veis pela entrada de dados no grafo.
		 * 
		 */
		
		// componentes relacionados � inserir v�rtices.
		
		labelVerticeDes = new JLabel("R�tulo:");
		
		textFieldVerticeDes = new JTextField(10);
		textFieldVerticeDes.setToolTipText("Digite um nome para o v�rtice.");
		
		buttonAddVertice = new JButton("Inserir");
		buttonAddVertice.setToolTipText("Insere o v�rtice no grafo.");
		buttonAddVertice.addActionListener(this);
		
		// componentes relacionados � listas de vertices inseridos.
		
		listOrigem = new JList();
		listOrigem.setToolTipText("Selecione o v�rtice de origem.");
		listOrigem.setFixedCellWidth(50);
		listOrigem.setVisibleRowCount(5);
		listOrigem.setAutoscrolls(true);
		listOrigem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listDestino = new JList();
		listDestino.setToolTipText("Selecione o v�rtice de destino");
		listDestino.setFixedCellWidth(50);
		listDestino.setVisibleRowCount(5);
		listDestino.setAutoscrolls(true);
		listDestino.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		labelSeta = new JLabel(" > ");
		labelSeta.setFont(new Font("Courier New", 1, 20));
		
		atualizaListas();
		
		panelListas = new JPanel();
		panelListas.setBorder(new TitledBorder("V�rtices: Origem > Destino"));
		panelListas.setLayout(new FlowLayout());
		
		panelListas.add(new JScrollPane(listOrigem));
		panelListas.add(labelSeta);
		panelListas.add(new JScrollPane(listDestino));


		// Componentes relacionados � inserir arestas.
		
		labelArestaDes = new JLabel("R�tulo:");
		labelArestaPeso = new JLabel("Peso:");
		
		textFieldArestaDes = new JTextField(10);
		textFieldArestaDes.setToolTipText("Digite um nome para a aresta");
		
		textFieldArestaPeso = new JTextField(10);
		textFieldArestaPeso.setToolTipText("Digite o peso da aresta.");
		
		buttonAddAresta = new JButton("Inserir");
		buttonAddAresta.setToolTipText("Insere a aresta n grafo.");
		buttonAddAresta.addActionListener(this);

		
		panelInserirVertices = new JPanel();
		panelInserirVertices.setBorder(new TitledBorder("Inserir V�rtices"));
		panelInserirVertices.setLayout(new GridLayout(2,1));
		
		JPanel panelVerticesTemp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelVerticesTemp1.add(labelVerticeDes);
		panelVerticesTemp1.add(textFieldVerticeDes);
		
		JPanel panelVerticesTemp2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelVerticesTemp2.add(buttonAddVertice);
		
		panelInserirVertices.add(panelVerticesTemp1);
		panelInserirVertices.add(panelVerticesTemp2);
		
		
		panelInserirArestas = new JPanel();
		panelInserirArestas.setBorder(new TitledBorder("Inserir Arestas"));
		panelInserirArestas.setLayout(new GridLayout(3,1));
		
		JPanel panelArestasTemp1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelArestasTemp1.add(labelArestaDes);
		panelArestasTemp1.add(textFieldArestaDes);
		
		JPanel panelArestasTemp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelArestasTemp2.add(labelArestaPeso);
		panelArestasTemp2.add(textFieldArestaPeso);
		
		JPanel panelArestasTemp3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelArestasTemp3.add(buttonAddAresta);
		
		panelInserirArestas.add(panelArestasTemp1);
		panelInserirArestas.add(panelArestasTemp2);
		panelInserirArestas.add(panelArestasTemp3);
		
		
		// Adiciona todos os componentes relacionados
		// � entrada de dados no panelSuperior.
		
		panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		panelSuperior.add(panelInserirVertices, BorderLayout.WEST);
		panelSuperior.add(panelListas, BorderLayout.CENTER);
		panelSuperior.add(panelInserirArestas, BorderLayout.EAST);
		
		
		/*
		 *  Fim da cria��o do Painel Superior.
		 * 
		 * * * * * * * * * * * * * * * * * * * * * * * */
		
		
		/* * * * * * * * * * * * * * * * * * * * * * * * *
		 *  Cria��o dos componentes do Painel Central.
		 *  No Painel Central encontra-se os elementos
		 *  respons�veis por definir o tipo de 
		 *  representa��o de grafo a ser gerada.
		 * 
		 */
		
		
		// Componentes relacionados � defini��o do tipo de representa��o.
		
		labelListaAdjac = new JLabel("Lista de Adjac�ncia");
		labelListaArestas = new JLabel("Lista de Arestas");
		labelMatrizAdjac = new JLabel("Matriz de Adjac�ncia");
		labelMatrizIncid = new JLabel("Matriz de Incid�ncia");
		
		radioButtonListaAdjac = new JRadioButton();
		radioButtonListaArestas = new JRadioButton();
		radioButtonMatrizAdjac = new JRadioButton();
		radioButtonMatrizIncid = new JRadioButton();
		
		radioButtonListaAdjac.setSelected(true);
		
		buttonGroupTpRepres = new ButtonGroup();
		buttonGroupTpRepres.add(radioButtonListaAdjac);
		buttonGroupTpRepres.add(radioButtonListaArestas);
		buttonGroupTpRepres.add(radioButtonMatrizAdjac);
		buttonGroupTpRepres.add(radioButtonMatrizIncid);
		
		
		JPanel panelTpRepresTemp1 = new JPanel();
		panelTpRepresTemp1.setLayout(new GridLayout(2,1));
		
		JPanel panelTemp1 = new JPanel();
		panelTemp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTemp1.add(radioButtonListaAdjac);
		panelTemp1.add(labelListaAdjac);
		
		JPanel panelTemp2 = new JPanel();
		panelTemp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTemp2.add(radioButtonListaArestas);
		panelTemp2.add(labelListaArestas);
		
		panelTpRepresTemp1.add(panelTemp1);
		panelTpRepresTemp1.add(panelTemp2);
		
		
		JPanel panelTpRepresTemp2 = new JPanel();
		panelTpRepresTemp2.setLayout(new GridLayout(2,1));
		
		JPanel panelTemp3 = new JPanel();
		panelTemp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTemp3.add(radioButtonMatrizAdjac);
		panelTemp3.add(labelMatrizAdjac);
		
		JPanel panelTemp4 = new JPanel();
		panelTemp4.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTemp4.add(radioButtonMatrizIncid);
		panelTemp4.add(labelMatrizIncid);
		
		panelTpRepresTemp2.add(panelTemp3);
		panelTpRepresTemp2.add(panelTemp4);
		
		
		panelTpRepres = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelTpRepres.setBorder(new TitledBorder("Tipos de Representa��o"));
		panelTpRepres.add(panelTpRepresTemp1);
		panelTpRepres.add(panelTpRepresTemp2);
		
		
		// Componentes relacionados � definir o tipo de grafo.
		
		labelNormal = new JLabel("Normal");
		labelDirigido = new JLabel("Dirigido");
		labelValorado = new JLabel("Valorado");
		labelDirigidoValorado = new JLabel("Dirigido e Valorado");
		
		radioButtonNormal = new JRadioButton();
		radioButtonDirigido = new JRadioButton();
		radioButtonValorado = new JRadioButton();
		radioButtonDirigidoValorado = new JRadioButton();
		
		radioButtonNormal.setSelected(true);
		
		buttonGroupTpGrafo = new ButtonGroup();
		buttonGroupTpGrafo.add(radioButtonNormal);
		buttonGroupTpGrafo.add(radioButtonDirigido);
		buttonGroupTpGrafo.add(radioButtonValorado);
		buttonGroupTpGrafo.add(radioButtonDirigidoValorado);
		
		
		JPanel panelTpGrafoTemp1 = new JPanel();
		panelTpGrafoTemp1.setLayout(new GridLayout(2,1));
		
		JPanel panelTemp5 = new JPanel();
		panelTemp5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTemp5.add(radioButtonNormal);
		panelTemp5.add(labelNormal);
		
		JPanel panelTemp6 = new JPanel();
		panelTemp6.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTemp6.add(radioButtonDirigido);
		panelTemp6.add(labelDirigido);
		
		panelTpGrafoTemp1.add(panelTemp5);
		panelTpGrafoTemp1.add(panelTemp6);
		
		
		JPanel panelTpGrafoTemp2 = new JPanel();
		panelTpGrafoTemp2.setLayout(new GridLayout(2,1));
		
		JPanel panelTemp7 = new JPanel();
		panelTemp7.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTemp7.add(radioButtonValorado);
		panelTemp7.add(labelValorado);
		
		JPanel panelTemp8 = new JPanel();
		panelTemp8.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTemp8.add(radioButtonDirigidoValorado);
		panelTemp8.add(labelDirigidoValorado);
		
		panelTpGrafoTemp2.add(panelTemp7);
		panelTpGrafoTemp2.add(panelTemp8);
		
		panelTpGrafo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelTpGrafo.setBorder(new TitledBorder("Tipos de Grafos"));
		panelTpGrafo.add(panelTpGrafoTemp1);
		panelTpGrafo.add(panelTpGrafoTemp2);

		
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		panelCentral.add(panelTpGrafo, BorderLayout.NORTH);
		panelCentral.add(panelTpRepres, BorderLayout.SOUTH);
		
		
		/*
		 *  Fim da cria��o do Painel Central.
		 * 
		 * * * * * * * * * * * * * * * * * * * * * * * */
		
		
		/* * * * * * * * * * * * * * * * * * * * * * * * *
		 *  Cria��o dos componentes do Painel Inferior.
		 *  No Painel Inferior encontra-se os elementos
		 *  respons�veis mostrar a representa��o gerada.
		 * 
		 */

		
		// componentes relacionados � apresenta��o do grafo.
		
		buttonGerar = new JButton("Gerar Representa��o");
		buttonGerar.setToolTipText("Gera a representa��o do grafo.");
		buttonGerar.addActionListener(this);
		
		JPanel panelButtonGerar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelButtonGerar.add(buttonGerar);
		
		textAreaRepres = new JTextArea(10, 72);
		textAreaRepres.setText("");
		textAreaRepres.setFont(fonte);
		textAreaRepres.setOpaque(false);
		textAreaRepres.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(textAreaRepres);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBorder(new TitledBorder("Representa��o:"));
		
		JPanel panelScroll = new JPanel();
		panelScroll.add(scroll);
		
		panelRepresGerada = new JPanel(new BorderLayout());
		panelRepresGerada.add(panelButtonGerar, BorderLayout.NORTH);
		panelRepresGerada.add(panelScroll, BorderLayout.SOUTH);
		
		
		panelInferior = new JPanel();
		panelInferior.setLayout(new BorderLayout());
		panelInferior.add(panelRepresGerada, BorderLayout.NORTH);
		
		
		// componentes relacionados � representa��o do complemento de um grafo.
		
		buttonComplemento = new JButton("Gerar Complemento");
		buttonComplemento.setToolTipText("Gera o complemento do grafo.");
		buttonComplemento.addActionListener(this);
		
		JPanel panelButtonCompl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelButtonCompl.add(buttonComplemento);
		
		panelInferior.add(panelButtonCompl, BorderLayout.SOUTH);
		
		
		/*
		 *  Fim da cria��o do Painel Inferior.
		 * 
		 * * * * * * * * * * * * * * * * * * * * * * * */
		
		
		// Adiciona os pain�is no Container.
		
		Container c = getContentPane();
		c.add(panelSuperior, BorderLayout.NORTH);
		c.add(panelCentral, BorderLayout.CENTER);
		c.add(panelInferior, BorderLayout.SOUTH);
		
		pack();
	}

	
	
	
	

	/**
	 * Atualiza as listas a cada vez que for
	 * inserido um novo v�rtice no grafo.
	 */
	private void atualizaListas() {
		listOrigem.setListData(grafo.getVertices().toArray());
		listDestino.setListData(grafo.getVertices().toArray());
	}
	
	
	/**
	 * Este m�todo adiciona um v�rtice no grafo.
	 * O nome do v�rtice � pego atrav�z do textFieldVerticesDes.
	 * O nome do v�rtice deve ser preenchido.
	 */
	private void addVerticeAction() {
		String nome = textFieldVerticeDes.getText().trim().toUpperCase();
		
		if (nome.length() > 0) {
		
			Vertice v = new Vertice(nome);
			try {
				grafo.adicionaVertice(v);
				atualizaListas();
				limpaAtributosVertice();
			} catch (VerticeRepetidoException e) {
				JOptionPane.showMessageDialog(this, e, "Erro!", JOptionPane.ERROR_MESSAGE);
				limpaAtributosVertice();
			}
		} else {
			JOptionPane.showMessageDialog(this, "O campo R�tulo deve ser preenchido!", "Aten��o!", JOptionPane.WARNING_MESSAGE);
			limpaAtributosVertice();
		}
	}
	
	/**
	 * Este m�todo adiciona uma aresta no grafo.
	 * Os v�rtices de origem e destino devem ser selecionados.
	 * Tamb�m devem ser preenchidos o nome e o peso a aresta.
	 */
	private void addArestaAction() {
		Vertice vO = null;
		Vertice vD = null;
		vO = (Vertice)listOrigem.getSelectedValue();
		vD = (Vertice)listDestino.getSelectedValue();
		String nome = textFieldArestaDes.getText().trim().toUpperCase();
		String peso = textFieldArestaPeso.getText().trim().toUpperCase();
		
		if (!(vO == null || vD == null)) {
		
			if (!vO.equals(vD)) {
				
				if (peso.length() == 0 || nome.length() == 0) {
					JOptionPane.showMessageDialog(this, "Os campos \"R�tulo\" e \"Peso\" devem ser preenchidos!", 
							"Aten��o!", JOptionPane.WARNING_MESSAGE);
					limpaAtributosAresta();
				} else {
					
					try {
						grafo.adicionaAresta(nome, vO, vD, peso);
						JOptionPane.showMessageDialog(this, "Aresta \"" + nome + "\" inserida com sucesso!", "Sucesso!", JOptionPane.PLAIN_MESSAGE);
						limpaAtributosAresta();
					} catch (ArestaRepetidaException e) {
						JOptionPane.showMessageDialog(this, e, "Erro!", JOptionPane.ERROR_MESSAGE);
						limpaAtributosAresta();
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Este sistema n�o suporta auto-la�o!", "Aten��o!", JOptionPane.WARNING_MESSAGE);
				limpaAtributosAresta();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Selecione os v�rtices", "Aten��o!", JOptionPane.WARNING_MESSAGE);
			limpaAtributosAresta();
		}
		
	}
	
	
	/**
	 * Este m�todo define as a��es do bot�o complemento.
	 */
	private void complementoAction() {
		
		textAreaCompl = new JTextArea(10, 70);
		textAreaCompl.setText("");
		textAreaCompl.setFont(fonte);
		textAreaCompl.setOpaque(false);
		textAreaCompl.setEditable(false);
		
		JScrollPane scroll2 = new JScrollPane(textAreaCompl);
		scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setBorder(new TitledBorder("Representa��o:"));
		
		JPanel panelScroll2 = new JPanel();
		panelScroll2.add(scroll2);
		
		complemento = new ComplementoGrafo(grafo);
		textAreaCompl.setText(complemento.gerarComplemento());
		
		JOptionPane.showMessageDialog(this, scroll2, "Complemento!", JOptionPane.PLAIN_MESSAGE);
		
	}
	
	
	/**
	 * Este m�todo limpa todos os atributos
	 * referentes � inserir arestas no grafo.
	 */
	private void limpaAtributosAresta() {
		listOrigem.clearSelection();
		listDestino.clearSelection();
		textFieldArestaDes.setText("");
		textFieldArestaPeso.setText("");
		textFieldArestaDes.requestFocus();
	}
	
	/**
	 * Este m�todo limpa todos os atributos
	 * referentes � inserir v�rtices no grafo.
	 */
	private void limpaAtributosVertice() {
		textFieldVerticeDes.setText("");
		textFieldVerticeDes.requestFocus();
	}

	
	// Classe respons�vel por definir as a��es dos componenetes na tela;
	 
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAddVertice) {
			addVerticeAction();
		} 
		else if (e.getSource() == buttonAddAresta) {
			addArestaAction();
		}
		else if (e.getSource() == buttonComplemento) {
			complementoAction();
			
			
		// A��es do bot�o gerar representa��o
		} else if (e.getSource() == buttonGerar) {
			
			if (radioButtonListaAdjac.isSelected()) {
				listaAdj = new ListaAdjacencia(grafo);
				
				if (radioButtonNormal.isSelected()) {
					textAreaRepres.setText(listaAdj.grafoNormal());
				} else if (radioButtonDirigido.isSelected()) {
					textAreaRepres.setText(listaAdj.grafoDirigido());
				} else if (radioButtonValorado.isSelected()) {
					textAreaRepres.setText(listaAdj.grafoValorado());
				} else if (radioButtonDirigidoValorado.isSelected()) {
					textAreaRepres.setText(listaAdj.grafoDirigidoValorado());
				}
				
			} else if (radioButtonListaArestas.isSelected()) {
				listaAre = new ListaArestas(grafo);
				
				if (radioButtonNormal.isSelected()) {
					textAreaRepres.setText(listaAre.grafoNormal());
				} else if (radioButtonDirigido.isSelected()) {
					textAreaRepres.setText(listaAre.grafoDirigido());
				} else if (radioButtonValorado.isSelected()) {
					textAreaRepres.setText(listaAre.grafoValorado());
				} else if (radioButtonDirigidoValorado.isSelected()) {
					textAreaRepres.setText(listaAre.grafoDirigidoValorado());
				}
				
			} else if (radioButtonMatrizAdjac.isSelected()) {
				matrizAdj = new MatrizAdjacencia(grafo);
				
				if (radioButtonNormal.isSelected()) {
					textAreaRepres.setText(matrizAdj.grafoNormal());
				} else if (radioButtonDirigido.isSelected()) {
					textAreaRepres.setText(matrizAdj.grafoDirigido());
				} else if (radioButtonValorado.isSelected()) {
					textAreaRepres.setText(matrizAdj.grafoValorado());
				} else if (radioButtonDirigidoValorado.isSelected()) {
					textAreaRepres.setText(matrizAdj.grafoDirigidoValorado());
				}
			
			} else if (radioButtonMatrizIncid.isSelected()) {
				matrizInc = new MatrizIncidencia(grafo);
				
				if (radioButtonNormal.isSelected()) {
					textAreaRepres.setText(matrizInc.grafoNormal());
				} else if (radioButtonDirigido.isSelected()) {
					textAreaRepres.setText(matrizInc.grafoDirigido());
				} else if (radioButtonValorado.isSelected()) {
					textAreaRepres.setText(matrizInc.grafoValorado());
				} else if (radioButtonDirigidoValorado.isSelected()) {
					textAreaRepres.setText(matrizInc.grafoDirigidoValorado());
				}
			
			}
		}
	}	
	
	

}
