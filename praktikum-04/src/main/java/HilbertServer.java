public class HilbertServer implements CommandExecutor {

	private Turtle turtle;

	@Override
	public String execute(String command) throws Exception {
		turtle = new Turtle(0.25, 0.25);
		int depth = Integer.parseInt(command);
		double dist = 0.8 / (Math.pow(2, depth + 1) - 1);
		hilbert(depth, dist, -90);
		return turtle.getTrace();
	}


	private void hilbert(int depth, double dist, double angle) {
		if (depth == 0) {
			return;
		}
		turtle.turn(-angle);
		hilbert(depth - 1, dist, -angle);
		turtle.move(dist);
		turtle.turn(angle);
		hilbert(depth - 1, dist, angle);
		turtle.move(dist);
		hilbert(depth - 1, dist, angle);
		turtle.turn(angle);
		turtle.move(dist);
		hilbert(depth - 1, dist, -angle);
		turtle.turn(-angle);
	}
}
