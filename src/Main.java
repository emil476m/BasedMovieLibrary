import GUI.Controllers.BaseController;
import GUI.Controllers.DeleteMovieReminderController;
import GUI.Models.ModelsHandler;
import GUI.Util.ExceptionHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/MainView.fxml"));

        Parent root = loader.load();

        ModelsHandler modelsHandler = new ModelsHandler();

        if (modelsHandler.getMovieModel().getMoviesToDeleteObservableList().size() > 0)
        {
            openDeleteReminder(modelsHandler);
        }

        BaseController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setup();

        primaryStage.setTitle("Based Movie Collection");
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }

    /**
     * Opens a new window that shows the user some movies they might want to delete
     */
    private void openDeleteReminder(ModelsHandler modelsHandler)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/DeleteMovieReminder.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            ExceptionHandler.displayError(new Exception("Failed to open", e));
        }

        Stage stage = new Stage();
        stage.setTitle("Add new movie");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);

        DeleteMovieReminderController controller = loader.getController();
        controller.setModel(modelsHandler);
        controller.setup();
        stage.showAndWait();
    }
}