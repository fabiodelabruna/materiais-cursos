import javax.swing.JOptionPane;
class lista05_exercicio09 {
	static double n;
	public static void main(String[] args) {
		n = Double.parseDouble(JOptionPane.showInputDialog("Escreva um n�mero"));
		if (n%2 == 0){
			JOptionPane.showMessageDialog(null, "N�mero par");
		}
		else{
			JOptionPane.showMessageDialog(null, "N�mero �mpar");
		}
	}

}
