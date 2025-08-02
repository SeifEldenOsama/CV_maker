package CV_maker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        ResumeController controller = new ResumeController();
        ResumeView view = new ResumeView(controller);

        Scene scene = new Scene(view.getRoot(), 900, 650);
        scene.getStylesheets().add(getClass().getResource("/CV_maker/syles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Resume Builder");
        controller.setStage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
