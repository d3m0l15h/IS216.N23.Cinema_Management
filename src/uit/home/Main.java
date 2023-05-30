package uit.home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../../resources/fxml/dashboard.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("../resources/login.css").toExternalForm());
        Image icon = new Image("resources/images/popcorn.png");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("JAVa");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
