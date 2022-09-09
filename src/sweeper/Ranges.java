package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
    private static Coordinates size;
    private static ArrayList<Coordinates> allCoordinates;
    private static Random random = new Random();

    static void setSize(Coordinates size) {
        Ranges.size = size;
        allCoordinates = new ArrayList<Coordinates>();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                allCoordinates.add(new Coordinates(x, y));
            }
        }
    }

    public static Coordinates getSize() {
        return size;
    }

    public static ArrayList<Coordinates> getAllCoordinates() {
        return allCoordinates;
    }

    public static boolean inRange (Coordinates coordinates) {
        return coordinates.x >= 0 && coordinates.x < size.x &&
                coordinates.y >= 0 && coordinates.y < size.y;
    }

    static Coordinates getRandomCoordinates() {
        return new Coordinates(random.nextInt(size.x), random.nextInt(size.y));
    }
}
