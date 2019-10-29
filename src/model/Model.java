package model;

import javafx.scene.control.TextArea;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;

public class Model {

    // Movie List
//    ArrayList<Movie> movieList = new ArrayList<>();
    String[] excludedKeywordsForValidation = {"English", "The"};
    /**
     *
     * @param searchKeyword
     * @return
     * This method search the keyword in entire movie collection and returns the List of Movie that has that keyword
     */
    public ArrayList<Movie> searchMovie(String searchKeyword,ArrayList<Movie> movieList) {
        ArrayList<Movie> searchedMovieList =  new ArrayList<>();
        System.out.println("Inside searchMovie method");
        for (Movie movie : movieList) {
            if(movie.getKeywords().contains(searchKeyword)||
                movie.getCastName().contains(searchKeyword) ||
                    movie.getDirectorName().contains(searchKeyword)||
                    // Rating and Year excluded for the keywords
//                    movie.getYear().contains(searchKeyword) ||
//                    movie.getRating().contains(searchKeyword)||
//                    movie.getWriterRole().contains(searchKeyword) ||
                    movie.getWriterName().contains(searchKeyword)||
                    movie.getCastRole().contains(searchKeyword) ||
                    // movie.getCountries().contains(searchKeyword) ||
//                    movie.getGenres().contains(searchKeyword) ||
                    movie.getCompanies().contains(searchKeyword) ||
                    movie.getTitle().contains(searchKeyword)
            ){
                System.out.println("Keywords:" + movie.getKeywords());
                searchedMovieList.add(movie);
            }
        }
        return searchedMovieList;
    }


    /**
     *
     * @param searchedMovieList
     * @param textArea
     */
    public void displaySearchedMovie(ArrayList<Movie> searchedMovieList, TextArea textArea){
        System.out.println("Inside displaySearchedMovie method");
        textArea.setText("");
        for (Movie movie: searchedMovieList){

            textArea.setText(textArea.getText() + "Title : " + movie.getTitle() + "\n");
            textArea.setText(textArea.getText() + "Year : " + movie.getYear() + "\n");
            textArea.setText(textArea.getText() + "Rating : "+ movie.getRating() + "\n");
            textArea.setText(textArea.getText() + "Keywords :"+ movie.getKeywords() + "\n");
            textArea.setText(textArea.getText() + "Country : "+ movie.getCountries() + "\n");
            textArea.setText(textArea.getText() + "CastName : "+ movie.getCastName() + "\n");
            textArea.setText(textArea.getText() + "CastRole : "+ movie.getCastRole() + "\n");

            textArea.setText(textArea.getText() + "Director : "+ movie.getDirectorName() + "\n");
            textArea.setText(textArea.getText() + "Writer Name : "+ movie.getWriterName() + "\n");
            textArea.setText(textArea.getText() + "Writer Role : "+ movie.getWriterRole() + "\n");

            textArea.setText(textArea.getText() + "Keywords : "+ movie.getKeywords() + "\n");
            textArea.setText(textArea.getText() + "Languages : "+ movie.getLanguages() + "\n");
            textArea.setText(textArea.getText() + "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- \n \n");

        }
    }

    /**
     *
     * @param movieList
     * @return
     */
    public ArrayList<String> getKeywordsFromMovieList(ArrayList<Movie> movieList){
        ArrayList<String> keywordsFromMovieList =  new ArrayList<>();
        for(Movie movie: movieList){
            keywordsFromMovieList.add(movie.getTitle());

//            keywordsFromMovieList.add(movie.getYear());

            // keywordsFromMovieList.add(movie.getRating());

            for(String string: movie.getKeywords()){
                keywordsFromMovieList.add(string);
            }

//            for(String string:movie.getCastName()){
//                keywordsFromMovieList.add(string);
//            }
//            for(String string:movie.getCastRole()){
//                keywordsFromMovieList.add(string);
//            }

//            for(String string:movie.getWriterName()){
//                keywordsFromMovieList.add(string);
//            }

//            for(String string:movie.getWriterRole()){
////                keywordsFromMovieList.add(string);
////            }
//
//            for(String string:movie.getLanguages()){
//                keywordsFromMovieList.add(string);
//            }
//            keywordsFromMovieList.add(movie.getDirectorName());

//            keywordsFromMovieList.add(movie.getGenres().toString());

        }

        return keywordsFromMovieList;
    }


    /**
     *
     * @param keyWordsFromList
     * @return
     */
    public ArrayList<String> filterKeywords(ArrayList<String> keyWordsFromList ){
        ArrayList<String> filteredKeyWords = new ArrayList<>();
        for(String string: keyWordsFromList ){
            if(string.contains(" ")){
                String[] split = string.split(" ");
                for(int i=0;i<split.length;i++){
                    filteredKeyWords.add(split[i]);
                }
            }
            else{
                filteredKeyWords.add(string);
            }
        }
        // Non reliable keywords
        filteredKeyWords.removeAll(Collections.singleton("Pictures"));
        filteredKeyWords.removeAll(Collections.singleton("Entertainment"));
        filteredKeyWords.removeAll(Collections.singleton("Productions"));
        filteredKeyWords.removeAll(Collections.singleton("The"));
        return filteredKeyWords;
    }


    /**
     *
     * @param keywordsList
     * @return
     */
    public HashSet<String> getHashSetOfKeywords(ArrayList<String> keywordsList){
        HashSet<String> keywordsHashSet =  new HashSet<>(keywordsList);
        return keywordsHashSet;
    }


    /**
     *
     * @param searchedMoviesKeywords
     * @param imdbMoviesKeywords
     * @return
     */
    public HashMap<String,Integer> getKeywordFrequencyMap(HashSet<String> searchedMoviesKeywords, ArrayList<String> imdbMoviesKeywords){
        HashMap<String, Integer> keywordFrequencyMap = new HashMap<>();
        for(String keywordSearched: searchedMoviesKeywords){
            int keywordFrequency = 0;
            for (String keyword: imdbMoviesKeywords){
                if(keywordSearched.equals(keyword)){
                    keywordFrequency++;
                }
            }
            System.out.println("Keyword: " + keywordSearched + "  Frequency: "+ keywordFrequency);
            keywordFrequencyMap.put(keywordSearched,keywordFrequency);
        }
        return keywordFrequencyMap;
    }

    /**
     *
     * @param keywordFrequencyMap
     * @return
     */

    public HashMap<String,Integer> getSortedKeywordFrequencyMap(HashMap<String,Integer> keywordFrequencyMap){
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(keywordFrequencyMap.entrySet());
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


    /**
     *
     * @param sortedKeywordFrequencyMap
     * @param noOfKeyword
     * @return
     */
    public HashMap<String,Integer> getNSortedKeywordFrequencyMap(HashMap<String,Integer> sortedKeywordFrequencyMap, int noOfKeyword){
        int i = 0;
        HashMap<String,Integer> nKeywordFrequencyMap = new HashMap<>();

        for(Map.Entry<String,Integer> entry: sortedKeywordFrequencyMap.entrySet()){
            if(i<noOfKeyword){
                nKeywordFrequencyMap.put(entry.getKey(),entry.getValue());
            i++;
            }
            else{
                break;
            }
        }
        return nKeywordFrequencyMap;
    }

}
