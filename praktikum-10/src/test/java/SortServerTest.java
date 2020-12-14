import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.Random;


public class SortServerTest {

	String SELECTION = "SELECTION";
	String BUBBLE = "BUBBLE";
	String INSERTION = "INSERTION";
	int UNSIGNED_RNG = 1000;
	int SIZE = 1000;

	String data;
	SortServer sortServer;

	@Before
	public void setUp() {
		sortServer = new SortServer();
		data = generateRandomData();
	}

	@Test
	public void testSortedBubble() throws Exception {
		String bubbleInput = addMethod(BUBBLE, data);
		String result = sortServer.execute(bubbleInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testSortedInsertion() throws Exception {
		String insertionInput = addMethod(INSERTION, data);
		String result = sortServer.execute(insertionInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testSortedSelection() throws Exception {
		String selectionInput = addMethod(SELECTION, data);
		String result = sortServer.execute(selectionInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void multiRun() throws Exception {
		multiRunComparison();
	}
	private void oneRunComparsion() throws Exception {
		String bubbleInput = addMethod(BUBBLE, data);
		String insertionInput = addMethod(INSERTION, data);
		String selectionInput = addMethod(SELECTION, data);


		boolean print = true;
		measureTime(BUBBLE, bubbleInput, print);
		measureTime(INSERTION, insertionInput, print);
		measureTime(SELECTION, selectionInput, print);
	}

	private void multiRunComparison() throws Exception {
		String bubbleInput = addMethod(BUBBLE, data);
		String insertionInput = addMethod(INSERTION, data);
		String selectionInput = addMethod(SELECTION, data);

		double a = 0;
		double b = 0;
		double c = 0;
		int RUNS = 10;
		for (int i = 0; i < RUNS; i++) {
			a += measureTime(BUBBLE, bubbleInput, false);
			b += measureTime(INSERTION, insertionInput, false);
			c += measureTime(SELECTION, selectionInput, false);
		}
		a /= RUNS;
		b /= RUNS;
		c /= RUNS;
		System.out.println(BUBBLE + " = " + a + " " + "ms");
		System.out.println(INSERTION + " = " + b + " " + "ms");
		System.out.println(SELECTION + " = " + c + " " + "ms");
	}


	private String generateRandomData() {
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < SIZE; i++) {
			stringBuffer.append(random.nextInt(UNSIGNED_RNG));
			stringBuffer.append("\n");
		}
		return stringBuffer.toString();
	}

	private String addMethod(String option, String input) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(option);
		stringBuffer.append("\n");
		stringBuffer.append(input);
		return stringBuffer.toString();
	}

	private Double measureTime(String algo, String input, boolean print) {
		int count = 0;
		long end, start = System.currentTimeMillis();
		do {
			sortServer.execute(input);
			end = System.currentTimeMillis();
			count++;
		} while (end - start < 1000);
		{
			double res = (double) ((end - start) / count);
			if(print) {
				System.out.println("algo= " + algo + " " + "time=" + (double) (end - start) / count);
			}
			return res;
		}
	}
}
