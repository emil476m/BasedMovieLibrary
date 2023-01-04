package GUI.Controllers;

import GUI.Models.ModelsHandler;
import GUI.Util.ExceptionHandler;
import GUI.Util.ModalOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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

    private ExceptionHandler exceptionHandler;

    public MainController() {
        exceptionHandler = new ExceptionHandler();
    }

    @FXML
    private void handleAddMovie(ActionEvent actionEvent) {
    }
    @FXML
    private void handleOpenCategories(ActionEvent actionEvent) {
        ModalOpener.openModal(getClass().getResource("/GUI/Views/CategoriesView.fxml"), "Categories", "Failed to open categories");
    }
    @FXML
    private void handleRemoveMovie(ActionEvent actionEvent) {
    }
    @FXML
    private void handleSearch(ActionEvent actionEvent) {
    }
}
