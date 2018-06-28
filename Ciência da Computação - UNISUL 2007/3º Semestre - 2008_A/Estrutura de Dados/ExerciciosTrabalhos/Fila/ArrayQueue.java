/**
 * Classe que representa uma Fila.
 * @author Fabio Dela Bruna, Marcio Oz�rio.
 * @since 21/03/2008.
 */
public class ArrayQueue implements Queue {
    
	
    private Object[] elements; // elementos da fila
    private int first; 	// ponteiro para o elemento do come�o da fila
    private int rear; 	// ponteiro para o elemento no final da fila
    private int size;	// tamanho da fila
    
    
    /**
     * Construtor default.
     */
    public ArrayQueue() {
    	this.elements = new Object[5];
    	this.first = 0;
    	this.rear = 0;
    	this.size = 0;
    
    }
    
    
    /**
     * Retira e retorna o objeto da frente da fila.
     * Ocorre um erro se a fila estiver vazia.
     */
    public Object dequeue() throws EmptyQueueException {
		if(isEmpty())
			throw new EmptyQueueException("A fila est� vazia");
		Object temp = elements[this.first];
		this.elements[this.first] = null;
		this.first ++;
		this.size--;
		return temp;
	}
    
    
    /**
     * Insere o objeto o no fim da fila.
     */
    public void enqueue(Object element) throws FullQueueException {
    	if(isFull())
    		throw new FullQueueException("A FILA EST� CHEIA!");
    	if(isEmpty()){
    		this.elements[this.rear] = element;
    		this.size++;
    	}else{
    		this.rear++;
    		this.rear = this.rear % this.elements.length;
    		this.elements[this.rear] = element;
    		this.size++;
    	}
    	
    }
    	
    
    /**
     * Retorna o primeiro elemento da Fila.
     */
    public Object front() throws EmptyQueueException {
    	return this.elements[this.first];
    }
    
    
    /**
     * Retorna se a Fila est� vazia.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    
    /**
     * Retorna o tamanho da Fila.
     */
    public int size() {
    	return size;
    }
    
    
    /**
     * Retorna se o vetor est� cheio.
     */
    public boolean isFull(){
		return this.size == 5;
    }
    
    
    /**
     * Mostra todos os elementos que est�o na lista.
     */
    public void showElements() throws EmptyQueueException {
	    if(isEmpty())
	    	throw new EmptyQueueException("A FILA EST� VAZIA!");	
	    if(this.rear < this.first){
	     	for (int i = 0; i <= this.rear; i++){
	    	   	System.out.println(elements[i]);
	     	}
	    }  
	 	for(int i = this.first; i < elements.length; i++){
		   	System.out.println(elements[i]);
	 	}
    } 	
    
} 
    
	
