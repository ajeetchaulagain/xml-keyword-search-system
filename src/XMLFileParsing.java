import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

//		search button
		TextField tf=new TextField();
		Button b = new Button("Search");
		HBox hboxSearch = new HBox(tf,b );
		hboxSearch.setSpacing(10);

//		Radio Buttons
		ToggleGroup group = new ToggleGroup();
		RadioButton button1 = new RadioButton("Top-3");
		RadioButton button2 = new RadioButton("Top-5");
		RadioButton button3 = new RadioButton("Top-8");
		RadioButton button4 = new RadioButton("Top-10");

		button1.setToggleGroup(group);
		button2.setToggleGroup(group);
		button3.setToggleGroup(group);
		button4.setToggleGroup(group);

		VBox radioVbox = new VBox();
		radioVbox.setSpacing(10);
		radioVbox.getChildren().addAll(button1,button2,button3,button4);

//		Buttons for Chart
		Text text=new Text("Display selected option in chart:");
		text.setStyle("-fx-font-weight: bold");

		Button b1 = new Button("Bar Chart");
		Button b2 = new Button("Pie Chart");
		HBox hBoxChartButton = new HBox(b1,b2);
		hBoxChartButton.setSpacing(10);

//		action of button when choosing file

		btnSource.setOnAction(e -> {

			FileChooser file = new FileChooser();
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
//grade pane
		GridPane grdPane = new GridPane();

		grdPane.setPadding(new Insets(10,10,10,10));
		grdPane.setHgap(10);
		grdPane.setVgap(10);
		grdPane.setAlignment(Pos.CENTER);

		grdPane.addRow(0,hboxSource);
		grdPane.addRow(1,hboxParsing);
		grdPane.addRow(2,hboxSearch);
		grdPane.addRow(3,txtR);
		grdPane.addRow(4,radioVbox);
		grdPane.addRow(5, text);
		grdPane.addRow(6, hBoxChartButton);

//scene
		Scene scene = new Scene(grdPane,600,600);
		stage.setScene(scene);
		stage.setTitle("IMDB XML File Parsing System");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
