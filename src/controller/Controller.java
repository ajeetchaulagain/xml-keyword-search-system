package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Model;
import model.Movie;
import model.XMLDOMParser;
import view.View;

import java.io.File;
import java.util.*;

public class Controller {

    private Model model;
    private View view;

    private File selectedFile = null;
    private ArrayList<Movie> movieList = null;
    private HashMap<String, Integer> nKeywordFrequencyMap;
    private HashMap<String, Integer> keywordFrequencyMap;
    private HashMap<String, Integer> sortedKeywordFrequencyMap;


    /**
     *
     * @param model
     * @param view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     *
     * @param stage
     * Method to add the button listeners
     */
    public void addButtonListeners(Stage stage) {

        TextArea textArea = view.getTextArea();

        view.addChooseSourceButtonListener(event -> {
            javafx.stage.FileChooser file = new javafx.stage.FileChooser();
            file.setTitle("Open File");
            selectedFile = file.showOpenDialog(stage);
            view.getLblSource().setText(selectedFile.getName());
        });


        view.addLoadTextButtonListener(event -> {
//            movieList = model.parseAndDisplayXML(selectedFile, textArea);
            XMLDOMParser xmldomParser = new XMLDOMParser();
            movieList = xmldomParser.parseAndDisplayXML(selectedFile,textArea);

        });


        view.addSearchButtonListener(event -> {

            System.out.println("Search Button clicked");
            String searchKeyword = view.getSearchField().getText();

            ArrayList<String> keywordsListFromMovieList = model.getKeywordsFromMovieList(movieList);
            ArrayList<String> filteredKeywordsFromMovieList = model.filterKeywords(keywordsListFromMovieList);
            for (String string : keywordsListFromMovieList) {
                System.out.println("Key: " + string);
            }
            for (String string : filteredKeywordsFromMovieList) {
                System.out.println("Key Filtered: " + string);
            }


            ArrayList<Movie> searchedMovieList = model.searchMovie(searchKeyword,movieList);
            ArrayList<String> keywordsListFromSearchedMovie = model.getKeywordsFromMovieList(searchedMovieList);
            ArrayList<String> filteredKeywords = model.filterKeywords(keywordsListFromSearchedMovie);

            for (String string : filteredKeywords) {
                System.out.println("Key Filtered [Searched Movie]" + string);
            }


            HashSet<String> keywordsHashSet = model.getHashSetOfKeywords(filteredKeywords);
            for (String string : keywordsHashSet) {
                System.out.println("Key Filtered [Searched Movie][Hashset]: " + string);
            }

            keywordFrequencyMap = model.getKeywordFrequencyMap(keywordsHashSet, filteredKeywordsFromMovieList);
            System.out.println("KeyWord Frequency" + keywordFrequencyMap.size());

            sortedKeywordFrequencyMap = model.getSortedKeywordFrequencyMap(keywordFrequencyMap);


//                  System.out.println("Filtered Keywords Size [HashSet]: "+ keywordsHashSet.size());
//                  System.out.println("Filtered Keyword Size [ArrayList]:" + filteredKeywords.size());
            model.displaySearchedMovie(searchedMovieList, textArea);


        });

        view.addBarChartButtonListener(event -> {
            // values needed
            // HashMap of top keywords
            Stage barChartStage = new Stage();

            barChartStage.setTitle("Bar Chart");
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final BarChart<String, Number> bc =
                    new BarChart<>(xAxis, yAxis);
            bc.setTitle("Correlated keyword occurrence chart");
            xAxis.setLabel("Keywords");

            yAxis.setLabel("No of Occurrence");

            RadioButton selectedRadioButton =
                    (RadioButton) view.getToggleGroup().getSelectedToggle();

            Scene scene = new Scene(bc, 800, 600);
            if (selectedRadioButton.getText() == "Top-3 Coorelated Keywords") {
                nKeywordFrequencyMap = model.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 3);
                XYChart.Series series = new XYChart.Series();

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                }

                bc.getData().addAll(series);
            } else if (selectedRadioButton.getText() == "Top-5 Coorelated Keywords") {
                nKeywordFrequencyMap = model.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 5);
                XYChart.Series series = new XYChart.Series();

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                }
                bc.getData().addAll(series);

            } else if (selectedRadioButton.getText() == "Top-8 Coorelated Keywords") {
                nKeywordFrequencyMap = model.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 8);
                XYChart.Series series = new XYChart.Series();

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
                }
                bc.getData().addAll(series);

            } else if (selectedRadioButton.getText() == "Top-10 Coorelated Keywords") {
                nKeywordFrequencyMap = model.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 10);
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


        view.addPiechartButtonListener(event -> {

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

            chart.setTitle("Correlated Keywords");
            ((Group) scene.getRoot()).getChildren().add(chart);

            RadioButton selectedRadioButton =
                    (RadioButton) view.getToggleGroup().getSelectedToggle();


            if (selectedRadioButton.getText() == "Top-3 Coorelated Keywords") {
                nKeywordFrequencyMap = model.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 3);

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    chart.getData().add(new PieChart.Data(entry.getKey(),entry.getValue()));
                }
            }

            else if (selectedRadioButton.getText() == "Top-5 Coorelated Keywords") {
                nKeywordFrequencyMap = model.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 5);

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    chart.getData().add(new PieChart.Data(entry.getKey(),entry.getValue()));
                }
            }
            else if (selectedRadioButton.getText() == "Top-8 Coorelated Keywords") {
                nKeywordFrequencyMap = model.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 8);

                for (Map.Entry<String, Integer> entry : nKeywordFrequencyMap.entrySet()) {
                    nKeywordFrequencyMap.put(entry.getKey(), entry.getValue());
                    chart.getData().add(new PieChart.Data(entry.getKey(),entry.getValue()));
                }

            } else if (selectedRadioButton.getText() == "Top-10 Coorelated Keywords") {
                nKeywordFrequencyMap = model.getNSortedKeywordFrequencyMap(sortedKeywordFrequencyMap, 10);

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

