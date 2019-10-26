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

public class ChooseSourceModel {

    // Movie List
    ArrayList<Movie> movieList = new ArrayList<>();
    String[] excludedKeywordsForValidation = {"English", "The"};

    /**
     *
     * @param selectedFile
     * @param textArea
     * @return
     */
    public ArrayList<Movie> parseAndDisplayXML(File selectedFile, TextArea textArea) {

        textArea.setText("");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        }
        try {
            Document doc = docBuilder.parse(selectedFile);

            doc.getDocumentElement().normalize();


            textArea.setText(textArea.getText() + "Root Element: " + doc.getDocumentElement().getNodeName() + "\n");
            NodeList nList = doc.getElementsByTagName("movie");
            textArea.setText(textArea.getText() + "----------------------------" + "\n");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Movie movie = null;
                Node nNode = nList.item(temp);
                textArea.setText(textArea.getText() + "\n Current Element :" + nNode.getNodeName() + "\n");
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    movie = new Movie();
                    Element eElement = (Element) nNode;

                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    textArea.setText(textArea.getText() + "Title : " + title + "\n");
                    movie.setTitle(title);

                    String year = eElement.getElementsByTagName("year").item(0).getTextContent();
                    textArea.setText(textArea.getText() + "Year : " + year + "\n");
                    movie.setYear(year);

                    String rating = eElement.getElementsByTagName("rating").item(0).getTextContent();
                    textArea.setText(textArea.getText() + "Rating : " + rating + "\n");
                    movie.setRating(rating);


                    NodeList directorsList = eElement.getElementsByTagName("director");
                    System.out.println("directorsList; " + directorsList.getLength());
                    for (int count = 0; count < directorsList.getLength(); count++) {
                        Node nodeDirector = directorsList.item(count);
                        if (nodeDirector.getNodeType() == nodeDirector.ELEMENT_NODE) {
                            Element director = (Element) nodeDirector;
                            String directorName = director.getElementsByTagName("name").item(0).getTextContent();
                            textArea.setText(textArea.getText() + "Director Name : " + directorName + "\n");
                            movie.setDirectorName(directorName);
                        }
                    }


                    NodeList genresNodeList = eElement.getElementsByTagName("item");
                    ArrayList<String> genres = new ArrayList<String>();

                    for (int count = 0; count < genresNodeList.getLength(); count++) {
                        Node nodeItem = genresNodeList.item(count);
                        if (nodeItem.getNodeType() == nodeItem.ELEMENT_NODE) {
                            Element item = (Element) nodeItem;
                            String genre = item.getTextContent();
                            textArea.setText(textArea.getText() + "Genre : " + genre + "\n");
                            genres.add(genre);
                            movie.setGenres(genres);
                        }
                    }


                    NodeList writersNodeList = eElement.getElementsByTagName("writer");
                    ArrayList<String> writersName = new ArrayList<>();
                    ArrayList<String> writersRole = new ArrayList<>();

                    for (int count = 0; count < writersNodeList.getLength(); count++) {
                        Node nodeItem = writersNodeList.item(count);
                        if (nodeItem.getNodeType() == nodeItem.ELEMENT_NODE) {
                            Element writer = (Element) nodeItem;
                            String writerName = writer.getElementsByTagName("name").item(0).getTextContent();
                            String writerRole = writer.getElementsByTagName("role").item(0).getTextContent();

//                            System.out.println("[writerName , writerRole]" + "[" + writerName + " " + writerRole + "]");


                            textArea.setText(textArea.getText() + "Writer Name: " + writerName + "\n");
                            textArea.setText(textArea.getText() + "Writer Role: " + writerRole + "\n");

                            writersName.add(writerName);
                            writersRole.add(writerRole);
                            movie.setWriterName(writersName);
                            movie.setWriterRole(writersRole);
                        }
                    }

                    NodeList countriesNodeList = eElement.getElementsByTagName("country");
                    ArrayList<String> countries = new ArrayList<String>();
                    for (int count = 0; count < countriesNodeList.getLength(); count++) {
                        Node nodeItem = countriesNodeList.item(count);
                        if (nodeItem.getNodeType() == nodeItem.ELEMENT_NODE) {
                            Element item = (Element) nodeItem;
                            String country = item.getTextContent();
                            textArea.setText(textArea.getText() + "Country : " + country + "\n");
                            countries.add(country);
                            movie.setCountries(countries);
                        }
                    }


                    NodeList languagesNodeList = eElement.getElementsByTagName("language");
                    ArrayList<String> languages = new ArrayList<String>();
                    for (int count = 0; count < languagesNodeList.getLength(); count++) {
                        Node nodeItem = languagesNodeList.item(count);
                        if (nodeItem.getNodeType() == nodeItem.ELEMENT_NODE) {
                            Element lang = (Element) nodeItem;
                            String language = lang.getTextContent();
                            textArea.setText(textArea.getText() + "Language : " + language + "\n");
                            languages.add(language);
                            movie.setLanguages(languages);
                        }
                    }

                    NodeList companiesNodeList = eElement.getElementsByTagName("company");
                    ArrayList<String> companies = new ArrayList<String>();
                    for (int count = 0; count < companiesNodeList.getLength(); count++) {
                        Node nodeItem = companiesNodeList.item(count);
                        if (nodeItem.getNodeType() == nodeItem.ELEMENT_NODE) {
                            Element comp = (Element) nodeItem;
                            String company = comp.getTextContent();
                            textArea.setText(textArea.getText() + "Company : " + company + "\n");
                            companies.add(company);
                            movie.setCompanies(companies);
                        }
                    }

                    NodeList castNodeList = eElement.getElementsByTagName("p");
                    ArrayList<String> castNames = new ArrayList<>();
                    ArrayList<String> castRoles = new ArrayList<>();

                    for (int count = 0; count < castNodeList.getLength(); count++) {
                        Node nodeItem = castNodeList.item(count);
                        if (nodeItem.getNodeType() == nodeItem.ELEMENT_NODE) {
                            Element cast = (Element) nodeItem;
                            String castName = cast.getElementsByTagName("name").item(0).getTextContent();
                            String castRole = cast.getElementsByTagName("role").item(0).getTextContent();

                            textArea.setText(textArea.getText() + "Cast Name: " + castName + "\n");
                            textArea.setText(textArea.getText() + "Cast Role: " + castRole + "\n");

                            castNames.add(castName);
                            castRoles.add(castRole);

                            movie.setCastName(castNames);
                            movie.setCastRole(castRoles);
                        }
                    }


                    NodeList keywordsNodeList = eElement.getElementsByTagName("kw");
                    ArrayList<String> keywords = new ArrayList<String>();
                    for (int count = 0; count < keywordsNodeList.getLength(); count++) {
                        Node nodeItem = keywordsNodeList.item(count);
                        if (nodeItem.getNodeType() == nodeItem.ELEMENT_NODE) {
                            Element key = (Element) nodeItem;
                            String keyword = key.getTextContent();
                            textArea.setText(textArea.getText() + "Keyword : " + keyword + "\n");
                            keywords.add(keyword);
                            movie.setKeywords(keywords);
                        }
                    }

                    movieList.add(movie);
//                    System.out.println(movie.toString());
                }
            }

            System.out.println("Movie List Size:" + movieList.size());

        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return movieList;
    }


    // This method search the keyword in entire movie collection and returns the List of Movie that has that keyword

    /**
     *
     * @param searchKeyword
     * @return
     */
    public ArrayList<Movie> searchMovie(String searchKeyword) {
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
            keywordsFromMovieList.add(movie.getYear());

            // keywordsFromMovieList.add(movie.getRating());

            for(String string: movie.getKeywords()){
                keywordsFromMovieList.add(string);
            }

            for(String string:movie.getCastName()){
                keywordsFromMovieList.add(string);
            }

//            for(String string:movie.getCastRole()){
//                keywordsFromMovieList.add(string);
//            }

            for(String string:movie.getWriterName()){
                keywordsFromMovieList.add(string);
            }

//            for(String string:movie.getWriterRole()){
////                keywordsFromMovieList.add(string);
////            }
//
//            for(String string:movie.getLanguages()){
//                keywordsFromMovieList.add(string);
//            }
            keywordsFromMovieList.add(movie.getDirectorName());

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
