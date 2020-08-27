package application.pathfinding;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GraphNode extends Rectangle {
	final GraphManager manager;
	GraphNode parent;
	static final int WIDTH = 40;
	static final int HEIGHT = 40;
	boolean emptyPath = true;
	boolean start = false;
	boolean end = false;
	int g, h, f;

	public GraphNode(int x, int y, GraphManager manager) {
		this.manager = manager;
		this.setX(x);
		this.setY(y);
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		this.setFill(Color.WHITE);
		this.setStroke(Color.BLACK);

		this.setOnMouseClicked(event -> {
			System.out.printf("Start: %b End: %b Empty: %b\n", start, end, emptyPath);
			if (event.isShiftDown()) {
					if (manager.startExists && start) {
						start = false;
						manager.startExists = false;
						setFill(Color.WHITE);
					} else if (manager.startExists) {
						// DO NOTHING
					} else {
						start = true;
						manager.startExists = true;
						manager.start = getSelf();
						setFill(Color.RED);
					}
			} else if (event.isControlDown()) {
					if (manager.endExists && end) {
						end = false;
						manager.endExists = false;
						setFill(Color.WHITE);
					} else if (manager.endExists) {
						// DO NOTHING
					} else {
						end = true;
						manager.endExists = true;
						manager.end = getSelf();
						setFill(Color.BLUE);
					}
			} else {
				if (!emptyPath && (!start || !end)) {
					setEmptyPath(true);
				} else if (start || end) {

				} else {
					setEmptyPath(false);
				}
			}
		});
	}
	
	//TODO: math for costs
	public void findCosts() {
		// 10 is the cost for 1 space
		int startX = (int) manager.start.getX()/GraphNode.WIDTH;
		int startY = (int) manager.start.getY()/GraphNode.HEIGHT;


		// G cost is equal to the distance from the start node to n
		int g = 0;
		
		// H cost is equal to the distance from n to the end node
		int h = 0;

		this.g = g;
		this.h = h;

		// F cost is the two costs combined
		this.f = g+h;
	}

	public void setEmptyPath(boolean bool) {
		this.emptyPath = bool;
		if(!bool) {
			this.setFill(Color.BLACK);
			this.setStroke(Color.WHITE);
		} else {
			this.setFill(Color.WHITE);
			this.setStroke(Color.BLACK);
		}
	}

	public GraphNode getSelf() {
		return this;
	}

	public boolean compare(GraphNode n) {
		if (this.getX() == n.getX() && this.getY() == n.getY()) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<GraphNode> getNeighbors() {
		ArrayList<GraphNode> neighbors = new ArrayList<>();
		int n = GraphNode.WIDTH;
		// top
		if(!(getY() == 0)) {
			if(manager.get((int) getX(), (int) getY() - n).emptyPath) {
				neighbors.add(manager.get((int) getX(), (int) getY() - n));
			}
		}
		// bottom
		if(!(getY()/n == Math.ceil(manager.height*.9/n) - 1)) {
			if(manager.get((int) getX(), (int) getY() + n).emptyPath) {
				neighbors.add(manager.get((int) getX(), (int) getY() + n));
			}
		}
		// left
		if(!(getX() == 0)) {
			if(manager.get((int) getX() - n, (int) getY()).emptyPath) {
				neighbors.add(manager.get((int) getX() - n, (int) getY()));
			}
		}
		// right
		if(!(getX()/n == (manager.width/n))) {
			if(manager.get((int) getX() + n, (int) getY()).emptyPath) {
				neighbors.add(manager.get((int) getX() + n, (int) getY()));
			}
		}

		return neighbors;
	}


}
