package sweeper;

import java.util.ArrayList;

public class Ranges {
    private static Coordinates size;
    private static ArrayList<Coordinates> allCoordinates;

    static void setSize(Coordinates size) {
        Ranges.size = size;
        allCoordinates = new ArrayList<Coordinates>();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                allCoordinates.add(new Coordinates(x, y));
            }
        }
    }

    public static void setSize(int cols, int rows) {
        setSize(new Coordinates(cols, rows));
    }

    public static Coordinates getSize() {
        return size;
    }

    public static ArrayList<Coordinates> getAllCoordinates() {
        return allCoordinates;
    }
}
