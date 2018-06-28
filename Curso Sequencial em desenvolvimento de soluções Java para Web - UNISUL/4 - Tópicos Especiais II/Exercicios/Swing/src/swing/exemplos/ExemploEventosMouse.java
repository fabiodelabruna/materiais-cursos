package swing.exemplos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * Classe de exemplo para aprendizagem sobre os eventos do mouse.
 * @author Fabio Dela Bruna.
 * @since 18/10/2008.
 */
public class ExemploEventosMouse extends JFrame implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	
	public ExemploEventosMouse() {
		super ("Exemlplo eventos do Mouse");
		setSize(400, 300);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////
	
	//M�todos referentes � interface MouseListener
	
	
	//Este evento ocorre quando for clicado com o mouse em determidado componente
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			JOptionPane.showMessageDialog(null, "Clique realizado com o bot�o esquerdo do mouse");
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			JOptionPane.showMessageDialog(null, "Clique realizado com o bot�o direito do mouse");
		}
	}

	//Este evento ocorre quando o mouse entrar na �rea de determinado painel.
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	//Este evento ocorre quandoo mouse sair da �rea de determinado painel.
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//Evento para quando o bot�o do mouse for clicado.
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//Evento para quando o bot�o do mouse � solto.
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////
	
	//M�todos referentes � interface MouseMotionListener
	
	
	//Quando move-se o mouse com o bot�o pressionado.
	public void mouseDragged(MouseEvent e) {
		JFrame f = (JFrame) e.getSource();
	//	f.getGraphics().drawLine(e.getX(), e.getY(), getX()+12, getY()-12);
		f.getGraphics().draw3DRect(e.getX(), e.getY(), 20, 40, true);
	}

	//Quando simplesmente move-se o mouse.
	public void mouseMoved(MouseEvent e) {
		JFrame f = (JFrame) e.getSource();
		f.getGraphics().drawLine(e.getX(), e.getY(), e.getX()+4, e.getY()+4);
		
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/**
	 * M�todo principal para efetuar os devidos testes do programa.
	 * @param args
	 */
	public static void main(String[] args) {
		ExemploEventosMouse app = new ExemploEventosMouse();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}


}
