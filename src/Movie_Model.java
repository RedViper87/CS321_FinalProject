import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Movie_Model{

    /* ATTRIBUTES */
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;      //url to movie poster
    private ArrayList<Rating> Ratings = new ArrayList<>();
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String Type;
    private String DVD;         //when the movie came to DVD
    private String BoxOffice;   //amount of money made at the BoxOffice
    private String Production;  //list of producing companies
    private String Website;
    private String Response;

    /* FUNCTIONS */
    public void setTitle(String title) {
        Title = title;
    }
    public String getTitle() {
        return Title;
    }

    public void setYear(String year) {
        Year = year;
    }
    public String getYear() {
        return Year;
    }

    public void setRated(String rated) {
        Rated = rated;
    }
    public String getRated() {
        return Rated;
    }

    public void setReleased(String released) {
        Released = released;
    }
    public String getReleased() {
        return Released;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }
    public String getRuntime() {
        return Runtime;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
    public String getGenre() {
        return Genre;
    }

    public void setDirector(String director) {
        Director = director;
    }
    public String getDirector() {
        return Director;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }
    public String getWriter() {
        return Writer;
    }

    public void setActors(String actors) {
        Actors = actors;
    }
    public String getActors() {
        return Actors;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }
    public String getPlot() {
        return Plot;
    }

    public void setLanguage(String language) {
        Language = language;
    }
    public String getLanguage() {
        return Language;
    }

    public void setCountry(String country) {
        Country = country;
    }
    public String getCountry() {
        return Country;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }
    public String getAwards() {
        return Awards;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
    public String getPoster() {
        return Poster;
    }

    public ArrayList<Rating> getRatings() {
        return Ratings;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }
    public String getMetascore() {
        return Metascore;
    }

    public void setImdbRating(String theImdbRating) {
        imdbRating = theImdbRating;
    }
    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbVotes(String theImdbVotes) {
        imdbVotes = theImdbVotes;
    }
    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbID(String theImdbID) {
        imdbID = theImdbID;
    }
    public String getImdbID() {
        return imdbID;
    }

    public void setType(String type) {
        Type = type;
    }
    public String getType() {
        return Type;
    }

    public void setDVD(String dvd) {
        DVD = dvd;
    }
    public String getDVD() {
        return DVD;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }
    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setProduction(String production) {
        Production = production;
    }
    public String getProduction() {
        return Production;
    }

    public void setWebsite(String website) {
        Website = website;
    }
    public String getWebsite() {
        return Website;
    }

    public void setResponse(String response) {
        Response = response;
    }
    public String getResponse() {
        return Response;
    }

}
