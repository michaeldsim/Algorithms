package application.pathfinding;

/*
Graph Manager that handles the graph nodes as well as holds the values of the starting and ending nodes.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class GraphManager {
    GraphNode[][] grid;
    int width, height;
    boolean startExists = false;
    boolean endExists = false;
    GraphNode start = null;
    GraphNode end = null;

    public GraphManager(int width, int height) {
        this.width = width;
        this.height = height;

        generateNewGraph();
    }

    public void generateNewGraph() {
        this.grid = new GraphNode[(int)Math.ceil(width/GraphNode.WIDTH)+1][(int)Math.ceil(height/GraphNode.HEIGHT)-1];
        for (int i = 0; i < width; i += GraphNode.WIDTH) {
            for (int j = 0; j < height * .9; j += GraphNode.HEIGHT) {
                grid[i/GraphNode.WIDTH][j/GraphNode.HEIGHT] = new GraphNode(i, j, this);
            }
        }

        startExists = false;
        endExists = false;
        start = null;
        end = null;
    }

    public GraphNode get(int x, int y) {
        System.out.println("[x,y] = [" + x/40 + ", " + y/40 + "], list size: " + grid.length);
        return grid[x/GraphNode.WIDTH][y/GraphNode.HEIGHT];
    }

    public ArrayList<GraphNode> getAsList() {
        ArrayList<GraphNode> list = new ArrayList<>();
        for (GraphNode[] array : grid) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }
}
