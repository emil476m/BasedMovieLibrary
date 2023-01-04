package GUI.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ModalOpener {
    public static void openModal(URL resource, String title, String errorMessage) {
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = null;

        try {
            root = loader.load();

            Stage stage = new Stage();

            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception(errorMessage, e));
        }
    }
}
