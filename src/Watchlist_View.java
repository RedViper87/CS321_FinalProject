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

public class Watchlist_View extends JPanel{

    JLabel watchlistTitleLabel = new JLabel("Watchlist View");
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
        this.setVisible(false);
        this.add(outer);
    }

    public void eraseWatchlistName() {
        watchlistNameField.setText("");
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
    /* alert controller that this button is pressed */
    void saveWatchlistListener(ActionListener listenerForSaveWatchlist) {
        saveWatchlistButton.addActionListener(listenerForSaveWatchlist);
    }

    public String getWatchlistMovies(){
        return movieInList.getText();
    }

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

    public String getCurrentWatchlistName(){
        return currentWatchlistName.getText();
    }

    public void setCurrentWatchlistName(){
        currentWatchlistName.setText("");
    }
    public void setMiddlePanel(){
        movieInList.setText("");
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
