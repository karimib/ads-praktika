import java.util.LinkedList;

public class ListStack<T> implements Stack<T> {

    private LinkedList<T> list;


    /**
     * Constructor for stack with fixed size
     *
     * @param size
     */
    public ListStack() {
        list = new LinkedList<T>();
    }

    /**
     * {@inheritDoc}
     */

    public void push(T obj) {
        list.addFirst(obj);
    }


    /**
     * {@inheritDoc}
     */

    public T pop() {
        if (list.isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }


    /**
     * {@inheritDoc}
     */

    public T peek() {
        if (list.isEmpty()) {
            return null;
        }
        return list.peek();
    }


    /**
     * {@inheritDoc}
     */

    public boolean isEmpty() {
        return list.isEmpty();
    }


    /**
     * Removes all elements of the stack.
     */

    public void removeAll() {
        list = new LinkedList<T>();

    }

    public void p() {
        if(!isEmpty()) {
            for(T t : list) {
                System.out.println(t.toString());
            }
        }
    }

}
