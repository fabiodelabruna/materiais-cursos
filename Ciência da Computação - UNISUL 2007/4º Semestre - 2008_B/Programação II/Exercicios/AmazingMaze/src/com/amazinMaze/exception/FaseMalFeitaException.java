package com.amazinMaze.exception;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FaseMalFeitaException extends RuntimeException
{
	// se a fase � carregada com dois jogadores ou duas sa�das, isso � disparado
    public FaseMalFeitaException(){
        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar a fase.");
     }
}
