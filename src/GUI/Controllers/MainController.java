package GUI.Controllers;

import BE.Category;
import BE.Movie;
import GUI.Util.ExceptionHandler;
import GUI.Models.ModelsHandler;
import GUI.Util.ModalOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController extends BaseController {
    public Button btnIMDB;
    @FXML
    private Button btnEditPRating;
    @FXML
    private Button btnClose;
    @FXML
    private TableView<Movie> tbvMovies;
    @FXML
    private TableColumn<Movie, String> clmTitle;
    @FXML
    private TableColumn<Movie, String> clmCategory;
    @FXML
    private TableColumn<Movie, Double> clmIMDB;
    @FXML
    private TableColumn<Movie, Double> clmPRating;
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

    public MainController() {

    }



    @FXML
    private void handleAddMovie(ActionEvent actionEvent) {
        //Load the new stage & view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/CreateMovieView.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            ExceptionHandler.displayError(new Exception("Failed to open movie creator", e));
        }

        Stage stage = new Stage();
        stage.setTitle("Add new movie");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        CreateMovieController controller = loader.getController();
        controller.setModel(getModelsHandler());
        controller.setup();
    }
    @FXML
    private void handleOpenCategories(ActionEvent actionEvent) {
        ModalOpener.openModal(getClass().getResource("/GUI/Views/CategoriesView.fxml"),
                "Categories",
                getModelsHandler(),
                "Failed to open categories");
    }
    @FXML
    private void handleRemoveMovie(ActionEvent actionEvent) {
    }
    @FXML
    private void handleSearch(ActionEvent actionEvent) {
    }

    @Override
    public void setup() {
        initializeMovies();
    }

    /**
     * shows the movies in the view.
     */
    private void initializeMovies(){
        tbvMovies.setItems(getModelsHandler().getMovieModel().getMovieObservableList());
        clmTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<>("CategoryNames"));
        clmIMDB.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        clmPRating.setCellValueFactory(new PropertyValueFactory<>("PRating"));
    }

    @FXML
    private void handleEditPRating(ActionEvent actionEvent) {
        Movie movie = tbvMovies.getSelectionModel().getSelectedItem();
        TextInputDialog dialog = new TextInputDialog("" + movie.getPRating());
        dialog.setTitle("Edit Personal Rating");
        dialog.setHeaderText("Rate movie: " + movie.getTitle());
        dialog.setContentText("What would you rate the movie?");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            //TODO make a check for int with alert box
            Double rating = Double.parseDouble(result.get());
            try {
                getModelsHandler().getMovieModel().editPRating(movie, rating);
                tbvMovies.refresh();
            } catch (Exception e) {
                ExceptionHandler.displayError(e);
            }
        }
    }

    @FXML
    private void handleClose(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void handleShowIMDB(ActionEvent actionEvent) {
    }
}
