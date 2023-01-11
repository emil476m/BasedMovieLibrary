package GUI.Controllers;

import BE.Category;
import BE.Movie;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

        FileChooser.ExtensionFilter videoExtensions = new FileChooser.ExtensionFilter("Video files", "*.mp4");

        fileChooser.getExtensionFilters().add(videoExtensions);

        file = fileChooser.showOpenDialog((Stage) btnCancel.getScene().getWindow());

        if(file != null) {
            txtfieldFilepath.setText(file.getAbsolutePath());
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
        List<Category> categoryList = tbvAllCatsForMovie.getSelectionModel().getSelectedItems();

        Movie movie = new Movie(rating, categoryList, path, title);

        try {
            getModelsHandler().getMovieModel().createMovie(movie);
            handleCancel();
        } catch (Exception e) {
            ExceptionHandler.displayError(e);
        }
    }

    @Override
    public void setup() {
        disableButtons();
        addTitleListener();
        showCategories();
        addFileListener();
        addRatingListener();
        multiSelect();
        tbvAllCatsForMovie.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void showCategories(){
        tbvAllCatsForMovie.setItems(getModelsHandler().getCategoryModel().getCategories());
        clmAllMovieCats.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void disableButtons(){
        btnCreateMovie.setDisable(true);
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
            ExceptionHandler.displayError(new Exception("Rating can not be empty"));
            return true;
        }

        if (isTitleEmpty()) {
            ExceptionHandler.displayError(new Exception("Title can not be empty"));
            return true;
        }
        return false;
    }


    private void multiSelect(){
        tbvAllCatsForMovie.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            Node node = evt.getPickResult().getIntersectedNode();

            // go up from the target node until a row is found or it's clear the
            // target node wasn't a node.
            while (node != null && node != tbvAllCatsForMovie && !(node instanceof TableRow)) {
                node = node.getParent();
            }

            // if is part of a row or the row,
            // handle event instead of using standard handling
            if (node instanceof TableRow) {
                // prevent further handling
                evt.consume();

                TableRow row = (TableRow) node;
                TableView tv = row.getTableView();

                // focus the tableview
                tv.requestFocus();

                if (!row.isEmpty()) {
                    // handle selection for non-empty nodes
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