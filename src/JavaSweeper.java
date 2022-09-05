import javax.swing.*;
import java.awt.*;

import sweeper.Box;

public class JavaSweeper extends JFrame {
    private final int COLS = 15;
    private final int ROWS = 1;
    private final int IMAGE_SIZE = 50;
    private JPanel panel;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                for (Box box : Box.values()) {
                    graphics.drawImage((Image) box.image,
                            box.ordinal() * IMAGE_SIZE, 0, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                COLS * IMAGE_SIZE,
                ROWS * IMAGE_SIZE));
        add(panel);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minesweeper");
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage(String name) {
        String filename = "image/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
