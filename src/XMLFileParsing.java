import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.*;

import controller.ChooseSourceController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ChooseSourceModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import view.ChooseSourceView;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLFileParsing extends Application {

	File selectedFile = null;

	@Override
	public void start(Stage stage) throws Exception {


		ChooseSourceView chooseSourceView = new ChooseSourceView();
		ChooseSourceModel chooseSourceModel = new ChooseSourceModel();
		ChooseSourceController chooseSourceController =  new ChooseSourceController(chooseSourceModel,chooseSourceView, stage);

		Scene scene = new Scene(chooseSourceView.asParent(),300,400);
		stage.setScene(scene);
		stage.setTitle("IMDB XML File Parsing System");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
