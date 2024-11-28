
public class Graph {
    private Node startNode; // Начальный узел (верхний левый угол карты)
    private String level;
    private int columns;
    private int rows;

    public Graph(String level) {
        this.level = level;
        columns = countColumns();
        rows = level.length() / columns;

        buildGraph();
    }
    private int countColumns() {
        int counter = 0;
        for (int i = 0; i < level.length(); i++) {
            if (level.charAt(i) == '\n') {
                break;
            }
            counter++;
        }
        return counter;
    }
    // Построение графа
    private void buildGraph() {
        if (level == null || level.isEmpty()) {
            throw new IllegalArgumentException("Error: Level data is empty or null.");
        }

        Node previousRowStart = null; // Узел начала предыдущей строки
        Node previousNode = null;    // Узел слева от текущего
        Node currentRowStart = null; // Узел начала текущей строки
        int x = 0, y = 0;

        for (int i = 0; i < level.length(); i++) {
            char symbol = level.charAt(i);

            // Переход на новую строку
            if (symbol == '\n') {
                previousRowStart = currentRowStart;
                currentRowStart = null;
                previousNode = null;
                x = 0;
                y++;
                continue;
            }

            // Создание текущего узла
            Node currentNode;
            switch (symbol) {
                case '1':
                    currentNode = new Node("Wall", x, y);
                    break;
                case '2':
                    currentNode = new Node("Player", x, y);
                    break;
                case '0':
                    currentNode = new Node("Floor", x, y);
                    break;
                case '3':
                    currentNode = new Node("Box", x, y);
                    break;
                case '4':
                    currentNode = new Node("Goal", x, y);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type: " + symbol);
            }

            // Установка начального узла
            if (startNode == null) {
                startNode = currentNode;
            }

            // Связь с узлом слева
            if (previousNode != null) {
                previousNode.setRight(currentNode);
                currentNode.setLeft(previousNode);
            }

            // Связь с узлом сверху
            if (previousRowStart != null) {
                previousRowStart.setDown(currentNode);
                currentNode.setUp(previousRowStart);
                previousRowStart = previousRowStart.getRight(); // Переход к следующему узлу сверху
            }

            // Обновление начального узла текущей строки
            if (currentRowStart == null) {
                currentRowStart = currentNode;
            }

            previousNode = currentNode; // Обновление узла слева
            x++;
        }

        System.out.println("Graph built successfully!");
    }

    public Node getStartNode() {
        return startNode;
    }
}
