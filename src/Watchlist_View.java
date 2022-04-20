import com.google.gson.Gson;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

/**
 * Class used to represent the viewable panel for Watchlists
 */
public class Watchlist_View extends JPanel{

    JLabel watchlistTitleLabel = new JLabel("Add, Delete, and Modify Watchlists!");
    JLabel watchlistNameLabel = new JLabel("Watchlist Name to Add or Delete:");
    JTextField watchlistNameField = new JTextField(10);
    JButton addWatchlistButton = new JButton("Add Watchlist");
    JButton deleteWatchlistButton = new JButton("Delete Watchlist");
    JButton saveWatchlistButton = new JButton("Save Watchlist");
    JLabel currentWatchlistName = new JLabel();
    JTextField movie = new JTextField();
    JTextArea movieInList;
    JButton watchlistButton;
    JPanel watchlistPanel = new JPanel();
    JPanel cwlMoviesPanel = new JPanel();
    JPanel moviesPanel = new JPanel();  //holds all the movies as buttons

    /**
     * Constructs the Watchlist view panel
     */
    Watchlist_View(){
        /* Create borderlines */
        Border blackline1 = BorderFactory.createLineBorder(Color.black, 1);
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);

        /* Title panel goes at the top of outer panel */
        JPanel titlePanel = new JPanel();
        watchlistTitleLabel.setFont(new Font(null, Font.BOLD, 20));
        titlePanel.add(watchlistTitleLabel);

        /* This panel is on the left side of the main panel */
        //Create a border to go around movies panel
        moviesPanel.setBorder(blackline1);
        //Set movies panel layout
        BoxLayout moviesPanelLayout = new BoxLayout(moviesPanel, BoxLayout.Y_AXIS);
        moviesPanel.setLayout(moviesPanelLayout);

        // watchlist of movies field
        movieInList = new JTextArea();
        movieInList.setDragEnabled(true);
        cwlMoviesPanel.add(movieInList);
        movieInList.setLineWrap(true);
        movieInList.setWrapStyleWord(true);

