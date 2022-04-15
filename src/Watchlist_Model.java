import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

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
            Watchlist_View errorMessage = null;
            errorMessage.displayError("That movie is already in this list.");
        }
    }
    public void RemoveMovieFromList(Movie_Model movieModel) {
        ListOfMovies.remove(movieModel);
    }

    public boolean addUserWatchlist(String username, String watchlist) throws IOException {
        boolean add = true;
        //add watchlist
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
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
    public boolean deleteUserWatchlist(String username, String watchlist) throws IOException {
        boolean delete = true;
        //add watchlist
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
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

    public ArrayList<Watchlist_Model> returnWatchlists(String username) throws IOException {
        ArrayList<Watchlist_Model> userWatchlists = new ArrayList<>();
        // get userdata
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content, User_Model[].class);
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        Gson g = new GsonBuilder().setPrettyPrinting().create();

        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                userWatchlists = user.getListOfWatchlists();
            }
        }
        return userWatchlists;
    }
}
