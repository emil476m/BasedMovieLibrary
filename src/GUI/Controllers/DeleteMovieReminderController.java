package GUI.Controllers;

import BE.Movie;
import GUI.Util.ConfirmOK;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteMovieReminderController extends BaseController {
    @FXML
    private Button CloseBtn;
    @FXML
    private TableView TbvDMovies;
    @FXML
    private TableColumn ColMovieTitle;
    @FXML
    private TableColumn ColMovieCategory;
    @FXML
    private TableColumn ColImdb;
    @FXML
    private TableColumn ColMyRating;

    private Movie selectedMovie;

    @Override
    public void setup() {
        initializeMoviesToDelete();
    }

    private void initializeMoviesToDelete()
    {
        TbvDMovies.setItems(getModelsHandler().getDeleteReminderModel().getMoviesToDelete());
        ColMovieTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ColMovieCategory.setCellValueFactory(new PropertyValueFactory<>("CategoryNames"));
        ColImdb.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        ColMyRating.setCellValueFactory(new PropertyValueFactory<>("PRating"));
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void DeleteMovie(ActionEvent event) {
        try {
            selectedMovie = (Movie) TbvDMovies.getSelectionModel().getSelectedItem();
            try {
                if( ConfirmOK.confirm("Are you sure?", "Do you want to delete the movie"))
                {
                    getModelsHandler().getDeleteReminderModel().deleteMovie(selectedMovie);
                }
            }
            catch (Exception e)
            {
                ExceptionHandler.displayError(e);
            }
        }
        catch (Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You do not have a movie selected", ButtonType.CANCEL);
            alert.showAndWait();
        }
    }
}
