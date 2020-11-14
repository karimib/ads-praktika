import org.junit.Test;
//@Author Patrick Egli
import static org.junit.Assert.assertEquals;



public class RouteServerTest {

	private static String INPUT =
			"Winterthur Zürich 25\n" +
					"Zürich Bern 126\n" +
					"Zürich Genf 277\n" +
					"Zürich Luzern 54\n" +
					"Zürich Chur 121\n" +
					"Zürich Berikon 16\n" +
					"Bern Genf 155\n" +
					"Genf Lugano 363\n" +
					"Lugano Luzern 206\n" +
					"Lugano Chur 152\n" +
					"Chur Luzern 146\n" +
					"Luzern Bern 97\n" +
					"Bern Berikon 102\n" +
					"Luzern Berikon 41";

	@Test
	public void testShortestPath() throws Exception {
		RouteServer routeServer = new RouteServer();
		String shortestPath = routeServer.execute(INPUT);
		System.out.println(shortestPath);
		String[] pathCities = shortestPath.split("->");

		assertEquals("Winterthur", pathCities[0]);
		assertEquals("Zürich", pathCities[1]);
		assertEquals("Luzern", pathCities[2]);
		assertEquals("Lugano", pathCities[3]);


	}

}