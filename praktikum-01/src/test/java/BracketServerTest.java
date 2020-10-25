import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class BracketServerTest {
	
	BracketServer bs;
	
	@Before
	public void setUp() throws Exception {
		bs = new BracketServer();
	}

	@Test
	public void testBracket() {
		test(")",false);
		test("(",false);
		test("()",true);
		test("(()]",false);
		test("((([([])])))",true);
		test("[])",false);
		test("[(3 +3)* 35 +3]* {3 +2}",true) ;
		test("[({3 +3)* 35} +3]* {3 +2}",false);
	}

	private void test(String s, boolean b) {
		assertEquals(s, b, bs.checkBrackets(s));
	}

}
