package application.sorting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    final int HEIGHT = 720;
    final int WIDTH = 960;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bubble Sort Visualizer");
        primaryStage.show();
    }
}
