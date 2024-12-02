import javax.swing.ImageIcon;
import java.awt.Image;

public class KyrgyzSkin implements PlayerSkin{
    private String type;
    private Image frontKyrgyzImage;
    private Image backKyrgyzImage;
    private Image leftKyrgyzImage;
    private Image rightKyrgyzImage;
    private Image wallImage;
    private Image boxImage;
    private Image targetImage;
    private Image groundImage;
    public KyrgyzSkin() {
        type = "Kyrgyz Skin";
        frontKyrgyzImage = new ImageIcon("images/front-kyrgyz.png").getImage();
        backKyrgyzImage = new ImageIcon("images/back-kyrgyz.png").getImage();
        leftKyrgyzImage = new ImageIcon("images/left-side-kyrgyz.png").getImage();
        rightKyrgyzImage = new ImageIcon("images/right-side-kyrgyz.png").getImage();
        wallImage = new ImageIcon("images/wall2.png").getImage();
        boxImage = new ImageIcon("images/box2.png").getImage();
        targetImage = new ImageIcon("images/target2.png").getImage();
        groundImage = new ImageIcon("images/ground2.png").getImage();
    }
    public String getType() {
            return type;
    }

    public Image getFrontPlayerImage() {
        return frontKyrgyzImage;
    }

    public Image getBackPlayerImage() {
        return backKyrgyzImage;
    }

    public Image getLeftPlayerImage() {
        return leftKyrgyzImage;
    }

    public Image getRightPlayerImage() {
        return rightKyrgyzImage;
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
        return groundImage;
    }
}
