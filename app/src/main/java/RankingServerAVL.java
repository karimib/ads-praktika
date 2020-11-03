import java.text.ParseException;
import java.util.function.Function;

public class RankingServerAVL<T> implements CommandExecutor {

	private AVLSearchTree<CompetitorAVL> tree;

	/**
	 * Produces a list sorted by rank (time) and a list sorted by name and birthyear.
	 */

	public String execute(String command) {
		tree = new AVLSearchTree<>();
		CompetitorAVLVisitor<CompetitorAVL> rankedByTime = new CompetitorAVLVisitor<>();
		//Map input file lines to CompetitorAVL objects and add them to the tree
		command.lines().map(mapToComp).forEach(c -> tree.add(c));
		//Inorder traversal gives us Competitors ranked by time
		tree.traversal().inorder(rankedByTime);
		return rankedByTime.toString();
	}

	/**
	 * Maps a line string to a competitor object
	 */

	private Function<String, CompetitorAVL> mapToComp = (line) -> {
		String[] input = line.split(String.valueOf(';'));
		CompetitorAVL competitor;
		try {
			competitor = new CompetitorAVL(
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

	/**
	 * Auxiliary visitor class
	 */
	private static class CompetitorAVLVisitor<T> implements Visitor<T> {
		private StringBuilder output;

		CompetitorAVLVisitor() {
			output = new StringBuilder();
		}

		public void visit(T s) {
			output.append(s.toString());
		}

		public String toString() {
			return output.toString();
		}
	}


}
