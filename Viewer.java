import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Viewer {
    private Canvas canvas;
    private Model model;

    public Viewer() {
        Controller controller = new Controller(this);
        this.model = controller.getModel();
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
//        canvas.repaint();
        System.out.println("Текущее состояние поля:");
        model.printDesktop(); // Используем сохранённый Model
    }

    public void showWonDialog() {
        JOptionPane.showMessageDialog(null, "Congratulations , you have successfully passed the level!!!");
    }
}
