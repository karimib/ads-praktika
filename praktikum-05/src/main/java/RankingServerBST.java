import java.text.ParseException;
import java.util.List;
import java.util.function.Function;


public class RankingServerBST implements CommandExecutor {

	SortedBinaryTree<CompetitorBST> tree;

	/**
	 * Produces a list sorted by rank (time) and a list sorted by name and birthyear.
	 */

	public String execute(String command) {
		tree = new SortedBinaryTree<>();
		command.lines().map(mapToComp).forEach(c -> tree.add(c));
		return tree.printTree();
	}

	/**
	 * Maps a line string to a competitor object
	 */

	private Function<String, CompetitorBST> mapToComp = (line) -> {
		String[] input = line.split(String.valueOf(';'));
		CompetitorBST competitor;
		try {
			competitor = new CompetitorBST(
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
