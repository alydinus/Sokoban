import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;


public class SettingsPanel extends JPanel {
    public SettingsPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Settings", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        add(label, BorderLayout.CENTER);
    }
}
