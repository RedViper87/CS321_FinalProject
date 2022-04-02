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
        Movie_View movieView = new Movie_View();
        Controller userController = new Controller(userView, userModel);

        JFrame movieLibrary = new JFrame();
        movieLibrary.setTitle("CPE 321 Final Project - My Movie Library");
        movieLibrary.setSize(960,540);  //960x540, same ratio as 1920x1080, but smaller
        //movieLibrary.setResizable(false);   //prevents frame from being resized
        movieLibrary.getContentPane().setBackground(new Color(70, 70, 70));    //change color of background
        movieLibrary.setLayout(new FlowLayout());
        movieLibrary.add(movieView);
        movieLibrary.add(userView);
        movieLibrary.add(watchlistView);
        movieLibrary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        movieLibrary.setVisible(true);
    }
}
