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
        if (Box.OPENED == flag.get(coordinates)) {
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
        if (GameState.PLAY != state) {
            start();
            return true;
        }
        return false;
    }

    private void checkWinner() {
        if (GameState.PLAY == state) {
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
        if(Box.BOMB != bomb.get(coordinates)) {
            if(bomb.get(coordinates).getNumber() == flag.getCountOfFlaggedBoxesAround(coordinates)) {
                for(Coordinates around: Ranges.getCoordinatesAround(coordinates)) {
                    if(flag.get(around) == Box.CLOSED) {
                        openBox(around);
                    }
                }
            }
        }
    }

    private void openBombs(Coordinates bombedCoordinates) {
        flag.setBombedToBox(bombedCoordinates);
        for(Coordinates coordinates: Ranges.getAllCoordinates()) {
            if(bomb.get(coordinates) == Box.BOMB) {
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
