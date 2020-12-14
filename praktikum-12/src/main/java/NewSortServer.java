public class NewSortServer implements CommandExecutor {
	private Sorter sorter;
	private String procedure;
	private Integer[] data;


	public String execute(String command) {
		parseCommand(command);

		sorter = new Sorter<Integer>()
			.withProcedure(procedure)
			.withThreshold(50)
			.sort(data);

		return Boolean.toString(sorter.isSorted(data));
	}

	private void parseCommand(String command) {
		String[] args = command.split("\\s");
		procedure = args[0];
		data = new Integer[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			data[i - 1] = Integer.valueOf(args[i]);
		}
	}

}

