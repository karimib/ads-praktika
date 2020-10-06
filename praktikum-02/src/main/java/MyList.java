import java.util.AbstractList;
import java.util.NoSuchElementException;

public class MyList extends AbstractList {

    private Node start;
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
    public boolean add(Object obj) {
        Node node = new Node(obj);
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
    @Override
    public boolean remove(Object obj) {
        Node curr = start;
        if (isEmpty()) {
            return false;
        }
        if (curr.data == obj || curr.data.equals(obj)) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            start = curr.next;
            --size;
            if (isEmpty()) {
                clear();
            }
            return true;
        }
        curr = curr.next;
        do {
            if (curr.data == obj || curr.data.equals(obj)) {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
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
    public Object get(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node curr = start;
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
        new MyList();
    }


    /**
     * Auxilary node class holding the data (the lists entry) and pointers to the previous / next nodes.
     */
    protected class Node {

        protected Object data;
        protected Node next;
        protected Node prev;

        public Node() {
            data = null;
            next = null;
            prev = null;
        }


        public Node(Object object) {
            this.data = object;
            next = null;
            prev = null;
        }


        public Node(Object object, Node prev, Node next) {
            this.data = object;
            this.prev = prev;
            this.next = next;
        }
    }

}
