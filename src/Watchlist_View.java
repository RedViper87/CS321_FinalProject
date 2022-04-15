import com.google.gson.Gson;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class Watchlist_View extends JPanel {

    JLabel watchlistTitleLabel = new JLabel("Watchlist View");
    private JLabel watchlistNameLabel = new JLabel("Watchlist Name to Add or Delete:");
    private JTextField watchlistNameField = new JTextField(10);
    private JButton addWatchlistButton = new JButton("Add Watchlist");
    private JButton deleteWatchlistButton = new JButton("Delete Watchlist");
    private JButton saveWatchlistButton = new JButton("Save Watchlist");
    private JLabel currentWatchlistName = new JLabel("Current Watchlist Name");
    private JTextField movie = new JTextField();
    private JTextField movieInList;
    private JButton watchlistButton;
    JPanel watchlistPanel = new JPanel();
    JPanel cwlMoviesPanel = new JPanel();
    JPanel moviesPanel = new JPanel();  //holds all the movies as buttons

    Watchlist_View() throws IOException {
        /* Get movie data from json and put in arrayList */
        Path path = Paths.get("SampleMovieFile.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        Gson gson = new Gson();
        Movie_Model[] list;
        list = gson.fromJson(content, Movie_Model[].class);
        ArrayList<Movie_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list);

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
        // add text field
        for(Movie_Model movies:arrayList) {
            String Title = movies.getTitle();

            // movie = new JTextField(Title);
            // movie.setDragEnabled(true);
            movieInList = new JTextField();
            movieInList.setDragEnabled(true);
            cwlMoviesPanel.add(movieInList);
            // moviesPanel.add(movie);
            movie.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
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
        //set layout here
        JLabel wdpCurrentName = new JLabel("Current Watchlist:");
        wdpCurrentWatchlist.add(wdpCurrentName);
        wdpCurrentWatchlist.add(currentWatchlistName);

        GridLayout watchlistGridLayout = new GridLayout(0, 1);
        cwlMoviesPanel.setLayout(watchlistGridLayout);
        //TO DO: add movies in the currently selected watchlist
        //grab movies that are in the watchlist, for each movie add to cwlMoviesPanel




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
        //add a test button
        /*JButton testButton = new JButton("Test Button");
        watchlistPanel.add(testButton);*/

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
        this.setVisible(false);
        this.add(outer);
    }

    public void eraseWatchlistName() {
        watchlistNameField.setText("");
    }

    public void setCurrentWatchlistName(String currentName) {
        currentWatchlistName.setText(currentName);
    }

    /* Get the watchlist name that is entered in the box */
    public String getWatchlistName() {
        return watchlistNameField.getText();
    }

    /* alert controller that this button is pressed */
    void addWatchlistListener(ActionListener listenerForAddWatchlist) {
        addWatchlistButton.addActionListener(listenerForAddWatchlist);
    }

    /* alert controller that this button is pressed */
    void deleteWatchlistListener(ActionListener listenerForDeleteWatchlist) {
        deleteWatchlistButton.addActionListener(listenerForDeleteWatchlist);
    }

    public void updateWatchlists(ArrayList<Watchlist_Model> watchlists, ArrayList<Movie_Model> Movies){
        watchlistPanel.removeAll();
        for(Watchlist_Model wl:watchlists){
            //do some stuff
            String Name = wl.getName();

            watchlistButton = new JButton(Name);
            //add button to watchlistPanel
            watchlistPanel.add(watchlistButton);
            watchlistButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            watchlistButton.addActionListener(event ->
            {
                currentWatchlistName.setText(Name);
                populateLeftMoviePanel(Movies);
                // TO DO: Populate currentWatchlistPanel with buttons of movies in the watchlist 'wl'
                // and left panel of movies

            });
        }
    }

    public void populateLeftMoviePanel(ArrayList<Movie_Model> movies){
        //moviesPanel.removeAll();
        for(Movie_Model theMovie:movies) {
            String Title = theMovie.getTitle();
            String Year = theMovie.getYear();
            movie = new JTextField(Title+","+Year);
            movie.setDragEnabled(true);
            moviesPanel.add(movie);
            movie.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }

    public JLabel getCurrentWatchlistName(){
        return currentWatchlistName;
    }

    /* display success */
    void displaySuccess(String success) {
        JOptionPane.showMessageDialog(this, success);
    }
    /* display error */
    void displayError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
}
