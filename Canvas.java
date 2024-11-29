import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Canvas extends JPanel {

    private Model model;


    public Canvas(Model model) {

        this.model = model;
        Color backgroundColor = new Color(40, 44, 52);
        setBackground(backgroundColor);
    }
    public void paint(Graphics g)    {
        super.paint(g);
        boolean stateGame = model.getState();
        if (stateGame) {
            drawDesktop(g);
        } else {
            drawError(g);
        }
    }
    public void drawDesktop(Graphics g){

    }
    public void drawError(Graphics g){
      
    }
}
