import javax.swing.JOptionPane;
class lista07_exer05 {

static int N_bancaria;
static double S_atual, V_oper, S_final;
static char oper;

	public static void main(String[] args) {

N_bancaria = Integer.parseInt(JOptionPane.showInputDialog("Digite o N�mero da conta banc�ria"));
S_atual = Double.parseDouble(JOptionPane.showInputDialog("Informe o saldo atual"));
oper = JOptionPane.showInputDialog("Opera��o desejada").charAt(0);
V_oper = Double.parseDouble(JOptionPane.showInputDialog("Valor da Opera��o"));
switch (oper){
case 'D':{
	S_final = S_atual + V_oper;
break;}
case 'R':{
	S_final = S_atual - V_oper;
break;}
default:{
	JOptionPane.showMessageDialog(null, "Opera��o Inv�lida");
}
}
JOptionPane.showMessageDialog(null, "Seu Saldo � de " + S_final);
if (S_final<0){
	JOptionPane.showMessageDialog(null, "Conta Estourada");
}
	}
	

}
