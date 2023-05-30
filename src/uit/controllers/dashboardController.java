package uit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import uit.home.database;
import uit.home.getData;
import uit.models.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class dashboardController implements Initializable {
//FXML
    @FXML
    private TableColumn<movieData, String> addMovie_durationCol;
    @FXML
    private TableColumn<movieData, String> addMovie_titleCol;
    @FXML
    private TableColumn<movieData, String> addMovie_genreCol;
    @FXML
    private TableColumn<movieData, String> addMovie_yearCol;
    @FXML
    private TableView<movieData> addMovie_table;
    @FXML
    private TableView<movieData> editScreening_table1;
    @FXML
    private TableColumn<movieData, String> editScreening_titleCol1;
    @FXML
    private TableColumn<movieData, String> editScreening_year;
    @FXML
    private TableColumn<screeningData, String> editScreening_dateCol;
    @FXML
    private TableColumn<screeningData, String> editScreening_roomCol;
    @FXML
    private TableColumn<screeningData, String> editScreening_startCol;
    @FXML
    private TableView<screeningData> editScreening_table2;
    @FXML
    private TableColumn<screeningData, String> editScreening_titleCol2;
    @FXML
    private TableColumn<screeningData, String> booking_dateCol;
    @FXML
    private TableColumn<screeningData, String> booking_roomCol;
    @FXML
    private TableColumn<screeningData, String> booking_startCol;
    @FXML
    private TableView<screeningData> booking_table;
    @FXML
    private TableColumn<screeningData, String> booking_titleCol;

    @FXML
    private Button addMovie;

    @FXML
    private AnchorPane addMovieForm;

    @FXML
    private StackPane addMoviePane;

    @FXML
    private TextField addMovie_duration;

    @FXML
    private TextField addMovie_genre;
    @FXML
    private ImageView addMovie_image;

    @FXML
    private Button addMovie_import;

    @FXML
    private TextField addMovie_search;

    @FXML
    private TextField addMovie_title;

    @FXML
    private TextField addMovie_year;
    @FXML
    private Button availableMovie_clear;

    @FXML
    private Label availableMovie_label1;

    @FXML
    private Button booking;

    @FXML
    private AnchorPane bookingForm;

    @FXML
    private StackPane bookingPane;

    @FXML
    private Button booking_buy;

    @FXML
    private Label booking_date;

    @FXML
    private ImageView booking_image;

    @FXML
    private Button booking_receipt;

    @FXML
    private Label booking_room;
    @FXML
    private TextField booking_search;

    @FXML
    private Label booking_start;

    @FXML
    private Label booking_title;

    @FXML
    private Button customer;

    @FXML
    private AnchorPane customerForm;

    @FXML
    private StackPane customerPane;

    @FXML
    private DatePicker customer_date;

    @FXML
    private TableColumn<?, ?> customer_dateCol;

    @FXML
    private TextField customer_search;

    @FXML
    private TableView<?> customer_table;

    @FXML
    private Label customer_ticket;

    @FXML
    private TableColumn<?, ?> customer_ticketCol;

    @FXML
    private Label customer_time;

    @FXML
    private TableColumn<?, ?> customer_timeCol;

    @FXML
    private Label customer_title;

    @FXML
    private TableColumn<?, ?> customer_titleCol;

    @FXML
    private Label customer_total;

    @FXML
    private TableColumn<?, ?> customer_totalCol;

    @FXML
    private Button dashboard;

    @FXML
    private AnchorPane dashboardForm;

    @FXML
    private StackPane dashboardPane;

    @FXML
    private Button editScreening;

    @FXML
    private AnchorPane editScreeningForm;

    @FXML
    private StackPane editScreeningPane;

    @FXML
    private Button editScreening_add;

    @FXML
    private DatePicker editScreening_date;

    @FXML
    private Button editScreening_delete;

    @FXML
    private ImageView editScreening_image;

    @FXML
    private TextField editScreening_room;
    @FXML
    private TextField editScreening_search1;

    @FXML
    private TextField editScreening_search2;

    @FXML
    private TextField editScreening_start;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_update;

    @FXML
    private Label username;

    @FXML
    private GridPane booking_seat;

    @FXML
    private TextField booking_name;

    @FXML
    private TextField booking_phone;

    @FXML
    private DatePicker booking_birth;

    @FXML
    private Label total;

    //Element
    private ObservableList<movieData> listAddMovie;
    private ObservableList<screeningData> listScreening2;
    private Image image;
    private Set<String> seatSet;
    List<String> vip = Arrays.asList("E2","E3","E4","F2","F3","F4");
    //Javafx.graphic
    private Parent root;
    private Scene scene;
    private Stage stage;
    //Java.sql
    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;
    private Statement statement;
    public void emptyAlert(String str){
        Alert alert;
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Message");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
    public void successAlert(String str){
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESSFUL");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
    public void existAlert(String str){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
    public void ImportImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png", "*.jpg"));

        Stage stage = (Stage) addMovieForm.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if(file != null) {
            image = new Image(file.toURI().toString(), 159, 202, true, false);
            addMovie_image.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }
    public void displayUsername(){
        username.setText(getData.username.toUpperCase());
    }
    public void switchForm(ActionEvent event){
        if(event.getSource() == dashboard) {
            dashboardForm.setVisible(true);
            dashboardPane.getStyleClass().add("navButtonHover");
            addMovieForm.setVisible(false);
            addMoviePane.getStyleClass().removeAll("navButtonHover");
            bookingForm.setVisible(false);
            bookingPane.getStyleClass().removeAll("navButtonHover");
            editScreeningForm.setVisible(false);
            editScreeningPane.getStyleClass().removeAll("navButtonHover");
            customerForm.setVisible(false);
            customerPane.getStyleClass().removeAll("navButtonHover");
        } else if (event.getSource() == addMovie) {
            dashboardForm.setVisible(false);
            dashboardPane.getStyleClass().removeAll("navButtonHover");
            addMovieForm.setVisible(true);
            addMoviePane.getStyleClass().add("navButtonHover");
            bookingForm.setVisible(false);
            bookingPane.getStyleClass().removeAll("navButtonHover");
            editScreeningForm.setVisible(false);
            editScreeningPane.getStyleClass().removeAll("navButtonHover");
            customerForm.setVisible(false);
            customerPane.getStyleClass().removeAll("navButtonHover");
        } else if (event.getSource() == booking) {
            dashboardForm.setVisible(false);
            dashboardPane.getStyleClass().removeAll("navButtonHover");
            addMovieForm.setVisible(false);
            addMoviePane.getStyleClass().removeAll("navButtonHover");
            bookingForm.setVisible(true);
            bookingPane.getStyleClass().add("navButtonHover");
            editScreeningForm.setVisible(false);
            editScreeningPane.getStyleClass().removeAll("navButtonHover");
            customerForm.setVisible(false);
            customerPane.getStyleClass().removeAll("navButtonHover");
        } else if (event.getSource() == editScreening) {
            dashboardForm.setVisible(false);
            dashboardPane.getStyleClass().removeAll("navButtonHover");
            addMovieForm.setVisible(false);
            addMoviePane.getStyleClass().removeAll("navButtonHover");
            bookingForm.setVisible(false);
            bookingPane.getStyleClass().removeAll("navButtonHover");
            editScreeningForm.setVisible(true);
            editScreeningPane.getStyleClass().add("navButtonHover");
            customerForm.setVisible(false);
            customerPane.getStyleClass().removeAll("navButtonHover");
        }else if (event.getSource() == customer) {
            dashboardForm.setVisible(false);
            dashboardPane.getStyleClass().removeAll("navButtonHover");
            addMovieForm.setVisible(false);
            addMoviePane.getStyleClass().removeAll("navButtonHover");
            bookingForm.setVisible(false);
            bookingPane.getStyleClass().removeAll("navButtonHover");
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

//Add Movie////////////////////////////////////////////////////////////////
    //SHOW TABLE LIST////////////////////////////////////////////////////////////////
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
            connect.close();
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
        image = new Image(URI, 159, 202, false, true);
        addMovie_image.setImage(image);
    }
    //INSERT////////////////////////////////////////////////////////////////
    public void insertAddMovie(){
        Alert alert;
        if(addMovie_title.getText().isEmpty()
                || addMovie_genre.getText().isEmpty()
                || addMovie_duration.getText().isEmpty()
                || addMovie_year.getText().isEmpty()) {
            emptyAlert("Fill in all the blank");
        }
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
                    existAlert(addMovie_title.getText() + " already exist");
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

                    successAlert("Successfully add " + addMovie_title.getText() + " - " + addMovie_year.getText());

                    clearAddMovie();
                    showAddMovieList();
                    connect.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //UPDATE////////////////////////////////////////////////////////////////
    public void updateAddMovie(){
        Alert alert;
        if(addMovie_title.getText().isEmpty()
                || addMovie_genre.getText().isEmpty()
                || addMovie_duration.getText().isEmpty()
                || addMovie_year.getText().isEmpty()) {
            emptyAlert("Fill in all the blank");
        }
        else {
            String sql = "UPDATE movie SET title = ?, genre = ?, duration = ?, year = ?, image = ? WHERE id = ?";
            String sql1 = "SELECT * from movie WHERE title = '" + addMovie_title.getText() + "'" +
                                                "AND year = '" + addMovie_year.getText() + "'";
            connect = database.connectDb();
            try {
                assert connect != null;
                result = connect.prepareStatement(sql1).executeQuery();
                if(result.next()){
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
                    prepare.setInt(4, Integer.parseInt(addMovie_year.getText()));
                    prepare.setString(5, URI);
                    prepare.setInt(6, getData.movieID);
                    prepare.execute();

                    successAlert("Successfully update");

                    clearAddMovie();
                    showAddMovieList();
                    connect.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //DELETE////////////////////////////////////////////////////////////////
    public void deleteAddMovie(){
        if(addMovie_title.getText().isEmpty()
                || addMovie_genre.getText().isEmpty()
                || addMovie_duration.getText().isEmpty()
                || addMovie_year.getText().isEmpty()) {
            emptyAlert("Fill in all the blank");
        } else {
            String sql = "DELETE FROM movie WHERE id = '" + getData.movieID + "'";
            connect = database.connectDb();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete movie");
            alert.setHeaderText("Are you sure want to delete this movie");
            alert.setContentText(null);

            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                try {
                    statement = connect.createStatement();
                    statement.execute(sql);
                    successAlert("Successfully delete");
                    connect.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            clearAddMovie();
            showAddMovieList();
        }
    }
    //CLEAR////////////////////////////////////////////////////////////////
    public void clearAddMovie(){
        addMovie_title.setText("");
        addMovie_genre.setText("");
        addMovie_duration.setText("");
        addMovie_year.setText("");
        addMovie_image.setImage(null);
        getData.clear();
    }
    //SEARCH////////////////////////////////////////////////////////////////
    public void searchAddMovie(){
        FilteredList<movieData> filter = new FilteredList<>(listAddMovie, e -> true);

        addMovie_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateMoviesData -> {

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }

                String keyword = newValue.toLowerCase();
                return predicateMoviesData.getTitle().toLowerCase().contains(keyword)
                        || predicateMoviesData.getGenre().toLowerCase().contains(keyword);
            });
        });

        SortedList<movieData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(addMovie_table.comparatorProperty());

        addMovie_table.setItems(filter);
    }

//Edit Screening////////////////////////////////////////////////////////////////
    //SHOW MOVIE////////////////////////////////////////////////////////////////
    public void showScreening1(){
        editScreening_titleCol1.setCellValueFactory(new PropertyValueFactory<>("title"));
        editScreening_year.setCellValueFactory(new PropertyValueFactory<>("year"));

        editScreening_table1.setItems(listAddMovie);
    }
    //SELECT MOVIE////////////////////////////////////////////////////////////////
    public void selectEditScreeningList1(){
        clearEditScreening();
        editScreening_update.setDisable(true);
        editScreening_update.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #C0C0C0");
        editScreening_delete.setDisable(true);
        editScreening_delete.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #C0C0C0");
        editScreening_add.setDisable(false);
        editScreening_add.setStyle("-fx-background-color: #00d219; -fx-border-color: #00d219");

        movieData movieD = editScreening_table1.getSelectionModel().getSelectedItem();
        int num = editScreening_table1.getSelectionModel().getSelectedIndex();

        if(num - 1 < -1) return;

        editScreening_title.setText(movieD.getTitle());
        getData.movieID = movieD.getId();
        String URI = "file:" + movieD.getImage();
        image = new Image(URI, 135, 174, false, true);
        editScreening_image.setImage(image);
    }
    //SEARCH MOVIE////////////////////////////////////////////////////////////////
    public void searchEditScreening1(){
        FilteredList<movieData> filter = new FilteredList<>(listAddMovie, e -> true);
        editScreening_search1.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateMoviesData -> {

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }

                String keyword = newValue.toLowerCase();
                return predicateMoviesData.getTitle().toLowerCase().contains(keyword);
            });
        });
        SortedList<movieData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(editScreening_table1.comparatorProperty());

        editScreening_table1.setItems(filter);
    }
    //SHOW SCREENING////////////////////////////////////////////////////////////////
    ObservableList<screeningData> screeningList2(){
        ObservableList<screeningData> listData = FXCollections.observableArrayList();
        String sql = "call screeningData()";
        connect = database.connectDb();
        screeningData screenD;
        try {
            result = connect.prepareStatement(sql).executeQuery();
            while (result.next()) {
                screenD = new screeningData(result.getInt(1),
                                            result.getInt(2),
                                            result.getString(3),
                                            result.getDate(4),
                                            result.getTime(5),
                                            result.getInt(6),
                                            result.getString(7));
                listData.add(screenD);
            }
            connect.close();
        }
        catch(Exception e){e.printStackTrace();}
        return listData;
    }
    public void showScreening2(){
        listScreening2 = screeningList2();
        editScreening_titleCol2.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        editScreening_startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        editScreening_dateCol.setCellValueFactory(new PropertyValueFactory<>("dateShow"));
        editScreening_roomCol.setCellValueFactory(new PropertyValueFactory<>("roomID"));

        editScreening_table2.setItems(listScreening2);
    }
    //SEARCH SCREEN////////////////////////////////////////////////////////////////
    public void searchEditScreening2(){
        FilteredList<screeningData> filter = new FilteredList<>(listScreening2, e -> true);
        editScreening_search2.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateScreeningData -> {

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }

                String keyword = newValue.toLowerCase();
                return predicateScreeningData.getMovieTitle().toLowerCase().contains(keyword);
            });
        });

        SortedList<screeningData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(editScreening_table2.comparatorProperty());

        editScreening_table2.setItems(filter);
    }
    //CLEAR////////////////////////////////////////////////////////////////
    public void selectEditScreeningList2(){
        editScreening_add.setDisable(true);
        editScreening_add.setStyle("-fx-background-color: #C0C0C0; -fx-border-color: #C0C0C0");
        editScreening_delete.setDisable(false);
        editScreening_delete.setStyle("-fx-background-color: #ae2d3c; -fx-border-color: #ae2d3c");
        editScreening_update.setDisable(false);
        editScreening_update.setStyle("-fx-background-color: #ffa400; -fx-border-color: #ffa400");
        screeningData screenD = editScreening_table2.getSelectionModel().getSelectedItem();
        int num  = editScreening_table2.getSelectionModel().getSelectedIndex();

        if(num - 1 < -1) return;

        editScreening_title.setText(screenD.getMovieTitle());
        editScreening_start.setText(String.valueOf(screenD.getStartTime()));
        editScreening_date.setValue(screenD.getDateShow().toLocalDate());
        editScreening_room.setText(String.valueOf(screenD.getRoomID()));

        getData.screeningID = screenD.getId();
        getData.path = screenD.getImage();
        String URI = "file:" + screenD.getImage();
        image = new Image(URI, 135, 174, false, true);
        editScreening_image.setImage(image);
    }
    //ADD////////////////////////////////////////////////////////////////
    public void clearEditScreening(){
        editScreening_start.setText("");
        editScreening_start.setPromptText("hh:mm:ss");
        editScreening_date.setValue(null);
        editScreening_date.setPromptText("mm/dd/yyyy");
        editScreening_room.setText("");
        editScreening_title.setText("Title");
        editScreening_image.setImage(null);
        getData.clear();
    }
    //SELECT SCREENING////////////////////////////////////////////////////////////////
    public void addEditScreening(){
        if(editScreening_start.getText().isEmpty() || editScreening_date.getValue() == null || editScreening_room.getText().isEmpty()) {
            emptyAlert("Fill in all the blank");
        } else {
            String sql = "SELECT * FROM screening WHERE movieID = ? AND startTime = ? AND dateShow = ? AND roomID = ?";
            connect = database.connectDb();
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, getData.movieID);
                prepare.setTime(2, Time.valueOf(editScreening_start.getText()));
                prepare.setDate(3, Date.valueOf(editScreening_date.getValue()));
                prepare.setInt(4, Integer.parseInt(editScreening_room.getText()));
                result = prepare.executeQuery();
                prepare.clearParameters();
                if(result.next()){
                    existAlert("This screening already exist");
                } else {
                    String sql2 = "INSERT INTO screening(movieID, startTime, dateShow, roomID) VALUES(?,?,?,?)";
                    prepare = connect.prepareStatement(sql2);
                    prepare.setInt(1, getData.movieID);
                    prepare.setTime(2, Time.valueOf(editScreening_start.getText()));
                    prepare.setDate(3, Date.valueOf(editScreening_date.getValue()));
                    prepare.setInt(4, Integer.parseInt(editScreening_room.getText()));
                    prepare.execute();
                    successAlert("Successfully adding new screen");
                    showScreening2();
                    clearEditScreening();
                    connect.close();
                }
            } catch (Exception e){e.printStackTrace();}
        }
    }
    //UPDATE////////////////////////////////////////////////////////////////
    public void updateEditScreening(){
        if(editScreening_start.getText().isEmpty() || editScreening_date.getValue() == null || editScreening_room.getText().isEmpty()) {
            emptyAlert("Fill in all the blank");
        } else {
            String sql = "SELECT * FROM screening WHERE movieID = ? AND startTime = ? AND dateShow = ? AND roomID = ?";
            connect = database.connectDb();
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, getData.movieID);
                prepare.setTime(2, Time.valueOf(editScreening_start.getText()));
                prepare.setDate(3, Date.valueOf(editScreening_date.getValue()));
                prepare.setInt(4, Integer.parseInt(editScreening_room.getText()));
                result = prepare.executeQuery();
                prepare.clearParameters();
                if(result.next()){
                    existAlert("This screen already exist");
                } else {
                    String sql2 = "UPDATE screening SET startTime = ?, dateShow = ?, roomID = ? WHERE id = ?";
                    prepare = connect.prepareStatement(sql2);
                    prepare.setDate(2, Date.valueOf(editScreening_date.getValue()));
                    prepare.setTime(1, Time.valueOf(editScreening_start.getText()));
                    prepare.setInt(3, Integer.parseInt(editScreening_room.getText()));
                    prepare.setInt(4, getData.screeningID);
                    prepare.execute();
                    successAlert("Successfully updating screen");
                    showScreening2();
                    clearEditScreening();
                    connect.close();
                }
            } catch (Exception e){e.printStackTrace();}
        }
    }
    //DELETE////////////////////////////////////////////////////////////////
    public void deleteEditScreening(){
        if(editScreening_start.getText().isEmpty() || editScreening_date.getValue() == null || editScreening_room.getText().isEmpty()) {
            emptyAlert("Fill in all the blank");
        } else {
            String sql = "DELETE FROM screening WHERE id = '" + getData.screeningID + "'";
            connect = database.connectDb();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete movie");
            alert.setHeaderText("Are you sure want to delete this screen");
            alert.setContentText(null);

            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                try {
                    statement = connect.createStatement();
                    statement.execute(sql);
                    successAlert("Successfully delete");
                    connect.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            clearEditScreening();
            showScreening2();
        }
    }

//Booking////////////////////////////////////////////////////////////////
    //CREATE ROOM FOR BOOKING////////////////////////////////////////////////////////////////
    public void createRoom(){
        seatSet = new HashSet<>();
        booking_seat.getChildren().clear();
        int row = 0, col = 0;
        String sql = "call getScreenAvailSeat(?)";
        connect = database.connectDb();
        try
        {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, getData.screeningID);
            result = prepare.executeQuery();
            while(result.next()){
                Button seat = new Button(result.getString(2) + result.getString(3));
                seat.setId(String.valueOf(result.getInt(1)));
                if(result.getString(4).equals("available")) {
                    seat.getStyleClass().add("seat");
                    seat.setOnAction(event -> {
                        if (seat.getStyleClass().contains("chooseSeat")) {
                            seat.getStyleClass().add("seat");
                            seat.getStyleClass().removeAll("chooseSeat");// unreserve the seat
                            if(vip.contains(seat.getText())){
                                total.setText(String.valueOf(Integer.parseInt(total.getText().replaceAll("[^\\d]", "")) - 75000) + "VND");
                            } else {
                                total.setText(String.valueOf(Integer.parseInt(total.getText().replaceAll("[^\\d]", "")) - 70000) + "VND");
                            }
                            seatSet.remove(seat.getId());
                        } else {
                            seat.getStyleClass().add("chooseSeat");
                            seat.getStyleClass().removeAll("seat");// reserve the seat
                            if(vip.contains(seat.getText())){
                                total.setText(String.valueOf(Integer.parseInt(total.getText().replaceAll("[^\\d]", "")) + 75000) + "VND");
                            } else {
                                total.setText(String.valueOf(Integer.parseInt(total.getText().replaceAll("[^\\d]", "")) + 70000) + "VND");
                            }
                            seatSet.add(seat.getId());
                        }
                    });
                }
                else {
                    seat.setDisable(true);
                    seat.getStyleClass().add("bookSeat");
                }
                booking_seat.add(seat,col,row);

                col++;
                if(col == 5) {
                    col = 0;
                    row++;
                }
            }
            connect.close();
        }catch (Exception e){e.printStackTrace();}
}
    //SHOW BOOKING////////////////////////////////////////////////////////////////
    public void showBooking(){
        booking_titleCol.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
        booking_startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        booking_dateCol.setCellValueFactory(new PropertyValueFactory<>("dateShow"));
        booking_roomCol.setCellValueFactory(new PropertyValueFactory<>("roomID"));

        booking_table.setItems(listScreening2);
    }
    //SEARCH BOOKING
    public void searchBooking(){
        FilteredList<screeningData> filter = new FilteredList<>(listScreening2, e -> true);
        booking_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateScreeningData -> {

                if(newValue.isEmpty() || newValue == null){
                    return true;
                }

                String keyword = newValue.toLowerCase();
                return predicateScreeningData.getMovieTitle().toLowerCase().contains(keyword);
            });
        });

        SortedList<screeningData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(booking_table.comparatorProperty());

        booking_table.setItems(filter);
    }
    //SELECT BOOKING////////////////////////////////////////////////////////////////
    public void selectBooking(){
        getData.clear();
        screeningData screenD = booking_table.getSelectionModel().getSelectedItem();
        int num  = booking_table.getSelectionModel().getSelectedIndex();

        if(num - 1 < -1) return;

        booking_title.setText(screenD.getMovieTitle());
        booking_start.setText(String.valueOf(screenD.getStartTime()));
        booking_date.setText(String.valueOf(screenD.getDateShow().toLocalDate()));
        booking_room.setText(String.valueOf(screenD.getRoomID()));

        getData.screeningID = screenD.getId();
        getData.path = screenD.getImage();
        String URI = "file:" + screenD.getImage();
        image = new Image(URI, 177, 203, false, true);
        booking_image.setImage(image);

        createRoom();
    }
    //
    public void customerSearchBooking(){
        String sql = "SELECT name, birth FROM customer WHERE phone = ?";
        connect = database.connectDb();
            booking_phone.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1,newValue);
                    result = prepare.executeQuery();
                    if(result.next()){
                        booking_name.setText(result.getString(1));
                        booking_name.setEditable(false);
                        booking_birth.setValue(result.getDate(2).toLocalDate());
                        booking_birth.setEditable(false);
                    } else {
                        booking_name.setText("");
                        booking_name.setEditable(true);
                        booking_birth.setValue(null);
                        booking_birth.setEditable(true);
                    }
                } catch (SQLException e) {throw new RuntimeException(e);}
            });
    }
    //
    public void buyBooking(){
        Iterator<String> iterator = seatSet.iterator();
        if(getData.screeningID == 0) { emptyAlert("Select screening first");}
        else if(booking_name.getText().isEmpty()
                || booking_phone.getText().isEmpty()
                || booking_birth.getValue() == null){
            emptyAlert("Fill all the information");
        }
        else{
            String sql1 = "call checkCus(?,?,?)";
            String sql2 = "INSERT INTO booking(customerID, screeningID) VALUES (?,?)";
            String sql3 = "INSERT INTO booking_detail VALUES (?,?,?)";
            connect = database.connectDb();
            try {
                assert connect != null;
                prepare = connect.prepareStatement(sql1);
                prepare.setString(1, booking_name.getText());
                prepare.setString(2, booking_phone.getText());
                prepare.setDate(3, Date.valueOf(booking_birth.getValue()));
                prepare.execute();
                prepare.close();

                prepare = connect.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                prepare.setString(1, booking_phone.getText());
                prepare.setInt(2, getData.screeningID);
                prepare.execute();
                result = prepare.getGeneratedKeys();
                if (result.next())
                {
                    getData.bookingID = result.getInt(1);
                }
                prepare.close();

                while (iterator.hasNext()){
                    prepare = connect.prepareStatement(sql3);
                    prepare.setInt(1, getData.bookingID);
                    prepare.setInt(2, Integer.parseInt(iterator.next()));
                    prepare.setInt(3, getData.screeningID);
                    prepare.execute();
                    prepare.clearParameters();
                }

                successAlert("Successfully booking");
                createRoom();

                connect.close();
            } catch (Exception e) {e.printStackTrace();}
        }
    }
//Customers

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //displayUsername();
        showAddMovieList();
        showScreening1();
        showScreening2();
        showBooking();
    }
}
