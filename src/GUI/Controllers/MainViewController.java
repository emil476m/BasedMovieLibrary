package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainViewController {
    @FXML
    private TableView tbvMovies;
    @FXML
    private TableColumn tbvTitle;
    @FXML
    private TableColumn tbvCategory;
    @FXML
    private TableColumn tbvIMDB;
    @FXML
    private TableColumn tbvPRating;
    @FXML
    private Button btnCategories;
    @FXML
    private Button btnAddMovie;
    @FXML
    private Button btnRemoveMovie;
    @FXML
    private TextField txtfSearch;
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
}
