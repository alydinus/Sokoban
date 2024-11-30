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
//                array = getLevelFromFile("levels/level5.sok");
                break;
            case 5:
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
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 1, 0, 0, 0, 3, 4, 2},
                {2, 0, 3, 0, 0, 0, 0, 0, 4, 2},
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

    public int getLevelNumber(){
        return level;
    }
}
