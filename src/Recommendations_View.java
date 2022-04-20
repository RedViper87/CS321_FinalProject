import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A class that creates a JPanel view of Recommendations
 */
public class Recommendations_View extends JPanel{

    JLabel recViewLabel = new JLabel("Get recommendations based on the movies you review!");
    JPanel resultsPanel = new JPanel();
    JButton movieButton;
    JButton refresh = new JButton("Refresh");
    JLabel title = new JLabel("Title:");
    JLabel year = new JLabel("Year:");
    JLabel rated = new JLabel("Rated:");
    JLabel released = new JLabel("Released:");
    JLabel runtime = new JLabel("Runtime:");
    JLabel genre = new JLabel("Genre:");
    JLabel director = new JLabel("Director:");
    JLabel writer = new JLabel("Writer:");
    JLabel actors = new JLabel("Actors:");
    JLabel plot = new JLabel("Plot:");
    JLabel language = new JLabel("Language:");
    JLabel country = new JLabel("Country:");
    JLabel awards = new JLabel("Awards:");
    JLabel ratings = new JLabel("Ratings:");
    JLabel metascore = new JLabel("Metascore:");
    JLabel imdbrating = new JLabel("imdbRating:");
    JLabel imdbvotes = new JLabel("imdbVotes:");
    JLabel imdbid = new JLabel("imbdID:");
    JLabel type = new JLabel("Type:");
    JLabel dvd = new JLabel("DVD:");
    JLabel boxoffice = new JLabel("BoxOffice:");
    JLabel production = new JLabel("Production:");
    JLabel website = new JLabel("Website:");
    JLabel response = new JLabel("Response:");

    /**
     * Constructs a Recommendation object that is the View for recommended movies
     */
    Recommendations_View() {
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);

        JPanel titlePanel = new JPanel();
        recViewLabel.setFont(new Font(null, Font.BOLD, 20));
        titlePanel.add(recViewLabel);

        BoxLayout moviesPanelLayout = new BoxLayout(resultsPanel, BoxLayout.X_AXIS);
        resultsPanel.setLayout(moviesPanelLayout);
        JScrollPane scroll = new JScrollPane(resultsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(210,400));
        scroll.getHorizontalScrollBar().setUnitIncrement(32);
        scroll.setBorder(blackline2);

        JPanel detailsPanel = new JPanel();
        JLabel movieDetailsLabel = new JLabel("Movie Details: ");
        BoxLayout detailsLayout = new BoxLayout(detailsPanel, BoxLayout.Y_AXIS);
        detailsPanel.setLayout(detailsLayout);
        BoxLayout attributes = new BoxLayout(detailsPanel, BoxLayout.Y_AXIS);
        detailsPanel.setLayout(attributes);
        detailsPanel.setBorder(blackline2);//Create a border to go around panel
        detailsPanel.add(movieDetailsLabel);
        detailsPanel.add(title);
        detailsPanel.add(year);
        detailsPanel.add(rated);
        detailsPanel.add(released);
        detailsPanel.add(runtime);
        detailsPanel.add(genre);
        detailsPanel.add(director);
        detailsPanel.add(writer);
        detailsPanel.add(actors);
        detailsPanel.add(plot);
        detailsPanel.add(language);
        detailsPanel.add(country);
        detailsPanel.add(awards);
        detailsPanel.add(ratings);
        detailsPanel.add(imdbrating);
        detailsPanel.add(imdbvotes);
        detailsPanel.add(imdbid);
        detailsPanel.add(type);
        detailsPanel.add(dvd);
        detailsPanel.add(boxoffice);
        detailsPanel.add(production);
        detailsPanel.add(website);
        detailsPanel.add(response);

