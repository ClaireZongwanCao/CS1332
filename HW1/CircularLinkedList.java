/**
 * CircularLinkedList implementation
 *
 * @author Zongwan Cao
 * @version 1.0
 */
public class CircularLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        LinkedListNode<T> it = new LinkedListNode<T>(data);
        LinkedListNode<T> current = head;
        int i = 0;
        if ((index < 0) | (index > size)) {
            throw new java.lang.IndexOutOfBoundsException();
        } else if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            while (i < index) {
                i++;
                current = current.getNext();
            }
            it.setNext(current);
            it.setPrevious(current.getPrevious());
            current.setPrevious(it);
            it.getPrevious().setNext(it);
            size++;
        }
    }

    @Override
    public T get(int index) {
        LinkedListNode<T> current = head;
        int i = 0;
        if ((index < 0) | (index >= size)) {
            throw new java.lang.IndexOutOfBoundsException();
        } else {
            while (i < index) {
                i++;
                current = current.getNext();
            }
            return current.getData();
        }
    }

    @Override
    public T removeAtIndex(int index) {
        LinkedListNode<T> current = head;
        int i = 0;
        if ((index < 0) | (index >= size)) {
            throw new java.lang.IndexOutOfBoundsException();
        } else {
            if (index == 0) {
                return removeFromFront();
            } else if (index == size - 1) {
                return removeFromBack();
            } else {
                T a;
                while (i < index) {
                    i++;
                    current = current.getNext();
                }
                a = current.getData();
                current.getNext().setPrevious(current.getPrevious());
                current.getPrevious().setNext(current.getNext());
                size--;
                return a;
            }
        }
    }

    @Override
    public void addToFront(T data) {
        LinkedListNode<T> it = new LinkedListNode<T>(data);
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        } else {
            if (head == null) {
                head = it;
                head.setNext(head);
                head.setPrevious(head);
            } else {
                it.setPrevious(head.getPrevious());
                it.setNext(head);
                it.getPrevious().setNext(it);
                head.setPrevious(it);
                head = it;
            }
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        LinkedListNode<T> it = new LinkedListNode<T>(data);
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        } else {
            if (head == null) {
                head = it;
                head.setNext(head);
                head.setPrevious(head);
            } else {
                it.setPrevious(head.getPrevious());
                it.setNext(head);
                it.getPrevious().setNext(it);
                head.setPrevious(it);
            }
            size++;
        }
    }

    @Override
    public T removeFromFront() {
        T a;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            size = 0;
            a = head.getData();
            head = null;
            return a;
        } else {
            a = head.getData();
            head.getNext().setPrevious(head.getPrevious());
            head.getPrevious().setNext(head.getNext());
            head = head.getNext();
            size--;
            return a;
        }
    }

    @Override
    public T removeFromBack() {
        T a;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            size = 0;
            a = head.getData();
            head = null;
            return a;
        } else {
            a = head.getPrevious().getData();
            head.setPrevious(head.getPrevious().getPrevious());
            head.getPrevious().setNext(head);
            size--;
            return a;
        }
    }

    @Override
    public Object[] toArray() {
        int i = 0;
        LinkedListNode<T> current = head;
        Object[] array1 = new Object[size];
        while (i < size) {
            array1[i] = current.getData();
            current = current.getNext();
            i++;
        }
        return array1;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }

}
