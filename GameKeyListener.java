import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    private Graph graph;
    private GameCanvas canvas;

    public GameKeyListener(Graph graph, GameCanvas canvas) {
        this.graph = graph;
        this.canvas = canvas;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean moved = false;

        System.out.println("Key pressed: " + KeyEvent.getKeyText(e.getKeyCode()));

        // Обработка нажатия клавиш
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                System.out.println("Attempting to move Up");
                moved = graph.movePlayer("Up");
            }
            case KeyEvent.VK_DOWN -> {
                System.out.println("Attempting to move Down");
                moved = graph.movePlayer("Down");
            }
            case KeyEvent.VK_LEFT -> {
                System.out.println("Attempting to move Left");
                moved = graph.movePlayer("Left");
            }
            case KeyEvent.VK_RIGHT -> {
                System.out.println("Attempting to move Right");
                moved = graph.movePlayer("Right");
            }
            default -> System.out.println("Key not bound to any action");
        }

        // Логируем результат перемещения
        if (moved) {
            System.out.println("Player moved successfully");
            canvas.repaint(); // Обновляем отрисовку, если игрок переместился
        } else {
            System.out.println("Player could not move in the requested direction");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key released: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key typed: " + e.getKeyChar());
    }
}
