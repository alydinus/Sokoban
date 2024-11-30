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
        won();
    }

    private void won() {
        boolean flag = true;
        for (int z = 0; z < arrayIndexes[0].length; z++) {
            int i = arrayIndexes[0][z];
            int j = arrayIndexes[1][z];
            if (desktop[i][j] != 3) {
                flag = false;
                break;
            }
        }
        if (flag) {
            viewer.showWonDialog();
            initialization();
            viewer.update();
        }
    }

    private void checkGoal() {
        for (int t = 0; t < arrayIndexes[0].length; t++) {
            int i = arrayIndexes[0][t];
            int j = arrayIndexes[1][t];
            if (desktop[i][j] == 0) {
                desktop[i][j] = 4;
            }
        }
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