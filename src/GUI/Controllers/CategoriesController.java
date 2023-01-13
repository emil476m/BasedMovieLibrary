package GUI.Controllers;

import BE.Category;
import GUI.Models.ModelsHandler;
import GUI.Util.ConfirmOK;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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
    }

    @FXML
    private void handleAddCat(ActionEvent actionEvent) {
        if (txtfieldNewCat != null && !txtfieldNewCat.getText().isEmpty()) {
            Category category = new Category(txtfieldNewCat.getText());

            try {
                getModelsHandler().getCategoryModel().addCategory(category);
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

        if (catToDelete != null && ConfirmOK.confirm("Remove category?",
                        "Are you sure you want to remove " + catToDelete.getName() + "?")) {
            try {
                getModelsHandler().getCategoryModel().deleteCategory(catToDelete);
                getModelsHandler().getMovieModel().updateMovieCats(catToDelete);
            }
            catch (Exception e) {
                ExceptionHandler.displayError(e);
            }
        }
    }

    @FXML
    private void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
