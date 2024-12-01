import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;
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
        if (stateGame) {
            drawDesktop(g);
        } else {
            drawError(g);
        }
    }

    private void drawDesktop(Graphics g) {
        int start = 300;
        int x = start;
        int y = 100;
        int width = 50;
        int height = 50;
        int offset = 0;
        int[][] desktop = model.getDesktop();
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    drawPlayer(model.getDirection(), g, x, y, width, height, this);
                } else if (desktop[i][j] == 2) {
                    g.drawImage(wallImage, x, y, width, height, this);
                } else if (desktop[i][j] == 3) {
                    g.drawImage(boxImage, x, y, width, height, this);
                } else if (desktop[i][j] == 4) {
                    g.drawImage(targetImage, x, y, width, height, this);
                } else if (desktop[i][j] == 0) {
                    g.drawImage(groundImage, x, y, width, height, this);
                }
                x = x + width + offset;
            }
            x = start;
            y = y + height + offset;
        }
    }

    public void drawError(Graphics g) {
        g.drawImage(errorImage, 300, 100, 200, 200, this);
    }

    public void drawPlayer(String direction, Graphics g, int x, int y, int width, int height, ImageObserver observer) {
        switch (direction){
            case "Up":
                g.drawImage(gamerUpImage, x, y, width, height, observer);
                break;
            case "Right":
                g.drawImage(gamerRightImage, x, y, width, height, observer);
                break;
            case "Down":
                g.drawImage(gamerDownImage, x, y, width, height, observer);
                break;
            case "Left":
                g.drawImage(gamerLeftImage, x, y, width, height, observer);
                break;
        }
    }
}
