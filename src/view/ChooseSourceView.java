package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ChooseSourceView {

    private GridPane gridPaneView;
    Button btnSource;
    HBox hboxSource;
    TextArea textArea;
    Button btnLoadFile;
    HBox hboxLoad;
    Label lblSource;


    public ChooseSourceView(){
        createAndConfigureLayoutControls();
        createAndConfigurePane();
    }

    public Parent asParent(){
        return gridPaneView;
    }

    public void createAndConfigureLayoutControls(){

        btnSource = new Button("Choose Source");
        lblSource = new Label("No File Chosen");
        hboxSource = new HBox(btnSource,lblSource );
        hboxSource.setSpacing(10);
        textArea = new TextArea();
        btnLoadFile = new Button("Load File");
        hboxLoad = new HBox(btnLoadFile);
        hboxLoad.setSpacing(10);
    }

    public void createAndConfigurePane(){

        gridPaneView = new GridPane();
        gridPaneView.setPadding(new Insets(10,10,10,10));
        gridPaneView.setHgap(10);
        gridPaneView.setVgap(10);
        gridPaneView.setAlignment(Pos.CENTER);

        gridPaneView.addRow(0,hboxSource);
        gridPaneView.addRow(1,hboxLoad);
        gridPaneView.addRow(2,textArea);
    }

    public void addChooseSourceButtonListener(EventHandler<ActionEvent> eventListener){
        btnSource.setOnAction(eventListener);
    }

    public void addLoadTextButtonListener(EventHandler<ActionEvent> eventListener){
        btnLoadFile.setOnAction(eventListener);
    }


    // Getters and Setters method reside here!

    public void setLblSource(Label lblSource) {
        this.lblSource = lblSource;
    }

    public Label getLblSource(){
        return this.lblSource;
    }

    public GridPane getGridPaneView() {
        return gridPaneView;
    }

    public void setGridPaneView(GridPane gridPaneView) {
        this.gridPaneView = gridPaneView;
    }

    public Button getBtnSource() {
        return btnSource;
    }

    public void setBtnSource(Button btnSource) {
        this.btnSource = btnSource;
    }

    public HBox getHboxSource() {
        return hboxSource;
    }

    public void setHboxSource(HBox hboxSource) {
        this.hboxSource = hboxSource;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public Button getBtnLoadFile() {
        return btnLoadFile;
    }

    public void setBtnLoadFile(Button btnLoadFile) {
        this.btnLoadFile = btnLoadFile;
    }

    public HBox getHboxLoad() {
        return hboxLoad;
    }

    public void setHboxLoad(HBox hboxLoad) {
        this.hboxLoad = hboxLoad;
    }
}
