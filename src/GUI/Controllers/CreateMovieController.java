package GUI.Controllers;

import BE.Category;
import BE.Movie;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class CreateMovieController extends BaseController {
    @FXML
    private TableView<Category> tbvAllCatsForMovie;
    @FXML
    private TableColumn<Category, String> clmAllMovieCats;
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

    private File file;

    /**
     * closes the CreateMovieView.
     */
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Disables the create button if an input is missing.
     */
    private final ChangeListener<String> fieldChangeListener = new ChangeListener<>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            btnCreateMovie.setDisable(isFileEmpty() || isTitleEmpty() || isRatingEmpty());
        }
    };

    /**
     * Opens the file explorer for the user to select a movie.
     * @param actionEvent
     */
    @FXML
    private void handleChooseFilepath(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Add your movie");

        FileChooser.ExtensionFilter videoExtensions = new FileChooser.ExtensionFilter("Video files", "*.mp4", "*.mpeg4");

        fileChooser.getExtensionFilters().add(videoExtensions);

        file = fileChooser.showOpenDialog((Stage) btnCancel.getScene().getWindow());

        if(file != null) {
            String fileUriString = file.toURI().toString();

            if ((fileUriString.endsWith(".mp4") || fileUriString.endsWith(".mpeg4"))) txtfieldFilepath.setText(file.getAbsolutePath());
        }
    }

    /**
     * Creates a new movie with the given inputs.
     */
    @FXML
    private void handleCreateMovie() {
        if (isInputMissing()) {
            return;
        }

        String title = txtfieldTitle.getText();
        String[] splitRating = txtfieldIMDBRating.getText().split("\\.");
        double rating = Double.parseDouble(splitRating.length > 1 ? splitRating[0] + "." + splitRating[1].charAt(0) : splitRating[0]);
        String path = txtfieldFilepath.getText();
        List<Category> categoryList = tbvAllCatsForMovie.getSelectionModel().getSelectedItems();

        Movie movie = new Movie(rating, categoryList, path, title);

        try {
            getModelsHandler().getMovieModel().createMovie(movie);
            getModelsHandler().getMovieModel().clearSearch();
            handleCancel();
        } catch (Exception e) {
            ExceptionHandler.displayError(e);
        }
    }

    /**
     * Initializes the class.
     */
    @Override
    public void setup() {
        setupCreateMovie();
        multiSelect();
        addTitleListener();
        addRatingListener();
        addFileListener();
    }

    /**
     * Shows categories, enables multiselect and disables buttons on startup.
     */
    private void setupCreateMovie(){
        btnCreateMovie.setDisable(true);
        //enables multiselect for the tableview.
        tbvAllCatsForMovie.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //shows categories in tableview.
        tbvAllCatsForMovie.setItems(getModelsHandler().getCategoryModel().getCategories());
        clmAllMovieCats.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void addTitleListener(){
        txtfieldTitle.textProperty().addListener(fieldChangeListener);
    }

    private void addRatingListener(){
        txtfieldIMDBRating.textProperty().addListener(fieldChangeListener);
    }

    private void addFileListener(){
        txtfieldFilepath.textProperty().addListener(fieldChangeListener);
    }

    /**
     * Checks if title is empty or null.
     * @return true if the title is empty or null, otherwise false.
     */
    private boolean isTitleEmpty() {
        return txtfieldTitle.getText() == null || txtfieldTitle.getText().trim().isEmpty();
    }

    /**
     * Checks if filepath is empty og null.
     * @return true if the filepath is empty or null, otherse false.
     */
    private boolean isFileEmpty() {
        return txtfieldFilepath.getText() == null || txtfieldFilepath.getText().trim().isEmpty();
    }

    /**
     * Checks if rating is empty or null.
     * @return true if rating is null or empty, otherwise false.
     */
    private boolean isRatingEmpty() {
        return txtfieldIMDBRating.getText() == null || txtfieldIMDBRating.getText().trim().isEmpty();
    }

    /**
     * Checks if there are the necessary inputs to create a movie.
     * @return true if all fields are filled out, otherwise false.
     */
    private boolean isInputMissing() {
        if (isFileEmpty()) {
            AlertOpener.validationError("No movie file is chosen");
            return true;
        }

        if (isRatingEmpty()) {
            AlertOpener.validationError("IMDB rating can not be empty");
            return true;
        }
        else if (!isRatingInputValid(txtfieldIMDBRating.getText())) {
            txtfieldIMDBRating.setText("");
            AlertOpener.validationError("IMDB rating must be between 0.0 and 10.0");
            return true;
        }

        if (isTitleEmpty()) {
            AlertOpener.validationError("Title can not be empty");
            return true;
        }

        return false;
    }

    /**
     * Makes the tableview able to multiselect without holding control,
     * by using node instances and mouse events.
     */
    private void multiSelect(){
        tbvAllCatsForMovie.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            Node node = evt.getPickResult().getIntersectedNode();

            // Gets the row that was clicked.
            while (node != null && node != tbvAllCatsForMovie && !(node instanceof TableRow)) {
                node = node.getParent();
            }

            // Don't use standard event, if node is a table row.
            if (node instanceof TableRow) {
                // Prevent further handling
                evt.consume();

                TableRow row = (TableRow) node;
                TableView tv = row.getTableView();

                // Focus the tableview
                tv.requestFocus();

                if (!row.isEmpty()) {
                    // Handle selection for non-empty nodes
                    int index = row.getIndex();

                    if (row.isSelected()) {
                        tv.getSelectionModel().clearSelection(index);
                    } else {
                        tv.getSelectionModel().select(index);
                    }
                }
            }
        });
    }
}