package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.ChooseSourceModel;
import model.Movie;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import view.ChooseSourceView;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ChooseSourceController {

    private ChooseSourceModel chooseSourceModel;
    private ChooseSourceView chooseSourceView;

    File selectedFile = null;
    ArrayList<Movie> movieList = null;
    HashMap<String, Integer> nKeywordFrequencyMap;
    HashMap<String, Integer> keywordFrequencyMap;
    HashMap<String, Integer> sortedKeywordFrequencyMap;

    public ChooseSourceController(ChooseSourceModel chooseSourceModel, ChooseSourceView chooseSourceView) {
        this.chooseSourceModel = chooseSourceModel;
        this.chooseSourceView = chooseSourceView;
    }

    // Method to add the button listeners
    public void addButtonListeners(Stage stage) {

        TextArea textArea = chooseSourceView.getTextArea();

        chooseSourceView.addChooseSourceButtonListener(event -> {
            javafx.stage.FileChooser file = new javafx.stage.FileChooser();
            file.setTitle("Open File");
            selectedFile = file.showOpenDialog(stage);
            chooseSourceView.getLblSource().setText(selectedFile.getName());
        });


        chooseSourceView.addLoadTextButtonListener(event -> {
            movieList = chooseSourceModel.parseAndDisplayXML(selectedFile, textArea);

        });

        chooseSourceView.addSearchButtonListener(event -> {

            System.out.println("Search Button clicked");
            String searchKeyword = chooseSourceView.getSearchField().getText();

            ArrayList<String> keywordsListFromMovieList = chooseSourceModel.getKeywordsFromMovieList(movieList);
            ArrayList<String> filteredKeywordsFromMovieList = chooseSourceModel.filterKeywords(keywordsListFromMovieList);
            for (String string : keywordsListFromMovieList) {
                System.out.println("Key: " + string);
            }
            for (String string : filteredKeywordsFromMovieList) {
                System.out.println("Key Filtered: " + string);
            }


            ArrayList<Movie> searchedMovieList = chooseSourceModel.searchMovie(searchKeyword);
            ArrayList<String> keywordsListFromSearchedMovie = chooseSourceModel.getKeywordsFromMovieList(searchedMovieList);
            ArrayList<String> filteredKeywords = chooseSourceModel.filterKeywords(keywordsListFromSearchedMovie);

            for (String string : filteredKeywords) {
                System.out.println("Key Filtered [Searched Movie]" + string);
            }


            HashSet<String> keywordsHashSet = chooseSourceModel.getHashSetOfKeywords(filteredKeywords);
            for (String string : keywordsHashSet) {
                System.out.println("Key Filtered [Searched Movie][Hashset]: " + string);
            }

            keywordFrequencyMap = chooseSourceModel.getKeywordFrequencyMap(keywordsHashSet, filteredKeywordsFromMovieList);
            System.out.println("KeyWord Frequency" + keywordFrequencyMap.size());

            sortedKeywordFrequencyMap = chooseSourceModel.getSortedKeywordFrequencyMap(keywordFrequencyMap);


//                  System.out.println("Filtered Keywords Size [HashSet]: "+ keywordsHashSet.size());
//                  System.out.println("Filtered Keyword Size [ArrayList]:" + filteredKeywords.size());
            chooseSourceModel.displaySearchedMovie(searchedMovieList, textArea);


        });

        chooseSourceView.addBarChartButtonListener(event -> {
            // values needed
            // HashMap of top keywords
            Stage barChartStage = new Stage();

            barChartStage.setTitle("Bar Chart Sample ");
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final BarChart<String, Number> bc =
                    new BarChart<>(xAxis, yAxis);
            bc.setTitle("KeyWord Occurrence");
            xAxis.setLabel("Keywords");

            yAxis.setLabel("No of Occurrence");

            RadioButton selectedRadioButton =
                    (RadioButton) chooseSourceView.getToggleGroup().getSelectedToggle();

            Scene scene = new Scene(bc, 800, 600);
            if (selectedRadioButton.getText() == "Top-3 Coorelated Keywords") {
                nKeywordFrequencyMap = chooseSourceModel.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 3);
                XYChart.Series series = new XYChart.Series();

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                }

                bc.getData().addAll(series);
            } else if (selectedRadioButton.getText() == "Top-5 Coorelated Keywords") {
                nKeywordFrequencyMap = chooseSourceModel.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 5);
                XYChart.Series series = new XYChart.Series();

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                }
                bc.getData().addAll(series);

            } else if (selectedRadioButton.getText() == "Top-8 Coorelated Keywords") {
                nKeywordFrequencyMap = chooseSourceModel.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 8);
                XYChart.Series series = new XYChart.Series();

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                }
                bc.getData().addAll(series);

            } else if (selectedRadioButton.getText() == "Top-10 Coorelated Keywords") {
                nKeywordFrequencyMap = chooseSourceModel.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 10);
                XYChart.Series series = new XYChart.Series();

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                }
                bc.getData().addAll(series);

            }
            barChartStage.setScene(scene);
            barChartStage.show();
        });


        chooseSourceView.addPiechartButtonListener(event -> {

            Scene scene = new Scene(new Group());
            Stage pieChartStage = new Stage();

            pieChartStage.setTitle("Bar Chart Sample ");
            pieChartStage.setWidth(500);
            pieChartStage.setHeight(500);

//            ObservableList<PieChart.Data> pieChartData =
//                    FXCollections.observableArrayList(
//
//                            new PieChart.Data("Test", 20),
//                            new PieChart.Data("Test 2",25),
//                            new PieChart.Data("Test 3", 35)
//                    );

            final PieChart chart = new PieChart();

            chart.setTitle("Test Fruit");
            ((Group) scene.getRoot()).getChildren().add(chart);

            RadioButton selectedRadioButton =
                    (RadioButton) chooseSourceView.getToggleGroup().getSelectedToggle();


            if (selectedRadioButton.getText() == "Top-3 Coorelated Keywords") {
                nKeywordFrequencyMap = chooseSourceModel.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 3);

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    chart.getData().add(new PieChart.Data(entry.getKey(),entry.getValue()));
                }
            }

            else if (selectedRadioButton.getText() == "Top-5 Coorelated Keywords") {
                nKeywordFrequencyMap = chooseSourceModel.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 5);

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    chart.getData().add(new PieChart.Data(entry.getKey(),entry.getValue()));
                }
            }
            else if (selectedRadioButton.getText() == "Top-8 Coorelated Keywords") {
                nKeywordFrequencyMap = chooseSourceModel.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 8);

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    chart.getData().add(new PieChart.Data(entry.getKey(),entry.getValue()));
                }

            } else if (selectedRadioButton.getText() == "Top-10 Coorelated Keywords") {
                nKeywordFrequencyMap = chooseSourceModel.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 10);

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    chart.getData().add(new PieChart.Data(entry.getKey(),entry.getValue()));
                }
            }
            pieChartStage.setScene(scene);
            pieChartStage.show();
        });
    }

}

