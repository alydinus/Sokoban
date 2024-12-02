import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Controller implements KeyListener , MouseListener , MouseWheelListener {
    private Model model;

    public Controller(Viewer viewer) {
        model = new Model(viewer);
    }

    public Model getModel() {
        return model;
    }


    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        String direction = "";
        switch (keyCode) {
            case 38:
                direction = "Up";
                break;
            case 40:
                direction = "Down";
                break;
            case 37:
                direction = "Left";
                break;
            case 39:
                direction = "Right";
                break;
            case 90:
                model.undoMove();
                return;
            case 89:
                model.redoMove();
                return;
            default:
                return;
        }

        model.move(direction);
    }
    public void keyReleased(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (isInsideRectangle(x, y, 50, 650, 150, 50)) {
            model.undoMove();
        } else if (isInsideRectangle(x, y, 250, 650, 150, 50)) {
            model.redoMove();
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseWheelMoved(MouseWheelEvent e) {

    }
    private boolean isInsideRectangle(int x, int y, int rectX, int rectY, int rectWidth, int rectHeight) {
        return x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight;
    }
}
