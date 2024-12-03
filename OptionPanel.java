import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

public class OptionPanel extends JPanel implements Runnable {

    private Levels level;
    private int rowCount;
    private Font font;
    private Model model;
    private JLabel timerLabel;
    private JLabel levelNameLabel;
    private JLabel stepsLabel;
    private int time;
    private Thread threadForTimer;
    private Thread threadForSteps;
    private int steps;

    public OptionPanel(Model model) {
        threadForTimer = new Thread(this);
        threadForSteps = new Thread(this);
        this.model = model;

        font = new Font("Arial", Font.BOLD, 20);


        level = new Levels();

        int levelName = level.getLevelNumber();
        rowCount = level.getFirstRowElementsQuantity("Level " + levelName);

        int width = rowCount * 50;
        int height = 50;

        setSize(width, height);
        setLocation(300, 50);
        setBackground(Color.blue);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        timerLabel = new JLabel("00:00");
        timerLabel.setFont(font);

        levelNameLabel = new JLabel("Level " + levelName);
        levelNameLabel.setFont(font);

        stepsLabel = new JLabel("Steps: " + steps);
        stepsLabel.setFont(font);

        add(timerLabel);
        add(levelNameLabel);
        add(stepsLabel);
    }

    public void paint(Graphics g){
        super.paint(g);
    }

    public void run() {
        while (true) {
            countSteps();
            try {
                Thread.sleep(1000);
                time++;
                int seconds = time % 60;
                int minutes = time / 60;

                if (seconds >= 10 && minutes >= 10) {
                    timerLabel.setText(minutes + ":" + seconds);
                } else if (seconds < 10 && minutes >= 10) {
                    timerLabel.setText(minutes + ":0" + seconds);
                } else if (seconds >= 10) {
                    timerLabel.setText("0" + minutes + ":" + seconds);
                } else {
                    timerLabel.setText("0" + minutes + ":0" + seconds);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startTimer() {
        threadForTimer.start();
    }
    public void startSteps() {
        threadForSteps.start();
    }

    public void countSteps() {
        steps = model.getSteps();
        stepsLabel.setText("Steps: " + steps);
    }

}