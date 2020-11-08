/* interface of visitor ADT */

public interface Visitor<T> {
	/* called for each element in the tree */
	void visit(T obj);
}
