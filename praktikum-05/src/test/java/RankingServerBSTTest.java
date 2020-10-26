import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;


class RankingServerBSTTest {

	private String data;
	private RankingServerBST server;

	@BeforeEach
	void setUp() throws IOException {
		data = prepareData();
		server = new RankingServerBST();

	}

	@Test
	void testExecute() {
		String result = server.execute(data);
		System.out.println(result);
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