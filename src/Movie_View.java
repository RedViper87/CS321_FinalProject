import com.google.gson.Gson;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Movie_View extends JPanel {

    JLabel movieLibraryLabel = new JLabel("Browse Movies!");
    JLabel movieDetailsLabel = new JLabel("Movie Details:");
    JButton movie;
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

    Movie_View() throws IOException {

        /* Get movie data from json */
        Path path = Paths.get("SampleMovieFile.json");
        Charset charset = StandardCharsets.UTF_8;
        String content = Files.readString(path, charset);
        Gson gson = new Gson();
        Movie_Model[] list;
        list = gson.fromJson(content,Movie_Model[].class);
        ArrayList<Movie_Model> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,list);

        /* start of panel */
        Border blackline1 = BorderFactory.createLineBorder(Color.black, 1);
        Border blackline2 = BorderFactory.createLineBorder(Color.black, 2);

        /* Title panel goes at the top of outer panel */
        JPanel titlePanel = new JPanel();
        movieLibraryLabel.setFont(new Font(null, Font.BOLD, 20));
        titlePanel.add(movieLibraryLabel);

        /* This panel is on the left side of the main panel */
        JPanel moviesPanel = new JPanel();

        //Create a border to go around movies panel
        moviesPanel.setBorder(blackline1);

        //Set movies panel layout
        BoxLayout moviesPanelLayout = new BoxLayout(moviesPanel, BoxLayout.Y_AXIS);
        moviesPanel.setLayout(moviesPanelLayout);

        // add buttons
        for(Movie_Model movies:arrayList){
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
            String s;
            String v;
            String Ratings;
            ArrayList <String> rate = new ArrayList<>();
            for(Rating r: movies.getRatings()){
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
                movie = new JButton(Title);
            }else{
                ImageIcon imageIcon = new ImageIcon(new URL(Icon));
                Image image = imageIcon.getImage();
                Image newImage = image.getScaledInstance(150, 225, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImage);
                movie = new JButton(imageIcon);
            }
            moviesPanel.add(movie);
            movie.setAlignmentX(Component.CENTER_ALIGNMENT);

            movie.addActionListener(event ->
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

        /* Add scroll bar */
        JScrollPane scroll = new JScrollPane(moviesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(210,400));
        scroll.getVerticalScrollBar().setUnitIncrement(32);

        /* This panel is on the right side of the main panel */
        JPanel displayMovie = new JPanel();
        BoxLayout detailsLayout = new BoxLayout(displayMovie, BoxLayout.Y_AXIS);
        displayMovie.setLayout(detailsLayout);
        JPanel detailsTitle = new JPanel();
        JPanel details = new JPanel();
        BoxLayout attributes = new BoxLayout(details, BoxLayout.Y_AXIS);
        details.setLayout(attributes);
        displayMovie.setBorder(blackline1);    //Create a border to go around panel
        detailsTitle.add(movieDetailsLabel);
        details.add(title);
        details.add(year);
        details.add(rated);
        details.add(released);
        details.add(runtime);
        details.add(genre);
        details.add(director);
        details.add(writer);
        details.add(actors);
        details.add(plot);
        details.add(language);
        details.add(country);
        details.add(awards);
        details.add(ratings);
        details.add(imdbrating);
        details.add(imdbvotes);
        details.add(imdbid);
        details.add(type);
        details.add(dvd);
        details.add(boxoffice);
        details.add(production);
        details.add(website);
        details.add(response);

        displayMovie.add(detailsTitle);
        displayMovie.add(details);

        JScrollPane scroll2 = new JScrollPane(displayMovie, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll2.setPreferredSize(new Dimension(1000,400));
        scroll2.getHorizontalScrollBar().setUnitIncrement(32);

        /* This main panel holds the movies panel and view panel */
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1250, 410));
        mainPanel.add(scroll);
        mainPanel.add(scroll2);

        /* Deal with outer panel */
        JPanel outer = new JPanel();
        //outer.setPreferredSize(new Dimension(200, 200));
        BoxLayout outerLayout = new BoxLayout(outer, BoxLayout.Y_AXIS);
        outer.setLayout(outerLayout);
        //Create a border to go around outer panel
        outer.setBorder(blackline2);

        outer.add(titlePanel);
        outer.add(mainPanel);

        this.setVisible(false);
        this.add(outer);
    }
}