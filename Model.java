public class Model {
    private Viewer viewer;
    private int[][] desktop;
    private int indexX;
    private int indexY;
    private int[][] arrayIndexes;
    private boolean stateDesktop;
    private Levels levels;

    public Model(Viewer viewer){
        this.viewer = viewer;
        levels = new Levels();
        initialization();
    }

    private void initialization() {

        desktop = levels.nextLevel();

        stateDesktop = true;
        int countOne = 0;
        int countThree = 0;
        int countFour = 0;

        for(int i = 0; i < desktop.length; i++) {
            for(int j = 0; j < desktop[i].length; j++) {
                if(desktop[i][j] == 1) {
                    countOne = countOne + 1;
                    indexX = i;
                    indexY = j;
                } else if(desktop[i][j] == 3) {
                    countThree = countThree + 1;
                } else if(desktop[i][j] == 4) {
                    countFour = countFour + 1;
                }
            }
        }

        if((countOne != 1) || (countThree == 0) || (countFour == 0) || (countThree != countFour)) {
            stateDesktop = false;
            return;
        }

        arrayIndexes = new int[2][countFour];

        int a = 0;
        for(int i = 0; i < desktop.length; i++) {
            for(int j = 0; j < desktop[i].length; j++) {
                if(desktop[i][j] == 4) {
                    arrayIndexes[0][a] = i;
                    arrayIndexes[1][a] = j;
                    a = a + 1;
                }
            }
        }
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