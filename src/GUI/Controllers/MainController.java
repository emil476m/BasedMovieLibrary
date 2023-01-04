package GUI.Controllers;

import GUI.Models.ModelsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {
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

    private ModelsHandler modelsHandler;

    public MainViewController() {
        modelsHandler = new ModelsHandler();
    }

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
