import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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

/**
 *  This is the Controller class that handles the
 *  functions and interactions between the views
 *  and the models.
 */
public class Controller {
    /* Movie Functions */
    //Get movie data from the Movie_Model
    //Send movie data to the Movie_View

    /* Watchlist Functions */
    //Get watchlist name from Watchlist_Model
    //Add movie to watchlist, updates both Watchlist_Model & Watchlist_View
    //Remove movie from watchlist, updates Watchlist_Model & Watchlist_View
    //Get titles in watchlist from Watchlist_Model, send to Watchlist_View

    /* User Profile Functions */
    //Add new user profile, update both User_Model & User_View
    private Movie_Library movieLibrary;
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
    public Controller(User_View userView, User_Model userModel, Review_View reviewView, Review_Model reviewModel, Watchlist_View watchlistView, Watchlist_Model watchlistModel, Recommendations_View recommendationsView,Recommendations_Model recommendationsModel ,Search_View searchView, Search_Model searchModel,Movie_View movieView, Movie_Library movieLibrary){
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
        this.watchlistView.deleteWatchlistListener(new deleteWatchlistListener());

        this.recommendationsModel = recommendationsModel;
        this.recommendationsView = recommendationsView;
        this.recommendationsView.refreshListener(new refreshListener());
        this.searchView = searchView;
        this.searchModel = searchModel;
        this.movieView = movieView;
        this.movieLibrary = movieLibrary;
        this.movieLibrary.logoutListener(new logoutListener());
        this.searchView.searchListener(new searchListener());

    }

    class checkListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String UserReviews = "";
            ArrayList<Watchlist_Model> UserWatchlists;
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
                    reviewView.setVisible(true);
                    watchlistView.setVisible(true);
                    searchView.setVisible(true);
                    recommendationsView.setVisible(true);
                    movieView.setVisible(true);
                    movieLibrary.setLogoutTrue();
                    userView.setVisible(false);
                    try {
                        reviewView.erase(); // erase text
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
                reviewModel.addUserReview(userModel.getUsername()); // add review in userdata.json
                reviewView.eraseComment();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                userdata = reviewModel.grabUserData(userModel.getUsername());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            reviewView.updateReview(userdata); // update review in view
        }
    }
    class logoutListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            reviewView.setVisible(false);
            watchlistView.setVisible(false);
            searchView.setVisible(false);
            recommendationsView.setVisible(false);
            movieView.setVisible(false);
            movieLibrary.setLogoutFalse();
            userView.setVisible(true);
        }
    }

    class searchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean cb1 = searchView.getcb1();
            boolean cb2 = searchView.getcb2();
            boolean cb3 = searchView.getcb3();
            boolean cb4 = searchView.getcb4();
            ArrayList<Movie_Model> movies = new ArrayList<>();
            String search = searchView.getSearch();
            try {
                movies = searchModel.SearchDatabase(cb1, cb2, cb3, cb4, search);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(movies.size() > 0){
                try {
                    searchView.displaySearchMovies(movies);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }else{
                searchView.displayError("No results from search.");
            }
            searchView.revalidate();
            searchView.repaint();
        }
    }

    class addWatchlistListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean add = true;
            ArrayList <Watchlist_Model> watchlists = new ArrayList<>();
            ArrayList <Movie_Model> movies = new ArrayList<>();
            String watchlistName = watchlistView.getWatchlistName();
            watchlistView.eraseWatchlistName();
            if(watchlistName.length() == 0) {
                watchlistView.displayError("Please enter a watchlist name.");
            }
            else{
                //create a watchlist with that name, and add to user model
                watchlistModel.setName(watchlistName);
                try {
                    add = watchlistModel.addUserWatchlist(userModel.getUsername(), watchlistName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(add){
                    try {
                        watchlists = watchlistModel.returnWatchlists(userModel.getUsername());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        movies = watchlistModel.returnMovies(userModel.getUsername(), watchlistName);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //add button to the list of watchlists
                    watchlistView.updateWatchlists(watchlists, movies);
                }else{
                    watchlistView.displayError(watchlistName+": already exists.");

                }

            }
            watchlistView.revalidate();
            watchlistView.repaint();
        }
    }
    class deleteWatchlistListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList <Watchlist_Model> watchlists = new ArrayList<>();
            ArrayList <Movie_Model> movies = new ArrayList<>();
            boolean delete = true;
            String watchlistName = watchlistView.getWatchlistName();
            watchlistView.eraseWatchlistName();
            if(watchlistName.length() == 0) {
                watchlistView.displayError("Please enter a watchlist name.");
            }else{
                try {
                    delete = watchlistModel.deleteUserWatchlist(userModel.getUsername(),watchlistName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(delete){
                    watchlistView.displaySuccess(watchlistName+": has been deleted.");
                    try {
                        watchlists = watchlistModel.returnWatchlists(userModel.getUsername());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        movies = watchlistModel.returnMovies(userModel.getUsername(), watchlistName);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    // delete button from panel
                    watchlistView.updateWatchlists(watchlists, movies);
                }else{
                    watchlistView.displayError(watchlistName+": does not exist.");
                }
            }
            watchlistView.revalidate();
            watchlistView.repaint();
        }
    }

    class refreshListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Movie_Model> movies = new ArrayList<>();
            try {
                movies = recommendationsModel.getReccomendations(userModel.getUsername());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(movies.size() == 0){
                recommendationsView.displayError("Please enter in a review to get recommended movies.");
            }
            try {
                recommendationsView.displayReccomendedMovies(movies);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            recommendationsView.revalidate();
            recommendationsView.repaint();
        }
    }

    /* Review Functions */
    //Add new review to a movie, update Review_Model & Review_View
    //Edit your review, update Review_Model & Review_View
    //Delete your review, update Review_Model & Review_View

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