        //add scroll bar
        JScrollPane movieScroller = new JScrollPane(moviesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        movieScroller.setPreferredSize(new Dimension(400, 400));
        movieScroller.getVerticalScrollBar().setUnitIncrement(32);

        /* This panel is in the middle of the main panel */
        //MAIN
        JPanel watchlistDisplayPanel = new JPanel();
        BoxLayout wdpLayout = new BoxLayout(watchlistDisplayPanel, BoxLayout.Y_AXIS);
        watchlistDisplayPanel.setLayout(wdpLayout);
        //NAME PANEL
        JPanel wdpName = new JPanel();
        wdpName.add(watchlistNameLabel);
        wdpName.add(watchlistNameField);
        //ADD & DELETE BUTTONS PANEL
        JPanel wdpAddDeleteButtons = new JPanel();
        wdpAddDeleteButtons.add(addWatchlistButton);
        wdpAddDeleteButtons.add(deleteWatchlistButton);
        //CURRENT WATCHLIST PANEL
        JPanel wdpCurrentWatchlist = new JPanel();
        BoxLayout wdplLayout = new BoxLayout(wdpCurrentWatchlist, BoxLayout.Y_AXIS);
        wdpCurrentWatchlist.setLayout(wdplLayout);

        //set layout here
        JLabel wdpCurrentName = new JLabel("Current Watchlist:");
        wdpCurrentWatchlist.add(wdpCurrentName);
        wdpCurrentWatchlist.add(currentWatchlistName);
        wdpCurrentWatchlist.add(saveWatchlistButton);

        GridLayout watchlistGridLayout = new GridLayout(0, 1);
        cwlMoviesPanel.setLayout(watchlistGridLayout);

        //add scroll bar to cwlMoviesPanel
        JScrollPane cwlMovieScroller = new JScrollPane(cwlMoviesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cwlMovieScroller.setPreferredSize(new Dimension(210, 210));
        cwlMovieScroller.getVerticalScrollBar().setUnitIncrement(32);
        //Add separate panels to Main
        watchlistDisplayPanel.setBorder(blackline1);    //Create a border to go around panel
        watchlistDisplayPanel.add(wdpName);
        watchlistDisplayPanel.add(wdpAddDeleteButtons);
        watchlistDisplayPanel.add(wdpCurrentWatchlist);
        watchlistDisplayPanel.add(cwlMovieScroller);

        /* This panel is on the right side of the main panel */
        //Create a border to go around watchlist panel
        watchlistPanel.setBorder(blackline1);
        //Set watchlist panel layout
        BoxLayout watchlistPanelLayout = new BoxLayout(watchlistPanel, BoxLayout.Y_AXIS);
        watchlistPanel.setLayout(watchlistPanelLayout);
        //add scroll bar
        JScrollPane watchlistScroller = new JScrollPane(watchlistPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        watchlistScroller.setPreferredSize(new Dimension(400, 400));
        watchlistScroller.getVerticalScrollBar().setUnitIncrement(32);

        /* This main panel holds the movies panel, watchlist display panel, and watchlists panel */
        JPanel mainPanel = new JPanel();
        BoxLayout mainPanelLayout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.add(movieScroller);
        mainPanel.add(watchlistDisplayPanel);
        mainPanel.add(watchlistScroller);

        /* Deal with outer panel */
        JPanel outer = new JPanel();
        outer.setPreferredSize(new Dimension(1250, 450));
        BoxLayout outerLayout = new BoxLayout(outer, BoxLayout.Y_AXIS);
        outer.setLayout(outerLayout);
        //Create a border to go around outer panel
        outer.setBorder(blackline2);

        outer.add(titlePanel);
        outer.add(mainPanel);
        this.setBackground(Color.darkGray);
        this.setVisible(false);
        this.add(outer);
    }

    /**
     * Erases the entered text in watchlist name field
     */
    public void eraseWatchlistName() {
        watchlistNameField.setText("");
    }

    /**
     * Returns the name from the watchlist name field
     * @return watchlistNameField
     */
    public String getWatchlistName() {
        return watchlistNameField.getText();
    }

    /**
     * Alerts Controller that the Add Watchlist button has been pressed
     * @param listenerForAddWatchlist the Add Watchlist listener
     */
    void addWatchlistListener(ActionListener listenerForAddWatchlist) {
        addWatchlistButton.addActionListener(listenerForAddWatchlist);
    }

    /**
     * Alerts the controller that the Delete Watchlist button has been pressed
     * @param listenerForDeleteWatchlist the Delete Watchlist listener
     */
    void deleteWatchlistListener(ActionListener listenerForDeleteWatchlist) {
        deleteWatchlistButton.addActionListener(listenerForDeleteWatchlist);
    }

    /**
     * Alerts the controller that the Save Watchlist button has been pressed
     * @param listenerForSaveWatchlist Save Watchlist listener
     */
    void saveWatchlistListener(ActionListener listenerForSaveWatchlist) {
        saveWatchlistButton.addActionListener(listenerForSaveWatchlist);
    }

    /**
     * Returns a list of movies in a watchlist
     * @return movieInList
     */
    public String getWatchlistMovies(){
        return movieInList.getText();
    }

    /**
     * Updates the watchlist panel
     * @param watchlists array of watchlist models to update
     * @param Movies array of movie models to update to watchlist
     * @param username String passed in of username
     */
    public void updateWatchlists(ArrayList<Watchlist_Model> watchlists, ArrayList<Movie_Model> Movies, String username){
        watchlistPanel.removeAll();
        for(Watchlist_Model wl:watchlists){
            String Name = wl.getName();

            watchlistButton = new JButton(Name);
            //add button to watchlistPanel
            watchlistPanel.add(watchlistButton);
            watchlistButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            watchlistButton.addActionListener(event ->
            {
                currentWatchlistName.setText(Name);

                populateLeftMoviePanel(Movies);
                try {
                    populateMiddlePanel(username, Name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * Populates the left movie panel with full list of movie names
     * @param movies array of movie models passed in
     */
    public void populateLeftMoviePanel(ArrayList<Movie_Model> movies){
        moviesPanel.removeAll();
        for(Movie_Model theMovie:movies) {
            String Title = theMovie.getTitle();
            movie = new JTextField(Title+",");
            movie.setDragEnabled(true);
            moviesPanel.add(movie);
            movie.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

    /**
     * Populates the middle movie panel with watchlist info
     * @param username String passed in of username
     * @param watchlistName String passed in of watchlist name
     * @throws IOException used for try catch
     */
    public void populateMiddlePanel(String username, String watchlistName) throws IOException {
        movieInList.setText("");
        Path path = Paths.get("UserData.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        User_Model[] list;
        list = gson.fromJson(content, User_Model[].class);
        ArrayList<User_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

        ArrayList<Movie_Model> movies = new ArrayList<>();
        for(User_Model user:arrayList){
            if(user.getUsername().equals(username)){
                ArrayList<Watchlist_Model> watchlistList;
                watchlistList = user.getListOfWatchlists();
                for(Watchlist_Model watchlist: watchlistList){
                    if(watchlist.getName().equals(watchlistName)){
                        movies = watchlist.getListOfMovies();
                    }
                }
            }
        }
        if(!movies.isEmpty()){

            for(Movie_Model movie:movies){
                if(!movie.getTitle().equals("")){
                    movieInList.append(movie.getTitle()+",");
                }
            }
        }
    }

    /**
     * Returns the current watchlist's name
     * @return currentWatchlistName
     */
    public String getCurrentWatchlistName(){
        return currentWatchlistName.getText();
    }

    /**
     * Sets the current watchlist's name
     */
    public void setCurrentWatchlistName(){
        currentWatchlistName.setText("");
    }

    /**
     * Sets the text in the middle panel to blank
     */
    public void setMiddlePanel(){
        movieInList.setText("");
    }

    /**
     * Used to display success message panel
     * @param success String for message to display
     */
    void displaySuccess(String success) {
        JOptionPane.showMessageDialog(this, success);
    }

    /**
     * Used to display error message panel
     * @param error String for message to display
     */
    void displayError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
}
