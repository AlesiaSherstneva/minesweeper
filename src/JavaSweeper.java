import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import sweeper.*;
import sweeper.Box;

public class JavaSweeper extends JFrame {
    private Game game;

    private int cols = 9;
    private int rows = 9;
    private int bombs = 10;
    private final int IMAGE_SIZE = 50;

    private JPanel panel;
    private JLabel label;
    private JMenuBar bar;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        game = new Game(cols, rows, bombs);
        game.start();
        setImages();
        initPanel();
        initLabel();
        initBar();
        initFrame();
    }

    private void initBar() {
        bar = new JMenuBar();

        Font font = new Font("Tahoma", Font.PLAIN, 18);
        JMenu level = new JMenu("Choose level");
        level.setFont(font);

        JRadioButtonMenuItem juniorLevel = new JRadioButtonMenuItem("Junior");
        juniorLevel.setSelected(true);
        juniorLevel.setFont(font);

        JRadioButtonMenuItem middleLevel = new JRadioButtonMenuItem("Middle");
        middleLevel.setFont(font);

        JRadioButtonMenuItem seniorLevel = new JRadioButtonMenuItem("Senior");
        seniorLevel.setFont(font);

        ButtonGroup levels = new ButtonGroup();
        levels.add(juniorLevel);
        levels.add(middleLevel);
        levels.add(seniorLevel);

        ActionListener juniorListener = e -> {
            rows = 9;
            cols = 9;
            bombs = 10;
            changeLevel();
        };
        juniorLevel.addActionListener(juniorListener);

        ActionListener middleListener = e -> {
            rows = 16;
            cols = 16;
            bombs = 40;
            changeLevel();
        };
        middleLevel.addActionListener(middleListener);

        ActionListener seniorListener = e -> {
            rows = 16;
            cols = 30;
            bombs = 99;
            changeLevel();
        };
        seniorLevel.addActionListener(seniorListener);

        level.add(juniorLevel);
        level.addSeparator();
        level.add(middleLevel);
        level.addSeparator();
        level.add(seniorLevel);

        bar.add(level);
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
        setDefaultLookAndFeelDecorated(true);
        setTitle("Minesweeper");
        setJMenuBar(bar);
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
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(filename)));
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
                if (game.getTotalFlagged() == 0) {
                    return "Welcome!";
                } else {
                    return "Think twice! Flagged " + game.getTotalFlagged() + " of "
                            + game.getTotalBombs() + " bombs.";
                }
        }
    }

    private void changeLevel() {
        game = new Game(cols, rows, bombs);
        game.start();
        initPanel();
        pack();
        setLocationRelativeTo(null);
    }
}
