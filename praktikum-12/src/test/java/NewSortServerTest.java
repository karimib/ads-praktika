import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.nanoTime;


public class NewSortServerTest {

	String QUICK = "QUICK";
	String SELECTION = "SELECTION";
	String BUBBLE = "BUBBLE";
	String INSERTION = "INSERTION";

	String QUICKER = "QUICKER";

	String QUICKER_PARALLEL = "QUICKER_PARALLEL";
	int UNSIGNED_RNG = 1000000;
	int SIZE = 1000;

	String testData;
	NewSortServer newSortServer;

	@Before
	public void setUp() {
		newSortServer = new NewSortServer();
		testData = generateRandomData(SIZE);
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
	public void testSortedQuick() throws Exception {
		String quickInput = addMethod(QUICK, testData);
		String result = newSortServer.execute(quickInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testSortedQuicker() throws Exception {
		String quickerInput = addMethod(QUICKER, testData);
		String result = newSortServer.execute(quickerInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testSortedParallelQuicker() throws Exception {
		String parallelQuickerInput = addMethod(QUICKER_PARALLEL, testData);
		String result = newSortServer.execute(parallelQuickerInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void multiRunComparison() throws Exception {
		String bubbleInput = addMethod(BUBBLE, testData);
		String insertionInput = addMethod(INSERTION, testData);
		String selectionInput = addMethod(SELECTION, testData);
		String quickInput = addMethod(QUICK, testData);
		String quickerInput = addMethod(QUICKER, testData);
		String parallelQuickerInput = addMethod(QUICKER_PARALLEL, testData);

		final int RUNS = 10;
		double a = 0;
		double b = 0;
		double c = 0;
		double d = 0;
		double e = 0;
		double f = 0;
		double avgA = 0;
		double avgB = 0;
		double avgC = 0;
		double avgD = 0;
		double avgE = 0;
		double avgF = 0;

		long end, start = nanoTime();
		for (int i = 0; i < RUNS; i++) {
			System.out.println("RUN " + i + "--------------------------------------");
			a += measureTime(BUBBLE, bubbleInput, true);
			b += measureTime(INSERTION, insertionInput, true);
			c += measureTime(SELECTION, selectionInput, true);
			d += measureTime(QUICK, quickerInput, true);
			e += measureTime(QUICKER, quickerInput, true);
			f += measureTime(QUICKER_PARALLEL, parallelQuickerInput, true);
			System.out.println();
		}
		end = nanoTime();


		avgA = a / RUNS;
		avgB = b / RUNS;
		avgC = c / RUNS;
		avgD = d / RUNS;
		avgE = e / RUNS;
		avgF = f / RUNS;

		System.out.println("SUMMARY");
		System.out.println("No. Runs : " + RUNS);
		System.out.println("Data Size : " + SIZE);
		System.out.println("Number Range (Unsigned) : " + "[0 -" + UNSIGNED_RNG + "]");
		System.out.println("Cores : " + Runtime.getRuntime().availableProcessors());
		System.out.println();
		System.out.println("STATISTICS");
		System.out.println(summary(BUBBLE, a, avgA));
		System.out.println(summary(INSERTION, b, avgB));
		System.out.println(summary(SELECTION, c, avgC));
		System.out.println(summary(QUICK, d, avgD));
		System.out.println(summary(QUICKER, e, avgE));
		System.out.println(summary(QUICKER_PARALLEL, f, avgF));

		long total = end - start;
		total /= 1000000;
		System.out.println("Total execution time : " + total + " " + "ms");
	}


	private String summary(String procedure, double total, double avg) {
		return procedure + "\n" + "Total : " + total + " " + "ms" + "\n" + "Avg : " + avg + " " + "ms";
	}

	private String generateRandomData(int size) {
		Random random = new Random();
		return IntStream.range(0, size)
			.mapToObj(i -> String.valueOf(random.nextInt(UNSIGNED_RNG)))
			.collect(Collectors.joining("\n"));
	}

	private String addMethod(String option, String input) {
		return option + "\n" + input;
	}

	private Long measureTime(String procedure, String input, boolean print) {
		int count = 0;
		long end, start = nanoTime();
		newSortServer.execute(input);
		end = nanoTime();
		long res = end - start;

		res /= 1000000;
		if (print) {
			System.out.println("procedure= " + procedure + " " + "time= " + res + " ms");
		}
		return res;

	}
}
