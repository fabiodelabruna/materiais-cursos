import javax.swing.JOptionPane;
class lista04_exercicio04 {
	static String N;
	static double H, Vh, P_d, S_b, S_l, Vd;

	public static void main(String[] args) {
		N = JOptionPane.showInputDialog("Nome do funcion�rio");
		H = Double.parseDouble(JOptionPane.showInputDialog("Quantidade de horas trabalhada"));
		Vh = Double.parseDouble(JOptionPane.showInputDialog("Valor por hora trabalhada"));
		P_d = Double.parseDouble(JOptionPane.showInputDialog("Percentual de desconto"));
		S_b = H*Vh;
		S_l = (H*Vh)- ((H*Vh)*(P_d/100));
		Vd = (H*Vh)* (P_d/100);
		JOptionPane.showMessageDialog(null, N);
		JOptionPane.showMessageDialog(null, "Seu s�lario bruto � " + S_b + " reais");
		JOptionPane.showMessageDialog(null, "Seu sal�rio l�quido � de " + S_l + " reais");
		JOptionPane.showMessageDialog(null, "O valor descontado � de " + Vd + " reais");

	}

}
