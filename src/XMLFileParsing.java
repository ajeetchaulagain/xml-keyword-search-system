import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import model.Model;
import view.View;

/**
 * Main application
 */

public class XMLFileParsing extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		View view = new View();
		Model chooseSourceModel = new Model();
		Controller controller =  new Controller(chooseSourceModel, view);
		controller.addButtonListeners(stage);

		Scene scene = new Scene(view.asParent(),700,700);
		stage.setScene(scene);
		stage.setTitle("Search System");
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
