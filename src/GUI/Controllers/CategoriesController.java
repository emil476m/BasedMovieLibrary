package GUI.Controllers;

import BE.Category;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CategoriesController extends BaseController {
    @FXML
    private TableView<Category> tbvCat;
    @FXML
    private TableColumn<Category, String> clmCat;
    @FXML
    private Button btnAddCat;
    @FXML
    private Button btnRemoveCat;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtfieldNewCat;

    @Override
    public void setup() {
        tbvCat.setItems(getModelsHandler().getCategoryModel().getCategories());

        clmCat.setCellValueFactory(new PropertyValueFactory<>("name"));

        setTbvCatChangeListener();
        setTxtfieldNewCatChangeListener();
    }

    /**
     * Tries to add a new category.
     * @param actionEvent
     */
    @FXML
    private void handleAddCat(ActionEvent actionEvent) {
        if (txtfieldNewCat != null && !txtfieldNewCat.getText().isEmpty()) {
            Category category = new Category(txtfieldNewCat.getText());

            try {
                getModelsHandler().getCategoryModel().addCategory(category);
                txtfieldNewCat.clear();
            }
            catch (Exception e) {
                ExceptionHandler.displayError(e);
            }
        }
    }

    /**
     * Tries to remove the selected category.
     * @param actionEvent
     */
    @FXML
    private void handleRemoveCat(ActionEvent actionEvent) {
        Category catToDelete = tbvCat.getSelectionModel().getSelectedItem();

        if (catToDelete != null && AlertOpener.confirm("Remove category?",
                        "Are you sure you want to remove " + catToDelete.getName() + "?")) {
            try {
                getModelsHandler().getMovieModel().updateMovieCats(catToDelete);
                getModelsHandler().getCategoryModel().deleteCategory(catToDelete);
            }
            catch (Exception e) {
                ExceptionHandler.displayError(e);
            }
        }
    }

    /**
     * Closes this window.
     * @param actionEvent
     */
    @FXML
    private void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Disables/enables the remove category button
     * if no category has been selected.
     */
    private void setTbvCatChangeListener() {
        tbvCat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnRemoveCat.setDisable(newValue == null);
        });
    }

    /**
     * Disables/enables the add category button
     * if the new category text field is empty.
     */
    private void setTxtfieldNewCatChangeListener() {
        txtfieldNewCat.textProperty().addListener((observable, oldValue, newValue) -> {
            btnAddCat.setDisable(newValue.isEmpty());
        });
    }
}
