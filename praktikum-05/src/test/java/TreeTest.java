import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TreeTest {
	Tree<String> tree;

	@Before
	public void setUp() throws Exception {
		tree = new SortedBinaryTree<String>();
		tree.add("B");
		tree.add("A");
		tree.add("C");
		tree.add("D");
	}

	@Test
	public void testInorder() {
		Visitor<String> v = new MyVisitor<String>();
		tree.traversal().inorder(v);
		assertEquals("inorder", "ABCD", v.toString());
	}

	@Test
	public void testPreorder() {
		Visitor<String> v = new MyVisitor<String>();
		tree.traversal().preorder(v);
		assertEquals("preorder", "BACD", v.toString());
	}

	@Test
	public void testPostorder() {
		Visitor<String> v = new MyVisitor<String>();
		tree.traversal().postorder(v);
		assertEquals("postorder", "ADCB", v.toString());
	}

	@Test
	public void testLevelorder() {
		Visitor<String> v = new MyVisitor<String>();
		tree.traversal().levelorder(v);
		assertEquals("levelorder", "BACD", v.toString());
	}

	@Test
	public void testInterval() {
		Visitor<String> v = new MyVisitor<String>();
		tree.add("E");
		tree.add("L");
		tree.add("I");
		tree.add("G");
		tree.add("H");
		tree.add("J");
		tree.add("F");
		tree.add("K");
		tree.traversal().interval("C", "H", v);
		assertEquals("interval", "CDEFGH", v.toString());
	}
}


class MyVisitor<T> implements Visitor<T> {
	StringBuilder output;

	MyVisitor() {
		output = new StringBuilder();
	}

	public void visit(T s) {
		output.append(s);
	}

	public String toString() {
		return output.toString();
	}
}