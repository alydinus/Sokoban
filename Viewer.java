import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.CardLayout;

public class Viewer {
    private Canvas canvas;
    private CardLayout cardLayout;
    private LevelChooser levelChooser;
    private JFrame frame;
    public Viewer() {
        Controller controller = new Controller(this);
        Model model = controller.getModel();
        canvas = new Canvas(model , controller);
        cardLayout = new CardLayout();
        levelChooser = new LevelChooser(this, model);
        frame = new JFrame("Sokoban Game MVC Pattern");
        frame.setSize(1200, 800);
        frame.setLocation(170,20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(cardLayout);
        frame.addKeyListener(controller);
        frame.addMouseWheelListener(controller);
        frame.addMouseListener(controller);
        frame.add(canvas, "Game");
        frame.add(levelChooser, "LevelChooser");
        showLevelChooser();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void update() {
        canvas.repaint();
    }

    public void showWonDialog() {
        JOptionPane.showMessageDialog(null, "Congratulations , you have successfully passed the level!!!");
    }

    public void showCanvas(){
        update();
        cardLayout.show(frame.getContentPane(), "Game");
        canvas.requestFocusInWindow();

    }
    public void showLevelChooser(){
        cardLayout.show(frame.getContentPane(), "LevelChooser");
    }
}
