import java.util.AbstractList;
import java.util.NoSuchElementException;

public class MyList<T extends Comparable<T>> extends AbstractList<T> {

    private Node<T> start;
    private int size;


    public MyList() {
        start = null;
        size = 0;
    }

    /**
     * Adds a specific element at the end of the list.
     * @param obj the element to add.
     * @return true if element successfully added. This implementation always returns true.
     */
    @Override
    public boolean add(T obj) {
        Node<T> node = new Node(obj);
        if (isEmpty()) {
            start = node;
            start.next = node;
        } else {
            node.prev = start.prev;
            node.next = start;
            node.prev.next = node;
        }
        start.prev = node;
        ++size;
        return true;
    }

    /**
     * Removes the first occurence of a specific element.
     *
     * @param obj the object to be removed.
     * @return always true.
     */
    public boolean remove(T obj) {
        Node<T> curr = start;
        if (isEmpty()) {
            return false;
        }
        do {
            if (curr.data == obj || curr.data.equals(obj)) {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                if(curr == start) {
                    start = start.next;
                }
                --size;
                return true;
            }
            curr = curr.next;
        } while (curr != start);
        return false;
    }

    /**
     * Returns the Element at a specific index.
     *
     * @param index the index of the element to get.
     * @return the element at index if its found.
     */
    @Override
    public T get(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> curr = start;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    /**
     * Returns true if the list is empty.
     *
     * @return true if list is empty.
     */
    public boolean isEmpty() {
        return size == 0 || start == null;
    }

    /**
     * Returns this lists size.
     *
     * @return the lists size.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Clears the list.
     */
    public void clear() {
        new MyList<T>();
    }





}
