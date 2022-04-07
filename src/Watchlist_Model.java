import com.google.gson.Gson;
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
            /* PRINT AN ERROR MESSAGE */
        }
    }
    public void RemoveMovieFromList(Movie_Model movieModel) {
        ListOfMovies.remove(movieModel);
    }
    // add a new watchlist
    public boolean addNewWatchlist() throws IOException {
        //get username so we know where to add watchlist data in UserData.json


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

        JsonObject newWatchlist = new JsonObject();
        newWatchlist.addProperty("watchlist_name", Name);
        File f = new File("UserData.json");
        //NEED TO DO: Add the watchlist name and list of movies to the user data file here
        if(f.length() == 0) {
            try (FileWriter file = new FileWriter("UserData.json", true)){
                file.write("[\n" + String.valueOf(newWatchlist));
                file.write("\n]");
                file.flush();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }else{

        }

        return true;
    }

    // delete a watchlist
}
