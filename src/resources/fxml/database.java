package resources.fxml;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
public class database {
    public static Connection connectDB(){
        try{
            Class.forName(null)
            Connection connect = DriverManager.getConnection(null, null, null)
        }catch(Exception e){e.printStackTrace(null);}
}
