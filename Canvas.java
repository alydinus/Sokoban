import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Canvas extends JPanel {
    private Model model;
    private Image boxImage;
    private Image errorImage;
    private Image gamerDownImage;
    private Image gamerLeftImage;
    private Image gamerRightImage;
    private Image gamerUpImage;
    private Image groundImage;
    private Image targetImage;
    private Image wallImage;

    public Canvas(Model model) {
        this.model = model;
        Color backgroundColor = new Color(40, 44, 52);
        setBackground(backgroundColor);

        File boxFile = new File("images/box.png");
        File errorFile = new File("images/error.png");
        File gamerDownFile = new File("images/gamerDown.jpg");
        File gamerLeftFile = new File("images/gamerLeft.jpg");
        File gamerRightFile = new File("images/gamerRight.jpg");
        File gamerUpFile = new File("images/gamerUp.jpg");
        File groundFile = new File("images/ground.png");
        File targetFile = new File("images/target.png");
        File wallFile = new File("images/wall.png");

        try {
            boxImage = ImageIO.read(boxFile);
            errorImage = ImageIO.read(errorFile);
            gamerDownImage = ImageIO.read(gamerDownFile);
            gamerLeftImage = ImageIO.read(gamerLeftFile);
            gamerRightImage = ImageIO.read(gamerRightFile);
            gamerUpImage = ImageIO.read(gamerUpFile);
            groundImage = ImageIO.read(groundFile);
            targetImage = ImageIO.read(targetFile);
            wallImage = ImageIO.read(wallFile);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        boolean stateGame = model.getState();
        int start = 50;
        int x = 50;
        int y = 50;
        int width = 50;
        int height = 50;
        int offset = 1;
        int[][] desktop = model.getDesktop();

        // 1 - Player
        // 2 - Wall
        // 3 - Box
        // 4 - Target
        if (stateGame) {
            for (int i = 0; i < desktop.length; i++) {
                for (int j = 0; j < desktop[i].length; j++) {
                    if (desktop[i][j] == 1) {
                        g.drawImage(gamerDownImage, x, y, null);
                    } else if (desktop[i][j] == 2) {
                        g.drawImage(wallImage, x, y, null);
                    } else if (desktop[i][j] == 3) {
                        g.drawImage(boxImage, x, y, null);
                    } else if (desktop[i][j] == 4) {
                        g.drawImage(targetImage, x, y, null);
                    }
                    x = x + width + offset;
                }
                x = start;
                y = y + height + offset;
            }
        } else {
            drawError(g);
        }
    }

    public void drawError(Graphics g) {

    }
}
