public class TestaPonto {

	public static void main(String[] args) {

		PontoUnidimensional p1 = new PontoUnidimensional(1);
		PontoBidimensional  p2 = new PontoBidimensional(1, 2);
		
		if(p2.equals(p1))
			System.out.println("S�o iguais");
		else 
			System.out.println("N�o S�o iguais");
	}
}