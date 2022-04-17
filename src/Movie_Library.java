import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *  This is the main driver class for CS321 Final Project ~ My Movie Library
 */
public class Movie_Library extends JPanel{

    private static final JButton Logout = new JButton("Logout");

    /**
     * Constructs a Movie_Library object
     * The logout button visability is set to false
     */
    Movie_Library(){
        Logout.setVisible(false);
    }

    /**
     * Adds an actionlistener to the logout button to be used in the Controller
     * @param listenerForLogout the actionlistener for the logout button
     */
    void logoutListener(ActionListener listenerForLogout){
        Logout.addActionListener(listenerForLogout);
    }

    /**
     * Sets the visibility of the Logout button to false
     */
    void setLogoutFalse(){
        Logout.setVisible(false);
    }

    /**
     * Sets the visability of the logout button to true
     */
    void setLogoutTrue(){
        Logout.setVisible(true);
    }

    /**
     * Main Driver for the project that contructs all of the objects that will be used in Controller
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Movie_Library movieLibrary = new Movie_Library();
        User_View  userView = new User_View();
        User_Model userModel = new User_Model();
        Watchlist_View watchlistView = new Watchlist_View();
        Watchlist_Model watchlistModel = new Watchlist_Model();
        Recommendations_Model recommendationsModel = new Recommendations_Model();
        Recommendations_View recommendationsView = new Recommendations_View();
        Review_View reviewView = new Review_View();
        Review_Model reviewModel = new Review_Model();
        Search_View searchView = new Search_View();
        Search_Model searchModel = new Search_Model();
        Movie_View movieView = new Movie_View();
        Controller userController = new Controller(userView, userModel, reviewView, reviewModel, watchlistView, watchlistModel, recommendationsView, recommendationsModel,searchView, searchModel,movieView, movieLibrary);

        JFrame library = new JFrame();
        JPanel logout = new JPanel();
        JPanel all = new JPanel();
        library.setTitle("CPE 321 Final Project - My Movie Library");
        library.setLayout(new BorderLayout());

        logout.add(Logout);

        all.add(logout);
        all.add(userView);
        all.add(searchView);
        all.add(movieView);
        all.add(watchlistView);
        all.add(reviewView);
        all.add(recommendationsView);

        BoxLayout layout = new BoxLayout(all, BoxLayout.Y_AXIS);
        all.setLayout(layout);
        JScrollPane scroll = new JScrollPane(all, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(960,540));
        scroll.getVerticalScrollBar().setUnitIncrement(32);
        library.add(scroll);
        library.pack();

        library.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        library.setVisible(true);
        library.setExtendedState(library.getExtendedState() | JFrame.MAXIMIZED_BOTH); //auto maximize screen
    }
}