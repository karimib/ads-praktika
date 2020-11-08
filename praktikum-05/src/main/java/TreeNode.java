public class TreeNode<T extends Comparable<T>> {
	T element;
	TreeNode<T> left, right;
	int height;
	int count;

	TreeNode(T element) {
		this.element = element;
		this.count = 1;
		this.height = 1;
		this.left = null;
		this.right = null;
	}

	TreeNode(T element, TreeNode left, TreeNode right) {
		this(element);
		this.left = left;
		this.right = right;
	}
	T getValue() {
		return element;
	}
}