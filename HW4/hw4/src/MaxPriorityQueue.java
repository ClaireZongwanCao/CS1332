public class MaxPriorityQueue<T extends Comparable<? super T>> implements
        PriorityQueueInterface<T> {

    private MaxHeap<T> heap;

    // Do not add any more instance variables

    /**
     * Creates a MaxPriorityQueue
     */
    public MaxPriorityQueue() {
        heap = new MaxHeap<T>();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        heap.add(item);
    }

    @Override
    public T dequeue() {
        if (heap.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return heap.remove();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void clear() {
        heap.clear();
    }

    @Override
    public MaxHeap<T> getBackingHeap() {
        return heap;
    }
}
