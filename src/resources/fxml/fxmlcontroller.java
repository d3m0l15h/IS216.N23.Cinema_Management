package resources.fxml;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class fxmlcontroller {
    private PasswordField signIn_password;
    private TextField signIn_username;
    private TextField signUp_username;
    private PasswordField signUp_password;
    private PasswordField signUp_confirmPassword;
    private TextField signUp_email;
    private TextField signUp_phoneNumber;
    private TextField signUp_fullName;
    private TextField signUp_address;
    
    private Button signUp_btn;
    private Button signIn_btn;
    private Hyperlink signUp_alreadyHaveAccount;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public void switchForm(ActionEvent e){
        if(e.getSource() == signIn_createAccount){
            signIn_form.setVisible(false);
            signUp_form.setVisible(true);
        }else if(e.getSource() == signUp_alreadyHaveAccount){
            signIn_form.setVisible(true);
            signUp_form.setVisible(false);
        }
    }
    public void signIn_close(){
        System.exit(0);
    }
    public void signin(){
        String  sql = "SELECT * FROM admin WHERE username =? and password =?";
        connect = database.connectDB();
        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1,signIn_username.getText());   
            prepare.setString(2, signIn_password.getText());     
            result = prepare.executeQuery();
            Alert alert;
            //CHECK IF USERNAME AND PASSWORD IS EMPTY
            if(signIn_username.getText().isEmpty()||signIn_password.getText().isEmpty() ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please fill in all fields");
                alert.showAndWait();
            }
            else{
                
            }
        }
    }
}
