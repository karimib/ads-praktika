import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class LabyrinthServerTest {

	LabyrinthServer labyrinthServer;

	@Before
	public void setup(){
		labyrinthServer = new LabyrinthServer();
	}

	@Test
	public void testExec() throws Exception {
		String input = prepareData();
		String result = labyrinthServer.execute(input);
		System.out.println(result);
	}

	private String prepareData() throws IOException {
		File resourcesDirectory = new File("src/test/resources");

		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(resourcesDirectory + "/Labyrinth.txt"), StandardCharsets.ISO_8859_1));
		StringBuffer b = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
			b.append(line);
			b.append('\n');
		}

		return b.toString();
	}

}