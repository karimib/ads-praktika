import org.w3c.dom.Node;

import java.sql.PreparedStatement;

public class SortedBinaryTree<T extends Comparable<T>> implements Tree<T> {
	protected TreeNode<T> root;

	@Override
	public int height() {
		return calcHeight(root);
	}

	@Override
	public int size() {
		return calcSize(root);
	}

	@Override
	public boolean balanced() {
		if(root == null) {
			return false;
		}
		return Math.abs(calcHeight(root.left) - calcHeight(root.left)) < 2;
	}

	@Override
	public void add(T x) {
		root = insertAt(root, x);
	}

	@Override
	public T remove(T x) {
		TreeNode<T> removed = new TreeNode<T>(null);
		root = removeAt(root, x, removed);
		return removed.element;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Traversal<T> traversal() {
		return new TreeTraversal<>(root);
	}

	// find node to replace
	private TreeNode<T> findRepAt(TreeNode<T> node, TreeNode<T> rep) {
		if (node.right != null) {
			node.right = findRepAt(node.right, rep);
		} else {
			rep.element = node.element;
			node = node.left;
		}
		return node;
	}

	private TreeNode<T> insertAt(TreeNode<T> node, T x) {
		if (node == null) {
			return new TreeNode<T>(x);
		} else {
			if (x.compareTo(node.element) <= 0) {
				node.left = insertAt(node.left, x);
			} else {
				node.right = insertAt(node.right, x);
			}
			return node;
		}
	}
	// remove node

	private TreeNode<T> removeAt(TreeNode<T> node, T x, TreeNode<T> removed) {
		if (node == null) {
			return null;
		} else {
			if (x.compareTo(node.element) == 0) {
				// found
				removed.element = node.element;
				if (node.left == null) {
					node = node.right;
				} else if (node.right == null) {
					node = node.left;
				} else {
					node.left = findRepAt(node.left, node);
				}
			} else if (x.compareTo(node.element) < 0) {
				// search left
				node.left = removeAt(node.left, x, removed);
			} else {
				// search right
				node.right = removeAt(node.right, x, removed);
			}
			return node;
		}
	}


	public int calcHeight(TreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		return Math.max(calcHeight(node.left), calcHeight(node.right)) + 1;
	}


	private int calcSize(TreeNode p) {
		if(p == null) {
			return 0;
		}
		return calcSize(p.right) + calcSize(p.left) + p.count;
	}

	// only for testing and debugging purposes: show the structure of the tree
	public String printTree() {
		StringBuilder out = new StringBuilder();
		if (root.right != null) {
			printTree(root.right, out, true, "");
		}
		out.append(root.element + "\n");
		if (root.left != null) {
			printTree(root.left, out, false, "");
		}
		return out.toString();
	}

	private void printTree(TreeNode node, StringBuilder out, boolean isRight, String indent) {
		if (node.right != null) {
			printTree(node.right, out, true,
					indent + (isRight ? "        " : " |      "));
		}
		out.append(indent);
		if (isRight) {
			out.append(" /");
		} else {
			out.append(" \\");
		}
		out.append("----- ");
		out.append(node.element + "\n");
		if (node.left != null) {
			printTree(node.left, out, false,
					indent + (isRight ? " |      " : "        "));
		}
	}

}
