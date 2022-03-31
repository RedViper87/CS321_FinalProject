import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    //Delete user profile, update both User_Model & User_View
    //Modify username, update both User_Model & User_View
    //Modify password, update User_Model

    private User_View userView;
    private User_Model userModel;

    // other views and models will go in this controller
    public Controller(User_View userView, User_Model userModel){
        this.userView = userView;
        this.userModel = userModel;
        this.userView.checkUserListener(new checkListener());
        this.userView.newUserListener(new addUserListener());
    }

    class checkListener implements ActionListener{

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
                try {
                    userModel.setUsername(username);
                    userModel.setPassword(password);
                    check = userModel.checkUser();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(check){
                    // take to home page
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
