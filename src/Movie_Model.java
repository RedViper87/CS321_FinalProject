import java.util.ArrayList;

public class Movie_Model{

    /* ATTRIBUTES */
    private String Title;
    private final String Year;
    private final String Rated;
    private final String Released;
    private final String Runtime;
    private final String Genre;
    private final String Director;
    private final String Writer;
    private final String Actors;
    private final String Plot;
    private final String Language;
    private final String Country;
    private final String Awards;
    private final String Poster;      //url to movie poster
    private final ArrayList<Rating> Ratings = new ArrayList<>();
    private final String Metascore;
    private final String imdbRating;
    private final String imdbVotes;
    private final String imdbID;
    private final String Type;
    private final String DVD;         //when the movie came to DVD
    private final String BoxOffice;   //amount of money made at the BoxOffice
    private final String Production;  //list of producing companies
    private final String Website;
    private final String Response;

    public Movie_Model() {
        Year = "";
        Rated = "";
        Released = "";
        Runtime = "";
        Genre = "";
        Director = "";
        Writer = "";
        Actors = "";
        Plot = "";
        Language = "";
        Country = "";
        Awards = "";
        Poster = "";
        Metascore = "";
        imdbRating = "";
        imdbVotes = "";
        imdbID = "";
        Type = "";
        DVD = "";
        BoxOffice = "";
        Production = "";
        Website = "";
        Response = "";
    }

    /* FUNCTIONS */
    public void setTitle(String title) {
        Title = title;
    }
    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public String getPoster() {
        return Poster;
    }

    public ArrayList<Rating> getRatings() {
        return Ratings;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getDVD() {
        return DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public String getWebsite() {
        return Website;
    }

    public String getResponse() {
        return Response;
    }
}
