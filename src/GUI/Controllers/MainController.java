package GUI.Controllers;

import BE.Category;
import BE.Movie;
import GUI.Models.MovieModel;
import GUI.Util.ExceptionHandler;
import GUI.Models.ModelsHandler;
import GUI.Util.ModalOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController extends BaseController {
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
    
    private MovieModel movieModel;

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

    /**
     * searches for movies when the search button is pressed and if there has been made a search it clears it if you press the same button
     * @param actionEvent
     */
    @FXML
    private void handleSearch(ActionEvent actionEvent) {
        String query = txtfieldSearch.getText().toLowerCase();
        if(txtfieldSearch.getText() != null && btnsearch.getText().equals("Search"))
        {
            tbvMovies.setItems(movieModel.getSearchResults(query));
            btnsearch.setText("Clear");
        }
        else if (btnsearch.getText().equals("Clear"))
        {
            txtfieldSearch.clear();
            btnsearch.setText("Search");
            tbvMovies.setItems(movieModel.getMovieObservableList());
        }
    }

    /**
     * searches for movies with a given parameter if the user presses the enter key
     * @param keyEvent
     */
    @FXML
    private void searchOnButtonPress(KeyEvent keyEvent) {
        String query = txtfieldSearch.getText().toLowerCase();
        if(txtfieldSearch.getText() != null && keyEvent.getCode() == KeyCode.ENTER)
        {
            btnsearch.setText("Clear");
            tbvMovies.setItems(movieModel.getSearchResults(query));
        }
    }

    @Override
    public void setup() {
        initializeMovies();
    }

    private void initializeMovies(){
        movieModel = getModelsHandler().getMovieModel();
        tbvMovies.setItems(getModelsHandler().getMovieModel().getMovieObservableList());
        clmTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<>("CategoryNames"));
        clmIMDB.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        clmPRating.setCellValueFactory(new PropertyValueFactory<>("PRating"));
    }

}
