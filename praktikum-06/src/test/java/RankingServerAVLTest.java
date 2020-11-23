import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RankingServerAVLTest {

	private RankingServerAVL<String> server;
	private String testData;
	private List<String> testDataList;


	@BeforeEach
	void setUp() throws IOException {
		testDataList = new ArrayList<>();
		testData = prepareData();
		server = new RankingServerAVL<>();


	}

	@Test
	void testCorrectRanking() {
		String result = server.execute(testData);
		assertTrue(result.startsWith("0 Kiptum Daniel 1978 02:11:31.1\n"));
		assertTrue(result.endsWith("0 Fessler Andreas 1962 05:33:39.1\n"));

	}

	private String prepareData() throws IOException {
		File resourcesDirectory = new File("src/test/resources");

		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(resourcesDirectory + "/RangZuerich.csv"), StandardCharsets.ISO_8859_1));
		StringBuffer b = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
			b.append(line);
			b.append('\n');
		}

		return b.toString();
	}
}