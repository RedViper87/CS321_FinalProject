import java.util.ArrayList;

public class Watchlist_Model{
    /* ATTRIBUTES */
    private String Name;
    private ArrayList<Movie_Model> ListOfMovies;

    /* FUNCTIONS */
    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }

    public void AddMovieToList(Movie_Model movieModel) {
        //if the movie isn't already in the list, add it
        if (!ListOfMovies.contains(movieModel)) {
            ListOfMovies.add(movieModel);
        }
        else {
            //movie is already in list
            /* PRINT AN ERROR MESSAGE */
        }
    }
    public void RemoveMovieFromList(Movie_Model movieModel) {
        ListOfMovies.remove(movieModel);
    }
    // make and delete watchlist
}
