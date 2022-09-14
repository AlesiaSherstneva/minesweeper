package sweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;
    private GameState state;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coordinates(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAY;
    }

    public Box getBox(Coordinates coordinates) {
        if(Box.OPENED == flag.get(coordinates)) {
            return bomb.get(coordinates);
        }
        return flag.get(coordinates);
    }

    public GameState getState() {
        return state;
    }

    public void pressLeftButton(Coordinates coordinates) {
        openBox(coordinates);
    }

    public void pressRightButton(Coordinates coordinates) {
        flag.toggleFlaggedToBox(coordinates);
    }

    private void openBox(Coordinates coordinates) {
        switch (flag.get(coordinates)) {
            case OPENED: break;
            case FLAGGED: break;
            case CLOSED:
                switch (bomb.get(coordinates)) {
                    case ZERO: break;
                    case BOMB: break;
                    default: flag.setOpenedToBox(coordinates);
                }
        }
    }
}
