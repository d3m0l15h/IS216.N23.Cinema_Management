package uit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import uit.home.database;
import uit.home.getData;
import uit.home.movieData;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
public class dashboardController implements Initializable {
//Element
    private ObservableList<movieData> listAddMovie;
    private Image image;
//Javafx.graphic
    private Parent root;
    private Scene scene;
    private Stage stage;
//Java.sql
    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;
    private Statement statement;
    public void ImportImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*png", "*jpg"));

        Stage stage = (Stage) addMovieForm.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if(file != null) {
            image = new Image(file.toURI().toString(), 157, 197, true, false);
            addMovie_image.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }
//FXML
    @FXML
    private TextField addMovie_duration;
    @FXML
    private Button addMovie_clear;
    @FXML
    private TextField addMovie_search;
    @FXML
    private TextField addMovie_title;
    @FXML
    private TextField addMovie_genre;
    @FXML
    private Button addMovie_add;
    @FXML
    private Button addMovie_update;
    @FXML
    private TableColumn<movieData, String> addMovie_durationCol;
    @FXML
    private TableColumn<movieData, String> addMovie_titleCol;
    @FXML
    private Button addMovie_import;
    @FXML
    private TextField addMovie_published;
    @FXML
    private TableColumn<movieData, String> addMovie_genreCol;
    @FXML
    private TableColumn<movieData, String> addMovie_publishedCol;
    @FXML
    private TableView<movieData> addMovie_table;
    @FXML
    private ImageView addMovie_image;
    @FXML
    private Button addMovie_delete;
    @FXML
    private Label username;
    @FXML
    private Button customer;
    @FXML
    private Button dashboard;
    @FXML
    private Button editScreening;
    @FXML
    private Button addMovie;
    @FXML
    private Button availableMovie;
    @FXML
    private AnchorPane dashboardForm;
    @FXML
    private AnchorPane addMovieForm;
    @FXML
    private AnchorPane availableMovieForm;
    @FXML
    private AnchorPane editScreeningForm;
    @FXML
    private AnchorPane customerForm;
    @FXML
    private StackPane dashboardPane;
    @FXML
    private StackPane addMoviePane;
    @FXML
    private StackPane availableMoviePane;
    @FXML
    private StackPane editScreeningPane;
    @FXML
    private StackPane customerPane;
    public void displayUsername(){
        username.setText(getData.username.toUpperCase());
    }
    public void switchForm(ActionEvent event){
        if(event.getSource() == dashboard) {
            dashboardForm.setVisible(true);
            dashboardPane.getStyleClass().add("navButtonHover");
            addMovieForm.setVisible(false);
            addMoviePane.getStyleClass().removeAll("navButtonHover");
            availableMovieForm.setVisible(false);
            availableMoviePane.getStyleClass().removeAll("navButtonHover");
            editScreeningForm.setVisible(false);
            editScreeningPane.getStyleClass().removeAll("navButtonHover");
            customerForm.setVisible(false);
            customerPane.getStyleClass().removeAll("navButtonHover");
        } else if (event.getSource() == addMovie) {
            dashboardForm.setVisible(false);
            dashboardPane.getStyleClass().removeAll("navButtonHover");
            addMovieForm.setVisible(true);
            addMoviePane.getStyleClass().add("navButtonHover");
            availableMovieForm.setVisible(false);
            availableMoviePane.getStyleClass().removeAll("navButtonHover");
            editScreeningForm.setVisible(false);
            editScreeningPane.getStyleClass().removeAll("navButtonHover");
            customerForm.setVisible(false);
            customerPane.getStyleClass().removeAll("navButtonHover");
        } else if (event.getSource() == availableMovie) {
            dashboardForm.setVisible(false);
            dashboardPane.getStyleClass().removeAll("navButtonHover");
            addMovieForm.setVisible(false);
            addMoviePane.getStyleClass().removeAll("navButtonHover");
            availableMovieForm.setVisible(true);
            availableMoviePane.getStyleClass().add("navButtonHover");
            editScreeningForm.setVisible(false);
            editScreeningPane.getStyleClass().removeAll("navButtonHover");
            customerForm.setVisible(false);
            customerPane.getStyleClass().removeAll("navButtonHover");
        } else if (event.getSource() == editScreening) {
            dashboardForm.setVisible(false);
            dashboardPane.getStyleClass().removeAll("navButtonHover");
            addMovieForm.setVisible(false);
            addMoviePane.getStyleClass().removeAll("navButtonHover");
            availableMovieForm.setVisible(false);
            availableMoviePane.getStyleClass().removeAll("navButtonHover");
            editScreeningForm.setVisible(true);
            editScreeningPane.getStyleClass().add("navButtonHover");
            customerForm.setVisible(false);
            customerPane.getStyleClass().removeAll("navButtonHover");
        }else if (event.getSource() == customer) {
            dashboardForm.setVisible(false);
            dashboardPane.getStyleClass().removeAll("navButtonHover");
            addMovieForm.setVisible(false);
            addMoviePane.getStyleClass().removeAll("navButtonHover");
            availableMovieForm.setVisible(false);
            availableMoviePane.getStyleClass().removeAll("navButtonHover");
            editScreeningForm.setVisible(false);
            editScreeningPane.getStyleClass().removeAll("navButtonHover");
            customerForm.setVisible(true);
            customerPane.getStyleClass().add("navButtonHover");
        }
    }
    public void signOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../../resources/fxml/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
//Add Movie
    //SHOW TABLE LIST
    public ObservableList<movieData> addMovieList(){
        ObservableList<movieData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";
        connect = database.connectDb();
        movieData movieD;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                movieD = new movieData(result.getString(1),
                                        result.getString(2),
                                        result.getString(3),
                                        result.getString(4),
                                        result.getDate(6));
                listData.add(movieD);
            }
        } catch (Exception e){e.printStackTrace();}
        return listData;
    }
    public void showAddMovieList(){
        listAddMovie = addMovieList();
        addMovie_titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovie_genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovie_durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovie_publishedCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        addMovie_table.setItems(listAddMovie);
    }
    public void selectAddMovieList(){
        movieData movieD = addMovie_table.getSelectionModel().getSelectedItem();
        int num  = addMovie_table.getSelectionModel().getSelectedIndex();

        if(num - 1 < -1) return;

        addMovie_title.setText(movieD.getTitle());
        addMovie_genre.setText(movieD.getGenre());
        addMovie_duration.setText(movieD.getDuration());
        addMovie_published.setText(movieD.getDate().toString());

        String uri = "file:" + movieD.getImage();
        image = new Image(uri, 157, 197, false, true);
        addMovie_image.setImage(image);
    }
    //INSERT
    public void insertAddMovie(){
        String sql = "INSERT INTO movie";
        String sql1 = "SELECT movieTitle, date from movie";
        connect = database.connectDb();
        Alert alert;
        try {
            assert connect != null;
            statement = connect.createStatement();
            prepare = connect.prepareStatement(sql1);
            result = prepare.executeQuery();
            if(result.next()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(addMovie_title.getText() + " already exist");
                alert.showAndWait();
            } else {
                statement.execute(sql1);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESSFUL");
                alert.setHeaderText(null);
                alert.setContentText("Successfully add new movie");
                alert.showAndWait();
            }
        }catch ( Exception e) {e.printStackTrace();}
    }
//Available Movies

//Edit Screening

//Customers

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        showAddMovieList();
    }
}
