import javax.swing.JOptionPane;
class lista05_exercicio09 {
	static int N;
	public static void main(String[] args) {
		N = Integer.parseInt(JOptionPane.showInputDialog("Digite n�mero"));
		if (N%2 == 0){
			JOptionPane.showMessageDialog(null, "N�mero par");
		}
		else{
			JOptionPane.showMessageDialog(null, "N�mero impar");
		}
	}

}
