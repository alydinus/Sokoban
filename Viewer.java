import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Viewer {
    private Canvas canvas;
    private CardLayout cardLayout;
    private LevelChooser levelChooser;
    private JFrame frame;
    private JPanel mainPanel;
    private OptionPanel optionPanel;

    public Viewer() {

        Controller controller = new Controller(this);
        Model model = controller.getModel();

        optionPanel = new OptionPanel(model);
        canvas = new Canvas(model, controller);
        cardLayout = new CardLayout();
        levelChooser = new LevelChooser(this, model);

        mainPanel = new JPanel(cardLayout);

        MenuPanel menuPanel = new MenuPanel(cardLayout, mainPanel);

        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(levelChooser, "LevelChooser");
        mainPanel.add(canvas, "Game");

        canvas.add(optionPanel);

        Image backgroundImage = new ImageIcon("images/background.jpg").getImage();

        frame = new JFrame("Sokoban Game MVC Pattern");
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.addKeyListener(controller);
        frame.addMouseWheelListener(controller);
        frame.addMouseListener(controller);
        frame.setResizable(false);
        frame.setVisible(true);

        showMenu();
    }

    public void update() {
        canvas.repaint();
    }

    public void showWonDialog() {
        JOptionPane.showMessageDialog(null, "Congratulations, you have successfully passed the level!!!");
    }

    public void showCanvas() {
        update();
        cardLayout.show(mainPanel, "Game");
        canvas.requestFocusInWindow();
    }

    public void showLevelChooser() {
        cardLayout.show(mainPanel, "LevelChooser");
    }

    public void showMenu() {
        cardLayout.show(mainPanel, "Menu");
    }
    public OptionPanel getOptionPanel() {
        return optionPanel;
    }
    public void setOptionPanel(OptionPanel optionPanel) {
        this.optionPanel = optionPanel;
    }
}
