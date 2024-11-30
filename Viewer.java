import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Viewer {
    private Canvas canvas;

    public Viewer() {
        Controller controller = new Controller(this);
        Model model = controller.getModel();
        canvas = new Canvas(model);
        JFrame frame = new JFrame("Sokoban Game MVC Pattern");
        frame.setSize(900, 700);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add("Center", canvas);
        frame.addKeyListener(controller);
        frame.addMouseWheelListener(controller);
        frame.addMouseListener(controller);
        frame.setVisible(true);
    }
    public void update() {
        canvas.repaint();
    }

    public void showWonDialog() {
        JOptionPane.showMessageDialog(null, "Congratulations , you have successfully passed the level!!!");
    }
}
