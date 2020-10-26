public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    public void inorder(Visitor<T> vis) {
       while (vis != null) {
           inorder(vis.);
       }
        // to be done
    }

    public void preorder(Visitor<T> vis) {
        // to be done
    }

    public void postorder(Visitor<T> vis) {
        // to be done
    }

    @Override
    public void levelorder(Visitor<T> vistor) {

    }

}
