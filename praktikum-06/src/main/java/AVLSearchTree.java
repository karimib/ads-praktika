public class AVLSearchTree<T extends Comparable<T>> extends SortedBinaryTree<T> {

	/**
	 * Insert into the tree; duplicates are ignored.
	 *
	 * @param element the item to insert.
	 */
	@Override
	public void add(T element) {
		root = insertAt(root, element);
	}


	/**
	 * If the tree is balanced.
	 * @return boolean if balanced.
	 */
	@Override
	public boolean balanced() {
		return balanced(root);
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 *
	 * @param toRemove the item to remove.
	 */
	@Override
	public T remove(T toRemove) {
		TreeNode<T> removed = new TreeNode<T>(null);
		root = removeAt(root, toRemove, removed);
		return removed.element;
	}

	@Override
	public AVLTreeTraversal<T> traversal() {
		return new AVLTreeTraversal<T>(root);
	}

	@Override
	public int height() {
		return calcHeight(root);
	}

	@Override
	public int size() {
		return calcSize(root);
	}



	private boolean balanced(TreeNode<T> root) {
		if(root == null) {
			return true;
		}
		return Math.abs(calcHeight(root.left) - calcHeight(root.right)) < 2 && balanced(root.left) && balanced(root.right);
	}


	private TreeNode<T> balance(TreeNode<T> parent) {
		if (parent == null) {
			return null;
		}
		if (height(parent.left) - height(parent.right) == 2) {
			if (height(parent.left.left) > height(parent.left.right)) {
				parent = rotateR(parent);
			} else {
				parent = rotateLR(parent);
			}
		} else if (height(parent.right) - height(parent.left) == 2) {
			if (height(parent.right.right) > height(parent.right.left)) {
				parent = rotateL(parent);
			} else {
				parent = rotateRL(parent);
			}
		}
		parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
		return parent;
	}


	/**
	 * Internal method to insert into a subtree.
	 *
	 * @param node the node that roots the tree.
	 * @return the new root.
	 */
	private TreeNode<T> insertAt(TreeNode<T> node, T element) {
		if (node == null) {
			node = new TreeNode<T>(element);
			return node;
		} else {
			int c = element.compareTo(node.element);
			if (c == 0) {
				node.count++;
			} else if (c < 0) {
				node.left = insertAt(node.left, element);
			} else if (c > 0) {
				node.right = insertAt(node.right, element);
			}
		}
		return balance(node);
	}

	private TreeNode<T> findRepAt(TreeNode<T> node, TreeNode<T> rep) {
		if (node.right != null) {
			node.right = findRepAt(node.right, rep);
		} else {
			rep.element = node.element;
			rep.count = node.count;
			rep.height = node.height;
			node = node.left;
		}
		return balance(node);
	}

    // remove node
    private TreeNode<T> removeAt(TreeNode<T> node, T x, TreeNode<T> removed) {
        if (node == null) {
            return null;
        } else {
            if (x.compareTo(node.element) == 0) {
                // found
                removed.element = node.element;
                if (node.count > 1) {
                    node.count--;
                    return node;
                } else if (node.left == null) {
                    node = node.right;
                } else if (node.right == null) {
                    node = node.left;
                } else {
                    node.left = findRepAt(node.left,node);
                }
            } else if (x.compareTo(node.element) < 0) {
                // search left
                node.left = removeAt(node.left, x, removed);
            } else {
                // search right
                node.right = removeAt(node.right, x, removed);
            }
            return balance(node);
        }
    }



	/**
	 * Rotate binary tree node with right child.
	 * For AVL trees, this is a single rotation for case 4.
	 * Update heights, then return new root.
	 */
	private TreeNode<T> rotateL(TreeNode<T> k1) {
		TreeNode<T> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	/**
	 * Double rotate binary tree node: first left child
	 * with its right child; then node k3 with new left child.
	 * For AVL trees, this is a double rotation for case 2.
	 * Update heights, then return new root.
	 */
	private TreeNode<T> rotateLR(TreeNode<T> k3) {
		k3.left = rotateL(k3.left);
		return rotateR(k3);
	}

	/**
	 * Double rotate binary tree node: first right child
	 * with its left child; then node k1 with new right child.
	 * For AVL trees, this is a double rotation for case 3.
	 * Update heights, then return new root.
	 */
	private TreeNode<T> rotateRL(TreeNode<T> k1) {
		k1.right = rotateR(k1.right);
		return rotateL(k1);
	}

	/**
	 * Rotate binary tree node with left child.
	 * For AVL trees, this is a single rotation for case 1.
	 * Update heights, then return new root.
	 */
	private TreeNode<T> rotateR(TreeNode<T> k2) {
		TreeNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	/**
	 * Return the height of node t, or 0, if null.
	 */
	private int height(TreeNode<T> t) {

		return t == null ? 0 : t.height;
	}


}
