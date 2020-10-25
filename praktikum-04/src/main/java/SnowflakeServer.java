public class SnowflakeServer implements CommandExecutor {

    private Turtle turtle;

    @Override
    public String execute(String command) {
        turtle = new Turtle();
        int stage = Integer.parseInt(command);
        schneeflocke(stage, 1);
        return turtle.getTrace();
    }

    private void schneeflocke(int stage, double dist) {
        if (stage == 0) {
            turtle.move(dist);
        } else {
            stage--;
            dist /= 3;
            schneeflocke(stage, dist);
            turtle.turn(60);
            schneeflocke(stage, dist);
            turtle.turn(-120);
            schneeflocke(stage, dist);
            turtle.turn(60);
            schneeflocke(stage, dist);
        }
    }


    private void drawSnowFlake(int stage, double dist) {
        for(int i = 0; i < 6; i++) {
            schneeflocke(stage, 1);
            turtle.turn(60);
        }
    }
}
