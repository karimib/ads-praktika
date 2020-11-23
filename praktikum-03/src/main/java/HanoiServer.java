
import java.util.List;

public class HanoiServer implements CommandExecutor {

	private List<String> result;

	public String execute(String command) {
		String[] lines = command.split(",");
		hanoi(Integer.parseInt(lines[0]), lines[1], lines[2], lines[3]);
		return result.toString();
	}

	/**
	 * Calculates and Prints the solution to solve the "Towers of Hanoi" puzzle recursively.
	 *
	 * @param n    the amount of disks on stack "from"
	 * @param from the stack "from"
	 * @param to   the stack "to"
	 * @param help the stack "help"
	 */
	private void hanoi(int n, String from, String to, String help) {
		if (n > 0) {
			hanoi(n - 1, from, help, to);
			result.add("Move no: " + n + "from " + from + " to" + to);
			hanoi(n - 1, help, to, from);
		}
	}
}