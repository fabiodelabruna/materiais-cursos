/**
 * Interface que define os m�todos a serem implementados.
 * @author Fabio Dela Bruna, Marcio Oz�rio.
 */
public interface Queue {
    
	
    public abstract int size();
    
    
    public abstract boolean isEmpty();
    
    
    public abstract Object front() throws EmptyQueueException;
    
    
    public abstract void enqueue(Object element) throws FullQueueException;
    
    
    public abstract Object dequeue() throws EmptyQueueException;
}