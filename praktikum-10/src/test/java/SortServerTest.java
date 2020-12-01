import org.junit.Test;
import org.junit.Assert;

import java.util.Random;

public class SortServerTest {

	private static final String SELECTION = "SELECTION";
	private static final String BUBBLE = "BUBBLE";
	private static final String INSERTION = "INSERTION";
	private static final int SIZE = 10000;
	SortServer sortServer;

	@Test
	public void testSortedBubble() throws Exception {
		sortServer = new SortServer();
		String data = generateRandomData();
		String bubbleInput = addMethod(BUBBLE, data);
		String result = sortServer.execute(bubbleInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testSortedInsertion() throws Exception {
		sortServer = new SortServer();
		String data = generateRandomData();
		String insertionInput = addMethod(INSERTION, data);
		String result = sortServer.execute(insertionInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testSortedSelection() throws Exception {
		sortServer = new SortServer();
		String data = generateRandomData();
		String selectionInput = addMethod(SELECTION, data);
		String result = sortServer.execute(selectionInput);
		Assert.assertEquals("true", result);
	}

	@Test
	public void testTime() throws Exception {
		sortServer = new SortServer();
		String data = generateRandomData();
		String bubbleInput = addMethod(BUBBLE, data);
		String insertionInput = addMethod(INSERTION, data);
		String selectionInput = addMethod(SELECTION, data);
		measureTime(BUBBLE, bubbleInput);
		measureTime(INSERTION, insertionInput);
		measureTime(SELECTION, selectionInput);
	}


	private String generateRandomData() {
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < SIZE; i++) {
			stringBuffer.append(random.nextInt(SIZE));
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

	private void measureTime(String algo, String input) throws Exception {
		int count = 0;
		long end, start = System.currentTimeMillis();
		do {
			sortServer.execute(input);
			end = System.currentTimeMillis();
			count++;
		} while (end - start < 1000);
		System.out.println("algo= " + algo + " " + "time=" + (double) (end - start) / count);
	}
}
