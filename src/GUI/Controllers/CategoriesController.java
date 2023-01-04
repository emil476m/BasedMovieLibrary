package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CategoriesController {
    @FXML
    private TableView tbvCat;
    @FXML
    private TableColumn clmCat;
    @FXML
    private Button btnAddCat;
    @FXML
    private Button btnRemoveCat;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtfieldNewCat;

    @FXML
    private void handleAddCat(ActionEvent actionEvent) {
    }

    @FXML
    private void handleRemoveCat(ActionEvent actionEvent) {
    }

    @FXML
    private void handleCancel(ActionEvent actionEvent) {
    }
}
