import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Canvas extends JPanel {

    private Model model;
    private GameModel graph;

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
        int start = 50;
        int x = start;
        int y = start;
        int width = 50;
        int height = 50;

        GameModel desktop = model.getDesktop();
        Node currentNode = desktop.getStartNode();
        Node newLineNode = desktop.getStartNode().getDown();
        while (true){
            if (currentNode.getType().equals("Player")){
                g.setColor(Color.MAGENTA);
                g.fillRect(x, y, width, height);
            } else if (currentNode.getType().equals("Wall")){
                g.setColor(Color.DARK_GRAY);
                g.fillRect(x, y, width, height);
            } else if (currentNode.getType().equals("Box")){
                g.setColor(Color.RED);
                g.fillRect(x, y, width, height);
            } else if (currentNode.getType().equals("Goal")){
                g.setColor(Color.ORANGE);
                g.fillRect(x, y, width, height);
            } else if (currentNode.getType().equals("Floor")){
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x, y, width, height);
            }else{
                g.setColor(Color.YELLOW);
                g.fillRect(x, y, width, height);
            }
            if (currentNode.getRight() == null && currentNode.getDown() == null){
                break;
            }
            if (currentNode.getRight() != null){
                currentNode = currentNode.getRight();
                x = x + width;
            } else {
                currentNode = newLineNode;
                newLineNode = newLineNode.getDown();
                x = start;
                y = y + height;
            }
        }
    }
    public void drawError(Graphics g){

    }
}
