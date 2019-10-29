package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.event.ChangeListener;
import java.awt.*;

public class ChooseSourceView {

    private GridPane gridPaneView;
    Button btnChooseSource;
    HBox hboxSource;
    TextArea textArea;
    Button btnLoadFile;
    HBox hboxLoad;
    Label lblSource;
    Label applicationTitle;
    Label applicationDescription;
    VBox vboxTitle;
    Separator separator1;
    Separator separator2;
    Separator separator3;

    HBox hboxSearch;
    HBox hboxRadioButtons;
    HBox hboxGraphButtons;



    TextField searchField;
    Button searchButton;


    Label searchLabel;

    Button pieChart;
    Button barChart;

    ToggleGroup toggleGroup;



    public ChooseSourceView(){
        createAndConfigureLayoutControls();
        createAndConfigurePane();
    }

    public Parent asParent(){
        return gridPaneView;
    }

    public void createAndConfigureLayoutControls(){

        applicationTitle = new Label("Welcome to XML Keyword Search System");
        applicationDescription = new Label("Please choose the XML file to parse!");
        applicationTitle.setStyle("-fx-font: 15 arial");

        separator1 = new Separator();
        separator1.setOrientation(Orientation.HORIZONTAL);

        separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);


        vboxTitle = new VBox(applicationTitle,applicationDescription);
        vboxTitle.setAlignment(Pos.TOP_CENTER);
        vboxTitle.setSpacing(10);


        btnChooseSource = new Button("Choose Source");
//        btnChooseSource.setStyle("-fx-font-size:12");
//        btnChooseSource.setStyle("-fx-background-color:blue");
        btnChooseSource.setStyle("-fx-text-color:blue");


        lblSource = new Label("No File Chosen  ");
        btnLoadFile = new Button("Load File");
        btnLoadFile.setAlignment(Pos.CENTER_RIGHT);


        hboxSource = new HBox(btnChooseSource,lblSource,separator2,btnLoadFile);
        hboxSource.setAlignment(Pos.CENTER_LEFT);
        hboxSource.setSpacing(10);
        textArea = new TextArea();
        textArea.setPrefHeight(300);
        textArea.setPrefWidth(700);
        textArea.setStyle("-fx-font:12 arial");

        searchLabel =  new Label("Enter keyword to search:");
        searchField =  new TextField();
        searchButton = new Button("Search");
        hboxSearch = new HBox(searchLabel,searchField,searchButton);
        hboxSearch.setAlignment(Pos.CENTER_LEFT);
        hboxSearch.setSpacing(10);

        toggleGroup =  new ToggleGroup();
        RadioButton top3Keyword = new RadioButton();
        top3Keyword.setText("Top-3 Coorelated Keywords");
        top3Keyword.setSelected(true);
        top3Keyword.setToggleGroup(toggleGroup);

        RadioButton top5Keyword = new RadioButton();
        top5Keyword.setText("Top-5 Coorelated Keywords");
        top5Keyword.setToggleGroup(toggleGroup);

        RadioButton top8Keyword = new RadioButton();
        top8Keyword.setText("Top-8 Coorelated Keywords");
        top8Keyword.setToggleGroup(toggleGroup);

        RadioButton top10Keyword = new RadioButton();
        top10Keyword.setText("Top-10 Coorelated Keywords");
        top10Keyword.setToggleGroup(toggleGroup);


        separator3 = new Separator();
        separator3.setOrientation(Orientation.HORIZONTAL);

        hboxRadioButtons = new HBox(top3Keyword,top5Keyword,top8Keyword,top10Keyword);
        hboxRadioButtons.setSpacing(5);
        hboxRadioButtons.setStyle("-fx-font:11 arial");

        pieChart = new Button("View Piechart");
        barChart = new Button("View Barchart");

        hboxGraphButtons =  new HBox(pieChart,barChart);
        hboxGraphButtons.setSpacing(30);
        hboxGraphButtons.setAlignment(Pos.CENTER);

    }

    public void createAndConfigurePane(){
        gridPaneView = new GridPane();
//        gridPaneView.setGridLinesVisible(true);
        gridPaneView.setPadding(new Insets(20,20,20,20));
        gridPaneView.setHgap(10);
        gridPaneView.setVgap(20);
        gridPaneView.setAlignment(Pos.TOP_CENTER);


        gridPaneView.addRow(0,vboxTitle);
        gridPaneView.addRow(1,separator1);
        gridPaneView.addRow(2,hboxSource);

        gridPaneView.addRow(3,textArea);
        gridPaneView.addRow(4,hboxSearch);
        gridPaneView.addRow(5,separator3);
        gridPaneView.addRow(6,hboxRadioButtons);
        gridPaneView.addRow(7,hboxGraphButtons);
    }

    public void addChooseSourceButtonListener(EventHandler<ActionEvent> eventListener){
        btnChooseSource.setOnAction(eventListener);
    }

    public void addLoadTextButtonListener(EventHandler<ActionEvent> eventListener){
        btnLoadFile.setOnAction(eventListener);
    }

    public void addSearchButtonListener(EventHandler<ActionEvent> eventListener){
        searchButton.setOnAction(eventListener);
    }

//    public void addToggleGroupListener(EventHandler<ActionEvent> eventListener){
//        toggleGroup.selectedToggleProperty().addListener(ChangeListene);
//    }

    public void addBarChartButtonListener(EventHandler<ActionEvent> eventListener){
        barChart.setOnAction(eventListener);
    }

    public void addPiechartButtonListener(EventHandler<ActionEvent> eventListener){
        pieChart.setOnAction(eventListener);
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

    public Button getBtnChooseSource() {
        return btnChooseSource;
    }

    public void setBtnChooseSource(Button btnChooseSource) {
        this.btnChooseSource = btnChooseSource;
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

    public TextField getSearchField() {
        return searchField;
    }

    public void setSearchField(TextField searchField) {
        this.searchField = searchField;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }
    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }
}
