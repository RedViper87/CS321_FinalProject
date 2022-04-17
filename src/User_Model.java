import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A class for making a user with a username, password, and list of watchlists and reviews
 */
public class User_Model{

    private String username;
    private String password;
    private final ArrayList<Watchlist_Model> listOfWatchlists;
    private final ArrayList<User_Reviews> listOfUserReviews;

    /**
     * Constructs a User_Model object and makes a lists of watchlists and reviews
     */
    public User_Model() {
        this.listOfWatchlists = new ArrayList<>();
        this.listOfUserReviews = new ArrayList<>();
    }

    /**
     * returns a list of user reviews
     * @return listOfUserReviews that contains a list of a specific user's reviews
     */
    public ArrayList<User_Reviews> getRatings() {
        return listOfUserReviews;
    }

    /**
     * returns a list of user watchlists
     * @return listOfUserWatchlists that contains a list of a specific user's watchlists
     */
    public ArrayList<Watchlist_Model> getListOfWatchlists() {
        return listOfWatchlists;
    }

    /**
     * Sets the current user's username
     * @param u the username of a user
     */
    public void setUsername(String u){
        username = u;
    }

    /**
     * Returns the username of the current user
     * @return username of the current user
     */
    public String getUsername(){
        return username;
    }

    /**
     * Returns the password of the current user
     * @return password of the current user
     */
    public String getPassword(){
        return password;
    }

    /**
     * Sets the password of the current user
     * @param p the password of a user
     */
    public void setPassword(String p){
        password = p;
    }

    /**
     * Adds a user review to the current user's list of reviews
     * @param name the name of the movie
     * @param number the score of the movie out of 10
     * @param comment the comment that a user makes about the movie
     */
    public void addUserReview(String name, String number, String comment) {
        User_Reviews review = new User_Reviews();
        review.setMovieName(name);
        review.setNumericalRating(number);
        review.setUserInputReview(comment);
        listOfUserReviews.add(review);
    }

    /**
     * Adds a watchlist to the current user's list of watchlists
     * @param name the name of a watchlist
     * @return boolean if that watchlist already exists
     */
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

    /**
     * Deletes a watchlist from the current user's list of watchlists
     * @param name the name of the watchlist to delete
     * @return boolean if a watchlist was deleted
     */
    public boolean deleteWatchlist(String name){
        for(Watchlist_Model watchlist:listOfWatchlists){
            if(watchlist.getName().equals(name)){
                listOfWatchlists.remove(watchlist);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new user to the list of users in the JSON
     * @return boolean if a user with the same name has already been created
     * @throws IOException
     */
    public boolean addNewUser() throws IOException {

        /* Make sure there is not a duplicate */
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
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
            Files.writeString(path, content, charset);
            String content2 = Files.readString(path, charset);
            int index = content2.lastIndexOf("}");
            String content3 = content2.substring(0,index)+ "},"+ content2.substring(index+1);
            Files.writeString(path, content3, charset);

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

    /**
     * Checks to see if a username and password match in the userdata.json
     * @return boolean if the username and password match what is in the userdata.json
     * @throws IOException
     */
    public boolean checkUser() throws IOException {
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
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

    /**
     *
     * @param username the username of the current user
     * @return ratings the list of ratings that a user as made
     * @throws IOException
     */
    public String grabUserDataReview(String username) throws IOException {
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

    /**
     * Grabs the user's watchlist data from the userdata.json
     * @param username the username of the current user
     * @return watchlists the array of watchlists
     * @throws IOException
     */
    public ArrayList<Watchlist_Model> grabUserWatchlists(String username) throws IOException {
        ArrayList<Watchlist_Model> watchlists = new ArrayList<>();
        /* get user data into arrayList */
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content, User_Model[].class);
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        for(User_Model user: arrayList){
            if(user.getUsername().equals(username)){
                String watchlistName;

                for(Watchlist_Model wl: user.getListOfWatchlists()){
                    watchlistName = wl.getName();
                    if(!watchlistName.equals("")){
                        watchlists.add(wl);
                    }
                }
                return watchlists;
            }
        }
        return watchlists;
    }
}
