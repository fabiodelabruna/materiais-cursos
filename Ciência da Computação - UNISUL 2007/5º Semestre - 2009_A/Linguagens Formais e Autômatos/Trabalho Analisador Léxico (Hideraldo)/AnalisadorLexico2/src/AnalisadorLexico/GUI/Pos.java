package AnalisadorLexico.GUI;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Pos {
	
	public static GridBagConstraints add(int coluna,int linha,
            int qtCols,int qtLin,String tamanho){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=coluna;//posicionamento horizontal
		gbc.gridy=linha;//posicionamento vertical
		gbc.gridwidth=qtCols;//qt colunas
		gbc.gridheight=qtLin;//qt linhas
		//fill = tamanho do componente dentro da c�lula
		if(tamanho.equals("NONE")){//respeita componente
			gbc.fill=GridBagConstraints.NONE;
		}
		if(tamanho.equals("HORIZONTAL")){//ocupa toda c�lula horizontamente
			gbc.fill=GridBagConstraints.HORIZONTAL;
		}
		if(tamanho.equals("VERTICAL")){//ocupa toda c�lula verticalmente
			gbc.fill=GridBagConstraints.VERTICAL;
		}
		if(tamanho.equals("BOTH")){//ocupa todos os lados da c�lula
			gbc.fill=GridBagConstraints.BOTH;
		}
		gbc.insets=new Insets(3,3,3,3);//epa�amento cima,baixo,direita,esquerda

		//ipdad = bordas internas do componente
		gbc.ipadx=0;
		gbc.ipady=0;
		//anchor = se o tamanho do componente n�o ocupar toda c�lula, onde ser� posicionado
		gbc.anchor=GridBagConstraints.WEST;
		return gbc;		
}

}
