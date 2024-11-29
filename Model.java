public class Model {
    private Viewer viewer;
    private int[][] desktop;

    public Model(Viewer viewer){
        this.viewer = viewer;
        initialization();
    }

    private void initialization() {
    }
    public void move(String direction){
        switch (direction){
            case "Left":
                moveLeft();
                break;
            case "Up":
                moveUp();
                break;
            case "Right":
                moveRight();
                break;
            case "Down":
                moveDown();
                break;
            default:
                break;
        }
        viewer.update();
        checkGoal();
        win();
    }

    private void win() {
    }

    private void checkGoal() {
        
    }

    private void moveDown() {
    }

    private void moveRight() {
    }

    private void moveLeft() {
    }

    private void moveUp() {
    }

    public boolean getState() {
        return false;
    }
}