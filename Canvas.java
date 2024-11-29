import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Canvas extends JPanel {

    private Model model;
    private Graph graph;

    public Canvas(Model model) {

        this.model = model;
        Color backgroundColor = new Color(40, 44, 52);
        setBackground(backgroundColor);
    }
    public void paint(Graphics g)    {
        super.paint(g);
        boolean stateGame = model.getState();
        if (stateGame) {
            drawDesktop(g);
        } else {
            drawError(g);
        }
    }
    public void drawDesktop(Graphics g){
        int start = 50;
        int x = start;
        int y = start;
        int width = 50;
        int height = 50;

        Graph desktop = model.getDesktop();
        Node currentNode = desktop.getStartNode();
        Node newLineNode = desktop.getStartNode().getDown();

        while (true){
            if (currentNode.getType().equals("Player")){
                drawPlayerCell(g,x,y,width,height);
            } else if (currentNode.getType().equals("Wall")){
                drawWallCell(g,x,y,width,height);
            } else if (currentNode.getType().equals("Box")){
                drawBoxCell(g,x,y,width,height);
            } else if (currentNode.getType().equals("Goal")){
                drawGoalCell(g,x,y,width,height);
            } else if (currentNode.getType().equals("Floor")){
                drawFloorCell(g,x,y,width,height);
            }else{
                drawDefaultCell(g,x,y,width,height);
            }
            if (currentNode.getRight() == null && currentNode.getDown() == null){
                break;
            }
            if (currentNode.getRight() != null){
                currentNode = currentNode.getRight();
                x = x + width;
            } else {
                currentNode = newLineNode;
                newLineNode = newLineNode.getDown();
                x = start;
                y = y + height;
            }
        }
    }

    private void drawPlayerCell(Graphics g, int x, int y, int width, int height) {
        // Фон
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);

        // Голова
        int headSize = width / 2;
        int headX = x + width / 4;
        int headY = y + height / 6;
        g.setColor(Color.PINK);
        g.fillOval(headX, headY, headSize, headSize);

        // Тело
        int bodyWidth = headSize;
        int bodyHeight = height / 2;
        int bodyX = headX;
        int bodyY = headY + headSize;
        g.setColor(Color.BLUE);
        g.fillRect(bodyX, bodyY, bodyWidth, bodyHeight);

        // Kalpak
        int hatWidth = headSize;
        int hatHeight = headSize / 2;
        int hatX = headX;
        int hatY = headY - hatHeight / 2;

        g.setColor(Color.WHITE);

        g.drawLine(hatX, hatY + hatHeight, hatX + hatWidth / 2, hatY);
        g.drawLine(hatX + hatWidth / 2, hatY, hatX + hatWidth, hatY + hatHeight);
        g.drawLine(hatX, hatY + hatHeight, hatX + hatWidth, hatY + hatHeight);

        for (int i = 0; i < hatHeight; i++) {
            int startX = hatX + i;
            int endX = hatX + hatWidth - i;
            g.drawLine(startX, hatY + hatHeight - i, endX, hatY + hatHeight - i);
        }

        g.setColor(Color.BLACK);
        g.drawLine(hatX + hatWidth / 2, hatY, hatX + hatWidth / 2, hatY + hatHeight);
    }




    private void drawWallCell(Graphics g, int x, int y, int width, int height) {

        g.setColor(new Color(139, 69, 19));
        g.fillRect(x, y, width, height);

        g.setColor(new Color(160, 82, 45));

        int panelWidth = width / 5;
        int panelHeight = height / 5;

        for (int i = 0; i <= width; i += panelWidth) {
            g.drawLine(x + i, y, x + i, y + height);
        }

        for (int i = 0; i <= height; i += panelHeight) {
            g.drawLine(x, y + i, x + width, y + i);
        }


        g.setColor(new Color(205, 92, 92));

        for (int i = 0; i < width; i += panelWidth) {
            for (int j = 0; j < height; j += panelHeight) {
                g.drawLine(x + i, y + j, x + i + panelWidth, y + j + panelHeight);
                g.drawLine(x + i + panelWidth, y + j, x + i, y + j + panelHeight);
            }
        }
    }

    private void drawBoxCell(Graphics g, int x, int y, int width, int height) {

        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, width - 1, height - 1);

        int innerMargin = width / 8;
        g.drawRect(x + innerMargin, y + innerMargin, width - 2 * innerMargin - 1, height - 2 * innerMargin - 1);

        g.drawLine(x, y, x + width - 1, y + height - 1);
        g.drawLine(x + width - 1, y, x, y + height - 1);
    }

    private void drawGoalCell(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);

        int circleDiameter = Math.min(width, height) / 3;
        g.setColor(new Color(139, 69, 19));
        g.fillOval(x + (width - circleDiameter) / 2, y + (height - circleDiameter) / 2, circleDiameter, circleDiameter);
    }


    private void drawFloorCell(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);
    }

    private void drawDefaultCell(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }


    public void drawError(Graphics g){

    }
}
