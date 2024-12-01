import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Graphics;

public class MenuPanel extends JPanel {
    private Image backgroundImage;

    public MenuPanel(CardLayout cardLayout, JPanel parentPanel) {

        backgroundImage = new ImageIcon("images/background.jpg").getImage();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> cardLayout.show(parentPanel, "LevelChooser"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(startGameButton, gbc);

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> cardLayout.show(parentPanel, "Settings"));
        gbc.gridy = 1;
        add(settingsButton, gbc);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridy = 2;
        add(exitButton, gbc);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}