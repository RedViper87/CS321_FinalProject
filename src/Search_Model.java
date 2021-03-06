import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Class that models a search panel
 */
public class Search_Model{
    /**
     * Constructs layout for the search model
     * @param cb1 checkbox 1 for Title search
     * @param cb2 checkbox 2 for Actor search
     * @param cb3 checkbox 3 for Genre search
     * @param cb4 checkbox 4 for Year search
     * @param searchParameter String used to compare and find movies based on which checkbox is checked
     * @return returnMovies, list of movies that match the criteria searched for
     * @throws IOException used for try catch
     */
    public ArrayList<Movie_Model> SearchDatabase(boolean cb1, boolean cb2, boolean cb3, boolean cb4, String searchParameter) throws IOException {

        ArrayList<Movie_Model> returnMovies = new ArrayList<>();

        /* get movie data into arraylist*/
        Path path = Paths.get("SampleMovieFile.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        Movie_Model[] list;
        list = gson.fromJson(content,Movie_Model[].class);
        ArrayList<Movie_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list);

        if(!cb1 && cb2 && !cb3 && !cb4) {
            for (Movie_Model movie : arrayList) {
                if (movie.getActors().contains(searchParameter)) {
                    returnMovies.add(movie);
                }
            }
        }
        if(!cb1 && !cb2 && cb3 && !cb4) {
            for (Movie_Model movie : arrayList) {
                if (movie.getGenre().contains(searchParameter)) {
                    returnMovies.add(movie);
                }
            }
        }
        if(!cb1 && cb2 && cb3 && cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getActors().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getGenre().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getYear().contains(searchParameter)) {
                    returnMovies.add(movie);
                }
            }
        }
        if(cb1 && !cb2 && !cb3 && cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getTitle().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getYear().contains(searchParameter)) {
                    returnMovies.add(movie);
                }
            }
        }
        if(!cb1 && cb2 && cb3 && !cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getActors().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getGenre().contains(searchParameter)) {
                    returnMovies.add(movie);
                }
            }
        }
        if(cb1 && !cb2 && cb3 && cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getTitle().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getGenre().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getYear().contains(searchParameter)) {
                    returnMovies.add(movie);
                }
            }
        }
        if(cb1 && cb2 && !cb3 && cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getActors().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getTitle().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getYear().contains(searchParameter)) {
                    returnMovies.add(movie);
                }
            }
        }
        if(cb1 && cb2 && cb3 && !cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getActors().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getGenre().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getTitle().contains(searchParameter)) {
                    returnMovies.add(movie);
                }
            }
        }
        if(cb1 && cb2 && cb3 && cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getActors().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getGenre().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getYear().contains(searchParameter)) {
                    returnMovies.add(movie);
                }else if(movie.getTitle().contains(searchParameter)){
                    returnMovies.add(movie);
                }
            }
        }

        if(!cb1 && !cb2 && !cb3 && cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getYear().contains(searchParameter)){
                    returnMovies.add(movie);
                }
            }
        }
        if(!cb1 && !cb2 && cb3 && cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getYear().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getGenre().contains(searchParameter)){
                    returnMovies.add(movie);
                }
            }
        }
        if(cb1 && cb2 && !cb3 && !cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getActors().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getTitle().contains(searchParameter)){
                    returnMovies.add(movie);
                }
            }
        }
        if(!cb1 && cb2 && !cb3 && cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getActors().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getYear().contains(searchParameter)) {
                    returnMovies.add(movie);
                }
            }
        }
        if(cb1 && !cb2 && cb3 && !cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getTitle().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getGenre().contains(searchParameter)){
                    returnMovies.add(movie);
                }
            }
        }
        if(cb1 && !cb2 && !cb3 && !cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getTitle().contains(searchParameter)){
                    returnMovies.add(movie);
                }
            }
        }
        if(!cb1 && !cb2 && !cb3 && !cb4){
            for(Movie_Model movie:arrayList){
                if(movie.getTitle().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getYear().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getRated().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getReleased().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getRuntime().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getGenre().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getDirector().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getWriter().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getActors().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getPlot().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getLanguage().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getCountry().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getAwards().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getMetascore().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getImdbRating().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getImdbVotes().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getImdbID().contains(searchParameter)){
                    returnMovies.add(movie);
                }else if(movie.getType().contains(searchParameter)){
                    returnMovies.add(movie);
                }
            }
        }
    return returnMovies;
    }
}
