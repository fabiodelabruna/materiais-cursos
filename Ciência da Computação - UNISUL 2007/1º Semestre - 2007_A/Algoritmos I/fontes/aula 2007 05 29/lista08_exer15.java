import javax.swing.JOptionPane;
class lista08_exer15 {

static int i,n;
static double m;
static String nome;

	public static void main(String[] args) {
		n = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de Aluno � serem cadastrados"));
for	(i=1; i<=n; i+=1){
	nome = JOptionPane.showInputDialog("Digite o nome do aluno");
	m = Double.parseDouble(JOptionPane.showInputDialog("Digite a m�dia do aluno"));
	if (m >= 7.0){
		JOptionPane.showMessageDialog(null, "O aluno est� aprovado");
	}
	else{
		if (m >= 2.0){
			JOptionPane.showMessageDialog(null, "O aluno est� em recupera��o");
		}
		else{
			JOptionPane.showMessageDialog(null, "O aluno est� reprovado");
		}
	}
}

	}

}
