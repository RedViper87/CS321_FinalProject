import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Watchlist_View extends JPanel {

    JLabel watchlistTitleLabel = new JLabel("Watchlist View");
    private JLabel watchlistNameLabel = new JLabel("Watchlist Name to Add or Delete:");
    private JTextField watchlistNameField = new JTextField(10);
    private JButton addWatchlistButton = new JButton("Add Watchlist");
    private JButton deleteWatchlistButton = new JButton("Delete Watchlist");
    private JLabel currentWatchlistName = new JLabel("Current Watchlist Name");

    Watchlist_View() {
        Border blackline1 = BorderFactory.createLineBorder(Color.black, 1);
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);

        /* Title panel goes at the top of outer panel */
        JPanel titlePanel = new JPanel();
        watchlistTitleLabel.setFont(new Font(null, Font.BOLD, 20));
        titlePanel.add(watchlistTitleLabel);

        /* This panel is on the left side of the main panel */
        JPanel moviesPanel = new JPanel();  //holds all the movies as buttons
        //set size of panel
        //moviesPanel.setPreferredSize(new Dimension(100, 200));
        //Create a border to go around movies panel
        moviesPanel.setBorder(blackline1);
        //Set movies panel layout
        BoxLayout moviesPanelLayout = new BoxLayout(moviesPanel, BoxLayout.Y_AXIS);
        moviesPanel.setLayout(moviesPanelLayout);
        //Here we will put a function that creates a button for every movie and links it with its movie model
        //but just using example buttons for now
        JButton button1 = new JButton("Movie 1");
        moviesPanel.add(button1);
        JButton button2 = new JButton("Movie 2");
        moviesPanel.add(button2);
        JButton button3 = new JButton("Movie 3");
        moviesPanel.add(button3);
        JButton button4 = new JButton("Movie 4");
        moviesPanel.add(button4);
        JButton button5 = new JButton("Movie 5");
        moviesPanel.add(button5);
        JButton button6 = new JButton("Movie 6");
        moviesPanel.add(button6);
        JButton button7 = new JButton("Movie 7");
        moviesPanel.add(button7);
        JButton button8 = new JButton("Movie 8");
        moviesPanel.add(button8);
        //add scroll bar
        JScrollPane movieScroller = new JScrollPane(moviesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        movieScroller.setPreferredSize(new Dimension(100, 150));

        /* This panel is on the right side of the main panel */
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
        wdpCurrentWatchlist.setBorder(blackline1);
        JLabel wdpCurrentName = new JLabel("Current Watchlist:");
        wdpCurrentWatchlist.add(wdpCurrentName);
        wdpCurrentWatchlist.add(currentWatchlistName);
        //--add a grid layout panel for watchlist movies
        //Add separate panels to Main
        watchlistDisplayPanel.setBorder(blackline1);    //Create a border to go around panel
        watchlistDisplayPanel.add(wdpName);
        watchlistDisplayPanel.add(wdpAddDeleteButtons);
        watchlistDisplayPanel.add(wdpCurrentWatchlist);

        /* This main panel holds the movies panel(attached to movieScroller) and watchlist panel */
        JPanel mainPanel = new JPanel();
        BoxLayout mainPanelLayout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.add(movieScroller);
        mainPanel.add(watchlistDisplayPanel);

        /* Deal with outer panel */
        JPanel outer = new JPanel();
        outer.setPreferredSize(new Dimension(450, 300));
        //BoxLayout outerLayout = new BoxLayout(outer, BoxLayout.Y_AXIS);
        //outer.setLayout(outerLayout);
        //Create a border to go around outer panel
        outer.setBorder(blackline2);

        outer.add(titlePanel);
        outer.add(mainPanel);

        this.add(outer);
        this.setVisible(true);
    }

    public void eraseWatchlistName() {
        watchlistNameField.setText("");
    }

    public void setCurrentWatchlistName(String currentName) {
        currentWatchlistName.setText(currentName);
    }

}
