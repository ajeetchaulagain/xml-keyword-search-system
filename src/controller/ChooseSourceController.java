package controller;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.ChooseSourceModel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import view.ChooseSourceView;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ChooseSourceController {

    private ChooseSourceModel chooseSourceModel;
    private ChooseSourceView chooseSourceView;

    File selectedFile = null;

    public ChooseSourceController(ChooseSourceModel chooseSourceModel, ChooseSourceView chooseSourceView, Stage stage) {
        this.chooseSourceModel = chooseSourceModel;
        this.chooseSourceView = chooseSourceView;
        addButtonListeners(stage);

    }


    public void addButtonListeners(Stage stage){

        this.chooseSourceView.addChooseSourceButtonListener(event -> {
            javafx.stage.FileChooser file = new javafx.stage.FileChooser();
            file.setTitle("Open File");
            selectedFile = file.showOpenDialog(stage);
            chooseSourceView.getLblSource().setText(selectedFile.getName());
        });


        this.chooseSourceView.addLoadTextButtonListener(event -> {
            TextArea textArea = chooseSourceView.getTextArea();
            textArea.setText("");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            try {
                docBuilder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                Document doc = docBuilder.parse(selectedFile);
                doc.getDocumentElement().normalize();
                textArea.setText(textArea.getText()+ "Root Element: " + doc.getDocumentElement().getNodeName() + "\n");
                NodeList nList = doc.getElementsByTagName("movie");
                textArea.setText(textArea.getText() + "----------------------------"+ "\n");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    textArea.setText(textArea.getText() + "\nCurrent Element :" + nNode.getNodeName() + "\n");
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        textArea.setText(textArea.getText() + "Title : " + eElement.getElementsByTagName("title").item(0).getTextContent() + "\n");
                        textArea.setText(textArea.getText() + "Year : " + eElement.getElementsByTagName("year").item(0).getTextContent() + "\n");
                        textArea.setText(textArea.getText() + "Rating : " + eElement.getElementsByTagName("rating").item(0).getTextContent() + "\n");

                        NodeList directorsList = eElement.getElementsByTagName("director");
                        for (int count = 0; count < directorsList.getLength(); count++) {
                            Node nodeDirector = directorsList.item(count);
                            if (nodeDirector.getNodeType() == nodeDirector.ELEMENT_NODE) {
                                Element director = (Element) nodeDirector;
                                textArea.setText(textArea.getText() + "Director Name : " + director.getElementsByTagName("name").item(0).getTextContent() + "\n");
                            }
                        }
                        NodeList genres = eElement.getElementsByTagName("item");
                        for (int count = 0; count < genres.getLength(); count++) {
                            Node nodeItem = genres.item(count);
                            if (nodeItem.getNodeType() == nodeItem.ELEMENT_NODE) {
                                Element item = (Element) nodeItem;
                                textArea.setText(textArea.getText() + "Genre : " + item.getTextContent() + "\n");
                            }
                        }

                    }
                }

            } catch (SAXException e1){
                e1.printStackTrace();
            } catch (IOException e1){
                e1.printStackTrace();
            }

        });
    }

}

