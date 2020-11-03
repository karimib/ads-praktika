import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


public class AVLSearchTreeTest {
    private AVLSearchTree<String> tree;

    private void init(AVLSearchTree<String> tree) {
        tree.add("E");
        tree.add("F");
        tree.add("G");
        tree.add("H");
        tree.add("J");
        tree.add("A");
        tree.add("B");
        tree.add("C");
        tree.add("D");
    }

    @Before
    public void setUp() throws Exception {
        tree = new AVLSearchTree<>();
        init(tree);
    }

    @Test
    public void testInorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().inorder(v);
        assertEquals("ABCDEFGHJ", v.toString());
    }

    @Test
    public void testPreorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().preorder(v);
        assertEquals("FBADCEHGJ", v.toString());
    }

    @Test
    public void testPostorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().postorder(v);
        assertEquals( "ACEDBGJHF", v.toString());
    }

    @Test
    public void testLevelorder() {
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().levelorder(v);
        assertEquals( "FBHADGJCE", v.toString());
    }

    @Test
    public void testHeight() {
        assertEquals(4,tree.height());
    }

    @Test
    public void testBalanced() {
        assertTrue(tree.balanced());
        Tree<String>  tree2 = new AVLSearchTree<String>();
        tree2.add("A");
        tree2.add("B");
        tree2.add("C");
        assertTrue(tree2.balanced());
    }


    @Test
    public void testRemove() {
        tree = new AVLSearchTree<String>();
        init(tree);
        tree.remove("F");
        tree.remove("H");
        tree.remove("J");
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().inorder(v);
        assertEquals("ABCDEG", v.toString());
        v = new MyVisitor<String>();
        tree.traversal().levelorder(v);
        assertEquals( "DBEACG", v.toString());
    }


    @Test
    public void testMixed() {
        tree = new AVLSearchTree<String>();
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            Character c = (char) ('A' + (Math.random() * 26));
            int op = (int) (Math.random() * 2);
            switch (op) {
                case 0:
                    list.add(c.toString());
                    tree.add(c.toString());
                    break;
                case 1:
                    list.remove(c.toString());
                    tree.remove(c.toString());
                    break;
            }
        }
        assertTrue(tree.balanced());
        assertEquals(tree.size(),list.size());
        Collections.sort(list);
        StringBuilder b = new StringBuilder();
        for (String s : list) {b.append(s);};
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().inorder(v);
        assertEquals(b.toString(), v.toString());
    }
    class MyVisitor<T> implements Visitor<T> {
        private StringBuilder output;

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

}
