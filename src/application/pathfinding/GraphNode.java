package application.pathfinding;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GraphNode extends Rectangle {
	final GraphManager manager;
	GraphNode parent = null;
	static final int WIDTH = 40;
	static final int HEIGHT = 40;
	boolean emptyPath = true;
	boolean start = false;
	boolean end = false;
	private int g, h, f;

	public GraphNode(int x, int y, GraphManager manager) {
		this.manager = manager;
		this.setX(x);
		this.setY(y);
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		this.setFill(Color.WHITE);
		this.setStroke(Color.BLACK);

		this.setOnMouseDragEntered(event -> {
			System.out.println(toString());
			if (!emptyPath && (!start || !end)) {
				setEmptyPath(true);
			} else if (start || end) {

			} else {
				setEmptyPath(false);
			}
		});

		this.setOnMouseClicked(event -> {
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

	public void findCosts() {
		// 10 is the cost for 1 space

		// G cost is equal to the distance from the start node to n
		if(this.equals(start)) {
			this.g = 0;
		} else {
			this.g = this.parent.getG() + 10;
		}

		// H cost is equal to the distance from n to the end node
		this.h = distanceTo(manager.end);

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

	public int distanceTo(GraphNode node) {
		int xDiff = (int) Math.abs(node.getX()/40 - getX()/40);
		int yDiff = (int) Math.abs(node.getY()/40 - getY()/40);
		return xDiff + yDiff;
	}

	public void setParent(GraphNode parent) {
		this.parent = parent;
	}

	public int getF() {
		return f;
	}

	public int getG() {
		return g;
	}

}
