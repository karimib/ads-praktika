import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import java.util.*;

public class ListTest {

	MyList list;

	@Before
	public void setUp() throws Exception {
		list = new MyList<>();
	}

	@Test
	public void testAdd() {
		list.clear();
		list.add("A");
		Object o = list.get(0);
		assertEquals("A", o);
	}

	@Test
	public void testAdd2() {
		list.clear();
		list.add("A");
		list.add("B");
		Object o = list.get(0);
		assertEquals("A", o);
		o = list.get(1);
		assertEquals("B", o);
	}

	@Test
	public void testAdd3() {
		list.clear();
		list.add("A");
		list.add("B");
		list.add("C");
		Object o = list.get(0);
		assertEquals("A", o);
		o = list.get(1);
		assertEquals("B", o);
		o = list.get(2);
		assertEquals("C", o);
	}


	@Test
	public void testSize() {
		list.clear();
		assertEquals(0, list.size());
		testAdd2();
		assertEquals(2, list.size());
	}

	@Test
	public void testRemove() {
		list.clear();
		list.add("A");
		list.remove("A");
		assertEquals(0, list.size());
		list.add("A");
		list.remove("B");
		assertEquals(1, list.size());
		list.remove("A");
		assertEquals(0, list.size());
	}

	@Test
	public void testMixed() {
		list.clear();
		List list2 = new LinkedList();
		for (int i = 0; i < 1000; i++) {
			Character c = (char) ('A' + (Math.random() * 26));
			int op = (int) (Math.random() * 2);
			switch (op) {
				case 0:
					list.add(c);
					list2.add(c);
					break;
				case 1:
					list.remove(c);
					list2.remove(c);
					break;
			}
		}
		assertEquals(list2.size(), list.size());
		for (int i = 0; i < list.size(); i++) {
			char c1 = (char) list.get(i);
			char c2 = (char) list2.get(i);
			assertEquals(c1, c2);
		}
	}

}
