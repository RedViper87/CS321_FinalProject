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

public class Review_Model extends Movie_Model{
    /* ATTRIBUTES */
    private String MovieName;
    private String NumericalRating;
    private String UserInputReview;

    /* FUNCTIONS */
    public void setNumericalRating(String numericalRating) {
        NumericalRating = numericalRating;
    }

    public void setUserInputReview(String userInputReview) {
        UserInputReview = userInputReview;
    }

    public void setMovieName(String movieName){
        MovieName = movieName;
    }

    public void addUserReview(String username) throws IOException {
        // when dropdown button for movie is clicked and button to enter review is clicked, add review
        Path path2 = Paths.get("UserData.json");
        Charset charset2 = StandardCharsets.UTF_8;
        String content2 = Files.readString(path2, charset2);
        Gson gson2 = new Gson();
        User_Model[] list2;
        list2 = gson2.fromJson(content2, User_Model[].class);
        ArrayList<User_Model> arrayList2 = new ArrayList<>();
        Collections.addAll(arrayList2, list2);

        Gson g = new GsonBuilder().setPrettyPrinting().create();

        for(User_Model user:arrayList2){
            if(user.getUsername().equals(username)){
                user.addUserReview(MovieName, NumericalRating,UserInputReview);
            }
        }
        String json = g.toJson(arrayList2);
        FileWriter file = new FileWriter("UserData.json");
        file.write(json);
        file.flush();
    }
    public String grabUserData(String username) throws IOException {
        /* get user data into arraylist*/
        Path path2 = Paths.get("UserData.json");
        Charset charset2 = StandardCharsets.UTF_8;
        String content2 = Files.readString(path2, charset2);
        Gson gson2 = new Gson();
        User_Model[] list2;
        list2 = gson2.fromJson(content2, User_Model[].class);
        ArrayList<User_Model> arrayList2 = new ArrayList<>();
        Collections.addAll(arrayList2, list2);

        for(User_Model user: arrayList2){
            if(user.getUsername().equals(username)){
                String movieName;
                String number;
                String comment;
                String Ratings;
                ArrayList <String> rate = new ArrayList<>();
                for(User_Reviews r: user.getRatings()){
                    movieName = r.getMovieName();
                    number = r.getNumericalRating();
                    comment = r.getUserInputReview();
                    if(!movieName.equals("")){
                        rate.add("{Movie Name: "+movieName+", Score: "+number+"/10, "+"Comment: "+comment+"}");
                    }
                }
                StringBuilder sb = new StringBuilder();
                for(String string: rate){
                    sb.append(string);
                }
                Ratings = sb.toString();
                return Ratings;
            }
        }
        return "";
    }
}