        JScrollPane scroll2 = new JScrollPane(detailsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll2.setPreferredSize(new Dimension(1250,400));
        scroll2.getVerticalScrollBar().setUnitIncrement(32);
        /* Main Outer Panel */
        JPanel outer = new JPanel();
        BoxLayout outerLayout = new BoxLayout(outer, BoxLayout.Y_AXIS);
        outer.setLayout(outerLayout);
        outer.setPreferredSize(new Dimension(1250, 600));
        outer.setBorder(blackline2);
        //add stuff to outer panel
        outer.add(titlePanel);
        outer.add(refresh);
        outer.add(scroll);
        outer.add(scroll2);
        this.setBackground(Color.darkGray);
        this.setVisible(false);
        this.add(outer);
    }

    /**
     * Adds an actionlistener to the refresh button to be used in the controller
     * @param listenerForRefresh the actionlistener for the refresh button
     */
    void refreshListener(ActionListener listenerForRefresh){
        refresh.addActionListener(listenerForRefresh);
    }

    /**
     * Displays the movies that are recommended to a user
     * @param movies the arraylist of movies that are recommended to a user
     * @throws IOException
     */
    public void displayRecommendedMovies(ArrayList<Movie_Model> movies) throws IOException {
        resultsPanel.removeAll();
        for(Movie_Model movie: movies){
            String Icon = movie.getPoster();
            String Title = movie.getTitle();
            String Year = movie.getYear();
            String Rated = movie.getRated();
            String Released = movie.getReleased();
            String Runtime = movie.getRuntime();
            String Genre = movie.getGenre();
            String Director = movie.getDirector();
            String Writer = movie.getWriter();
            String Actors = movie.getActors();
            String Plot = movie.getPlot();
            String Language = movie.getLanguage();
            String Country = movie.getCountry();
            String Awards = movie.getAwards();
            String Metascore = movie.getMetascore();
            String Imdbrating = movie.getImdbRating();
            String Imdbvotes = movie.getImdbVotes();
            String Imdbid = movie.getImdbID();
            String Type = movie.getType();
            String Dvd = movie.getDVD();
            String Boxoffice = movie.getBoxOffice();
            String Production = movie.getProduction();
            String Website = movie.getWebsite();
            String Response = movie.getResponse();
            String s;
            String v;
            String Ratings;
            ArrayList <String> rate = new ArrayList<>();
            for(Rating r: movie.getRatings()){
                s = r.getSource();
                v = r.getValue();
                rate.add("{Source: "+s+", Value: "+v+"} ");
            }
            StringBuilder sb = new StringBuilder();
            for(String string: rate){
                sb.append(string);
            }
            Ratings = sb.toString();

            if(Icon.equals("N/A")){
                movieButton = new JButton(Title);
            }else{
                ImageIcon imageIcon = new ImageIcon(new URL(Icon));
                Image image = imageIcon.getImage();
                Image newImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImage);
                movieButton = new JButton(imageIcon);
            }
            resultsPanel.add(movieButton);
            movieButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            movieButton.addActionListener(event ->
            {
                title.setText("Title: "+ Title);
                year.setText("Year: "+ Year);
                rated.setText("Rated: " + Rated);
                released.setText("Released: " + Released);
                runtime.setText("Runtime: " + Runtime);
                genre.setText("Genre: " + Genre);
                director.setText("Director: " + Director);
                writer.setText("Writer: " + Writer);
                actors.setText("Actors: " + Actors);
                plot.setText("Plot: " + Plot);
                language.setText("Language: " + Language);
                country.setText("Country: " + Country);
                awards.setText("Awards: " + Awards);
                ratings.setText("Ratings: " + Ratings);
                metascore.setText("Metascore: " + Metascore);
                imdbrating.setText("imdbRating: " + Imdbrating);
                imdbvotes.setText("imbdVotes: " + Imdbvotes);
                imdbid.setText("imdbID: " + Imdbid);
                type.setText("Type: " + Type);
                dvd.setText("DVD: " + Dvd);
                boxoffice.setText("BoxOffice: " + Boxoffice);
                production.setText("Production: " + Production);
                website.setText("Website: " + Website);
                response.setText("Response: " + Response);
            });
        }

    }

    /**
     * Displays an error if the user hasn't made any reviews yet
     */
    void displayError(){
        JOptionPane.showMessageDialog(this, "Please enter in a review to get recommended movies.");
    }
}

