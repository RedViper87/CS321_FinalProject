import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Movie_Library {
    /**
     *  This is the main driver for CS321 Final Project ~ My Movie Library
     */
    public static void main(String[] args) throws IOException {
        //main code goes here
        ArrayList <User_Model> users = new ArrayList<User_Model>();
        ArrayList <Movie_Model> movies = new ArrayList<Movie_Model>();

        User_View  userView = new User_View();
        User_Model userModel = new User_Model();
        Watchlist_View watchlistView = new Watchlist_View();
        Recommendations_View recommendationsView = new Recommendations_View();
        Review_View reviewView = new Review_View();
        Search_View searchView = new Search_View();
        Movie_View movieView = new Movie_View();
        Controller userController = new Controller(userView, userModel);

        JFrame movieLibrary = new JFrame();
        movieLibrary.setLayout(new BoxLayout(movieLibrary, BoxLayout.Y_AXIS));
        JPanel topPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel botPanel = new JPanel();

        movieLibrary.setTitle("CPE 321 Final Project - My Movie Library");
        movieLibrary.setSize(1152,648); //keep aspect ratio same as 1920x1080
        //movieLibrary.setResizable(false);   //prevents frame from being resized
        movieLibrary.getContentPane().setBackground(new Color(70, 70, 70));    //change color of background
        movieLibrary.setLayout(new FlowLayout());

        topPanel.add(userView);
        movieLibrary.add(topPanel);

        midPanel.add(movieView);
        movieLibrary.add(midPanel);

        botPanel.add(searchView);
        botPanel.add(watchlistView);
        botPanel.add(recommendationsView);
        botPanel.add(reviewView);
        movieLibrary.add(botPanel);

        movieLibrary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        movieLibrary.setVisible(true);

    }
}
