import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class Movie_Library extends JPanel{
    /**
     *  This is the main driver for CS321 Final Project ~ My Movie Library
     */
    private static JButton Logout = new JButton("Logout");
    Movie_Library(){
        Logout.setVisible(false);
    }
    /* alert controller that this button is pressed */
    void logoutListener(ActionListener listenerForLogout){
        Logout.addActionListener(listenerForLogout);
    }
    void setLogoutFalse(){
        Logout.setVisible(false);
    }
    void setLogoutTrue(){
        Logout.setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        Movie_Library movieLibrary = new Movie_Library();
        User_View  userView = new User_View();
        User_Model userModel = new User_Model();
        Watchlist_View watchlistView = new Watchlist_View();
        Recommendations_View recommendationsView = new Recommendations_View();
        Review_View reviewView = new Review_View();
        Review_Model reviewModel = new Review_Model();
        Search_View searchView = new Search_View();
        Movie_View movieView = new Movie_View();
       Controller userController = new Controller(userView, userModel, reviewView, reviewModel, watchlistView, recommendationsView, searchView, movieView, movieLibrary);

        JFrame library = new JFrame();
        JPanel logout = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel botPanel = new JPanel();
        JPanel all = new JPanel();
        library.setTitle("CPE 321 Final Project - My Movie Library");
        library.setLayout(new BorderLayout());
        library.getContentPane().setBackground(new Color(70, 70, 70));    //change color of background

        logout.add(Logout);
        topPanel.add(userView);
        topPanel.add(searchView);


        midPanel.add(movieView);
        midPanel.add(watchlistView);

        botPanel.add(recommendationsView);
        botPanel.add(reviewView);
        all.add(logout);
        all.add(topPanel);
        all.add(midPanel);
        all.add(botPanel);
        BoxLayout layout = new BoxLayout(all, BoxLayout.Y_AXIS);
        all.setLayout(layout);
        JScrollPane scroll = new JScrollPane(all, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(1920,1080));
        library.add(scroll);
        library.pack();

        library.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        library.setVisible(true);
        library.setExtendedState(library.getExtendedState() | JFrame.MAXIMIZED_BOTH); //auto maximize screen
    }


}
