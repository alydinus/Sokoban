import java.awt.Graphics;
import javax.swing.JPanel;

public class GameCanvas extends JPanel {
    private Graph graph;

    public GameCanvas(Graph graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tileSize = 50;  // Размер плитки
        int offsetX = 100;  // Сдвиг по X
        int offsetY = 100;  // Сдвиг по Y

        // Отрисовываем граф
        graph.render(g, tileSize, offsetX, offsetY);
    }
}
