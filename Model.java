import java.util.Stack;

public class Model {
    private Viewer viewer;
    private int[][] desktop;
    private int indexX;
    private int indexY;
    private int[][] arrayIndexes;
    private boolean stateDesktop;
    private Levels levels;
    private String direction;
    private Stack<int[][]> undoStack;
    private Stack<int[][]> redoStack;
    private OptionPanel optionPanel;
    private int steps;
    public Model(Viewer viewer) {
        this.viewer = viewer;
        levels = new Levels();
        initialization();
        direction = "Down";
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        optionPanel = viewer.getOptionPanel();
        if (optionPanel == null) {
            optionPanel = new OptionPanel(this);
            viewer.setOptionPanel(optionPanel);
        }
    }

    private void initialization() {

        desktop = levels.nextLevel();

        stateDesktop = true;
        int countOne = 0;
        int countThree = 0;
        int countFour = 0;

        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    countOne = countOne + 1;
                    indexX = i;
                    indexY = j;
                } else if (desktop[i][j] == 3) {
                    countThree = countThree + 1;
                } else if (desktop[i][j] == 4) {
                    countFour = countFour + 1;
                }
            }
        }

        if ((countOne != 1) || (countThree == 0) || (countFour == 0) || (countThree != countFour)) {
            stateDesktop = false;
            return;
        }

        arrayIndexes = new int[2][countFour];

        int a = 0;
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 4) {
                    arrayIndexes[0][a] = i;
                    arrayIndexes[1][a] = j;
                    a = a + 1;
                }
            }
        }
    }

    public void move(String direction) {
        saveState();
        this.direction = direction;
        switch (direction) {
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
        steps++;
        optionPanel.countSteps();
        checkGoal();
        viewer.update();
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

    private void moveLeft() {
        if (desktop[indexX][indexY - 1] == 3) {
            if (desktop[indexX][indexY - 2] == 0 || desktop[indexX ][indexY - 2] == 4) {
                desktop[indexX][indexY - 1] = 0;
                desktop[indexX][indexY - 2] = 3;
            }
        }

        if (desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY - 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveRight() {
        if (desktop[indexX][indexY + 1] == 3) {
            if (desktop[indexX][indexY + 2] == 0 || desktop[indexX][indexY + 2] == 4) {
                desktop[indexX][indexY + 1] = 0;
                desktop[indexX][indexY + 2] = 3;
            }
        }

        if (desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY + 1] == 4) {
            desktop[indexX][indexY] = 0;
            indexY = indexY + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveDown() {
        if (desktop[indexX + 1][indexY] == 3) {
            if (desktop[indexX + 2][indexY] == 0 || desktop[indexX + 2][indexY] == 4) {
                desktop[indexX + 1][indexY] = 0;
                desktop[indexX + 2][indexY] = 3;
            }
        }

        if (desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX = indexX + 1;
            desktop[indexX][indexY] = 1;
        }
    }

    private void moveUp() {
        if (desktop[indexX - 1][indexY] == 3) {
            if (desktop[indexX - 2][indexY] == 0 || desktop[indexX - 2][indexY] == 4) {
                desktop[indexX - 1][indexY] = 0;
                desktop[indexX - 2][indexY] = 3;
            }
        }

        if (desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX = indexX - 1;
            desktop[indexX][indexY] = 1;
        }
    }

    public boolean getState() {
        return stateDesktop;
    }
    public void chooseLevel(int[][] level) {
        level = desktop;
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

    public int[][] getDesktop() {
        return desktop;
    }
    public String getDirection() {
        return direction;
    }

    public void startLevel(String command) {
        desktop = levels.getLevel(command);
        chooseLevel(desktop);
        viewer.showCanvas();
    }
    public void undoMove() {
        if (0 < undoStack.size()) {
            redoStack.push(copyField(desktop));
            desktop = undoStack.pop();
            updatePlayerPosition();
            steps--;
        }
    }

    public void redoMove() {
        if (0 < redoStack.size()) {
            undoStack.push(copyField(desktop));
            desktop = redoStack.pop();
            updatePlayerPosition();
            steps++;
        }
    }

    private void saveState() {
        if (50 <= undoStack.size()) {
            undoStack.remove(0);
        }
        undoStack.push(copyField(desktop));
        redoStack.clear();
    }

    private int[][] copyField(int[][] field) {
        int[][] copy = new int[field.length][];
        for (int i = 0; i < field.length; i++) {
            copy[i] = new int[field[i].length];
            for (int j = 0; j < field[i].length; j++) {
                copy[i][j] = field[i][j];
            }
        }
        return copy;
    }

    private void updatePlayerPosition() {
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    indexX = i;
                    indexY = j;
                    break;
                }
            }
        }

        viewer.update();
    }
    public int getSteps(){
        return steps;
    }
}