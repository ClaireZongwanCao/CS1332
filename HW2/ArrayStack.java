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
    	size = -1;
    }

    /**
     * Construct an ArrayStack with the specified
     * initial capacity of initialCapacity
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayStack(int initialCapacity) {
    	backing = (T[]) new Object[initialCapacity];
    	size = -1;
    }

    @Override
    public void push(T data) {
    	if (size() == backing.length) {
    		throw new IllegalArgumentException();
    	}
    	backing[size++] = data;  	
    }

    @Override
    public T pop() {
    	if (isEmpty())
    		throw new java.util.NoSuchElementException();
    	return backing[size];
    }

    @Override
    public int size() {
    	return (size + 1);
    }

    @Override
    public boolean isEmpty() {
    	return (size == -1);
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
