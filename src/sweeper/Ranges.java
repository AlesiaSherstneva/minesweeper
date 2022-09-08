package sweeper;

public class Ranges {
    private static Coordinates size;

    public static Coordinates getSize() {
        return size;
    }

    static void setSize(Coordinates size) {
        Ranges.size = size;
    }

    public static void setSize(int cols, int rows) {
        setSize(new Coordinates(cols, rows));
    }
}
