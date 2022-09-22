package sweeper;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Coordinates)) {
            return super.equals(object);
        }
        Coordinates coordinates = (Coordinates) object;
        return this.x == coordinates.x && this.y == coordinates.y;
    }
}
