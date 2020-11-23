import java.awt.*;

public class DrawServer implements CommandExecutor {
	private ServerGraphics serverGraphics;
	@Override
	public String execute(String command) throws Exception {
		serverGraphics = new ServerGraphics();
		return draw(command);
	}

	private String draw(String command) {
		double size = Double.parseDouble(command);
		serverGraphics.drawRect(size, size, size, size);
		serverGraphics.setColor(Color.red);
		return serverGraphics.getTrace();
	}
}
