import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that models a watchlist
 */
public class Watchlist_Model{
    /* ATTRIBUTES */
    private String Name;
    private final ArrayList<Movie_Model> ListOfMovies;

    /**
     * Constructs a watchlist model
     */
    public Watchlist_Model() {
        this.ListOfMovies = new ArrayList<>();
    }
    /* FUNCTIONS */

    /**
     * Sets the name of the watchlist
     * @param name String passed to set name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Returns the name of a watchlist
     * @return Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Returns a watchlist's list of movies
     * @return ListOfMovies
     */
    public ArrayList<Movie_Model> getListOfMovies() {
        return ListOfMovies;
    }

    /**
     * Adds a movie to a watchlist
     * @param movieName String passed to set a movie's title
     */
    public void addMovie(String movieName){
        Movie_Model movie = new Movie_Model();
        movie.setTitle(movieName);
        ListOfMovies.add(movie);
    }

    /**
     * Removes all the movies from a watchlist
     */
    public void removeMovies(){
        ListOfMovies.clear();
    }

    /**
     * Adds movies to the watchlist and puts the data in UserData.json
     * @param listOfMovies array of String movie titles
     * @param username String of passed in username
     * @param watchlistName String of passed in watchlist name
     * @throws IOException used for try catch
     */
    public void AddMovieToList(String [] listOfMovies, String username, String watchlistName) throws IOException {
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content, User_Model[].class);
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        Gson g = new GsonBuilder().setPrettyPrinting().create();

        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                ArrayList<Watchlist_Model> watchlistList;
                watchlistList = user.getListOfWatchlists();
                for(Watchlist_Model watchlist: watchlistList){
                    if(watchlist.getName().equals(watchlistName)){
                        if(listOfMovies == null){
                            watchlist.removeMovies();
                        }
                        watchlist.removeMovies();
                        assert listOfMovies != null;
                        for(String movie: listOfMovies){
                            watchlist.addMovie(movie);
                        }
                    }
                }
            }
        }
        String json = g.toJson(arrayList);
        FileWriter file = new FileWriter("UserData.json");
        file.write(json);
        file.flush();
    }

    /**
     * Adds a user watchlist to the UserData.json
     * @param username  String of passed in username
     * @param watchlist String of passed in watchlist name
     * @return add, true or false
     * @throws IOException used for try catch
     */
    public boolean addUserWatchlist(String username, String watchlist) throws IOException {
        boolean add = true;
        //add watchlist
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content, User_Model[].class);
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        Gson g = new GsonBuilder().setPrettyPrinting().create();

        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                add = user.addWatchlist(watchlist);
            }
        }
        String json = g.toJson(arrayList);
        FileWriter file = new FileWriter("UserData.json");
        file.write(json);
        file.flush();
        return add;
    }

    /**
     * Deletes a users watchlist
     * @param username String passed in username
     * @param watchlist String passed in watchlist name
     * @return delete, true or false
     * @throws IOException used for try catch
     */
    public boolean deleteUserWatchlist(String username, String watchlist) throws IOException {
        boolean delete = true;
        //add watchlist
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content, User_Model[].class);
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        Gson g = new GsonBuilder().setPrettyPrinting().create();

        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                delete = user.deleteWatchlist(watchlist);
            }
        }
        String json = g.toJson(arrayList);
        FileWriter file = new FileWriter("UserData.json");
        file.write(json);
        file.flush();
        return delete;
    }

    /**
     * Returns all of a user's watchlists
     * @param username String passed in username
     * @return userWatchlists array
     * @throws IOException used for try catch
     */
    public ArrayList<Watchlist_Model> returnWatchlists(String username) throws IOException {
        ArrayList<Watchlist_Model> userWatchlists = new ArrayList<>();
        // get userdata
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content, User_Model[].class);
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                userWatchlists = user.getListOfWatchlists();
            }
        }
        return userWatchlists;
    }

    /**
     * Returns a list of movies in a watchlist
     * @param username String passed in username
     * @param watchlistName String passed in watchlist name
     * @return arrayList2, list of movie models
     * @throws IOException used for try catch
     */
    public ArrayList<Movie_Model> returnMovies(String username, String watchlistName) throws IOException {
        ArrayList<Movie_Model> MovieList = new ArrayList<>();
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content, User_Model[].class);
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        /* get movie data into arraylist*/
        Path path2 = Paths.get("SampleMovieFile.json");
        Charset charset2 = StandardCharsets.UTF_8;
        String content2 = Files.readString(path2, charset2);
        Gson gson2 = new Gson();
        Movie_Model[] list2;
        list2 = gson2.fromJson(content2,Movie_Model[].class);
        ArrayList<Movie_Model> arrayList2 = new ArrayList<>();
        Collections.addAll(arrayList2,list2);

        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                ArrayList<Watchlist_Model> watchlistList;
                watchlistList = user.getListOfWatchlists();
                for(Watchlist_Model watchlist: watchlistList){
                    if(watchlist.getName().equals(watchlistName)){
                        // get the list of movies from user
                        MovieList = watchlist.getListOfMovies();
                    }
                }
            }
        }
        // take out the movies that are already in the watchlist
        for (Movie_Model movieModel : MovieList) {
            arrayList2.removeIf(movie -> movieModel.getTitle().equals(movie.getTitle()) && movieModel.getYear().equals(movie.getYear()));
        }
        return arrayList2;
    }
}