package application.pathfinding;

import java.util.ArrayList;

public class APathfinding {

    private ArrayList<GraphNode> path;

    GraphNode start;
    GraphNode end;

    private ArrayList<GraphNode> open;
    private ArrayList<GraphNode> closed;

    public APathfinding() {

        if (start == null && end == null) {return;}

        this.path = new ArrayList<>();
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();

        this.open.add(start);

        while(!open.isEmpty()) {

        }

    }

    public void constructPath() {};
    public void AStar() {};
}
