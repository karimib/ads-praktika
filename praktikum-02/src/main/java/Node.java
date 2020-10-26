/**
 * Auxilary node class holding the data (the lists entry) and pointers to the previous / next nodes.
 */
public class Node<T extends Comparable<T>> implements Comparable<T> {

	public T data;
	public Node<T> next;
	public Node<T> prev;

	public Node() {
		data = null;
		next = null;
		prev = null;
	}


	public Node(T data) {
		this.data = data;
		next = null;
		prev = null;
	}


	public Node(T data, Node<T> prev, Node<T> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	@Override
	public int compareTo(T obj) {
		return this.getData().compareTo((T) obj);
	}
}