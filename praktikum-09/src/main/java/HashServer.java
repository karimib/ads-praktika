import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HashServer implements CommandExecutor {

	private Map<Integer, Competitor> map = new HashMap<Integer, Competitor>();

	@Override
	public String execute(String command) throws Exception {
		if (command.toUpperCase().startsWith("GET")) {
		}
		else {
			command.lines().map(mapToComp).forEach(c -> map.put(c.hashCode(), c));
		}
		return command;
	}



	private Function<String, Competitor> mapToComp = (line) -> {
		String[] input = line.split(String.valueOf(';'));
		Competitor competitor;
		try {
			competitor = new Competitor(
					Integer.parseInt(input[0]),
					input[1],
					Integer.parseInt(input[2]),
					input[3],
					input[4]
			);
		} catch (ParseException e) {
			throw new RuntimeException("Parsing failed at: " + line);
		}
		return competitor;
	};
}
