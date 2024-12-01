import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Stroke;

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
        String title = "Choose Level";
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 80));
        FontMetrics titleMetrics = g2d.getFontMetrics();
        int titleX = (getWidth() - titleMetrics.stringWidth(title)) / 2;
        int titleY = 140;
        g2d.drawString(title, titleX, titleY);
        int buttonWidth = 144;
        int buttonHeight = 189;
        int spacingX = 30;
        int spacingY = 30;
        int startX = 190;
        int startY = 180;

        Color buttonColor = new Color(129, 51, 51);
        Color borderColor = new Color(255, 255, 255);
        Color textColor = Color.WHITE;
        String[] levels = {"1", "2", "3", "4", "5", "6", "7", "8" , "9", "10"};
        int columns = 5;

        for (int i = 0; i < levels.length; i++) {
            int column = i % columns;
            int row = i / columns;
            int x = startX + column * (buttonWidth + spacingX);
            int y = startY + row * (buttonHeight + spacingY);

            g2d.setColor(buttonColor);
            g2d.fillRoundRect(x, y, buttonWidth, buttonHeight, 25, 25);
            Stroke oldStroke = g2d.getStroke();
            g2d.setStroke(new BasicStroke(8.0f));
            g2d.setColor(borderColor);

            g2d.drawRoundRect(x, y, buttonWidth, buttonHeight, 25, 25);
            g2d.setStroke(oldStroke);
            g2d.setColor(textColor);
            g2d.setFont(new Font("Arial", Font.BOLD, 70));
            FontMetrics metrics = g2d.getFontMetrics();
            String levelText = levels[i];
            int textX = x + (buttonWidth - metrics.stringWidth(levelText)) / 2;
            int textY = y + (buttonHeight - metrics.getHeight()) / 2 + metrics.getAscent();
            g2d.drawString(levelText, textX, textY);
        }
    }
}
