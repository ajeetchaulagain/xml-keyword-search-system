package controller;

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
import java.util.ArrayList;
import java.util.HashSet;

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
                  ArrayList<Movie> movieList = chooseSourceModel.parseAndDisplayXML(selectedFile,textArea);

                  ArrayList<String> keywordsListFromMovieList = chooseSourceModel.getKeywordsFromMovieList(movieList);

                  for(String string: keywordsListFromMovieList){
                      System.out.println("Key: "+ string);
                  }

                  ArrayList<String> filteredKeywords = chooseSourceModel.filterKeywords(keywordsListFromMovieList);
                  for(String string: filteredKeywords){
                      System.out.println("Key Filtered: "+ string);
                  }


              });

              chooseSourceView.addSearchButtonListener(event -> {
                  System.out.println("Search Button clicked");
                  String searchKeyword = chooseSourceView.getSearchField().getText();
                  ArrayList<Movie> searchedMovieList = chooseSourceModel.searchMovie(searchKeyword);

                  ArrayList<String> keywordsListFromSearchedMovie = chooseSourceModel.getKeywordsFromMovieList(searchedMovieList);
                  ArrayList<String> filteredKeywords = chooseSourceModel.filterKeywords(keywordsListFromSearchedMovie);



                  for (String string: filteredKeywords){
                      System.out.println("Key Filtered [Searched Movie]" + string);
                  }


                  HashSet<String> keywordsHashSet = chooseSourceModel.getHashSetOfKeywords(filteredKeywords);
                  System.out.println("Filtered Keywords Size [HashSet]: "+ keywordsHashSet.size());
                  System.out.println("Filtered Keyword Size [ArrayList]:" + filteredKeywords.size());

                  TextArea textArea = chooseSourceView.getTextArea();
                  chooseSourceModel.displaySearchedMovie(searchedMovieList,textArea);


              });
    }

}

