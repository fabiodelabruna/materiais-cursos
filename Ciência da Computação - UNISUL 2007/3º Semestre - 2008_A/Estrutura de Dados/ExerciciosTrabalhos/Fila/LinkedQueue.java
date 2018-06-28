/**
 * Classe que representa uma Fila Encadeada (LinkedList).
 * @author Fabio Dela Bruna, Marcio Oz�rio.
 * @since 26/03/2008.
 */
public class LinkedQueue implements Queue {
    
    private Node first;
    private Node rear;
    private int size;
    
    
    /**
     * Construtor default.
     */
    public LinkedQueue() {
    	this.first = null;
    	this.rear = null;
    	this.size = 0;
    }
    
    
    /**
     * Insere o objeto o no fim da fila.
     */
    public void enqueue(Object element) throws FullQueueException {
    	Node newNode = new Node(element, null);
    	if(this.rear != null){
    		this.rear.setNext(newNode);
    	}else{
    		this.first = newNode;
    	}
    	this.rear = newNode;
    	this.size++;   	
    }
    
    
    /**
     * Retira e retorna o objeto da frente da fila.
     * Ocorre um erro se a fila estiver vazia.
     */
    public Object dequeue() throws EmptyQueueException {
    	if(isEmpty())
    		throw new EmptyQueueException("A FILA EST� VAZIA!");
    	
    	Node temp = this.first;
    	this.first = this.first.getNext();
    	this.size--;
    	if (this.size == 0)
			this.rear = null;
    	
    	return temp.getElement();
    }
    
    
    /**
     * Retorna o objeto da frente da fila sem retir�-lo.
     * Ocorre um erro se a fila estiver vazia.
     */
    public Object front() throws EmptyQueueException {
    	if(isEmpty())
    		throw new EmptyQueueException("A FILA EST� VAZIA!");
    	return this.first.getElement();
    }
    
    
    /**
     * Retorna um booleano indicando se a fila est� vazia ou n�o.
     */
    public boolean isEmpty() {
        return this.first == null;
    }
    
    
    /**
     * Imprime todos os elementos da Fila.
     */
    public void showElements(){
		Node temp = this.first;
		for (int i = 0; i < this.size; i++) {
			System.out.println(temp.getElement());
			temp = temp.getNext();
		}
    }
	
    
    /**
     * Retorna o n�mero de objetos na fila.
     */
    public int size() {
        return size;
    }

    
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
		
}