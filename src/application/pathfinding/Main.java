package application.pathfinding;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
    final int WIDTH = 950;
    final int HEIGHT = 740;
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            GraphManager gm = new GraphManager(WIDTH, HEIGHT);
            root.getChildren().addAll(gm.getAsList());

            HBox bottom = new HBox();
            root.setBottom(bottom);
            bottom.getChildren().add(new Text("Left Click to create walls, Shift Click to create start node, Control Click to create end node, Delete to generate new grid, Escape to quit."));

            Scene scene = new Scene(root, WIDTH, HEIGHT);
            scene.setOnKeyPressed(e -> {
                        switch (e.getCode()) {
                            case ESCAPE:
                                System.exit(0);
                                break;
                            case DELETE:
                                root.getChildren().removeAll(gm.getAsList());
                                gm.generateNewGraph();
                                root.getChildren().addAll(gm.getAsList());

                                break;
                            case S:
                                if (gm.startExists && gm.endExists) {
                                    APathfinding algo = new APathfinding(gm.start, gm.end);
                                    try {
                                        for(GraphNode n : algo.calc()) {
                                            if(n.equals(gm.start) || n.equals(gm.end)) {

                                            } else {
                                                n.setFill(Color.TEAL);
                                            }
                                        }
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
                                }
                                break;
                        }
                    });

            primaryStage.setScene(scene);
            primaryStage.setTitle("A* Pathfinding Algorithm Visualizer");
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
