import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Coordinates;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {
    private Game game;

    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;
    private JPanel panel;
    private JLabel label;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initLabel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                for (Coordinates coordinates : Ranges.getAllCoordinates()) {
                    graphics.drawImage((Image) game.getBox(coordinates).image,
                            coordinates.x * IMAGE_SIZE,
                            coordinates.y * IMAGE_SIZE,
                            this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                int x = event.getX() / IMAGE_SIZE;
                int y = event.getY() / IMAGE_SIZE;
                Coordinates coordinates = new Coordinates(x, y);
                switch (event.getButton()) {
                    case MouseEvent.BUTTON1:
                        game.pressLeftButton(coordinates);
                        break;
                    case MouseEvent.BUTTON3:
                        game.pressRightButton(coordinates);
                        break;
                    case MouseEvent.BUTTON2:
                        game.start();
                        break;
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private void initLabel() {
        label = new JLabel(getMessage());
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(label, BorderLayout.SOUTH);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
        setIconImage(getImage("icon"));
    }

    private Image getImage(String name) {
        String filename = "image/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private String getMessage() {
        switch (game.getState()) {
            case BOMBED:
                return "BA-DA-BOOM! You lost!";
            case WON:
                return "CONGRATULATIONS! You won!";
            case PLAY:
            default:
                if(game.getTotalFlagged() == 0) {
                    return "Welcome!";
                } else {
                    return "Think twice! Flagged " + game.getTotalFlagged() + " of "
                            + game.getTotalBombs() + " bombs.";
                }
        }
    }
}
