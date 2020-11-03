import java.text.ParseException;
import java.util.List;
import java.util.function.Function;


public class RankingServerBST implements CommandExecutor {

	private SortedBinaryTree<CompetitorBST> tree;

	/**
	 * Produces a list sorted by rank (time) and a list sorted by name and birthyear.
	 */

	public String execute(String command) {
		tree = new SortedBinaryTree<>();
		CompetitorBSTVisitor<CompetitorBST> rankedByTime = new CompetitorBSTVisitor<>();
		//Map input file lines to CompetitorBST objects and add them to the tree
		command.lines().map(mapToComp).forEach(c -> tree.add(c));
		//Inorder traversal gives us Competitors ranked by time
		tree.traversal().inorder(rankedByTime);
		return rankedByTime.toString();
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

	/**
	 * Auxiliary visitor class
	 */
	private static class CompetitorBSTVisitor<T> implements Visitor<T> {
		private StringBuilder output;

		CompetitorBSTVisitor() {
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
