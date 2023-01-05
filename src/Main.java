import BE.Category;
import BLL.CategoryManager;
import BLL.Interfaces.ICategoryManager;
import DAL.DB.CategoryDAO_DB;
import DAL.Interfaces.ICategoryDAO;
import GUI.Controllers.BaseController;
import GUI.Models.ModelsHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/MainView.fxml"));

        Parent root = loader.load();

        BaseController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setup();

        primaryStage.setTitle("Based Movie Collection");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));


        //Add fixed height and width here


        primaryStage.show();
    }
}