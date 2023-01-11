package GUI.Controllers;

import BE.Category;
import BE.Movie;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CreateMovieController extends BaseController {
    @FXML
    private TableView<Category> tbvAllCatsForMovie;
    @FXML
    private TableColumn<Category, String> clmAllMovieCats;
    @FXML
    private TableView<Category> tbvMovieCats;
    @FXML
    private TableColumn<Category, String> clmMovieCats;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtfieldFilepath;
    @FXML
    private TextField txtfieldIMDBRating;
    @FXML
    private TextField txtfieldTitle;
    @FXML
    private Button btnFilepath;
    @FXML
    private Button btnCreateMovie;
    @FXML
    private Button btnAddCat;
    @FXML
    private Button btnRemoveCat;

    private Category selectedAllCat;

    private Category selectedMovieCat;


    private File file;

    public CreateMovieController(){

    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleChooseFilepath(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add your song");

        FileChooser.ExtensionFilter videoExtensions = new FileChooser.ExtensionFilter("Video files", "*.mp4", "*.mpeg4");

        fileChooser.getExtensionFilters().add(videoExtensions);

        file = fileChooser.showOpenDialog((Stage) btnCancel.getScene().getWindow());

        if(file != null) {
            String fileUriString = file.toURI().toString();

            if ((fileUriString.endsWith(".mp4") || fileUriString.endsWith(".mpeg4"))) txtfieldFilepath.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void handleCreateMovie() {
        if (isInputMissing()) {
            return;
        }

        String title = txtfieldTitle.getText();
        Double rating = Double.parseDouble(txtfieldIMDBRating.getText());
        String path = txtfieldFilepath.getText();
        List<Category> categoryList = getModelsHandler().getMovieModel().getCategoryObservableList();

        Movie movie = new Movie(rating, categoryList, path, title);

        try {
            getModelsHandler().getMovieModel().createMovie(movie);
            handleCancel();
        } catch (Exception e) {
            ExceptionHandler.displayError(e);
        }
    }

    @FXML
    private void handleAddCat(ActionEvent actionEvent) {
        getModelsHandler().getMovieModel().addCatToCreateMovieView(selectedAllCat);
        tbvMovieCats.refresh();
    }

    @FXML
    private void handleRemoveCat(ActionEvent actionEvent) {
        getModelsHandler().getMovieModel().removeCatFromCreateMovie(selectedMovieCat);
        tbvMovieCats.refresh();
    }

    @Override
    public void setup() {
        disableButtons();
        addTitleListener();
        showCategories();
        addFileListener();
        addRatingListener();
        addAllCategorySelectionListener();
        addMovieCategorySelectionListener();
    }

    private void showCategories(){
        tbvAllCatsForMovie.setItems(getModelsHandler().getCategoryModel().getCategories());
        clmAllMovieCats.setCellValueFactory(new PropertyValueFactory<>("name"));

        tbvMovieCats.setItems(getModelsHandler().getMovieModel().getCategoryObservableList());
        clmMovieCats.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void disableButtons(){
        btnCreateMovie.setDisable(true);
        btnAddCat.setDisable(true);
        btnRemoveCat.setDisable(true);
    }

    private void addTitleListener(){
        txtfieldTitle.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isFileEmpty()) {
                btnCreateMovie.setDisable(true);
            }
            else if (!isTitleEmpty() && !isRatingEmpty()) {
                btnCreateMovie.setDisable(false);
            }
        });
    }

    private void addRatingListener(){
        txtfieldIMDBRating.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isFileEmpty()) {
                btnCreateMovie.setDisable(true);
            }
            else if (!isTitleEmpty() && !isRatingEmpty()) {
                btnCreateMovie.setDisable(false);
            }
        });
    }

    private void addFileListener(){
        txtfieldFilepath.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isFileEmpty()) {
                btnCreateMovie.setDisable(true);
            }
            else if (!isTitleEmpty() && !isRatingEmpty()) {
                btnCreateMovie.setDisable(false);
            }
        });
    }

    private boolean isTitleEmpty() {
        return txtfieldTitle.getText() == null || txtfieldTitle.getText().trim().isEmpty();
    }

    private boolean isFileEmpty() {
        return txtfieldFilepath.getText() == null || txtfieldFilepath.getText().trim().isEmpty();
    }

    private boolean isRatingEmpty() {
        return txtfieldIMDBRating.getText() == null || txtfieldIMDBRating.getText().trim().isEmpty();
    }

    private boolean isInputMissing() {
        if (isFileEmpty()) {
            ExceptionHandler.displayError(new Exception("No movie file is chosen"));
            return true;
        }

        if (isRatingEmpty()) {
            ExceptionHandler.displayError(new Exception("IMDB rating can not be empty"));
            return true;
        }

        if (isTitleEmpty()) {
            ExceptionHandler.displayError(new Exception("Title can not be empty"));
            return true;
        }
        return false;
    }

    private void addAllCategorySelectionListener() {
        tbvAllCatsForMovie.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedAllCat = newValue;
                btnAddCat.setDisable(false);
            }
            else {
                selectedAllCat = null;
                btnAddCat.setDisable(true);
            }
        });
    }

    private void addMovieCategorySelectionListener() {
        tbvMovieCats.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedMovieCat = newValue;
                btnRemoveCat.setDisable(false);
            }
            else {
                selectedMovieCat = null;
                btnRemoveCat.setDisable(true);
            }
        });
    }
}