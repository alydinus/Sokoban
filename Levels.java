import java.io.FileInputStream;
import java.io.IOException;

public class Levels {
    private int level;
    public Levels() {
        level = 5;
    }
    public String nextLevel() {
        String currentLevel = null;
        switch (level) {
            case 1:
                currentLevel = getFirstLevel();
                break;
            case 2:
                currentLevel = getSecondLevel();
                break;
            case 3:
                currentLevel = getThirdLevel();
                break;
            case 4:
                currentLevel = getLevelFromFile("levels/level4.sok");
                break;
            case 5:
                currentLevel = getLevelFromFile("levels/level5.sok");

            default:

        }
        return currentLevel;
    }
    private String getFirstLevel() {
        return  "1111111\n" +
                "1000001\n" +
                "1203001\n" +
                "1040001\n" +
                "1000001\n"+
                "1111111\n";
    }

    private String getSecondLevel() {
        return  "11111\n"+
                "10021\n"+
                "10301\n"+
                "14001\n"+
                "11111\n";
    }
    private String getThirdLevel() {
        return  "11111111111\n" +
                "14000000041\n" +
                "10003000001\n" +
                "10000000001\n" +
                "10030000001\n" +
                "10011110031\n" +
                "12014000001\n" +
                "10010000001\n" +
                "11111111111\n";
    }

    private String getLevelFromFile(String fileName) {
        FileInputStream inputStream = null;
        StringBuilder container;
        try {
            inputStream = new FileInputStream(fileName);
            container = new StringBuilder();
            int unicode;
            while ((unicode = inputStream.read()) != -1) {
                char symbol = (char) unicode;
                if (('0' <= symbol && symbol <= '6') || symbol == '\n') {
                    container.append(symbol);
                }
            }
            if (container.length() > 0 && container.charAt(container.length() - 1) != '\n') {
                container.append('\n');
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
        return container.toString();
    }
}
