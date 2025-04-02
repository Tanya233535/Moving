import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class TeleportMovingImageExample extends JPanel implements KeyListener {
    private int x = 100;
    private int y = 100;
    private Image image;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public TeleportMovingImageExample() {
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

        // Запускаем таймер для обновления положения объекта
        Timer timer = new Timer(100, e -> move());
        timer.start();
    }

    private void move() {
        if (upPressed) {
            y -= 9; // Перемещение вверх
        }
        if (downPressed) {
            y += 9; // Перемещение вниз
        }
        if (leftPressed) {
            x -= 9; // Перемещение влево
        }
        if (rightPressed) {
            x += 9; // Перемещение вправо
        }

        // Телепортация
        if (x < 0) {
            x = getWidth() - 50; // Телепортация на правую сторону
        } else if (x > getWidth() - 50) {
            x = 0; // Телепортация на левую сторону
        }

        if (y < 0) {
            y = getHeight() - 50; // Телепортация вниз
        } else if (y > getHeight() - 50) {
            y = 0; // Телепортация вверх
        }

        repaint(); // Перерисовка панели
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Не используется
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Более хитрое перемещение");
        TeleportMovingImageExample panel = new TeleportMovingImageExample();
        frame.add(panel);
        frame.setSize(800, 600); // Размеры окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
