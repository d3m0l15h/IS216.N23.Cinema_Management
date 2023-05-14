package uit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;


public class loginControllers {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;
    @FXML
    private AnchorPane signInPane;
    @FXML
    private AnchorPane signUpPane;
    private Stage stage;
    private Parent root;
    private Scene scene;
    public void login(ActionEvent event) throws Exception{
        String userName = usernameField.getText();
        String password = passwordField.getText();
        if(userName.equals("admin") && password.equals("admin")) {
            root = FXMLLoader.load(getClass().getResource("../../resources/fxml/home.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else
            statusLabel.setText("Wrong username or password");
    }
    public void handleButtonEvent(ActionEvent actionEvent) {
        signUpPane.toFront();
    }
    public void handleMouseEvent(MouseEvent mouseEvent) {
        signInPane.toFront();
    }
}
