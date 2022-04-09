import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    // add a new watchlist
    public boolean addNewWatchlist(String username) throws IOException {
        /* Make sure there is not a duplicate */
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        Gson gson = new Gson();
        Watchlist_Model[] list;
        list = gson.fromJson(content, Watchlist_Model[].class);
        if(list == null){
            return false;
        }
        ArrayList<Watchlist_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        /* check if watchlist is in list */
        for(Watchlist_Model watchlist:arrayList) {
            if(watchlist.getName().equals(Name)) {
                return false;
            }
        }

        //*Add the watchlist name to the user data file here
        Path path2 = Paths.get("UserData.json");
        Charset charset2 = StandardCharsets.UTF_8;
        String content2 = new String(Files.readAllBytes(path2), charset2);
        Gson gson2 = new Gson();
        User_Model[] list2;
        list2 = gson2.fromJson(content2, User_Model[].class);
        ArrayList<User_Model> arrayList2 = new ArrayList<>();
        Collections.addAll(arrayList2, list2);

        Gson g = new GsonBuilder().setPrettyPrinting().create();

        for(User_Model user:arrayList2){
            if(user.getUsername().equals(username)){
                user.addWatchlist(Name);
            }
        }
        String json = g.toJson(arrayList2);
        FileWriter file = new FileWriter("UserData.json");
        file.write(json);
        file.flush();

        return true;
    }

    // delete a watchlist
}
