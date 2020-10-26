import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RankingServerBSTTest {

	private String data;
	private RankingServerBST server;

	@BeforeEach
	void setUp() throws IOException {
		data = prepareData();
		server = new RankingServerBST();

	}

	@Test
	void testCorrectRanking() {
		String result = server.execute(data);
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