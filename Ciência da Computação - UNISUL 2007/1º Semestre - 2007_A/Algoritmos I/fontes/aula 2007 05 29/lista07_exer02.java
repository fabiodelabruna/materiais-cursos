import javax.swing.JOptionPane;
class lista07_exer02 {

static String N;
static char Cat;
static double S_inicial, S_novo;

	public static void main(String[] args) {
	
N = JOptionPane.showInputDialog("Digite o nome do Funcion�rio");
Cat = JOptionPane.showInputDialog("Digite a Categoria desse Funcion�rio").charAt(0);
S_inicial = Double.parseDouble(JOptionPane.showInputDialog("Digite o S�lario atual doFuncion�rio"));

switch (Cat){
case 'A':{
	S_novo = S_inicial+(S_inicial*(10.0/100));
break;}
case 'B':{
	S_novo = S_inicial+(S_inicial*(15.0/100));
break;}
case 'C':{
	S_novo = S_inicial+(S_inicial*(25.0/100));
break;}
case 'D':{
	S_novo = S_inicial+(S_inicial*(35.0/100));
break;}
default:{
	S_novo = S_inicial+(S_inicial*(50.0/100));
}
}
	JOptionPane.showMessageDialog(null, "Funcion�rio: " + N);
	JOptionPane.showMessageDialog(null, "Categoria: " + Cat);
	JOptionPane.showMessageDialog(null, "Sal�rio novo: " + S_novo);
	}

}
