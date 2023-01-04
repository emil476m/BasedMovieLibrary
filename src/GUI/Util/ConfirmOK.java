package GUI.Util;

import GUI.Controllers.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.util.Optional;

public class ConfirmOK {
    /**
     * Opens a modal to confirm that the user wants to delete something.
     * @param header The header text to display.
     * @param content The content text to display.
     * @return true if the user clicked "ok", otherwise false.
     */
    public static boolean confirm(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //alert.getDialogPane().getStylesheets().removeAll();
        //alert.getDialogPane().getStylesheets().add(MainController.currentStyle);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
    }
}