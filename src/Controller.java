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
            username = userView.getUsername();
            password = userView.getPassword();
            if(username.length() == 0 || password.length() == 0){
                userView.displayErrorMessage("Please Enter a Username AND Password");
            }else{
                userModel.setUsername(username);
                userModel.setPassword(password);
                // load the user data
            }

        }
    }
    class addUserListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username;
            String password;
            username = userView.getUsername();
            password = userView.getPassword();
            if(username.length() == 0 || password.length() == 0){
                userView.displayErrorMessage("Please Enter a Username AND Password");
            }else{
                userModel.setUsername(username);
                userModel.setPassword(password);
                try {
                    userModel.addNewUser();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }



    /* Rating Functions */
    //Add new rating to a movie, update Rating_Model & Rating_View
    //Edit your rating, update Rating_Model & Rating_View
    //Delete your rating, update Rating_Model & Rating_View

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
