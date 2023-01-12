package GUI.Controllers;

import BE.Movie;
import GUI.Util.ConfirmOK;
import GUI.Util.ExceptionHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DeleteMovieReminderController extends BaseController {
    @FXML
    private Button CloseBtn;
    @FXML
    private Button DeleteBtn;
    @FXML
    private TableView<Movie> TbvDMovies;
    @FXML
    private TableColumn<Movie, String> ColMovieTitle;
    @FXML
    private TableColumn<Movie, String> ColMovieCategory;
    @FXML
    private TableColumn<Movie, Double> ColImdb;
    @FXML
    private TableColumn<Movie, Double> ColMyRating;

    @Override
    public void setup() {
        initializeMoviesToDelete();
    }

    private void initializeMoviesToDelete()
    {
        TbvDMovies.setItems(getModelsHandler().getMovieModel().getMoviesToDeleteObservableList());
        ColMovieTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ColMovieCategory.setCellValueFactory(new PropertyValueFactory<>("CategoryNames"));
        ColImdb.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        ColMyRating.setCellValueFactory(new PropertyValueFactory<>("PRating"));

        setTbvDMoviesChangeListener();
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void DeleteMovie(ActionEvent event) {
        Movie selectedMovie = TbvDMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            try {
                if(ConfirmOK.confirm("Are you sure?", "Do you want to delete the movie"))
                {
                    getModelsHandler().getMovieModel().deleteMovieToDelete(selectedMovie);
                }
            }
            catch (Exception e)
            {
                ExceptionHandler.displayError(e);
            }
        }
    }

    public void DeleteAllMovies(ActionEvent event) {
        try
        {
            if(ConfirmOK.confirm("Are you sure?", "Do you want to delete all movies on the list?"))
            {
                getModelsHandler().getMovieModel().deleteAllMoviesToDelete();

                handleClose(new ActionEvent());
            }
        }
        catch (Exception e)
        {
            ExceptionHandler.displayError(e);
        }
    }

    /**
     * Disables/enables the movie delete button,
     * if no movie has been selected.
     */
    private void setTbvDMoviesChangeListener() {
        TbvDMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            DeleteBtn.setDisable(newValue == null);
        });
    }
}
