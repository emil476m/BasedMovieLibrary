package GUI.Util;

import GUI.Controllers.MainController;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ExceptionHandler {
    /**
     * Handles thrown exceptions by displaying the error in an Alert window.
     * @param t - Exception thrown
     */
    /**
     * Displays an error as a modal to the user.
     * @param throwable The error to display.
     */
    public static void displayError(Throwable throwable) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Something went wrong...");
        alert.setHeaderText(throwable.getLocalizedMessage());
        alert.showAndWait();
    }

    /**
     * Exception where you can write your own message to the user
     * @param s the string which we wish the user to see
     */
    public void displayNiceError(String s){
        //Create new alert.
        Alert alert = new Alert(Alert.AlertType.ERROR);

        //Set title and load error from parameter.
        alert.setTitle("Something went wrong");
        alert.setHeaderText(s);

        //Display and wait on user action.
        alert.showAndWait();

    }
}
