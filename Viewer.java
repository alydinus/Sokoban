import javax.swing.JFrame;

public class Viewer {

    public Viewer() {
        Controller controller = new Controller(this);
        Model model = controller.getModel();
        Canvas canvas = new Canvas(model);

        JFrame frame = new JFrame("Sokaban Game MVC Pattern");
        frame.setSize(800, 800);
        frame.setLocation(300, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
