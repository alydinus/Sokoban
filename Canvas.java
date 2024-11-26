import javax.swing.JPanel;
import java.awt.*;

public class Canvas extends JPanel {

    private Model model;

    public Canvas(Model model) {

        this.model = model;
        Color backgroundColor = new Color(40, 44, 52);
        setBackground(backgroundColor);
    }
    public void paint(Graphics g)    {
        super.paint(g);
    }
}
