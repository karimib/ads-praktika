import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;


public class NewSortServerTest {

	String SELECTION = "SELECTION";
	String BUBBLE = "BUBBLE";
	String INSERTION = "INSERTION";

	String QUICKER = "QUICKER";
	int UNSIGNED_RNG = 10000;
	int SIZE = 10000;

	String testData;
	NewSortServer newSortServer;

	@Before
	public void setUp() {
		newSortServer = new NewSortServer();
		testData = generateRandomData();
	}

	@Test
	public void testSortedBubble() throws Exception {
		String bubbleInput = addMethod(BUBBLE, testData);
		String result = newSortServer.execute(bubbleInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testSortedInsertion() throws Exception {
		String insertionInput = addMethod(INSERTION, testData);
		String result = newSortServer.execute(insertionInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testSortedSelection() throws Exception {
		String selectionInput = addMethod(SELECTION, testData);
		String result = newSortServer.execute(selectionInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testSortedQuicker() throws Exception {
		String quickerInput = addMethod(SELECTION, testData);
		String result = newSortServer.execute(quickerInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void multiRun() throws Exception {
		multiRunComparison();
	}

	private void oneRunComparsion() throws Exception {
		String bubbleInput = addMethod(BUBBLE, testData);
		String insertionInput = addMethod(INSERTION, testData);
		String selectionInput = addMethod(SELECTION, testData);


		boolean print = true;
		measureTime(BUBBLE, bubbleInput, print);
		measureTime(INSERTION, insertionInput, print);
		measureTime(SELECTION, selectionInput, print);
	}

	private void multiRunComparison() throws Exception {
		String bubbleInput = addMethod(BUBBLE, testData);
		String insertionInput = addMethod(INSERTION, testData);
		String selectionInput = addMethod(SELECTION, testData);
		String quickerInput = addMethod(QUICKER, testData);

		double a = 0;
		double b = 0;
		double c = 0;
		double d = 0;

		int RUNS = 10;
		for (int i = 0; i < RUNS; i++) {
			a += measureTime(BUBBLE, bubbleInput, false);
			b += measureTime(INSERTION, insertionInput, false);
			c += measureTime(SELECTION, selectionInput, false);
			d += measureTime(QUICKER, quickerInput, false);
		}
		a /= (RUNS * 1000);
		b /= (RUNS * 1000);
		c /= (RUNS * 1000);
		d /= (RUNS * 1000);
		System.out.println("Runs : " + RUNS + " " + "runs");
		System.out.println("Data : " + SIZE + " " + "size");
		System.out.println("Number Range (Unsigned) : " + UNSIGNED_RNG + " " + "range");
		System.out.println("***************************AVERAGE TIME PER RUN********************************");
		System.out.println(BUBBLE + " = " + a + " " + "ms");
		System.out.println(INSERTION + " = " + b + " " + "ms");
		System.out.println(SELECTION + " = " + c + " " + "ms");
		System.out.println(QUICKER + " = " + d + " " + "ms");
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
			newSortServer.execute(input);
			end = System.currentTimeMillis();
			count++;
		} while (end - start < 1000);
		{
			double res = (double) ((end - start) / count);
			if (print) {
				System.out.println("algo= " + algo + " " + "time=" + (double) (end - start) / count);
			}
			return res;
		}
	}
}
