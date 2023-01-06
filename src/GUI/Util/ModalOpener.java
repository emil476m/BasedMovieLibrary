package GUI.Util;

import GUI.Controllers.BaseController;
import GUI.Models.ModelsHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ModalOpener {
    public static void openModal(URL resource, String title, ModelsHandler modelsHandler, String errorMessage) {
        FXMLLoader loader = new FXMLLoader(resource);

        try {
            Parent root = loader.load();

            BaseController controller = loader.getController();
            controller.setModel(modelsHandler);
            controller.setup();

            Stage stage = new Stage();

            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception(errorMessage, e));
        }
    }
}
