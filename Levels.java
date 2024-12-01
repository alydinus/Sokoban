import java.io.FileInputStream;
import java.io.IOException;

public class Levels {
    private int level;

    public Levels() {
        level = 1;
    }

    public int[][] nextLevel() {
        int[][] array = null;
        switch (level) {
            case 1:
                array = getFirstLevel();
                break;
            case 2:
                array = getSecondLevel();
                break;
            case 3:
                array = getThirdLevel();
                break;
            case 4:
                array = getFourthLevel();
                break;
            case 5:
                array = getLevelFromFile("levels/level5.sok");
                break;
            case 6:
                array = getLevelFromFile("levels/level6.sok");
                break;
            case 7:
                array = getLevelFromFile("levels/level7.sok");
                break;
            case 8:
                array = getLevelFromFile("levels/level8.sok");
                break;
            default:
                level = 1;
                array = getFirstLevel();
        }
        level = level + 1;
        return array;
    }

    private int[][] getFirstLevel() {
        return new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 1, 0, 3, 0, 3, 4, 2},
                {2, 0, 3, 0, 0, 0, 0, 0, 4, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 2, 2, 0, 0, 0, 0, 2},
                {2, 0, 0, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 3, 0, 0, 0, 0, 4, 2},
                {2, 0, 4, 0, 0, 0, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
        };
    }

    private int[][] getSecondLevel() {
        return new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 3, 0, 0, 0, 4, 0, 2},
                {2, 0, 2, 2, 2, 0, 2, 2, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 4, 2},
                {2, 0, 3, 1, 0, 3, 0, 0, 0, 2},
                {2, 0, 2, 2, 2, 0, 2, 2, 2, 2},
                {2, 0, 0, 4, 0, 3, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 4, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
        };
    }

    private int[][] getThirdLevel() {
        return new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 1, 0, 0, 0, 3, 4, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
        };
    }

    private int[][] getFourthLevel() {
        return new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 2, 4, 0, 0, 2, 4, 0, 2},
                {2, 0, 0, 2, 0, 3, 2, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 1, 0, 0, 0, 0, 0, 2},
                {2, 0, 2, 0, 0, 0, 3, 0, 0, 2},
                {2, 0, 3, 0, 0, 2, 0, 0, 0, 2},
                {2, 0, 0, 0, 2, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 2, 4, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
    }

    private int[][] getLevelFromFile(String fileName) {

        FileInputStream in = null;
        StringBuilder container = null;

        try {
            in = new FileInputStream(fileName);
            container = new StringBuilder();

            int unicode;

            while ((unicode = in.read()) != -1) {
                char symbol = (char) unicode;
                if (('0' <= symbol && symbol <= '4' || symbol == '\n')) {
                    container.append(symbol);
                }
            }
        } catch (IOException ioe) {
            System.out.println("ioe" + ioe);
            container = null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ioe) {
                System.out.println("ioe" + ioe);
            }
        }
        int[][] array = null;
        if (container != null) {
            array = convertToArray(container);
            System.out.println(array);
        }

        return array;
    }

    private int[][] convertToArray(StringBuilder container) {
        int rowCount = calculateRowCount(container);
        int[][] array = buildArray(container, rowCount);

        fillArray(container, array);

        return array;
    }


    private int calculateRowCount(StringBuilder container) {
        int rowCount = 1;

        for (int i = 0; i < container.length(); i++) {
            if (container.charAt(i) == '\n') {
                rowCount++;
            }
        }

        return rowCount;
    }

    private int[][] buildArray(StringBuilder container, int rowCount) {
        int[][] array = new int[rowCount][];

        int currentRowLength = 0;
        int currentRow = 0;

        for (int i = 0; i < container.length(); i++) {
            char element = container.charAt(i);

            if (element == '\n' || i == container.length() - 1) {
                if (i == container.length() - 1) {
                    currentRowLength = currentRowLength + 1;
                }
                array[currentRow] = new int[currentRowLength];
                currentRow = currentRow + 1;
                currentRowLength = 0;
            } else {
                currentRowLength = currentRowLength + 1;
            }
        }

        return array;
    }


    private void fillArray(StringBuilder container, int[][] array) {
        int row = 0;
        int column = 0;

        for (int i = 0; i < container.length(); i++) {
            char element = container.charAt(i);

            if (element == '\n') {
                row = row + 1;
                column = 0;
                continue;
            }

            int value = Character.getNumericValue(element);
            array[row][column] = value;
            column = column + 1;
        }
    }


    public int getLevelNumber() {
        return level;
    }

    public int[][] getLevel(String command) {
        int[][] array = null;
        switch (command) {
            case "Level 1":
                array = getFirstLevel();
                break;
            case "Level 2":
                array = getSecondLevel();
                break;
            case "Level 3":
                array = getThirdLevel();
                break;
            case "Level 4":
                array = getFourthLevel();
                break;
            case "Level 5":
                array = getLevelFromFile("levels/level5.sok");
                break;
            case "Level 6":
                array = getLevelFromFile("levels/level6.sok");
                break;
            case "Level 7":
                array = getLevelFromFile("levels/level7.sok");
                break;
            case "Level 8":
                array = getLevelFromFile("levels/level8.sok");
                break;
            default:
                array = getFirstLevel();
        }
        return array;
    }
}
