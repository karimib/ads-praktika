import java.util.LinkedList;
import java.util.Queue;

public class AVLTreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;


    public AVLTreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    private void inorder(TreeNode<T> node, Visitor<T> vis) {
        if (node != null) {
            inorder(node.left, vis);
            for (int i=0; i < node.count; i++) vis.visit(node.element);
            inorder(node.right, vis);
        }
    }
    @Override
    public void inorder(Visitor<T> vis) {
        inorder(root, vis);
    }

    private void preorder(TreeNode<T> node, Visitor<T> vis) {
        if (node != null) {
            for (int i=0; i < node.count; i++) vis.visit(node.element);
            preorder(node.left, vis);
            preorder(node.right, vis);
        }
    }

    @Override
    public void preorder(Visitor<T> vis) {
        preorder(root, vis);
    }

    private void postorder(TreeNode<T> node, Visitor<T> vis) {
        if (node != null) {
            postorder(node.left, vis);
            postorder(node.right, vis);
            for (int i=0; i < node.count; i++) vis.visit(node.element);
        }
    }
    @Override
    public void postorder(Visitor<T> vis) {
        postorder(root, vis);
    }

    private void levelorder(TreeNode<T> node, Visitor<T> visitor) {
        Queue<TreeNode<T>> q = new LinkedList<TreeNode<T>>();
        if (node != null) {
            q.offer(node);
        }
        while (!q.isEmpty()) {
            node = q.poll();
            for (int i=0; i < node.count; i++) visitor.visit(node.element);
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
    }

    @Override
    public void levelorder(Visitor<T> vis) {
        levelorder(root,vis);
    }

    @Override
    public void interval(Comparable<T> min, Comparable<T> max, Visitor<T> visitor) {
        interval(min, max, visitor);
    }

}
