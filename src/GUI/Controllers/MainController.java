package GUI.Controllers;

import BE.Category;
import BE.Movie;
import GUI.Models.MovieModel;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import GUI.Models.ModelsHandler;
import GUI.Util.ModalOpener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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

    private Movie selectedMovie;
    private File directory;

    /**
     * opens the view where we can add a movie.
     * @param actionEvent
     */
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

    /**
     * Tries to delete the selected movie.
     * @param actionEvent
     */
    @FXML
    private void handleRemoveMovie(ActionEvent actionEvent) {
        Movie selectedMovie = tbvMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null && AlertOpener.confirm("Remove movie?",
                "Are you sure you want to remove " + selectedMovie.getTitle() + "?")) {
            try {
                getModelsHandler().getMovieModel().deleteMovie(selectedMovie);
            }
            catch (Exception e) {
                ExceptionHandler.displayError(e);
            }
        }
    }

    private void search(String search) {
        String query = search.toLowerCase();
        tbvMovies.setItems(getModelsHandler().getMovieModel().getSearchResults(query));
    }

    /**
     * searches for movies when the search button is pressed and if there has been made a search it clears it if you press the same button
     * @param actionEvent
     */
    @FXML
    private void handleSearch(ActionEvent actionEvent) {
        String search = txtfieldSearch.getText();

        if(search != null) search(search);
    }

    @FXML
    private void onClearSearch(ActionEvent actionEvent) {
        if (txtfieldSearch.getText() != null) {
            txtfieldSearch.setText("");
            search("");
        }
    }

    /**
     * searches for movies with a given parameter if the user presses the enter key
     * @param keyEvent
     */
    @FXML
    private void searchOnButtonPress(KeyEvent keyEvent) {
        String query = txtfieldSearch.getText();

        if(query != null && keyEvent.getCode() == KeyCode.ENTER) search(query);
    }

    @Override
    public void setup() {
        initializeMovies();
        setTbvMoviesChangeListener();
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

    /**
     * Opens an Input alert box where you can write your personal rating.
     * @param actionEvent
     */
    @FXML
    private void handleEditPRating(ActionEvent actionEvent) {
        Movie movie = tbvMovies.getSelectionModel().getSelectedItem();

        if (movie != null) {
            TextInputDialog dialog = new TextInputDialog("" + movie.getPRating());
            dialog.setTitle("Edit Personal Rating");
            dialog.setHeaderText("Rate movie: " + movie.getTitle());
            dialog.setContentText("What would you rate the movie?");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()){
                if (!isRatingInputValid(dialog.getEditor().getText())) {
                    dialog.getEditor().setText("");
                    AlertOpener.validationError("Personal rating must be between 0.0 and 10.0");

                    // Opens the dialog again if the personal rating was invalid.
                    handleEditPRating(new ActionEvent());
                }
                else {
                    double rating = Double.parseDouble(result.get());

                    try {
                        getModelsHandler().getMovieModel().editPRating(movie, rating);
                        tbvMovies.refresh();
                    } catch (Exception e) {
                        ExceptionHandler.displayError(e);
                    }
                }
            }
        }
    }

    /**
     * closes the program, when you press the close button.
     * @param actionEvent
     */
    @FXML
    private void handleClose(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    /**
     * opens the standard browser of the computer on a IMDB search for the selected movie
     * @param actionEvent
     */
    public void handleShowIMDB(ActionEvent actionEvent) {
        selectedMovie = tbvMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            Desktop desktop = Desktop.getDesktop();
            String link = "http://www.imdb.com/find?s=tt&q=";
            String title = selectedMovie.getTitle().replace(" ", "");
            if(desktop.isSupported(Desktop.Action.BROWSE))
            {
                try
                {
                    desktop.browse(URI.create(link+title));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    ExceptionHandler.displayError(new Exception("Failed to open IMDB", e));
                }
            }
        }
    }

    /**
     * Disables/enables the remove movie button,
     * the open IMDB button, and the edit personal rating button,
     * if no movie has been selected.
     */
    private void setTbvMoviesChangeListener() {
        tbvMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            boolean disable = newValue == null;

            btnRemoveMovie.setDisable(disable);
            btnIMDB.setDisable(disable);
            btnEditPRating.setDisable(disable);
        });
    }

    /**
     * plays the selected movie on a double click
     * @param mouseEvent
     */
    @FXML
    private void playMovie(MouseEvent mouseEvent)
    {
        if(mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2)
        {
            openMediaPlayer();
        }
    }

    private void openMediaPlayer()
    {
        selectedMovie = (Movie) tbvMovies.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            directory = new File(selectedMovie.getFilePath());
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                try {
                    desktop.open(directory.getAbsoluteFile());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "the chosen movie's file does not exist", ButtonType.CLOSE);
                    alert.showAndWait();
                }
            }
        }
    }
}
