/**
 * ArrayQueue
 * Implementation of a queue using
 * an array as the backing structure
 *
 * @author Your Name Here
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueADT<T> {

    // Do not add instance variables
    private T[] backing;
    private int size;
    private int front;
    private int back;

    /**
     * Construct an ArrayQueue with an
     * initial capacity of INITIAL_CAPACITY
     *
     * Use Constructor Chaining
     */
    public ArrayQueue() {

    }

    /**
     * Construct an ArrayQueue with the specified
     * initial capacity of initialCapacity
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayQueue(int initialCapacity) {

    }

    @Override
    public void enqueue(T data) {

    }

    @Override
    public T dequeue() {

    }

    @Override
    public int size() {

    }

    @Override
    public boolean isEmpty() {

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
