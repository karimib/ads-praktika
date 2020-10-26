import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

	private TreeNode<T> root;

	public TreeTraversal(TreeNode<T> root) {
		this.root = root;
	}

	public void inorder(Visitor<T> vis) {
		inorder(root, vis);
	}

	public void preorder(Visitor<T> vis) {
		preorder(root, vis);
	}

	public void postorder(Visitor<T> vis) {
		postorder(root, vis);
	}

	@Override
	public void levelorder(Visitor<T> vis) {
		levelorder(root, vis);
	}


	private void inorder(TreeNode<T> node, Visitor<T> visitor) {
		if (node != null) {
			inorder(node.left, visitor);
			visitor.visit(node.element);
			inorder(node.right, visitor);
		}
	}

	private void preorder(TreeNode<T> node, Visitor<T> visitor) {
		if (node != null) {
			visitor.visit(node.element);
			preorder(node.left, visitor);
			preorder(node.right, visitor);
		}
	}

	private void postorder(TreeNode<T> node, Visitor<T> visitor) {
		if (node != null) {
			postorder(node.left, visitor);
			postorder(node.right, visitor);
			visitor.visit(node.element);
		}
	}

	private void levelorder(TreeNode<T> node, Visitor<T> visitor) {
		Queue<TreeNode<T>> q = new ArrayDeque<>();
		if (node != null) {
			q.offer(node);
		}
		while (!q.isEmpty()) {
			node = q.poll();
			visitor.visit(node.element);
			if (node.left != null) {
				q.offer(node.left);
			}
			if (node.right != null) {
				q.offer(node.right);
			}
		}
	}


}
