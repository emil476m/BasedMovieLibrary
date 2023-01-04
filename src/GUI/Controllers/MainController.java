package GUI.Controllers;

import BE.Movie;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BaseController implements Initializable {
    @FXML
    private TableView tbvMovies;
    @FXML
    private TableColumn clmTitle;
    @FXML
    private TableColumn clmCategory;
    @FXML
    private TableColumn clmIMDB;
    @FXML
    private TableColumn clmPRating;
    @FXML
    private Button btnCategories;
    @FXML
    private Button btnAddMovie;
    @FXML
    private Button btnRemoveMovie;
    @FXML
    private TextField txtfieldSearch;
    @FXML
    private Button btnsearch;

    private ExceptionHandler exceptionHandler;
    private Movie selectedMovie;
    private File directory;
    @FXML
    private void handleAddMovie(ActionEvent actionEvent) {
    }
    @FXML
    private void handleOpenCategories(ActionEvent actionEvent) {
    }
    @FXML
    private void handleRemoveMovie(ActionEvent actionEvent) {
    }
    @FXML
    private void handleSearch(ActionEvent actionEvent) {
    }

    @FXML
    private void playMovie(MouseEvent mouseEvent)
    {
        if(mouseEvent.getButton() == MouseButton.PRIMARY)
        {
            openMediaPlayer();
        }
    }

    private void openMediaPlayer()
    {
       selectedMovie = (Movie) tbvMovies.getSelectionModel().getSelectedItem()
        directory = new File(selectedMovie.getFilePath());
        Desktop desktop = Desktop.getDesktop();
        if(desktop.isSupported(Desktop.Action.OPEN))
        {
            try {
                desktop.open(directory.getAbsoluteFile());
            }
            catch (Exception ex)
            {
                exceptionHandler.displayError(ex);
            }
        }
    }

    @Override
    public void setup() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
