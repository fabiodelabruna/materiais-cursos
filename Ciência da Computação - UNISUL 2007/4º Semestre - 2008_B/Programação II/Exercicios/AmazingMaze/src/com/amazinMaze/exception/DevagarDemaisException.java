package com.amazinMaze.exception;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class DevagarDemaisException extends RuntimeException {
	public DevagarDemaisException(String event) {
		JOptionPane.showMessageDialog(null, "O tempo acabou e a pir�mide explodiu!\n" +
				"Da pr�xima tente ser mais r�pido\n" +
				"Agora voc� vai ter que come�ar tudo denovo!","GAME OVER!!",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("imagens\\gameover.jpg"));
		System.exit(0);
	}
}
