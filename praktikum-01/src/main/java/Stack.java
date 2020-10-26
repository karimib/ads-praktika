/**
 * Interface for a simple stack implementation.
 */

public interface Stack<T> {
	/**
	 * Pushes the element on top of the stack.
	 *
	 * @param t
	 */

	void push(T t);

	/**
	 * Removes and returns the element on top of the stack.
	 *
	 * @return t
	 */

	T pop();

	/**
	 * Returns the element on top of the stack without removing it.
	 *
	 * @return t
	 */

	T peek();

	/**
	 * Checks whether the stack is empty or not.
	 *
	 * @return true if stack is empty
	 */

	boolean isEmpty();
}
