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
                {2, 0, 0, 1, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 3, 0, 3, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 4, 0, 0, 0, 3, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 4, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 4, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
    }

    private int[][] getSecondLevel() {
        return new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 1, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 3, 0, 0, 0, 0, 3, 2},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 3, 0, 0, 2},
                {2, 0, 0, 0, 0, 2, 0, 0, 0, 2},
                {2, 0, 0, 0, 2, 2, 0, 0, 0, 2},
                {2, 0, 0, 4, 2, 4, 0, 0, 4, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
    }

    private int[][] getThirdLevel() {
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

    private int[][] getFourthLevel() {
        return new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 4, 0, 4, 0, 0, 2},
                {2, 0, 4, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 3, 0, 0, 3, 0, 3, 0, 2},
                {2, 1, 0, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2,}
        };
    }

    private int[][] getLevelFromFile(String fileName) {
        FileInputStream inputStream = null;
        StringBuilder container = null;
        try {
            inputStream = new FileInputStream(fileName);
            container = new StringBuilder();
            int unicode;
            while ((unicode = inputStream.read()) != -1) {
                char symbol = (char) unicode;
                if (('0' <= symbol && symbol <= '4') || symbol == '\n') {
                    container.append(symbol);
                }
            }
        } catch (IOException ioe) {
            System.out.println("ioe" + ioe);
            container = null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ioe) {
                System.out.println("ioe" + ioe);
            }
        }
        int[][] array = null;
        if (container != null) {
            array = convertToArray(container);
        }
        return array;
    }

    private int[][] convertToArray(StringBuilder container) {
        int count = 0;
        for (int i = 0; i < container.length(); i++) {
            char element = container.charAt(i);
            if (element == '\n') {
                count = count + 1;
            }
            if (i == container.length() - 1) {
                count = count + 1;
            }
        }
        int[][] array = new int[count][];
        int index = 0;
        int indexArray = 0;
        for (int i = 0; i < container.length(); i++) {
            char element = container.charAt(i);
            if (element == '\n') {
                array[indexArray] = new int[index];
                indexArray = indexArray + 1;
                index = 0;
                continue;
            }
            index = index + 1;
            if (i == container.length() - 1) {
                array[indexArray] = new int[index];
            }
        }
        int row = 0;
        int column = 0;
        for (int z = 0; z < container.length(); z++) {
            char element = container.charAt(z);
            if (element == '\n') {
                row = row + 1;
                column = 0;
                continue;
            }
            int number = Integer.parseInt("" + element);
            array[row][column] = number;
            column = column + 1;

        }
        return array;
    }
    public int getLevelNumber(){
        return level;
    }
}
