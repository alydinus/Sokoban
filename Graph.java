import java.awt.Graphics;

public class Graph {
    private Node startNode; // Начальный узел (верхний левый угол карты)

    public Graph(String levelData, int width, int height) {
        buildGraph(levelData, width, height);
    }

    // Построение графа
    private void buildGraph(String levelData, int width, int height) {
        String[] rows = levelData.split(" ");
        Node previousRowStart = null;
        Node previousNode = null;

        System.out.println("Building graph...");
        for (int y = 0; y < height; y++) {
            String row = rows[y];
            Node currentRowStart = null;

            for (int x = 0; x < width; x++) {
                // Создание узла с соответствующим типом
                Node currentNode = switch (row.charAt(x)) {
                    case '1' -> new Node("Wall", x, y);
                    case '2' -> new Node("Player", x, y);
                    case '0' -> new Node("Space", x, y);
                    case '3' -> new Node("Box", x, y);
                    case '4' -> new Node("Target", x, y);
                    default -> throw new IllegalArgumentException("Unknown type: " + row.charAt(x));
                };
                System.out.println("Created Node at (" + x + ", " + y + ") with type: " + currentNode.getType());

                // Устанавливаем связь "влево"
                if (previousNode != null) {
                    previousNode.setRight(currentNode);
                    currentNode.setLeft(previousNode);
                    System.out.println("Linked (" + x + ", " + y + ") to left neighbor (" + (x - 1) + ", " + y + ")");
                } else {
                    currentRowStart = currentNode;
                    if (startNode == null) {
                        startNode = currentNode; // Сохраняем верхний левый узел
                        System.out.println("Start node set at (" + x + ", " + y + ")");
                    }
                }

                // Устанавливаем связь "вверх"
                if (previousRowStart != null) {
                    Node aboveNode = previousRowStart;
                    aboveNode.setDown(currentNode);
                    currentNode.setUp(aboveNode);
                    System.out.println("Linked (" + x + ", " + y + ") to top neighbor (" + x + ", " + (y - 1) + ")");
                    previousRowStart = previousRowStart.getRight();
                }

                previousNode = currentNode;
            }

            // Переход к следующей строке
            previousRowStart = currentRowStart;
            previousNode = null;
        }
    }


    // Отрисовка графа
    public void render(Graphics g, int tileSize, int offsetX, int offsetY) {
        System.out.println("Rendering graph...");
        Node currentRow = startNode;

        while (currentRow != null) { 
            Node current = currentRow;
            while (current != null) {
                System.out.println("Rendering Node at (" + current.getX() + ", " + current.getY() + ") with type: " + current.getType());
                current.render(g, tileSize, offsetX, offsetY);
                current = current.getRight();
            }
            currentRow = currentRow.getDown();
        }
    }

    // Перемещение игрока
    public boolean movePlayer(String direction) {
        Node currentRow = startNode;

        while (currentRow != null) {
            Node current = currentRow;

            while (current != null) {
                if ("Player".equals(current.getType())) { // Находим узел игрока
                    System.out.println("Player found at (" + current.getX() + ", " + current.getY() + ")");
                    Node target = switch (direction) {
                        case "Up" -> current.getUp();
                        case "Down" -> current.getDown();
                        case "Left" -> current.getLeft();
                        case "Right" -> current.getRight();
                        default -> null;
                    };

                    if (target != null && "Space".equals(target.getType())) {
                        current.setType("Space");  // Текущий узел становится пустым
                        target.setType("Player"); // Целевой узел становится игроком
                        return true;
                    }
                }
                current = current.getRight();
            }
            currentRow = currentRow.getDown();
        }
        return false;
    }
}
