
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
//                array = getLevelFromFile("levels/level5.sok");
                break;
            case 6:
//                array = getLevelFromFile("levels/level6.sok");
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
                {}
        };
    }

    private int[][] getSecondLevel() {
        return new int[][]{
                {}
        };
    }

    private int[][] getThirdLevel() {
        return new int[][]{
                {}
        };
    }

    private int[][] getFourthLevel() {
        return new int[][]{
                {}
        };
    }

    public int getLevelNumber(){
        return level;
    }
}
