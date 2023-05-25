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
import uit.models.movieData;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
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
            image = new Image(file.toURI().toString(), 165, 210, true, false);
            addMovie_image.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }
//FXML
    @FXML
    private TextField addMovie_duration;
    @FXML
    private TextField addMovie_search;
    @FXML
    private TextField addMovie_title;
    @FXML
    private TextField addMovie_genre;
    @FXML
    private TableColumn<movieData, String> addMovie_durationCol;
    @FXML
    private TableColumn<movieData, String> addMovie_titleCol;
    @FXML
    private Button addMovie_import;
    @FXML
    private TextField addMovie_year;
    @FXML
    private TableColumn<movieData, String> addMovie_genreCol;
    @FXML
    private TableColumn<movieData, String> addMovie_yearCol;
    @FXML
    private TableView<movieData> addMovie_table;
    @FXML
    private ImageView addMovie_image;
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
                movieD = new movieData(result.getInt(1),
                                        result.getString(2),
                                        result.getString(3),
                                        result.getInt(4),
                                        result.getString(6),
                                        result.getInt(5));
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
        addMovie_yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        addMovie_table.setItems(listAddMovie);
    }
    public void selectAddMovieList(){
        movieData movieD = addMovie_table.getSelectionModel().getSelectedItem();
        int num  = addMovie_table.getSelectionModel().getSelectedIndex();

        if(num - 1 < -1) return;

        addMovie_title.setText(movieD.getTitle());
        addMovie_genre.setText(movieD.getGenre());
        addMovie_duration.setText(String.valueOf(movieD.getDuration()));
        addMovie_year.setText(String.valueOf(movieD.getYear()));
        getData.movieID = movieD.getId();
        getData.path = movieD.getImage();
        String URI = "file:" + movieD.getImage();
        image = new Image(URI, 165, 210, false, true);
        addMovie_image.setImage(image);
    }
    //INSERT
    public void insertAddMovie(){
        Alert alert;
        if(addMovie_title.getText().isEmpty()
                || addMovie_genre.getText().isEmpty()
                || addMovie_duration.getText().isEmpty()
                || addMovie_year.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Message");
            alert.setHeaderText(null);
            alert.setContentText("Empty field");
            alert.showAndWait();}
        else {
            String sql = "INSERT INTO movie(title, genre, duration, image, year) VALUES(?,?,?,?,?)";
            String sql1 = "SELECT title, year from movie WHERE title = '" + addMovie_title.getText() + "'" +
                    "AND year = '" + addMovie_year.getText() + "'";
            connect = database.connectDb();
            try {
                assert connect != null;
                statement = connect.createStatement();
                result = statement.executeQuery(sql1);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(addMovie_title.getText() + " already exist");
                    alert.showAndWait();
                } else {
                    String URI = getData.path;
                    URI = URI.replace("\\", "\\\\");

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addMovie_title.getText());
                    prepare.setString(2, addMovie_genre.getText());
                    prepare.setInt(3, Integer.parseInt(addMovie_duration.getText()));
                    prepare.setString(4, URI);
                    prepare.setInt(5, Integer.parseInt(addMovie_year.getText()));

                    prepare.execute();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCCESSFUL");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully add new movie");
                    alert.showAndWait();

                    clearAddMovie();
                    showAddMovieList();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //UPDATE
    public void updateAddMovie(){
        Alert alert;
        if(addMovie_title.getText().isEmpty()
                || addMovie_genre.getText().isEmpty()
                || addMovie_duration.getText().isEmpty()
                || addMovie_year.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Message");
            alert.setHeaderText(null);
            alert.setContentText("Empty field");
            alert.showAndWait();
        }
        else {
            String sql = "UPDATE movie SET title = ?, genre = ?, duration = ?, year = ?, image = ? WHERE id = ?";
            connect = database.connectDb();
            try {
                String URI = getData.path;
                URI = URI.replace("\\", "\\\\");

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, addMovie_title.getText());
                prepare.setString(2, addMovie_genre.getText());
                prepare.setInt(3, Integer.parseInt(addMovie_duration.getText()));
                prepare.setInt(4, Integer.parseInt(addMovie_year.getText()));
                prepare.setString(5, URI);
                prepare.setInt(6, getData.movieID);
                prepare.execute();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESSFUL");
                alert.setHeaderText(null);
                alert.setContentText("Successfully update");
                alert.showAndWait();

                clearAddMovie();
                showAddMovieList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //DELETE
    public void deleteAddMovie(){
        String sql = "DELETE FROM movie WHERE id = '" + getData.movieID + "'";
        connect = database.connectDb();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete movie");
        alert.setHeaderText("Are you sure want to delete this movie");
        alert.setContentText(null);

        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK) {
            try {
                statement = connect.createStatement();
                statement.execute(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESSFUL");
                alert.setHeaderText(null);
                alert.setContentText("Successfully delete");
                alert.showAndWait();

            }
            catch (Exception e) {e.printStackTrace();}

        }
        clearAddMovie();
        showAddMovieList();
    }
    //CLEAR
    public void clearAddMovie(){
        addMovie_title.setText("");
        addMovie_genre.setText("");
        addMovie_duration.setText("");
        addMovie_year.setText("");
        addMovie_image.setImage(new Image("resources/images/image_96px.png"));
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
