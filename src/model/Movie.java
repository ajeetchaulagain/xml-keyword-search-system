package model;

import java.util.ArrayList;


/**
 * POJO class to map the parsed data from XML file
 */
public class Movie {

    // Instance Variables
    private String title;
    private String year;
    private String rating;

    private String directorName;

    private ArrayList<String> genres;
    private ArrayList<String> writerName;
    private ArrayList<String> writerRole;

    private ArrayList<String> countries;

    private ArrayList<String> languages;

    private ArrayList<String> castName;
    private ArrayList<String> castRole;

    private ArrayList<String> keywords;


    private ArrayList<String> companies;




    // Getter and Setter Methods for the fields

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getWriterName() {
        return writerName;
    }

    public void setWriterName(ArrayList<String> writerName) {
        this.writerName = writerName;
    }

    public ArrayList<String> getWriterRole() {
        return writerRole;
    }

    public void setWriterRole(ArrayList<String> writerRole) {
        this.writerRole = writerRole;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public ArrayList<String> getCastName() {
        return castName;
    }

    public void setCastName(ArrayList<String> castName) {
        this.castName = castName;
    }

    public ArrayList<String> getCastRole() {
        return castRole;
    }

    public void setCastRole(ArrayList<String> castRole) {
        this.castRole = castRole;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<String> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<String> companies) {
        this.companies = companies;
    }

    // To string method is overrided with the new definition.
    @Override
    public String toString() {
            return "Movie Title:"+title+" year: "+year+ " rating: "+ rating + " director name:" + directorName + " genres: "+ genres + "\n \n";
    }

}