import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        String levelData = "00000 02030 00400 00000";

        int width = 5;
        int height = 4;

        Graph graph = new Graph(levelData, width, height);

        JFrame frame = new JFrame("Game");
        GameCanvas canvas = new GameCanvas(graph);

        frame.add(canvas);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(new GameKeyListener(graph, canvas));

        canvas.repaint();
    }
}

