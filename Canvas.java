import javax.swing.JPanel;
import java.awt.Font;
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
    private Image backgroundImage;
    public Canvas(Model model, Controller controller) {
        this.model = model;
        Color backgroundColor = new Color(40, 44, 52);
        setBackground(backgroundColor);
        File backgroundFile = new File("images/background.jpg");
        File boxFile = new File("images/box.png");
        File errorFile = new File("images/error.png");
        File gamerDownFile = new File("images/front-player.jpg");
        File gamerLeftFile = new File("images/left-side-player.jpg");
        File gamerRightFile = new File("images/right-side-player.jpg");
        File gamerUpFile = new File("images/back-player.jpg");
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
            backgroundImage = ImageIO.read(backgroundFile);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        addKeyListener(controller);
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
        Font font = new Font("Arial", Font.BOLD, 50);
        g.drawImage(errorImage, 300, 170, null);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("Initialization Error!", 200, 100);
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
