import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class MovingImageExample extends JPanel implements KeyListener {
    private int x = 100;
    private int y = 100;
    private Image image;

    public MovingImageExample() {
        // Открываем диалог для выбора файла изображения
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите изображение");
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            image = Toolkit.getDefaultToolkit().getImage(fileToOpen.getAbsolutePath());
        } else {
            System.exit(0); // Закрываем программу, если изображение не выбрано
        }

        // Устанавливаем фокус на панель
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, x, y, 50, 50, this); // Отрисовка изображения
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Обработка нажатия клавиши
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Обработка отпускания клавиши
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (y - 50 >= 0) {
                    y -= 50; // Перемещение вверх
                }
                break;
            case KeyEvent.VK_DOWN:
                if (y + 50 <= getHeight() - 50) {
                    y += 50; // Перемещение вниз
                }
                break;
            case KeyEvent.VK_LEFT:
                if (x - 50 >= 0) {
                    x -= 50; // Перемещение влево
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (x + 50 <= getWidth() - 50) {
                    x += 50; // Перемещение вправо
                }
                break;
        }
        repaint(); // Перерисовка панели
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Не используется
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Простое перемещение");
        MovingImageExample panel = new MovingImageExample();
        frame.add(panel);
        frame.setSize(800, 600); // Размеры окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}