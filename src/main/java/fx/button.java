package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;


public class button extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        DropShadow shadow = new DropShadow();
        Button button = new Button();

        button.setText("Decline");
        button.setOnMouseEntered((event)->{
            button.setEffect(shadow);
        });
        button.setOnMouseExited(event->{
            button.setEffect(null);
        });
        button.setLayoutX(100);
        button.setLayoutY(100);
        group.getChildren().add(button);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.show();
        FXMLLoader loader = new FXMLLoader();

        stage.setOnCloseRequest(event -> {
        });
    }
}
