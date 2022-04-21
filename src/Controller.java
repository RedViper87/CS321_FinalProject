import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  This is the Controller class that handles the
 *  functions and interactions between the views
 *  and the models.
 */
public class Controller {

    private final Movie_Library movieLibrary;
    private final Movie_View movieView;
    private final Watchlist_View watchlistView;
    private final Watchlist_Model watchlistModel;
    private final Review_View reviewView;
    private final Review_Model reviewModel;
    private final Search_View searchView;
    private final Search_Model searchModel;
    private final Recommendations_Model recommendationsModel;
    private final Recommendations_View recommendationsView;
    private final User_View userView;
    private final User_Model userModel;

    /**
     * Constructs a Controller object used to interface between various Views and Models.
     * @param userView the view for the user login
     * @param userModel the model for the user login
     * @param reviewView the view for the review section
     * @param reviewModel the model for a review
     * @param watchlistView the view for the watchlist section
     * @param watchlistModel the model for a watchlist
     * @param recommendationsView the view for the recommendations section
     * @param recommendationsModel the model for a recommendation
     * @param searchView the view for the search section
     * @param searchModel the model for a search
     * @param movieView the view for the movie browsing section
     * @param movieLibrary the main movie library frame
     */
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
        this.watchlistView.saveWatchlistListener(new saveWatchlistListener());

        this.recommendationsModel = recommendationsModel;
        this.recommendationsView = recommendationsView;
        this.recommendationsView.refreshListener(new refreshListener());

        this.searchView = searchView;
        this.searchModel = searchModel;
        this.searchView.searchListener(new searchListener());

        this.movieView = movieView;
        this.movieLibrary = movieLibrary;
        this.movieLibrary.logoutListener(new logoutListener());
    }

    /* User Profile Functions */
    /**
     * checkListener checks if a username and password matches entries in the database or if it already exists or not
     */
    class checkListener implements ActionListener{
        /**
         * Invoked when Check for User action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String UserReviews;
            ArrayList<Watchlist_Model> UserWatchlists;
            ArrayList <Movie_Model> movies;
            String watchlistName = watchlistView.getWatchlistName();
            String username;
            String password;
            boolean check = false;
            username = userView.getUsername();
            password = userView.getPassword();
            userView.erasePassword();
            if(username.length() == 0 || password.length() == 0){
                userView.displayError("Please Enter a Username AND Password");
            }else{
                userView.eraseUsername();
                try {
                    userModel.setUsername(username);
                    userModel.setPassword(password);
                    check = userModel.checkUser();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(check){
                    // set other views equal to true
                    userView.displayMessage(String.format("Welcome back, %s! \nPress OK to start browsing your movies.", username));
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

                        UserWatchlists = userModel.grabUserWatchlists(username);
                        movies = watchlistModel.returnMovies(userModel.getUsername(), watchlistName);
                        watchlistView.updateWatchlists(UserWatchlists, movies, userModel.getUsername());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    userView.displayError("Username and Password do not match or does not exist.");
                }
            }
        }
    }

    /**
     * addUserListener checks is a user exists when Add User button is pressed
     */
    class addUserListener implements ActionListener{
        /**
         * Invoked when Add User action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String username;
            String password;
            boolean check = false;
            username = userView.getUsername();
            password = userView.getPassword();
            userView.erasePassword();
            if(username.length() == 0 || password.length() == 0){
                userView.displayError("You Must Enter a Username AND Password when creating account.");
            }else{
                userView.eraseUsername();
                userModel.setUsername(username);
                userModel.setPassword(password);
                try {
                    check = userModel.addNewUser();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(check){
                    userView.displayMessage(String.format("Welcome %s! Enjoy the movies!", username));
                }else{
                    userView.displayError(String.format("User '%s' already exists.", username));
                }
            }
        }
    }

    /* Review Functions */
    /**
     * addReviewListener updates the reviewModel and reviewView for Add Review action
     */
    class addReviewListener implements ActionListener{
        /**
         * Invoked when Add Review action occurs.
         *
         * @param e the event to be processed
         */
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

    /* Search Functions */
    /**
     * searchListener updates searchView and searchModel for Search action
     */
    class searchListener implements ActionListener{
        /**
         * Invoked when Search action occurs.
         *
         * @param e the event to be processed
         */
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
                searchView.displayError();
            }
            searchView.revalidate();
            searchView.repaint();
        }
    }

    /* Watchlist Functions */
    /**
     * addWatchlistListener updates the watchlistView and watchlistModel for an Add Watchlist action
     */
    class addWatchlistListener implements ActionListener{
        /**
         * Invoked when Add Watchlist action occurs.
         *
         * @param e the event to be processed
         */
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
                    watchlistView.updateWatchlists(watchlists, movies, userModel.getUsername());
                }else{
                    watchlistView.displayError(watchlistName+": already exists.");

                }

            }
            watchlistView.revalidate();
            watchlistView.repaint();
        }
    }
    /**
     * deleteWatchlistListener updates the watchlistView and watchlistModel for a Delete Watchlist action
     */
    class deleteWatchlistListener implements ActionListener{
        /**
         * Invoked when Delete Watchlist action occurs.
         *
         * @param e the event to be processed
         */
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
                    watchlistView.updateWatchlists(watchlists, movies, userModel.getUsername());
                    watchlistView.setMiddlePanel();
                    watchlistView.setCurrentWatchlistName();
                }else{
                    watchlistView.displayError(watchlistName+": does not exist.");
                }
            }
            watchlistView.revalidate();
            watchlistView.repaint();
        }
    }
    /**
     * saveWatchlistListener updates the watchlistView and watchlistModel for Save Watchlist action
     */
    class saveWatchlistListener implements ActionListener{
        /**
         * Invoked when Save Watchlist action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String movieText = watchlistView.getWatchlistMovies();
            String[] movies = movieText.split(",");
            try {
                watchlistModel.AddMovieToList(movies, userModel.getUsername(), watchlistView.getCurrentWatchlistName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /* Recommendations Functions */
    /**
     * refreshListener updates recommendationsView and recommendationsModel for Get Recommendations action
     */
    class refreshListener implements ActionListener{
        /**
         * Invoked when Get Recommendations action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Movie_Model> movies = new ArrayList<>();
            try {
                movies = recommendationsModel.getRecommendations(userModel.getUsername());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(movies.size() == 0){
                recommendationsView.displayError();
            }
            try {
                recommendationsView.displayRecommendedMovies(movies);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            recommendationsView.revalidate();
            recommendationsView.repaint();
        }
    }

    /* Logout Functions */
    /**
     * logoutListener waits for Logout action and sets view panel visibilities to false, and user login panel to true
     */
    class logoutListener implements ActionListener{
        /**
         * Invoked when Logout action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            userView.displayMessage(String.format("Goodbye, %s!", userModel.getUsername()));
            reviewView.setVisible(false);
            watchlistView.setVisible(false);
            searchView.setVisible(false);
            recommendationsView.setVisible(false);
            movieView.setVisible(false);
            movieLibrary.setLogoutFalse();
            userView.setVisible(true);
        }
    }
}