

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
        if (upNode == null || upNode.getType().equals("Wall")) {
            return;
        }
        if (upNode.getType().equals("Box") || upNode.getType().equals("BoxOnGoal")) {
            Node upBoxNode = upNode.getUp();
            if (upBoxNode == null || upBoxNode.getType().equals("Wall") ||
                    upBoxNode.getType().equals("Box") || upBoxNode.getType().equals("BoxOnGoal")) {
                return;
            }
            processBoxMove(upNode, upBoxNode);
        }

        processPlayerMove(player, upNode);
        player = upNode;
    }

    private void moveDown() {
        Node downNode = player.getDown();
        if (downNode == null || downNode.getType().equals("Wall")) {
            return;
        }
        if (downNode.getType().equals("Box") || downNode.getType().equals("BoxOnGoal")) {
            Node downBoxNode = downNode.getDown();
            if (downBoxNode == null || downBoxNode.getType().equals("Wall") ||
                    downBoxNode.getType().equals("Box") || downBoxNode.getType().equals("BoxOnGoal")) {
                return;
            }
            processBoxMove(downNode, downBoxNode);
        }

        processPlayerMove(player, downNode);
        player = downNode;
    }

    private void moveLeft() {
        Node leftNode = player.getLeft();
        if (leftNode == null || leftNode.getType().equals("Wall")) {
            return;
        }
        if (leftNode.getType().equals("Box") || leftNode.getType().equals("BoxOnGoal")) {
            Node leftBoxNode = leftNode.getLeft();
            if (leftBoxNode == null || leftBoxNode.getType().equals("Wall") ||
                    leftBoxNode.getType().equals("Box") || leftBoxNode.getType().equals("BoxOnGoal")) {
                return;
            }
            processBoxMove(leftNode, leftBoxNode);
        }

        processPlayerMove(player, leftNode);
        player = leftNode;
    }

    private void moveRight() {
        Node rightNode = player.getRight();
        if (rightNode == null || rightNode.getType().equals("Wall")) {
            return;
        }
        if (rightNode.getType().equals("Box") || rightNode.getType().equals("BoxOnGoal")) {
            Node rightBoxNode = rightNode.getRight();
            if (rightBoxNode == null || rightBoxNode.getType().equals("Wall") ||
                    rightBoxNode.getType().equals("Box") || rightBoxNode.getType().equals("BoxOnGoal")) {
                return;
            }
            processBoxMove(rightNode, rightBoxNode);
        }

        processPlayerMove(player, rightNode);
        player = rightNode;
    }

    private void processBoxMove(Node boxNode, Node targetNode) {
        if (boxNode.getType().equals("BoxOnGoal")) {
            boxNode.setType("Goal");
        } else {
            boxNode.setType("Floor");
        }

        if (targetNode.getType().equals("Goal")) {
            targetNode.setType("BoxOnGoal");
        } else {
            targetNode.setType("Box");
        }
    }

    private void processPlayerMove(Node playerNode, Node targetNode) {
        if (playerNode.getType().equals("PlayerOnGoal")) {
            playerNode.setType("Goal");
        } else {
            playerNode.setType("Floor");
        }

        if (targetNode.getType().equals("Goal")) {
            targetNode.setType("PlayerOnGoal");
        } else {
            targetNode.setType("Player");
        }
    }
}
