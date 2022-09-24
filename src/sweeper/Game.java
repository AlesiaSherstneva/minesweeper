package sweeper;

public class Game {
    private final Bomb bomb;
    private final Flag flag;
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
        if (flag.get(coordinates) == Box.OPENED) {
            return bomb.get(coordinates);
        }
        return flag.get(coordinates);
    }

    public void pressLeftButton(Coordinates coordinates) {
        if (isGameOver()) return;
        openBox(coordinates);
        checkWinner();
    }

    public void pressRightButton(Coordinates coordinates) {
        if (GameState.PLAY == state) {
            flag.toggleFlaggedToBox(coordinates);
        } else {
            start();
        }
    }

    public GameState getState() {
        return state;
    }

    public int getTotalBombs() {
        return bomb.getTotalBombs();
    }

    public int getTotalFlagged() {
        return flag.getTotalFlagged();
    }

    private boolean isGameOver() {
        if (state != GameState.PLAY) {
            start();
            return true;
        }
        return false;
    }

    private void checkWinner() {
        if (state == GameState.PLAY) {
            if (flag.getTotalClosed() == bomb.getTotalBombs()) {
                state = GameState.WON;
                flag.setFlaggedToLastClosedBoxes();
            }
        }
    }

    private void openBox(Coordinates coordinates) {
        switch (flag.get(coordinates)) {
            case OPENED:
                setOpenedToClosedBoxesAroundNumber(coordinates);
                break;
            case FLAGGED:
                break;
            case CLOSED:
                switch (bomb.get(coordinates)) {
                    case ZERO:
                        openBoxesAroundZero(coordinates);
                        break;
                    case BOMB:
                        openBombs(coordinates);
                        break;
                    default:
                        flag.setOpenedToBox(coordinates);
                        break;
                }
        }
    }

    private void setOpenedToClosedBoxesAroundNumber(Coordinates coordinates) {
        if (bomb.get(coordinates) != Box.BOMB) {
            if (bomb.get(coordinates).getNumber() == flag.getCountOfFlaggedBoxesAround(coordinates)) {
                for (Coordinates around : Ranges.getCoordinatesAround(coordinates)) {
                    if (flag.get(around) == Box.CLOSED && bomb.get(around) != Box.BOMB) {
                        openBox(around);
                    }
                }
            }
        }
    }

    private void openBombs(Coordinates bombedCoordinates) {
        flag.setBombedToBox(bombedCoordinates);
        for (Coordinates coordinates : Ranges.getAllCoordinates()) {
            if (bomb.get(coordinates) == Box.BOMB) {
                flag.setOpenedToClosedBox(coordinates);
            } else {
                flag.SetNobombToFlaggedBombs(coordinates);
            }
        }
        state = GameState.BOMBED;
    }

    private void openBoxesAroundZero(Coordinates coordinates) {
        flag.setOpenedToBox(coordinates);
        for (Coordinates around : Ranges.getCoordinatesAround(coordinates)) {
            openBox(around);
        }
    }
}
