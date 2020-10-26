import java.util.Queue;
import java.util.ArrayDeque;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

	private TreeNode<T> root;

	public TreeTraversal(TreeNode<T> root) {
		this.root = root;
	}
	@Override
	public void inorder(Visitor<T> visitor) {
		inorder(root, visitor);
	}

	@Override
	public void preorder(Visitor<T> visitor) {
		preorder(root, visitor);
	}

	@Override
	public void postorder(Visitor<T> visitor) {
		postorder(root, visitor);
	}

	@Override
	public void levelorder(Visitor<T> visitor) {
		levelorder(root, visitor);
	}

	@Override
	public void interval(Comparable<T> min, Comparable<T> max, Visitor<T> visitor) {
		interval(root, min, max,visitor);
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

	private void interval(TreeNode<T> node, Comparable<T> min, Comparable<T> max, Visitor<T> visitor) {
		if(node != null) {
			if(min.compareTo(node.getValue()) < 0) {
				interval(node.left,min, max, visitor);
			}
			if(min.compareTo(node.getValue()) < 1 && max.compareTo(node.getValue()) > -1) {
				visitor.visit(node.getValue());
			}
			if(max.compareTo(node.getValue()) > 0) {
				interval(node.right,min, max, visitor);
			}
		}
	}

}
