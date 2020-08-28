package application.pathfinding;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class APathfinding {

    private ArrayList<GraphNode> path;

    GraphNode start;
    GraphNode end;

    private ArrayList<GraphNode> open;
    private ArrayList<GraphNode> closed;

    public APathfinding(GraphNode start, GraphNode end) {
        this.start = start;
        this.end = end;
    }

    public ArrayList<GraphNode> calc() throws InterruptedException {
        this.path = new ArrayList<>();
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();

        this.open.add(start);

        while(!open.isEmpty()) {
            GraphNode current = getLowestFCost();

            if(current.equals(end)) {
                constructPath(current);
                break;
            }

            open.remove(current);
            closed.add(current);

            for (GraphNode n : current.getNeighbors()) {
                if(closed.contains(n) || !n.emptyPath) {
                    continue;
                }

                n.setParent(current);
                n.findCosts();

                if(!open.contains(n)) {
                    open.add(n);
                }
            }

            updateScene();
        }

        return this.path;
    }

    public GraphNode getLowestFCost() {
        GraphNode lowest = open.get(0);
        for(GraphNode node : open) {
            if(node.getF() < lowest.getF()) {
                lowest = node;
            }
        }

        return lowest;
    }

    public void updateScene() {
        for (GraphNode n : open) {
            if(n.equals(start) || n.equals(end)) {

            } else {
                n.setFill(Color.LIME);
            }
        }

        for (GraphNode n : closed) {
            if(n.equals(start) || n.equals(end)) {

            } else {
                n.setFill(Color.BROWN);
            }
        }
    }

    public void constructPath(GraphNode current) {
        GraphNode node = current;
        this.path.add(current);

        while(node.parent != null) {
            this.path.add(node.parent);
            node = node.parent;
        }

        this.path.add(start);
    };

}
