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

import javax.swing.filechooser.FileNameExtensionFilter;

public class JavaFXTextProcessing extends Application {

//	File selectedFile = new File("");
	
	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void start(Stage stage)throws Exception{
		
		Button btnSource = new Button("Choose Source");
		Label lblSource = new Label("File");
		HBox hboxSource = new HBox(btnSource,lblSource );
		hboxSource.setSpacing(20);

		Button btnSearch = new Button("Load Text");
		btnSearch.setMinWidth(100);
		HBox hbox = new HBox(btnSearch);
		hbox.setAlignment(Pos.CENTER);

		TextArea txtR = new TextArea();
//		txtR.setText(selectedFile.getName());
		GridPane grdPane = new GridPane();

		//		------------------choose file button-----------------------
		btnSource.setOnAction(e->  {
			FileChooser file = new FileChooser();
			file.setTitle("Open File");

//			FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TxT files (*.txt)", "*.txt");
//			file.getExtensionFilters().add(extensionFilter);

			File selectedFile = file.showOpenDialog(stage);

			if(selectedFile!=null){
				lblSource.setText(selectedFile.getName());
			}
		});


		grdPane.setPadding(new Insets(10,10,10,10));
		//grdPane.setMinSize(10, 10);
		grdPane.setHgap(10);
		grdPane.setVgap(10);
		grdPane.setAlignment(Pos.CENTER);
		
		grdPane.addRow(0,hboxSource);
		grdPane.addRow(1,hbox);
		grdPane.addRow(2, txtR);
				
		Scene scene = new Scene(grdPane,300,400);
		stage.setScene(scene);
		stage.setTitle("Open File System");
		stage.show();


	}



}
