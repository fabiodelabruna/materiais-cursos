import javax.swing.JOptionPane;
class lista05_exercicio11 {
	static double s, s_total;
	static int nd;
	public static void main(String[] args) {
		s = Double.parseDouble(JOptionPane.showInputDialog("Digite seu sal�rio"));
		nd = Integer.parseInt(JOptionPane.showInputDialog("Digite o n�mero de dependentes"));
		if (nd>2){
			s_total = s + (100*nd);
		}
		else{
			s_total = s + 150;
		}
		JOptionPane.showMessageDialog(null, "Total a receber: " + s_total);
	}

}
