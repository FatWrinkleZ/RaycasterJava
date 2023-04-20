package advanced_topics_final;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Game game = new Game(WIDTH, HEIGHT);
        scene = new Scene(game, WIDTH, HEIGHT);
        stage.setScene(scene);
        scene.setOnKeyPressed(game);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }

}