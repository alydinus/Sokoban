
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;



public class LevelChooser extends JPanel {
    private Viewer viewer;
    private Model model;
    private LevelChooserController levelChooserController;

    public LevelChooser(Viewer viewer, Model model) {
        this.viewer = viewer;
        this.model = model;
        this.levelChooserController = new LevelChooserController(viewer, model);
        setLayout(null);
        addMouseListener(levelChooserController);
    }
    public void paint(Graphics g) {
        super.paint(g);
        Image backgroundImage = new ImageIcon("images/background.jpg").getImage();
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        Graphics2D g2d = (Graphics2D) g;
        int buttonWidth = 150;
        int buttonHeight = 130;
        int spacingX = 30;
        int spacingY = 30;
        int startX = 200;
        int startY = 200;

        Color buttonColor = new Color(173, 216, 230);
        Color borderColor = new Color(0, 120, 215);
        Color textColor = Color.BLACK;

        String[] levels = {"Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7", "Level 8"};
        int columns = 4;

        for (int i = 0; i < levels.length; i++) {
            int column = i % columns;
            int row = i / columns;
            int x = startX + column * (buttonWidth + spacingX);
            int y = startY + row * (buttonHeight + spacingY);
            g2d.setColor(buttonColor);
            g2d.fillRoundRect(x, y, buttonWidth, buttonHeight, 25, 25);

            g2d.setColor(borderColor);
            g2d.drawRoundRect(x, y, buttonWidth, buttonHeight, 25, 25);

            g2d.setColor(textColor);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            FontMetrics metrics = g2d.getFontMetrics();
            int textX = x + (buttonWidth - metrics.stringWidth(levels[i])) / 2;
            int textY = y + ((buttonHeight - metrics.getHeight()) / 2) + metrics.getAscent();
            g2d.drawString(levels[i], textX, textY);
        }
    }


}
