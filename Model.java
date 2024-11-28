

public class Model {
    private Node startNode;
    private Node newLine;
    private Node[] goals;
    private Viewer viewer;
    private Node player;
    private Graph desktop;
    private boolean stateDesktop;
    private Levels levels;
    public Model(Viewer viewer) {
        this.viewer = viewer;
        levels = new Levels();
        initialization();
    }

    private void initialization() {
        String level = levels.nextLevel();
        int playerNumber = 0;
        int boxesNumber = 0;
        int goalsNumber = 0;
        char element;
        for (int i = 0; i < level.length(); i++) {
            element = level.charAt(i);
            if (element == '2') {
                playerNumber = playerNumber + 1;
            } else if (element == '3') {
                boxesNumber = boxesNumber + 1;
            } else if (element == '4') {
                goalsNumber = goalsNumber + 1;
            }
        }
        if ((playerNumber != 1) || (boxesNumber == 0) || (goalsNumber == 0) || (boxesNumber != goalsNumber)) {
            stateDesktop = false;
            return;
        }
        desktop = new Graph(level);
        if (desktop.getStartNode() == null || desktop == null) {
            stateDesktop = false;
            System.err.println("Error: startNode is null. Graph was not built correctly.");
            return;
        }
        startNode = desktop.getStartNode();
        System.out.println("Start node: " + startNode.getType());
        player = getPlayer();
        goals = getGoals(goalsNumber);
        stateDesktop = true;
    }

    private Node[] getGoals(int goalsNumber) {
        Node[] goals = new Node[goalsNumber];
        Node currentNode = startNode;
        newLine = startNode.getDown();
        int index = 0;
        while (true) {
            if (currentNode.getType().equals("Goal")) {
                goals[index] = currentNode;
                index = index + 1;
            }
            if ((currentNode.getRight() == null && currentNode.getDown() == null) || goals[goalsNumber - 1] != null) {
                break;
            }
            if (currentNode.getRight() == null) {
                currentNode = newLine;
                newLine = newLine.getDown();
                continue;
            }
            currentNode = currentNode.getRight();
        }
        return goals;
    }

    public boolean getState() {
        return stateDesktop;
    }
    public Node getPlayer() {
        System.out.println("Graph structure:");
        Node row = startNode;

        // Печать графа для отладки
        while (row != null) {
            Node col = row;
            while (col != null) {
                System.out.print(col.getType() + " ");
                col = col.getRight();
            }
            System.out.println();
            row = row.getDown();
        }

        Node currentNode = startNode;
        newLine = startNode.getDown();

        // Поиск игрока
        while (currentNode != null) {
            if ("Player".equals(currentNode.getType())) {
                return currentNode;
            }

            if (currentNode.getRight() == null) {
                if (newLine == null) {
                    throw new IllegalStateException("Error: newLine is null. Graph is incomplete.");
                }
                currentNode = newLine;
                newLine = newLine.getDown();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        throw new IllegalStateException("Player node not found in the graph.");
    }

    public void move(String direction) {
        if (direction.equals("Up")) {
            moveUp();
        } else if (direction.equals("Down")) {
            moveDown();
        } else if (direction.equals("Left")) {
            moveLeft();
        } else if (direction.equals("Right")) {
            moveRight();
        } else {
            return;
        }
        viewer.update();
    }
    public Graph getDesktop() {
        return desktop;
    }
    private void moveUp() {
        Node upNode = player.getUp();
        if (upNode == null) {
            return;
        }
        if (upNode.getType().equals("Wall")) {
            return;
        }
        if (upNode.getType().equals("Box")) {
            Node upBoxNode = upNode.getUp();
            if (upBoxNode == null || upBoxNode.getType().equals("Wall") || upBoxNode.getType().equals("Box")) {
                return;
            }
            upNode.setType("Floor");
            upBoxNode.setType("Box");
        }
        player.setType("Floor");
        player = upNode;
        player.setType("Player");
    }
    private void moveDown() {
        Node downNode = player.getDown();
        if (downNode == null) {
            return;
        }
        if (downNode.getType().equals("Wall")) {
            return;
        }
        if (downNode.getType().equals("Box")) {
            Node downBoxNode = downNode.getDown();
            if (downBoxNode == null || downBoxNode.getType().equals("Wall") || downBoxNode.getType().equals("Box")) {
                return;
            }
            downNode.setType("Floor");
            downBoxNode.setType("Box");
        }
        player.setType("Floor");
        player = downNode;
        player.setType("Player");
    }
    private void moveLeft() {
        Node leftNode = player.getLeft();
        if (leftNode == null) {
            return;
        }
        if (leftNode.getType().equals("Wall")) {
            return;
        }
        if (leftNode.getType().equals("Box")) {
            Node leftBoxNode = leftNode.getLeft();
            if (leftBoxNode == null || leftBoxNode.getType().equals("Wall") || leftBoxNode.getType().equals("Box")) {
                return;
            }
            leftNode.setType("Floor");
            leftBoxNode.setType("Box");
        }
        player.setType("Floor");
        player = leftNode;
        player.setType("Player");
    }
    private void moveRight() {
        Node rightNode = player.getRight();
        if (rightNode == null) {
            return;
        }
        if (rightNode.getType().equals("Wall")) {
            return;
        }
        if (rightNode.getType().equals("Box")) {
            Node rightBoxNode = rightNode.getRight();
            if (rightBoxNode == null || rightBoxNode.getType().equals("Wall") || rightBoxNode.getType().equals("Box")) {
                return;
            }
            rightNode.setType("Floor");
            rightBoxNode.setType("Box");
        }
        player.setType("Floor");
        player = rightNode;
        player.setType("Player");
    }
}
