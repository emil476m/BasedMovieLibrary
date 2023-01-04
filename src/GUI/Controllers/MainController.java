package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    @Override
    public void setup() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
