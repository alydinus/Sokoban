import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class Node {
    private String type; // Тип узла: "Space", "Wall", "Player", "Box", "Target"
    private int x, y;    // Логические координаты узла
    private Node up, down, left, right; // Ссылки на соседей

    // Цвета для каждого типа узла
    private static final Map<String, Color> colorMap = new HashMap<>();
    static {
        colorMap.put("Space", Color.LIGHT_GRAY);
        colorMap.put("Wall", Color.DARK_GRAY);
        colorMap.put("Player", Color.BLUE);
        colorMap.put("Box", Color.ORANGE);
        colorMap.put("Target", Color.GREEN);
    }

    public Node(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Отрисовка узла
    public void render(Graphics g, int tileSize, int offsetX, int offsetY) {
        int drawX = offsetX + x * tileSize;
        int drawY = offsetY + y * tileSize;

        // Устанавливаем цвет в зависимости от типа узла
        Color color = colorMap.getOrDefault(type, Color.BLACK);
        g.setColor(color);
        g.fillRect(drawX, drawY, tileSize, tileSize);

        // Рисуем границу узла кароч это внешние
        g.setColor(Color.BLACK);
        g.drawRect(drawX, drawY, tileSize, tileSize);
    }
}