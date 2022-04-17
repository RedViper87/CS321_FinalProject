import java.util.ArrayList;

/**
 * Class that models a movie
 */
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

    /**
     * Constructs a movie model
     */
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

    /**
     * Sets the title of a movie
     * @param title String passed in
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Returns the title of a movie
     * @return Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Returns the year of a movie
     * @return Year
     */
    public String getYear() {
        return Year;
    }

    /**
     * Returns the Motion Picture Association film rating (ex. G, PG, PG-13, R)
     * @return Rated
     */
    public String getRated() {
        return Rated;
    }

    /**
     * Returns release date of a movie
     * @return Released
     */
    public String getReleased() {
        return Released;
    }

    /**
     * Returns the runtime of a movie
     * @return Runtime
     */
    public String getRuntime() {
        return Runtime;
    }

    /**
     * Returns the genre of a movie
     * @return Genre
     */
    public String getGenre() {
        return Genre;
    }

    /**
     * Returns the director of a movie
     * @return Director
     */
    public String getDirector() {
        return Director;
    }

    /**
     * Returns the writer of a movie
     * @return Writer
     */
    public String getWriter() {
        return Writer;
    }

    /**
     * Returns the Actors for a movie
     * @return Actors
     */
    public String getActors() {
        return Actors;
    }

    /**
     * Returns the plot of a movie
     * @return Plot
     */
    public String getPlot() {
        return Plot;
    }

    /**
     * Returns the language of a movie
     * @return Language
     */
    public String getLanguage() {
        return Language;
    }

    /**
     * Returns the country of origin of a movie
     * @return Country
     */
    public String getCountry() {
        return Country;
    }

    /**
     * Returns the awards of a movie
     * @return Awards
     */
    public String getAwards() {
        return Awards;
    }

    /**
     * Returns the URL for the poster of a movie
     * @return Poster
     */
    public String getPoster() {
        return Poster;
    }

    /**
     * Returns array of ratings, with source and value, for a movie
     * @return Ratings
     */
    public ArrayList<Rating> getRatings() {
        return Ratings;
    }

    /**
     * Returns the metascore of a movie
     * @return Metascore
     */
    public String getMetascore() {
        return Metascore;
    }

    /**
     * Returns the Imdb Rating for a movie
     * @return imdbRating
     */
    public String getImdbRating() {
        return imdbRating;
    }

    /**
     * Returns the Imdb Votes for a movie
     * @return imdbVotes
     */
    public String getImdbVotes() {
        return imdbVotes;
    }

    /**
     * Returns the ImdbID of a movie
     * @return imdbID
     */
    public String getImdbID() {
        return imdbID;
    }

    /**
     * Returns the type of movie
     * @return Type
     */
    public String getType() {
        return Type;
    }

    /**
     * Returns date that a movie went on DVD
     * @return DVD
     */
    public String getDVD() {
        return DVD;
    }

    /**
     * Returns the amount of money the movie made at the Box Office
     * @return BoxOffice
     */
    public String getBoxOffice() {
        return BoxOffice;
    }

    /**
     * Returns the production of a movie
     * @return Production
     */
    public String getProduction() {
        return Production;
    }

    /**
     * Returns the website for a movie
     * @return Website
     */
    public String getWebsite() {
        return Website;
    }

    /**
     * Returns the respons for a movie
     * @return Response
     */
    public String getResponse() {
        return Response;
    }
}
