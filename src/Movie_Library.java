import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
public class Movie_Library {
    /**
     *  This is the main driver for CS321 Final Project ~ My Movie Library
     */
    public static void main(String[] args) {
        //main code goes here
        ArrayList <User_Model> users = new ArrayList<User_Model>();
        ArrayList <Movie_Model> movies = new ArrayList<Movie_Model>();

        User_View  userView = new User_View();
        User_Model userModel = new User_Model();
        Watchlist_View watchlistView = new Watchlist_View();
        Controller userController = new Controller(userView, userModel);
        JFrame movieLibrary = new JFrame();
        movieLibrary.setSize(500,500);
        movieLibrary.setLayout(new FlowLayout());
        movieLibrary.add(userView);
        movieLibrary.add(watchlistView);
        movieLibrary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        movieLibrary.setVisible(true);

    }
}
