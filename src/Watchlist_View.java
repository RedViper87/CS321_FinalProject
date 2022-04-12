import com.google.gson.Gson;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
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
    private JLabel currentWatchlistName = new JLabel("Current Watchlist Name");
    private JButton movie = new JButton();
    private JButton watchlistButton;
    JPanel watchlistPanel = new JPanel();

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
        JPanel moviesPanel = new JPanel();  //holds all the movies as buttons
        //Create a border to go around movies panel
        moviesPanel.setBorder(blackline1);
        //Set movies panel layout
        BoxLayout moviesPanelLayout = new BoxLayout(moviesPanel, BoxLayout.Y_AXIS);
        moviesPanel.setLayout(moviesPanelLayout);
        // add buttons
        for(Movie_Model movies:arrayList) {
            String Icon = movies.getPoster();
            String Title = movies.getTitle();
            String Year = movies.getYear();
            String Rated = movies.getRated();
            String Released = movies.getReleased();
            String Runtime = movies.getRuntime();
            String Genre = movies.getGenre();
            String Director = movies.getDirector();
            String Writer = movies.getWriter();
            String Actors = movies.getActors();
            String Plot = movies.getPlot();
            String Language = movies.getLanguage();
            String Country = movies.getCountry();
            String Awards = movies.getAwards();
            String Metascore = movies.getMetascore();
            String Imdbrating = movies.getImdbRating();
            String Imdbvotes = movies.getImdbVotes();
            String Imdbid = movies.getImdbID();
            String Type = movies.getType();
            String Dvd = movies.getDVD();
            String Boxoffice = movies.getBoxOffice();
            String Production = movies.getProduction();
            String Website = movies.getWebsite();
            String Response = movies.getResponse();
            String s = null;
            String v = null;
            String Ratings;
            ArrayList<String> rate = new ArrayList<>();
            for (Rating r : movies.getRatings()) {
                s = r.getSource();
                v = r.getValue();
                rate.add("{Source: " + s + ", Value: " + v + "} ");
            }
            StringBuffer sb = new StringBuffer();
            for (String string : rate) {
                sb.append(string);
            }
            Ratings = sb.toString();

            if (Icon.equals("N/A")) {
                movie = new JButton(Title);
            } else {
                ImageIcon imageIcon = new ImageIcon(new URL(Icon));
                Image image = imageIcon.getImage();
                Image newImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImage);
                movie = new JButton(imageIcon);
            }
            moviesPanel.add(movie);
            movie.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        //add scroll bar
        JScrollPane movieScroller = new JScrollPane(moviesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        movieScroller.setPreferredSize(new Dimension(210, 400));
        movieScroller.getVerticalScrollBar().setUnitIncrement(16);

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
        JPanel cwlMoviesPanel = new JPanel();
        GridLayout watchlistGridLayout = new GridLayout(0, 2);
        cwlMoviesPanel.setLayout(watchlistGridLayout);
        //add buttons of the movies in the currently selected watchlist
        JButton currentButton1 = new JButton("Movie 1");
        cwlMoviesPanel.add(currentButton1);
        JButton currentButton2 = new JButton("Movie 2");
        cwlMoviesPanel.add(currentButton2);
        JButton currentButton3 = new JButton("Movie 3");
        cwlMoviesPanel.add(currentButton3);
        JButton currentButton4 = new JButton("Movie 4");
        cwlMoviesPanel.add(currentButton4);
        JButton currentButton5 = new JButton("Movie 5");
        cwlMoviesPanel.add(currentButton5);
        JButton currentButton6 = new JButton("Movie 6");
        cwlMoviesPanel.add(currentButton6);
        //add scroll bar to cwlMoviesPanel
        JScrollPane cwlMovieScroller = new JScrollPane(cwlMoviesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cwlMovieScroller.setPreferredSize(new Dimension(210, 210));
        cwlMovieScroller.getVerticalScrollBar().setUnitIncrement(16);
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
        watchlistScroller.setPreferredSize(new Dimension(210, 400));
        watchlistScroller.getVerticalScrollBar().setUnitIncrement(16);
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
        outer.setPreferredSize(new Dimension(700, 450));
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

    public void updateWatchlists(ArrayList<Watchlist_Model> watchlists){
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
                // TO DO: Populate currentWatchlistPanel with buttons of movies in the watchlist 'wl'
            });
        }
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
