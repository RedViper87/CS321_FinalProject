import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Recommendations_Model {

    public ArrayList<Movie_Model> getRecommendations(String username) throws IOException {

        ArrayList<Movie_Model> Movies = new ArrayList<>();
        ArrayList<Movie_Model> returnMovies = new ArrayList<>();
        ArrayList<String> movieNames = new ArrayList<>();

        Path path = Paths.get("SampleMovieFile.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        Movie_Model[] list;
        list = gson.fromJson(content,Movie_Model[].class);
        ArrayList<Movie_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list);

        Path path2 = Paths.get("UserData.json");
        Charset charset2 = StandardCharsets.UTF_8;
        String content2 = Files.readString(path2, charset2);
        Gson gson2 = new Gson();
        User_Model[] list2;
        list2 = gson2.fromJson(content2, User_Model[].class);
        ArrayList<User_Model> arrayList2 = new ArrayList<>();
        Collections.addAll(arrayList2, list2);


        // get movie names from user data
        for(User_Model user: arrayList2){
            if(user.getUsername().equals(username)){
                for(User_Reviews review:user.getRatings()){
                    String moviename = review.getMovieName();
                    movieNames.add(moviename);
                }
            }
        }

        // put movies in an arraylist

        for (String movieName : movieNames) {
            for (Movie_Model movie : arrayList) {
                if (movie.getTitle().equals(movieName)) {
                    Movies.add(movie);
                }
            }
        }

        // get movies with the same genre
        for (Movie_Model movieModel : Movies) {
            for (Movie_Model movie : arrayList) {
                if (movieModel.getGenre().equals(movie.getGenre())) {
                    returnMovies.add(movie);
                }
            }
        }
        return returnMovies;
    }
}
