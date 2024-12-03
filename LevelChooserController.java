import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class LevelChooserController implements ActionListener , MouseListener {
    private Model model;
    private Viewer viewer;
    private OptionPanel optionPanel;
    public LevelChooserController(Viewer viewer, Model model) {
        this.model = model;
        this.viewer = viewer;
        optionPanel = viewer.getOptionPanel();

    }
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        switch(command){
            case "Level 1":
            case "Level 2":
            case "Level 3":
            case "Level 4":
            case "Level 5":
            case "Level 6":
            case "Level 7":
            case "Level 8":
            case "Level 9":
                model.startLevel(command);
                break;
            case "Back":
//                viewer.showMenu();
                break;
            default:
                System.out.println("Invalid value");
                break;
        }
    }
    public void selectLevel(int levelNumber) {
        if (levelNumber >= 1 && levelNumber <= 9) {
            String levelName = "Level " + levelNumber;
            model.startLevel(levelName);
        } else {
            System.out.println("Invalid level: " + levelNumber);
        }
    }
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (isInsideRectangle(x, y, 215, 220, 150, 130)) {
            selectLevel(1);
        } else if (isInsideRectangle(x, y, 375, 220, 150, 130)) {
            selectLevel(2);
        } else if (isInsideRectangle(x, y, 535, 220, 150, 130)) {
            selectLevel(3);
        } else if (isInsideRectangle(x, y, 215, 375, 150, 130)) {
            selectLevel(4);
        } else if (isInsideRectangle(x, y, 375, 375, 150, 130)) {
            selectLevel(5);
        } else if (isInsideRectangle(x, y, 535, 375, 150, 130)) {
            selectLevel(6);
        } else if (isInsideRectangle(x, y, 215, 530, 150, 130)) {
            selectLevel(7);
        }else if (isInsideRectangle(x, y, 740, 360, 150, 130)) {
            selectLevel(8);
        }
        optionPanel.startTimer();
    }

    private boolean isInsideRectangle(int x, int y, int rectX, int rectY, int rectWidth, int rectHeight) {
        return x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight;
    }


    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {


    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
