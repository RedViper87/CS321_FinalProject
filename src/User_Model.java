import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class User_Model{

    private String username;
    private String password;
    private ArrayList<Watchlist_Model> listOfWatchlists;
    private ArrayList<User_Reviews> listOfUserReviews;

    public User_Model() {
        this.listOfWatchlists = new ArrayList<>();
    }

    public ArrayList<User_Reviews> getRatings() {
        return listOfUserReviews;
    }

    public ArrayList<Watchlist_Model> getListOfWatchlists() {
        return listOfWatchlists;
    }

    public void setUsername(String u){
        username = u;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String p){
        password = p;
    }

    public void addUserReview(String name, String number, String comment) {
        User_Reviews review = new User_Reviews();
        review.setMovieName(name);
        review.setNumericalRating(number);
        review.setUserInputReview(comment);
        listOfUserReviews.add(review);
    }
    public boolean addWatchlist(String name){
        Watchlist_Model watchlist = new Watchlist_Model();
        watchlist.setName(name);
        for(Watchlist_Model watchlists:listOfWatchlists){
            if(watchlists.getName().equals(name)){
                return false;
            }
        }
        listOfWatchlists.add(watchlist);
        return true;
    }
    public boolean deleteWatchlist(String name){
        for(Watchlist_Model watchlist:listOfWatchlists){
            if(watchlist.getName().equals(name)){
                listOfWatchlists.remove(watchlist);
                return true;
            }
        }
        return false;
    }

    public boolean addNewUser() throws IOException {

        /* Make sure there is not a duplicate */
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content,User_Model[].class);
        if(list == null){
            return false;
        }
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list);

        /* check if user is in list */
        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                return false;
            }
        }
        String name = "\""+username+"\"";
        String pass = "\""+password+"\"";
        String newUser = "{\"username\": "+name+", \"password\":"+pass+", \"listOfWatchlists\": [{\"Name\": \"\",\"ListOfMovies\": [{\"Title\":\"\",\"Year\":\"\",\"Rated\":\"\",\"Released\":\"\",\"Runtime\":\"\",\"Genre\":\"\",\"Director\":\"\",\"Writer\":\"\",\"Actors\":\"\",\"Plot\":\"\",\"Language\":\"\",\"Country\":\"\",\"Awards\":\"\",\"Poster\":\"\",\"Ratings\":[{\"Source\":\"\",\"Value\":\"\"}],\"Metascore\":\"\",\"imdbRating\":\"\",\"imdbVotes\":\"\",\"imdbID\":\"\",\"Type\":\"\",\"DVD\":\"\",\"BoxOffice\":\"\",\"Production\":\"\",\"Website\":\"\",\"Response\":\"\"}]}],\n" + "  \"listOfUserReviews\": [{\"moviename\": \"\", \"numericalrating\": \"\", \"userinputreview\": \"\"}]\n" + "  }";


       File f = new File("UserData.json");
        if(f.length() == 0){
            try (FileWriter file = new FileWriter("UserData.json", true)){
                file.write("[\n"+newUser);
                file.write("\n]");
                file.flush();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }else{
            content = content.replaceAll("]$", "");
            Files.write(path, content.getBytes(charset));
            String content2 = new String(Files.readAllBytes(path), charset);
            int index = content2.lastIndexOf("}");
            String content3 = content2.substring(0,index)+ "},"+ content2.substring(index+1);
            Files.write(path, content3.getBytes(charset));

            try (FileWriter file = new FileWriter("UserData.json", true)){
                file.write(newUser);
                file.write("\n]");
                file.flush();
            }catch(IOException ex){
                ex.printStackTrace();
            }

        }
        return true;
    }

    public boolean checkUser() throws IOException {
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content,User_Model[].class);
        if(list == null){
            return false;
        }
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list);

        /* check if user is in list */
        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }

    public String grabUserDataReview(String username) throws IOException {
        /* get user data into arraylist*/
        Path path2 = Paths.get("UserData.json");
        Charset charset2 = StandardCharsets.UTF_8;
        String content2 = new String(Files.readAllBytes(path2), charset2);
        Gson gson2 = new Gson();
        User_Model[] list2;
        list2 = gson2.fromJson(content2, User_Model[].class);
        ArrayList<User_Model> arrayList2 = new ArrayList<>();
        Collections.addAll(arrayList2, list2);

        for(User_Model user: arrayList2){
            if(user.getUsername().equals(username)){
                String movieName = null;
                String number = null;
                String comment = null;
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
                StringBuffer sb = new StringBuffer();
                for(String string: rate){
                    sb.append(string);
                }
                Ratings = sb.toString();
                return Ratings;
            }
        }

        return "";
    }

    public ArrayList<Watchlist_Model> grabUserWatchlists(String username) throws IOException {
        /* get user data into arrayList */
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content, User_Model[].class);
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        for(User_Model user: arrayList){
            if(user.getUsername().equals(username)){
                String watchlistName = null;

                ArrayList<Watchlist_Model> watchlists = new ArrayList<>();
                for(Watchlist_Model wl: user.getListOfWatchlists()){
                    watchlistName = wl.getName();
                    if(!watchlistName.equals("")){
                        watchlists.add(wl);
                    }
                }
                return watchlists;
            }
        }
        ArrayList<Watchlist_Model> theArray = new ArrayList<Watchlist_Model>();
        return theArray;
    }

}
