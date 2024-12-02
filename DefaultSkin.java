import javax.swing.ImageIcon;
import java.awt.Image;

public class DefaultSkin implements PlayerSkin{
    private String type;
    private Image frontDefaultImage;
    private Image backDefalutImage;
    private Image leftDefaultImage;
    private Image rightDefaultImage;
    private Image wallImage;
    private Image boxImage;
    private Image targetImage;
    private Image groundImage;
    public DefaultSkin() {
        type = "Default Skin";
        frontDefaultImage = new ImageIcon("images/front-player.jpg").getImage();
        backDefalutImage = new ImageIcon("images/back-player.jpg").getImage();
        leftDefaultImage = new ImageIcon("images/left-side-player.jpg").getImage();
        rightDefaultImage = new ImageIcon("images/right-side-player.jpg").getImage();
        wallImage = new ImageIcon("images/wall.png").getImage();
        boxImage = new ImageIcon("images/box.png").getImage();
        targetImage = new ImageIcon("images/target.png").getImage();
        groundImage = new ImageIcon("images/ground.png").getImage();
    }

    public String getType() {
        return type;
    }

    public Image getFrontPlayerImage() {
        return frontDefaultImage;
    }

    public Image getBackPlayerImage() {
        return backDefalutImage;
    }

    public Image getLeftPlayerImage() {
        return leftDefaultImage;
    }

    public Image getRightPlayerImage() {
        return rightDefaultImage;
    }

    public Image getWallImage() {
        return wallImage;
    }

    public Image getBoxImage() {
        return boxImage;
    }

    public Image getTargetImage() {
        return targetImage;
    }

    public Image getGroundImage() {
        return  groundImage;
    }
}
