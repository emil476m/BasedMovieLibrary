package GUI.Util;

import javafx.scene.control.Alert;

public class ExceptionHandler {
    /**
     * Handles thrown exceptions by displaying the error in an Alert window.
     * @param t - Exception thrown
     */
    public static void displayError(Throwable t) {

        //Create new alert.
        Alert alert = new Alert(Alert.AlertType.ERROR);

        //Set title and load error from parameter.
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());

        //Display and wait on user action.
        alert.showAndWait();
    }
}
