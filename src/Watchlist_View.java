import javax.swing.border.Border;
import java.awt.*;
import javax.swing.*;

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
        // add buttons
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
        JButton button9 = new JButton("Movie 9");
        moviesPanel.add(button9);
        JButton button10 = new JButton("Movie 10");
        moviesPanel.add(button10);
        JButton button11 = new JButton("Movie 11");
        moviesPanel.add(button11);
        JButton button12 = new JButton("Movie 12");
        moviesPanel.add(button12);
        JButton button13 = new JButton("Movie 13");
        moviesPanel.add(button13);
        JButton button14 = new JButton("Movie 14");
        moviesPanel.add(button14);
        JButton button15 = new JButton("Movie 15");
        moviesPanel.add(button15);
        JButton button16 = new JButton("Movie 16");
        moviesPanel.add(button16);
        //add scroll bar
        JScrollPane movieScroller = new JScrollPane(moviesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        movieScroller.setPreferredSize(new Dimension(100, 150));
        movieScroller.getVerticalScrollBar().setUnitIncrement(16);

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
        //wdpCurrentWatchlist.setBorder(blackline1);
        //set layout here
        JLabel wdpCurrentName = new JLabel("Current Watchlist:");
        wdpCurrentWatchlist.add(wdpCurrentName);
        wdpCurrentWatchlist.add(currentWatchlistName);
        JPanel cwlMoviesPanel = new JPanel();
        GridLayout watchlistGridLayout = new GridLayout(0, 2);
        cwlMoviesPanel.setLayout(watchlistGridLayout);
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

        /* This main panel holds the movies panel(attached to movieScroller) and watchlist panel */
        JPanel mainPanel = new JPanel();
        BoxLayout mainPanelLayout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.add(movieScroller);
        mainPanel.add(watchlistDisplayPanel);

        /* Deal with outer panel */
        JPanel outer = new JPanel();
        outer.setPreferredSize(new Dimension(500, 450));
        BoxLayout outerLayout = new BoxLayout(outer, BoxLayout.Y_AXIS);
        outer.setLayout(outerLayout);
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
