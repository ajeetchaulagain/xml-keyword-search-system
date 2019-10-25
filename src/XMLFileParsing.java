import java.io.*;
import controller.ChooseSourceController;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import model.ChooseSourceModel;
import view.ChooseSourceView;

public class XMLFileParsing extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		ChooseSourceView chooseSourceView = new ChooseSourceView();
		ChooseSourceModel chooseSourceModel = new ChooseSourceModel();
		ChooseSourceController chooseSourceController =  new ChooseSourceController(chooseSourceModel,chooseSourceView);
		chooseSourceController.addButtonListeners(stage);

		Scene scene = new Scene(chooseSourceView.asParent(),700,700);
		stage.setScene(scene);
		stage.setTitle("Search System");
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
