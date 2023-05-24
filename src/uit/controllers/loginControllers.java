package uit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import uit.home.database;
import uit.home.getData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class loginControllers {
    @FXML
    private TextField signIn_username;
    @FXML
    private PasswordField signIn_password;
    @FXML
    private Label signIn_statusLabel;
    @FXML
    private AnchorPane signInPane;
    @FXML
    private AnchorPane signUpPane;
    @FXML
    private TextField signUp_username;
    @FXML
    private TextField signUp_email;
    @FXML
    private PasswordField signUp_password;
    @FXML
    private TextField signUp_phone;
    @FXML
    private PasswordField signUp_confirm;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label confirmLabel;
    @FXML
    private Label signUp_statusLabel;
    @FXML
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Stage stage;
    private Parent root;
    private Scene scene;
    public boolean isValidEmail(){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");

        Matcher match = pattern.matcher(signUp_email.getText());

        if(match.find() && match.group().equals(signUp_email.getText())){
            return true;
        }
        emailLabel.setText("Invalid email");
        return false;
    }
    public void signUp(){
        int count = 0;
        String sql = "INSERT INTO admin (email,username,password,phone) VALUES (?,?,?,?)";
        String sql2 = "SELECT username FROM admin WHERE username=?";
        connect = database.connectDb();

        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql2);
            prepare.setString(1,signUp_username.getText());
            if(signUp_password.getText().isEmpty() || signUp_username.getText().isEmpty()
                    || signUp_email.getText().isEmpty() || signUp_phone.getText().isEmpty()
                    || signUp_confirm.getText().isEmpty()) {
                signUp_statusLabel.setText("One of the field is empty");
                signUp_statusLabel.setTextFill(Color.INDIANRED);
                count++;
            } else {signUp_statusLabel.setText("");}
            if(prepare.executeQuery().next()) {
                usernameLabel.setText("Username in used");
                count++;
            } else {usernameLabel.setText("");}
            if(!isValidEmail()) count++;
                else emailLabel.setText("");
            if(!signUp_password.getText().equals(signUp_confirm.getText())) {
                confirmLabel.setText("Password not match");
                count++;
            } else {confirmLabel.setText("");}
            if(count == 0) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, signUp_email.getText());
                prepare.setString(2, signUp_username.getText());
                prepare.setString(3,signUp_password.getText());
                prepare.setString(4,signUp_phone.getText());
                prepare.execute();
                signUp_statusLabel.setText("Sign up success");
                signUp_statusLabel.setTextFill(Color.GREEN);
                signUp_email.setText("");
                signUp_username.setText("");
                signUp_phone.setText("");
                signUp_password.setText("");
                signUp_confirm.setText("");
            }
        }catch (Exception e) {e.printStackTrace();}
    }
    public void login(ActionEvent event) {
        String sql = "SELECT * FROM admin WHERE username=? AND password=?";
        connect = database.connectDb();
        try {
            assert connect != null;
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signIn_username.getText());
            prepare.setString(2, signIn_password.getText());

            result = prepare.executeQuery();
            if (result.next()) {
                getData.username = signIn_username.getText();
                root = FXMLLoader.load(getClass().getResource("../../resources/fxml/dashboard.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            } else
                signIn_statusLabel.setText("Wrong username or password");
        } catch (Exception e) {e.printStackTrace();}
    }
    public void handleButtonEvent(ActionEvent actionEvent) {signUpPane.toFront();}
    public void handleMouseEvent(MouseEvent mouseEvent) {
        signInPane.toFront();
    }
}
