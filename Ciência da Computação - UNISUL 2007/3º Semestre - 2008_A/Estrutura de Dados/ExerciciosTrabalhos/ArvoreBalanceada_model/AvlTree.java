
public class AvlTree {

	private AvlNode root;

	public AvlTree( ) {
		root = null;
	}

	/**
	 * Insere na �rvore; itens duplicados s�o ignorados.
	 * @param x o item a inserir.
	 */
	public void insert(Comparable x) {
		root = insert(x, root);
	}

	/**
	 * Remove da �rvore. Nada � feito se x n�o for encontrado.
	 * @param x o item a remover.
	 */
	public void remove(Comparable x) {
		remove(x, root);
	}
	
	

	/**
	 * Procura o menor item da �rvore.
	 * @return o menor item ou null se a �rvore estiver vazia.
	 */
	public Comparable findMin() {
		return elementAt(findMin(root));
	}

	/**
	 * Procura o maior item da �rvore.
	 * @return o maior item ou null se a �rvore estiver vazia.
	 */
	public Comparable findMax() {
		return elementAt(findMax(root));
	}

	/**
	 * Procura um item na �rvore.
	 * @param x o item a procurar.
	 * @return o item correspondente ou null se o item n�o for encontrado.
	 */
	public Comparable find(Comparable x) {
		return elementAt(find(x, root));
	}

	/**
	 * Esvazia a �rvore.
	 */
	public void makeEmpty( ) {
		root = null;
	}

	/**
	 * Testa se a �rvore esta vazia.
	 * @return true se a �rvore estiver vazia, false caso contr�rio.
	 */
	public boolean isEmpty( ) {
		return root == null;
	}

	/**
	 * Imprime o conte�do da �rvore em ordem crescente.
	 */
	public void printTree( ) {
		if (isEmpty())
			System.out.println("A �rvore est� vazia");
		else
			printTree( root );
	}
	
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if (obj instanceof AvlTree) {
			AvlTree avl = (AvlTree) obj;
			return avl.root.equals(this.root);
		}
		return false;
	}

	/**
	 * M�todo interno para recuperar o atributo elemento.
	 * @param t o n�.
	 * @return o elemento ou null se element for null.
	 */
	private Comparable elementAt(AvlNode t) {
		return t == null ? null : t.element;
	}

	/**
	 * M�todo interno para inserir em uma sub-�rvore.
	 * @param x o item a inserir.
	 * @param t o nodo que � a raiz da �rvore.
	 * @return a nova raiz.
	 */
	private AvlNode insert(Comparable x, AvlNode t) {
		if( t == null )
			t = new AvlNode( x, null, null );
		else if( x.compareTo( t.element ) < 0 ){
			t.left = insert( x, t.left );
			if( height( t.left ) - height( t.right ) == 2 )
				if( x.compareTo( t.left.element ) < 0 )
					t = rotateWithLeftChild( t );
				else
					t = doubleWithLeftChild( t );
		}else if( x.compareTo( t.element ) > 0 ){
			t.right = insert( x, t.right );
			if( height( t.right ) - height( t.left ) == 2 )
				if( x.compareTo( t.right.element ) > 0 )
					t = rotateWithRightChild( t );
				else
					t = doubleWithRightChild( t );
		}else
			t.incrementaQuantidade();  
		t.height = max( height( t.left ), height( t.right ) ) + 1;
		return t;
	}

	private void remove(Comparable x, AvlNode t){
		if(t == null)
			;
		else if(x.compareTo(t.element) < 0 ){
			remove(x, t.left);
		}else if(x.compareTo(t.element) > 0){
			remove(x, t.right);
		}else{
			t.decrementaQuantidade();
		}
	}
	
	
	/**
	 * M�todo interno para producar o menor item da sub�rvore.
	 * @param t o n�s raiz da �rvore.
	 * @return n� contendo o menor item.
	 */
	private AvlNode findMin( AvlNode t ) {
		if(t == null)
			return t;
		if (t.left == null)
			return t;
		
		AvlNode temp =  findMin(t.left);
		if(temp.quantidade == 0){
			if(t.quantidade == 0)
				temp = (t.right!=null) ? findMin(t.right) : t;
			else
				temp = t;
		}
		return temp;
	}

	/**
	 * M�todo inteno para procurar o maior item da sub�rvore.
	 * @param t o n�s raiz da �rvore.
	 * @return n� contendo o maior item.
	 */
	private AvlNode findMax( AvlNode t ) {
		if(t == null)
			return null;
		if (t.right == null)
			return t;
		
		AvlNode temp =  findMax(t.right);
		if(temp.quantidade == 0){
			if(t.quantidade == 0)
				temp = (t.left!=null) ? findMax(t.left) : t;
			else
				temp = t;
		}
		return temp;
		
	}

	/**
	 * M�todo interno para procurar um item na sub�rvore.
	 * @param x o item a ser procurado.
	 * @param t o n�s raiz da �rvore.
	 * @return n� contendo o elemento produrado.
	 */

	private AvlNode find( Comparable x, AvlNode t ) {
		while( t != null )
			if( x.compareTo( t.element ) < 0 )
				t = t.left;
			else if( x.compareTo( t.element ) > 0 )
				t = t.right;
			else
				if(t.quantidade != 0)
					return t;    

		return null;  
	}

	/**
	 * M�todo interno para imprimir a sub�rvore em ordem crescente
	 * @param t o n�s raiz da �rvore.
	 */
	private static void printTree( AvlNode t ) {
		if( t != null ){
			printTree( t.left );
			
			if(t.quantidade>0)
				System.out.println( t.element );
			
			printTree( t.right );
		}
	}

	/**
	 * Return a altura do n�s t, ou -1, se null.
	 */
	private static int height(AvlNode t) {
		return t == null ? -1 : t.height;
	}

	/**
	 * Return maximo dos dois valores.
	 */
	private static int max(int lhs, int rhs) {
		return lhs > rhs ? lhs : rhs;
	}

	/**
	 * Gira o n� com o filho da esquerda.
	 * Para uma �rvore AVL, esta � uma rota��o simples (caso 1).
	 * Altera alturas, e ent�o retorna o raiz.
	 */
	private static AvlNode rotateWithLeftChild(AvlNode k2) {
		AvlNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = max( height( k1.left ), k2.height ) + 1;
		return k1;
	}

	/**
	 * Gira o n� com o filho da direita.
	 * Para �rvore AVL, esta � uma rota��o simples (caso 4).
	 * Altera alturas, e ent�o retorna o raiz.
	 */
	private static AvlNode rotateWithRightChild(AvlNode k1) {
		AvlNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
		k2.height = max( height( k2.right ), k1.height ) + 1;
		return k2;
	}

	/**
	 * Rota��o dupla: first left child
	 * with its right child; then node k3 with new left child.
	 * Para �rvore AVL, esta � uma rota��o dupla (caso 2.
	 * Altera alturas, e ent�o retorna o raiz.
	 */
	private static AvlNode doubleWithLeftChild(AvlNode k3) {
		k3.left = rotateWithRightChild( k3.left );
		return rotateWithLeftChild( k3 );
	}

	/**
	 * Rota��o Dupla: first right child
	 * with its left child; then node k1 with new right child.
	 * Para �rvore AVL, esta � uma rota��o dupla (caso 2.
	 * Altera alturas, e ent�o retorna o raiz.
	 */
	private static AvlNode doubleWithRightChild( AvlNode k1 ) {
		k1.right = rotateWithLeftChild( k1.right );
		return rotateWithRightChild( k1 );
	}
}