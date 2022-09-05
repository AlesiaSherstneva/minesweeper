import javax.swing.*;
import java.awt.*;

public class JavaSweeper extends JFrame {
    private final int COLS = 15;
    private final int ROWS = 1;
    private final int IMAGE_SIZE = 50;
    private JPanel panel;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(getImage("bomb"), 0, 0, this);
                graphics.drawImage(getImage("num1"), IMAGE_SIZE, 0, this);
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

    private Image getImage(String name) {
        ImageIcon icon = new ImageIcon("resources/image/" + name + ".png");
        return icon.getImage();
    }
}
