import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.*;

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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLFileParsing extends Application {
	File selectedFile = null;

	@Override
	public void start(Stage stage) throws Exception {

		Button btnSource = new Button("Choose Source");
		Label lblSource = new Label("No File Chosen");
		HBox hboxSource = new HBox(btnSource,lblSource );
		hboxSource.setSpacing(10);
		TextArea txtR = new TextArea();

		btnSource.setOnAction(e -> {

			javafx.stage.FileChooser file = new javafx.stage.FileChooser();
			file.setTitle("Open File");
			selectedFile = file.showOpenDialog(stage);
			lblSource.setText(selectedFile.getName());

		});

		Button btnLoadFile = new Button("Load File");

		btnLoadFile.setOnAction(e -> {

			txtR.setText("");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder docBuilder = null;
			try {

				docBuilder = factory.newDocumentBuilder();

			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Document doc = docBuilder.parse(selectedFile.getName());

				doc.getDocumentElement().normalize();

				txtR.setText(txtR.getText() + "Root element :" + doc.getDocumentElement().getNodeName() + "\n" );
				NodeList nList = doc.getElementsByTagName("movie");

				txtR.setText(txtR.getText() + "----------------------------------" + "\n");

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);

					txtR.setText(txtR.getText() + "\nCurrent Element :" + nNode.getNodeName() + "\n");

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;

						txtR.setText(txtR.getText() + "Title : " + eElement.getElementsByTagName("title").item(0).getTextContent() + "\n");

						txtR.setText(txtR.getText() + "Year : " + eElement.getElementsByTagName("year").item(0).getTextContent() + "\n");

						txtR.setText(txtR.getText() + "Rating : " + eElement.getElementsByTagName("rating").item(0).getTextContent() + "\n");

						NodeList directorsList = eElement.getElementsByTagName("director");
						for (int count = 0; count < directorsList.getLength(); count++) {

							Node nodeDirector = directorsList.item(count);
							if (nodeDirector.getNodeType() == nodeDirector.ELEMENT_NODE) {
								Element director = (Element) nodeDirector;

								txtR.setText(txtR.getText() + "Director Name : " + director.getElementsByTagName("name").item(0).getTextContent() + "\n");
							}
						}

						NodeList genres = eElement.getElementsByTagName("item");


						for (int count = 0; count < genres.getLength(); count++) {

							Node nodeItem = genres.item(count);
							if (nodeItem.getNodeType() == nodeItem.ELEMENT_NODE) {
								Element item = (Element) nodeItem;

								txtR.setText(txtR.getText() + "Genre : " + item.getTextContent() + "\n");

							}
						}

					}
				}

			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});


		HBox hboxParsing = new HBox(btnLoadFile);
		hboxParsing.setSpacing(10);



		GridPane grdPane = new GridPane();

		grdPane.setPadding(new Insets(10,10,10,10));
		grdPane.setHgap(10);
		grdPane.setVgap(10);
		grdPane.setAlignment(Pos.CENTER);

		grdPane.addRow(0,hboxSource);
		grdPane.addRow(1,hboxParsing);
		grdPane.addRow(2,txtR);

		Scene scene = new Scene(grdPane,300,400);
		stage.setScene(scene);
		stage.setTitle("IMDB XML File Parsing System");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
