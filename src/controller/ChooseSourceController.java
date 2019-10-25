package controller;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.ChooseSourceModel;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import view.ChooseSourceView;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChooseSourceController {

    private ChooseSourceModel chooseSourceModel;
    private ChooseSourceView chooseSourceView;

    File selectedFile = null;

    public ChooseSourceController(ChooseSourceModel chooseSourceModel, ChooseSourceView chooseSourceView) {
        this.chooseSourceModel = chooseSourceModel;
        this.chooseSourceView = chooseSourceView;
    }

    // Method to add the button listeners
    public void addButtonListeners(Stage stage){

                chooseSourceView.addChooseSourceButtonListener(event -> {
                    javafx.stage.FileChooser file = new javafx.stage.FileChooser();
                    file.setTitle("Open File");
                    selectedFile = file.showOpenDialog(stage);
                    chooseSourceView.getLblSource().setText(selectedFile.getName());
                });

              chooseSourceView.addLoadTextButtonListener(event -> {
                  TextArea textArea = chooseSourceView.getTextArea();
                  chooseSourceModel.parseAndDisplayXML(selectedFile,textArea);

              });

              chooseSourceView.addSearchButtonListener(event -> {
                  System.out.println("Search Button clicked");
                  String text = chooseSourceView.getSearchField().getText();
                  System.out.println("Value:" + text);

              });
    }

}

