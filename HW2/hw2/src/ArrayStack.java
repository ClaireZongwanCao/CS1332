/**
 * ArrayStack
 * Implementation of a stack using
 * an array as a backing structure
 *
 * @author Zongwan Cao
 * @version 1.0
 */
public class ArrayStack<T> implements StackADT<T> {

    private static final Exception IllegalArgumentException = null;
	// Do not add instance variables
    private T[] backing;
    private int size;

    /**
     * Construct an ArrayStack with
     * an initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public ArrayStack() {
    	this(INITIAL_CAPACITY);
    	size = 0;
    }

    /**
     * Construct an ArrayStack with the specified
     * initial capacity of initialCapacity
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayStack(int initialCapacity) {
    	backing = (T[]) new Object[initialCapacity];
    	size = 0;
    }

    @Override
    public void push(T data)  {
    	if (data == null) {
    		throw new IllegalArgumentException("null");
    	}
    	if (size == backing.length) {
    		regrow();
    	}
    	backing[size] = data; 
    	size++;
    }
    
    private void regrow() {
    	T[] arr = (T[]) new Object[size * 2];
		int i;
		for (i = 0; i < size; i++) {
			arr[i] = backing[i];
		}
		backing = arr;
	}

    @Override
    public T pop() {
    	T a;
    	if (isEmpty())
    		throw new java.util.NoSuchElementException("null");
    	size--;
    	a = backing[size];
    	backing[size] = null;
    	return a;
    }

    @Override
    public int size() {
    	return size;
    }

    @Override
    public boolean isEmpty() {
    	return (size == 0);
    }

    /**
     * Returns the backing array for your queue.
     * This is for grading purposes only. DO NOT EDIT THIS METHOD.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        return backing;
    }
}
