package GUI.Controllers;

import BE.Category;
import GUI.Models.ModelsHandler;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesController implements Initializable {
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

    private ExceptionHandler exceptionHandler;

    public CategoriesController() {
        exceptionHandler = new ExceptionHandler();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbvCat.setItems(ModelsHandler.getCategoryModel().getCategories());

        clmCat.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    private void handleAddCat(ActionEvent actionEvent) {
        if (txtfieldNewCat != null && !txtfieldNewCat.getText().isEmpty()) {
            Category category = new Category(txtfieldNewCat.getText());

            try {
                ModelsHandler.getCategoryModel().addCategory(category);
            }
            catch (Exception e) {
                exceptionHandler.displayError(e);
            }
        }
    }

    @FXML
    private void handleRemoveCat(ActionEvent actionEvent) {
    }

    @FXML
    private void handleCancel(ActionEvent actionEvent) {
    }
}
