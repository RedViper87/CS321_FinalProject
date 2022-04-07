import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  This is the Controller class that handles the
 *  functions and interactions between the views
 *  and the models.
 */
public class Controller {

    private Movie_View movieView;
    private Movie_Model movieModel;
    private Watchlist_View watchlistView;
    private Watchlist_Model watchlistModel;
    private Review_View reviewView;
    private Review_Model reviewModel;
    private Search_View searchView;
    private Search_Model searchModel;
    private Recommendations_Model recommendationsModel;
    private Recommendations_View recommendationsView;
    private User_View userView;
    private User_Model userModel;

    // other views and models will go in this controller
    public Controller(User_View userView, User_Model userModel,
                      Review_View reviewView, Review_Model reviewModel,
                      Watchlist_View watchlistView, Watchlist_Model watchlistModel){

        this.userView = userView;
        this.userModel = userModel;
        this.userView.checkUserListener(new checkListener());
        this.userView.newUserListener(new addUserListener());

        this.reviewView = reviewView;
        this.reviewModel = reviewModel;
        this.reviewView.addReviewListener(new addReviewListener());

        this.watchlistView = watchlistView;
        this.watchlistModel = watchlistModel;
        this.watchlistView.addWatchlistListener(new addWatchlistListener());

    }


    /* Movie Functions */
    //Get movie data from the Movie_Model
    //Send movie data to the Movie_View

    /* Watchlist Functions */
    //Get watchlist name from Watchlist_Model
    //Add movie to watchlist, updates both Watchlist_Model & Watchlist_View
    //Remove movie from watchlist, updates Watchlist_Model & Watchlist_View
    //Get titles in watchlist from Watchlist_Model, send to Watchlist_View

    class addWatchlistListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String watchlistName;

            watchlistName = watchlistView.getWatchlistName();
            watchlistView.eraseWatchlistName();
            if(watchlistName.length() == 0){
                watchlistView.displayError("Please Enter a Watchlist Name");
            }
            else{
                //Add watchlist to list of watchlists for user
                userModel.addWatchlist(watchlistName);
            }
        }

    }

    /* User Profile Functions */
    //Add new user profile, update both User_Model & User_View
    //Delete user profile, update both User_Model & User_View
    //Modify username, update both User_Model & User_View
    //Modify password, update User_Model


    class checkListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String UserReviews = "";
            String username;
            String password;
            boolean check = false;
            username = userView.getUsername();
            password = userView.getPassword();
            userView.eraseUsername();
            userView.erasePassword();
            if(username.length() == 0 || password.length() == 0){
                userView.displayError("Please Enter a Username AND Password");
            }else{
                try {
                    userModel.setUsername(username);
                    userModel.setPassword(password);
                    check = userModel.checkUser();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(check){
                    // set other views equal to true
                    try {
                        UserReviews = userModel.grabUserDataReview(username);
                        reviewView.updateReview(UserReviews);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    userView.displayError("Username and Password do not match or does not exist.");

                }

            }

        }
    }
    class addUserListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username;
            String password;
            boolean check = false;
            username = userView.getUsername();
            password = userView.getPassword();
            userView.eraseUsername();
            userView.erasePassword();
            if(username.length() == 0 || password.length() == 0){
                userView.displayError("Please Enter a Username AND Password");
            }else{
                userModel.setUsername(username);
                userModel.setPassword(password);
                try {
                    check = userModel.addNewUser();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(check){
                    userView.displaySuccess("Account successfully created! Please login.");
                }else{
                    userView.displayError("User already exists.");
                }
            }
        }
    }

    /* Review Functions */
    //Add new review to a movie, update Review_Model & Review_View
    //Edit your review, update Review_Model & Review_View
    //Delete your review, update Review_Model & Review_View

    class addReviewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String userdata = "";
            String movieTitle = reviewView.getMovie();
            String rating = String.valueOf(reviewView.getRating());
            String comment = reviewView.getComment();
            reviewModel.setMovieName(movieTitle);
            reviewModel.setUserInputReview(comment);
            reviewModel.setNumericalRating(rating);
            try {
                reviewModel.addUserReview(userModel.getUsername());
                reviewView.eraseComment();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                userdata = reviewModel.grabUserData(userModel.getUsername());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            reviewView.updateReview(userdata);
        }
    }

    /* Search Functions */
    //Filter by Title, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Year, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Rated, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Genre, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Director, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Writer, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Actor, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Language, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Country, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Awards, ask Search_Model to search database, return list of matching movies to Search_View
    //Filter by Ratings, ask Search_Model to search database, return list of matching movies to Search_View
}
